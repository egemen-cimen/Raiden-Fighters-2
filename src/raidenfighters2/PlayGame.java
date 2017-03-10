package raidenfighters2;

import java.awt.Color;
import java.awt.Font;
//import java.awt.Color;
//import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PlayGame extends JFrame implements KeyListener, Runnable
{
	/**
	 * 	Game windows for the game
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private Icon actor, bullet, enemyPlane, life, background, tank, lazer/*, enemyBullet*/;
	private JLabel heroLabel, lifeBar1,lifeBar2,lifeBar3, backgroundLabel, scoreLabel, lazerIndicator; 
	private int xActor = (580-68)/2;
	private int yActor = 540;
	private int backgroundY = -6400;
	private boolean running = true; 
	private final int enemyCount;
	private final EnemyLabel enemy[];
	private final static Random ranGen = new Random();
	private int score, highScore;
	private int lives;
	private User currentUser;
	BulletLabel bulletShot;
	LazerLabel lazerShot;
	private final int gameMode;
	private int lazerTime;
	private int lazerCooldown;
	private boolean lazerActive;
	private boolean lazerCanActivate;
	
	
	
	public PlayGame ( int mode, User userFromWelcomeScreen )
	{
		setResizable(false);
		currentUser = userFromWelcomeScreen;
		gameMode = mode;
		lazerCanActivate = true;
		lazerActive = false;
		lazerTime = 0;
		lazerCooldown = 0;
		if ( mode == 0 )	// easy mode
			enemyCount = 3;
		else if ( mode == 1 )	// medium mode
			enemyCount = 5;
		else enemyCount = 4;	// unknown mode
		
		enemy = new EnemyLabel[enemyCount];
		try
		{
			createGUI();

		}
		catch ( IOException io )
		{
			System.err.println("Missing files");
			System.exit(0);
		}
	}
	
	public void createGUI( ) throws IOException
	{

		setTitle("Raiden Fighters");
		score = 0;
		lives = 3;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(null);
		actor = new ImageIcon(getClass().getResource("images\\Raiden_MK-II.gif"));
		bullet = new ImageIcon(getClass().getResource("images\\Bullet.png"));
		enemyPlane = new ImageIcon(getClass().getResource("images\\enemy.gif"));
		life = new ImageIcon(getClass().getResource("images\\life.jpg"));
		background = new ImageIcon(getClass().getResource("images\\map.png"));/*life.jpg*/
		tank = new ImageIcon(getClass().getResource("images\\tank.png"));
		lazer = new ImageIcon(getClass().getResource("images\\longLazer.png"));
		//enemyBullet = new ImageIcon(getClass().getResource("images\\enemyBullet.png"));
		scoreLabel = new JLabel("Score: " + score);
		scoreLabel.setBounds(450,0,100,100);
		scoreLabel.setFont( new Font("Serif", Font.BOLD, 20) );
		scoreLabel.setForeground(Color.black);
		panel.add(scoreLabel);
		heroLabel = new JLabel(actor);
		heroLabel.setBounds(xActor, yActor, 68, 92);
		lifeBar1 = new JLabel(life);
		lifeBar1.setBounds(10, 10, 51, 51);
		panel.add(lifeBar1);
		lifeBar2 = new JLabel(life);
		lifeBar2.setBounds(10, 70, 51, 51);
		panel.add(lifeBar2);
		lifeBar3 = new JLabel(life);
		lifeBar3.setBounds(10, 130, 51, 51);
		panel.add(lifeBar3);
		panel.add(heroLabel);
		lazerIndicator = new JLabel("Lazer Ready");
		lazerIndicator.setBounds(450,600,200,100);
		lazerIndicator.setFont( new Font("Serif", Font.BOLD, 14) );
		lazerIndicator.setForeground(Color.black);
		panel.add(lazerIndicator);
		panel.setBackground(Color.black);	// black panel to fill short background
		panel.setOpaque(true);
		this.add(panel);
		this.setSize(580, 730);
		
		
		for ( int i = 0; i < enemyCount-gameMode; i++ )
		{
			enemy[i] = new EnemyLabel(enemyPlane, 540/(i+1));
			panel.add(enemy[i]);
		}
		
		if ( gameMode == 1 && enemyCount >= 5 )
		{
			enemy[enemyCount-gameMode] = new EnemyLabel(tank, 540/(enemyCount-gameMode+1));
			panel.add(enemy[enemyCount-gameMode]);
		}

		backgroundLabel = new JLabel(background);
		//backgroundLabel.setBounds(0, backgroundY, 564, 7100);
		if (backgroundLabel.getParent() != panel)
			panel.add(backgroundLabel);
		
		addKeyListener(this);
		setVisible(true);
		
	}
		
	public void moveShip (int newX, int newY)
	{
		heroLabel.setBounds(newX, newY, 68, 92);
		backgroundLabel.setBounds(0, backgroundY, 564, 7100);
		if ( lazerShot != null && lazerActive )
		{
			lazerShot.shootLazer(xActor, yActor);
			
		}
			
	}
	
	public void makeBullet ( int newX, int newY )
	{
		//System.out.println("x: " + newX + "\ny: " + newY);
		if ( bulletShot == null )
			bulletShot = new BulletLabel(newX, newY, bullet);

		draw();

		
	}
	public void makeLazer( int newX, int newY )
	{
		//System.out.println("x: " + newX + "\ny: " + newY);
		if ( lazerShot == null && lazerCanActivate )
		{
			lazerShot = new LazerLabel(newX, newY, lazer);
			lazerTime = 0;
			lazerShot.shootLazer( xActor, yActor );
			lazerActive = true;
		}

		draw();

		
	}
				
	
	
	public void draw()
	{
		heroLabel.setBounds(xActor, yActor, 68, 92);
		if ( bulletShot != null )
		{
			if(bulletShot.getParent() != panel)
				panel.add(bulletShot);
		}
		if ( lazerShot != null )
		{
			if(lazerShot.getParent() != panel)
			{
				lazerShot.shootLazer( xActor, yActor );
				panel.add(lazerShot);
				
				panel.remove(backgroundLabel);	// refresh background
				if (backgroundLabel.getParent() != panel)
					panel.add(backgroundLabel);
				
			}
				
			
		}

	}

	public void makeBulletGo ( )
	{
		if ( bulletShot != null )
		{
			bulletShot.goBullet();
			if ( bulletShot.getyCoordinate() < -55 )	// bullet is outside
				bulletShot = null;
				
			backgroundLabel.setBounds(0, backgroundY, 564, 7100);

			panel.remove(backgroundLabel);	// refresh background
			if (backgroundLabel.getParent() != panel)
				panel.add(backgroundLabel);


		}
		
	}
	
	public void makeEnemyGo ( EnemyLabel enemy )
	{
		if ( enemy != null )
		{
			if ( enemy.getxCoordinate() - xActor < 20 && enemy.getxCoordinate() - xActor > -20 && enemy.getxCoordinate() > 0 && enemy.getxCoordinate() < 500  )	// intelligence
			{
				enemy.setxCoordinate(enemy.getxCoordinate()+20-ranGen.nextInt(100)+ranGen.nextInt(100));
				backgroundLabel.setBounds(0, backgroundY, 564, 7100);
			}

			
			enemy.goEnemy();
			if ( enemy.getyCoordinate() > 830 )	// respawn enemy
			{
				enemy.setyCoordinate(-100);
				enemy.setxCoordinate(ranGen.nextInt(500));
				
			}
			
			if  ( bulletShot != null && 
					enemy.getxCoordinate()+enemy.getEnemyWidth() > bulletShot.getxCoordinate() && enemy.getxCoordinate() < bulletShot.getBulletWidth() + bulletShot.getxCoordinate() &&
					enemy.getyCoordinate()+enemy.getEnemyHeight() > bulletShot.getyCoordinate() && enemy.getyCoordinate() < bulletShot.getBulletHeight() + bulletShot.getyCoordinate()
				)	// check bullet hitting enemy
			{
				enemy.setyCoordinate(-100);
				enemy.setxCoordinate(ranGen.nextInt(500));
				bulletShot.setBounds(-100,-100);	// move bullet off screen
				bulletShot = null;
				score += 10 + gameMode;
				scoreLabel.setText("Score: " + score);


			}
			
			if  (  lazerShot != null && enemy.getxCoordinate()+enemy.getEnemyWidth() > lazerShot.getxCoordinate() && enemy.getxCoordinate() < lazerShot.getBulletWidth() + lazerShot.getxCoordinate() )	// check lazer
			{
				enemy.setyCoordinate(-100);
				enemy.setxCoordinate(ranGen.nextInt(500));
				score += 10 + gameMode;
				scoreLabel.setText("Score: " + score);
				
			}
				
			if  (	enemy.getxCoordinate()+enemy.getEnemyWidth() > xActor && enemy.getxCoordinate() < 68 + xActor &&
					enemy.getyCoordinate()+enemy.getEnemyHeight() > yActor && enemy.getyCoordinate() < 92 + yActor
				)	// check hero hit
			{
				enemy.setyCoordinate(-100);
				enemy.setxCoordinate(ranGen.nextInt(500));
				score -= 10;
				scoreLabel.setText("Score: " + score);
				lives -= 1;
				
				if ( lives == 1 )
				{
					lifeBar2.setBounds(-100, -100, 51, 51);
				}
				if ( lives == 2 )
				{
					lifeBar3.setBounds(-100, -100, 51, 51);
				}
				if ( lives == 0 )
				{
					stopRunning();
					highScore = currentUser.getHighscore();
					if ( highScore < score )
					{
						JOptionPane.showMessageDialog(null, "Your New High Score: " + score, "Your New High Score", JOptionPane.INFORMATION_MESSAGE );
						highScore = score;
						currentUser.setHighscore(highScore);
						DataBase.saveToFile( currentUser ); // save current user to hash map
					}
					else JOptionPane.showMessageDialog(null, "Score: " + score, "Score", JOptionPane.INFORMATION_MESSAGE );
					this.setVisible(false);
					
				}
				
				//System.out.println("Lives: " + lives);
				//System.out.println("Score: " + score);
				draw();

			}
			
		}
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent event)
	{
		String whichKey = KeyEvent.getKeyText(event.getKeyCode());
		
		if (whichKey.compareTo("Left")==0)
		{
			changeLayoutLeft();
			
		}
		else if (whichKey.compareTo("Right")==0)
		{
			changeLayoutRight();
			
		}
		else if (whichKey.compareTo("Up")==0)
		{
			changeLayoutUp();
			
		}
		else if (whichKey.compareTo("Down")==0)
		{
			changeLayoutDown();
			
		}
		else if (whichKey.compareTo("Ctrl")==0 || whichKey.compareTo("Z")==0 || whichKey.compareTo("Space")==0 )
		{
			makeBullet(xActor, yActor);
			
		}		
		else if (whichKey.compareTo("Shift")==0 || whichKey.compareTo("X")==0)
		{
			makeLazer(xActor, yActor);
			
		}
		else{}
	}

	public void changeLayoutLeft()
	{
		if ( xActor > 0 )
			xActor-=10;
		moveShip(xActor,yActor);
	}
		
	public void changeLayoutRight()
	{
		if ( xActor < 500 )
			xActor+=10;
		moveShip(xActor,yActor);
	}

	public void changeLayoutUp()
	{
		if ( yActor > 0 )
			yActor-=10;
		moveShip(xActor,yActor);
	}

	public void changeLayoutDown()
	{
		if ( yActor < 600 )
			yActor+=10;
		moveShip(xActor,yActor);
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// No need?
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		//No need?
	}

	@Override
	public void run()
	{
		while (running)
		{
			if (bulletShot != null)
				makeBulletGo();
			
			
			
			for ( int i = 0; i < enemyCount; i++ )
			{
				makeEnemyGo ( enemy[i] );

			}
			backgroundY += 1;
			if ( backgroundY == 0 )
				backgroundY = -6400;
			backgroundLabel.setBounds(0, backgroundY, 564, 7100);

			if ( lazerActive == true && lazerShot != null )
			{
				lazerTime += 10;
				lazerCanActivate = false;
				lazerShot.shootLazer(xActor, yActor);
				//System.out.println("lazerTime: "+lazerTime);
			}
			if ( lazerTime == 5000 )
			{
				if(lazerShot != null)
				{
					lazerShot.shootLazer(-100, -100);
					lazerShot = null;
				}
				lazerActive = false;
			}
 
			
			if ( lazerCanActivate == false )
				lazerCooldown += 10;
			if ( lazerCooldown == 20000 )
			{
				lazerCanActivate = true;
				lazerCooldown = 0;
			}
			
			if ( lazerCanActivate )
				lazerIndicator.setText("Lazer Ready");
			else if ( lazerActive ) 
				lazerIndicator.setText("Lazer In Use");
			else
				lazerIndicator.setText("Lazer NOT Ready");
			
			//System.out.println("LazerCooldown: " + lazerCooldown );
			//System.out.println("LazerCanActivate: " + lazerCanActivate );
			//System.out.println("LazerActive: " + lazerActive );
			
			
			sleep(10);
			draw();
		}
		
	}

	public void sleep(int ms)
	{
		try
		{
			Thread.sleep(ms);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public synchronized void startRunning()
	{
		running = true;
	}

	public synchronized void stopRunning()
	{
		running = false;
	}
	
}

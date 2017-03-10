package raidenfighters2;

//import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.InputMismatchException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;


public class WelcomeScreen extends JFrame implements ActionListener
{
	/**
	 * 	Welcome screen for the game. Contains a menu bar
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JMenuBar menuBar;
	private JMenu file, help;
	private JMenuItem fileRegister, filePlayGame, helpCredits, fileScoreBoard, fileQuit;
	private Icon background;
	private JLabel backLabel;
	
	public WelcomeScreen()
	{
		setResizable(false);
		setLayout(new FlowLayout());
		setSize(360, 480);
		setTitle("Raiden Fighters 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		try
		{
			background = new ImageIcon(getClass().getResource("images\\welcome.png"));
			backLabel = new JLabel(background);
			add(backLabel);
		
		}
		catch ( Exception e )
		{
			System.err.println("Image problem");
			
		}
		
		
		menuBar = new JMenuBar();
		file = new JMenu("File");
		file.addActionListener(this);
		menuBar.add(file);
		
		help = new JMenu("Help");
		menuBar.add(help);
		
		fileRegister = new JMenuItem("Register");
		file.add(fileRegister);
		fileRegister.addActionListener(this);
		
		filePlayGame = new JMenuItem("Play Game");
		file.add(filePlayGame);
		filePlayGame.addActionListener(this);
		
		helpCredits = new JMenuItem("Credits");

		help.add(helpCredits);
		helpCredits.addActionListener(this);
	
		
		fileScoreBoard = new JMenuItem("Score Board");
		file.add(fileScoreBoard);
		fileScoreBoard.addActionListener(this);
		
		fileQuit = new JMenuItem("Quit");
		file.add(fileQuit);
		fileQuit.addActionListener(this);

		setJMenuBar(menuBar);
	}
	
	
	


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource().equals(helpCredits) )
		{
			new Credits();
			
		}
		else if ( e.getSource().equals(fileRegister) )
		{
			User newUser = CreateNewUser.register();
			if ( newUser != null )	// register that user if username is unique
				DataBase.database.put(newUser.getName(), newUser);
			
		}
		else if ( e.getSource().equals(filePlayGame) )
		{
			//String gameModeString = JOptionPane.showInputDialog(null, "Select Game Mode\n0.Easy Mode\n1.Medium Mode", "Game Mode", 
			//		JOptionPane.QUESTION_MESSAGE );
			int gameMode = 0;
			
			

			
			User tempUser = DataBase.login();
			if ( tempUser != null )
			while (true)
			{
				try
				{
					String gameModeString = JOptionPane.showInputDialog(null, "Select Game Mode\n0.Easy Mode\n1.Medium Mode", "Game Mode", 
							JOptionPane.QUESTION_MESSAGE );
					gameMode = Integer.parseInt(gameModeString);
					if ( !(gameMode == 0 || gameMode == 1) )
						throw new NumberFormatException();
					break;
				}
				catch ( NumberFormatException nfe )
				{
					JOptionPane.showMessageDialog(null, "Invalid Game Mode","Username Error", JOptionPane.ERROR_MESSAGE );
					//nfe.printStackTrace();
					
				}
			}
			
			
			if ( tempUser != null)
			{
				PlayGame game = new PlayGame(gameMode,tempUser);	//TODO get game mode
				ExecutorService executor = Executors.newCachedThreadPool();
				executor.execute(game);
			}
			

		}
		else if ( e.getSource().equals(fileScoreBoard) )
		{
			//System.out.println("fileScoreBoard");
			ScoreBoard.showScores(DataBase.database);
				
		}
		else if ( e.getSource().equals(fileQuit) )
		{
			DataBase.saveToFile();
			System.exit(0);
				
		}
		
		
		
	}






}

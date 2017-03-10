package raidenfighters2;

import java.util.Random;

import javax.swing.Icon;
import javax.swing.JLabel;

public class EnemyLabel extends JLabel
{
	/**
	 * Label for enemy
	 */
	private static final long serialVersionUID = 1L;
	private int xCoordinate;
	private int yCoordinate;
	private int enemyWidth = 68;
	private int enemyHeight = 92;
	private final static Random ranGen = new Random();
	
	
	public EnemyLabel ( Icon imageIcon, int spawnXcoordinate )
	{
		super(imageIcon);
		xCoordinate = spawnXcoordinate-80-ranGen.nextInt(50);
		yCoordinate = -spawnXcoordinate- ranGen.nextInt(20);
		this.setVisible(true);
		this.setOpaque(true);
	}
	
	public void setBounds( int newX, int newY )
	{
		super.setBounds(newX,newY,enemyWidth,enemyHeight);

	}
	
	public int getEnemyWidth() {
		return enemyWidth;
	}


	public int getEnemyHeight() {
		return enemyHeight;
	}

	public void goEnemy()
	{
		yCoordinate += 2;
		setBounds(xCoordinate, yCoordinate, enemyWidth, enemyHeight);
	}
	
	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
	
}

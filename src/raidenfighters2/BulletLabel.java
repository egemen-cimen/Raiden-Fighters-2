package raidenfighters2;

import javax.swing.Icon;
//import java.util.*;
//import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BulletLabel extends JLabel
{
	/**
	 * 	Label for bullet
	 */
	private static final long serialVersionUID = 1L;
	private int xCoordinate;
	private int yCoordinate;
	private int bulletWidth = 33;
	private int bulletHeight = 53;
	public BulletLabel ( int spawnXcoordinate, int spawnYcoordinate, Icon imageIcon )
	{
		super(imageIcon);
		xCoordinate = spawnXcoordinate+17;
		yCoordinate = spawnYcoordinate-10;
		setBounds(xCoordinate, yCoordinate, bulletWidth, bulletHeight);
		this.setVisible(true);
		this.setOpaque(true);

		
	}
	

	
	public int getBulletWidth() {
		return bulletWidth;
	}



	public int getBulletHeight() {
		return bulletHeight;
	}



	public void setBounds( int newX, int newY )
	{
		super.setBounds(newX,newY,bulletWidth,bulletHeight);

	}

	public void goBullet()
	{
		yCoordinate -= 10;
		setBounds(xCoordinate, yCoordinate, bulletWidth, bulletHeight);
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


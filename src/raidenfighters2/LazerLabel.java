package raidenfighters2;

import javax.swing.Icon;

public class LazerLabel extends BulletLabel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public LazerLabel(int spawnXcoordinate, int spawnYcoordinate, Icon imageIcon)
	{
		super(spawnXcoordinate, spawnYcoordinate, imageIcon);
		// TODO Auto-generated constructor stub
	}

	public void shootLazer(int heroX, int heroY )
	{
		super.setyCoordinate( heroY );
		super.setxCoordinate( heroX+18 );
		super.setBounds( super.getxCoordinate(), super.getyCoordinate()-800, super.getBulletWidth(), 800 );
	}
	
	
}

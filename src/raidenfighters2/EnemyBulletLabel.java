package raidenfighters2;

import javax.swing.Icon;

public class EnemyBulletLabel extends BulletLabel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public EnemyBulletLabel(int spawnXcoordinate, int spawnYcoordinate, Icon imageIcon)
	{
		super(spawnXcoordinate, spawnYcoordinate, imageIcon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void goBullet()
	{
		super.setyCoordinate( super.getyCoordinate() - 10 );
		super.setBounds( super.getxCoordinate(), super.getyCoordinate() );
	}
	
	
}

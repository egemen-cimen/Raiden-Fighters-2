package raidenfighters2;

public class User
{
	private String name;
	private String password;
	private int highscore;
	
	public User( String n, String p )
	{
		name = n;
		password = p;
		highscore = 0;
		
	}
	
	public User( String n, String p, int h )
	{
		name = n;
		password = p;
		highscore = h;
		
	}

	
	

	public String getName()
	{
		return name;
	}

	public String getPassword()
	{
		return password;
	}

	public int getHighscore()
	{
		return highscore;
	}

	public void setHighscore(int highscore)
	{
		this.highscore = highscore;
	}
	
	
	
}

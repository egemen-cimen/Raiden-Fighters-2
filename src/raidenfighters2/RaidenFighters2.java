package raidenfighters2;

import java.io.IOException;

public class RaidenFighters2
{
	public static void main(String[] args) throws IOException
	{
		@SuppressWarnings("unused")
		DataBase dataBase = new DataBase();	// create a new database
		
		WelcomeScreen welcome = new WelcomeScreen();	// create gui for welcome screen
		welcome.setVisible(true);
		

		//GameScreen myGame = new GameScreen();
		//myGame.createGUI();
		
		
	}

}

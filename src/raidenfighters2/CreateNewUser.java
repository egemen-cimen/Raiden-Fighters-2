package raidenfighters2;
import javax.swing.JOptionPane;

public abstract class CreateNewUser
{

	public static User register ()
	{
		final String name;
		final String password;

		name = JOptionPane.showInputDialog(null, "Enter Name:", "Input", 
				JOptionPane.QUESTION_MESSAGE );
		if (DataBase.database.containsKey(name))
		{
			JOptionPane.showMessageDialog(null, "Username alread exists","Username Error", JOptionPane.ERROR_MESSAGE );
			return null;	// return null if username alread exists
		}
		
		if ( name.compareTo("") == 0 || name.compareTo(" ") == 0 )
		{
			JOptionPane.showMessageDialog(null, "Username can't be empty","Username Error: Empty", JOptionPane.ERROR_MESSAGE );
			return null;
		}
			

		password = JOptionPane.showInputDialog(null, "Enter Password:", "Input", 
				JOptionPane.QUESTION_MESSAGE );

		if ( password.compareTo("") == 0 || password.compareTo(" ") == 0 )
		{
			JOptionPane.showMessageDialog(null, "Password can't be empty","Password Error: Empty", JOptionPane.ERROR_MESSAGE );
			return null;
		}
			
		
		User newUser = new User(name, password);
		
		return newUser;	// return newly created user if username wasn't registered
		
	}
	
	public static User register ( String n, String p, int h )
	{
		User newUser = new User(n, p, h);
		
		return newUser;
	}
}

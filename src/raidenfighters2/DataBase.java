package raidenfighters2;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.io.*;

public class DataBase
{
	protected static HashMap<String, User> database = new HashMap<String, User>();
	private static File file;
	public DataBase()
	{
		String username;
		String password;
		int highScore;
		

		try
		{
			//file = new File("C:\\Users\\Egemen\\workspace\\CSE212_Project\\src\\raidenfighters2\\scores\\data.txt");
			file = new File("data.txt");
			
			@SuppressWarnings("resource")
			Scanner fileScanner = new Scanner(file);
			while(fileScanner.hasNext())
			{
				username = fileScanner.next();
				password = fileScanner.next();
				highScore = fileScanner.nextInt();
				User tempUser = CreateNewUser.register(username, password, highScore);
				//System.out.println("Added: " + tempUser.getName() +" "+ tempUser.getPassword() +" "+ tempUser.getHighscore());
				database.put(tempUser.getName(), tempUser);	//add user to hash map
				
				//System.out.println(tempUser.getName() +" "+ tempUser.getPassword() +" "+ tempUser.getHighscore());
				
			}
		}
		catch ( FileNotFoundException io )
		{
			System.err.println("File problem");
			//io.printStackTrace();
			System.exit(0);
		}
		catch ( InputMismatchException im )
		{
			System.err.println("Format problem");
			//im.printStackTrace();
			System.exit(0);
		}
		catch ( java.util.NoSuchElementException ne )
		{
			System.err.println("Format problem");
			//ne.printStackTrace();
			System.exit(0);
		}
		
		
	}
	
	public static void saveToFile()
	{
		FileOutputStream outFile;
		Writer writer = null;
		String tempString;
		try
		{
			outFile = new FileOutputStream("data.txt");
			writer = new BufferedWriter(new OutputStreamWriter ( outFile ));

			for (User tempUser: database.values())
			{

				//writer.write("something");

				tempString = String.format( "%s %s %d\n", tempUser.getName(), tempUser.getPassword(), tempUser.getHighscore() );
				writer.write(tempString);
				
				
			}
		}
		catch (FileNotFoundException e)
		{
			System.err.println("File write problem");
			e.printStackTrace();
			System.exit(0);
		}
		catch (IOException e)
		{
			System.err.println("File write problem");
			e.printStackTrace();
			System.exit(0);
		}
		finally
		{
			if ( writer!=null )
			{
				try
				{
					writer.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		
	}
	
	public static void saveToFile( User addThisUser )
	{
		String addThisName = addThisUser.getName();
		database.put( addThisName, addThisUser );
		
		
		FileOutputStream outFile;
		Writer writer = null;
		String tempString;
		try
		{
			outFile = new FileOutputStream("data.txt");
			writer = new BufferedWriter(new OutputStreamWriter ( outFile ));

			for (User tempUser: database.values())
			{

				//writer.write("something");

				tempString = String.format( "%s %s %d\n", tempUser.getName(), tempUser.getPassword(), tempUser.getHighscore() );
				writer.write(tempString);
				
				
			}
		}
		catch (FileNotFoundException e)
		{
			System.err.println("File write problem");
			e.printStackTrace();
			System.exit(0);
		}
		catch (IOException e)
		{
			System.err.println("File write problem");
			e.printStackTrace();
			System.exit(0);
		}
		finally
		{
			if ( writer!=null )
			{
				try
				{
					writer.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		
	}
	
	public static User login()
	{
		String name;
		String password;
		User tempUser;
		
		name = JOptionPane.showInputDialog(null, "Enter Name:", "Input", 
				JOptionPane.QUESTION_MESSAGE );
		
		if ( !DataBase.database.containsKey(name) )
		{
			JOptionPane.showMessageDialog(null, "Username doesn't exist","Username Error", JOptionPane.ERROR_MESSAGE );
			return null;	// return null if not registered
		}

		tempUser = database.get(name);
		
		password = JOptionPane.showInputDialog(null, "Enter Password:", "Input", 
				JOptionPane.QUESTION_MESSAGE );
		
		if ( tempUser.getPassword().equals(password) )
		{
			return tempUser;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Wrong password","Password Error", JOptionPane.ERROR_MESSAGE );
			return null;
		}
		
	}
	
}

package raidenfighters2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

public class ScoreBoard implements Comparable<ScoreBoard>
{
	String name;
	int highscore;
	
	public ScoreBoard ( User userForScore )
	{
		name = userForScore.getName();
		highscore = userForScore.getHighscore();
		
	}
	
	public static void showScores( HashMap<String, User> database )
	{
		List<ScoreBoard> scoreboard = new ArrayList<ScoreBoard>();
		int scoreboardsize;
		int scoreboardlimit;
		String stringToBeDisplayed = "";
		
		for (User userForScore : database.values())
		{
			scoreboard.add(new ScoreBoard(userForScore));
		}
		
		Collections.sort(scoreboard);
		
		scoreboardsize = scoreboard.size()-1;
		if ( scoreboardsize <= 5 )	// if highscoreboard is smaller than 5
			scoreboardlimit = -1;
		else 
			scoreboardlimit = scoreboardsize - 5;
		
		int j = 1;
		for ( int i = scoreboardsize; i > scoreboardlimit; i-- )
		{
			ScoreBoard sorted = scoreboard.get(i);
			//System.out.print(sorted.getName() + " - " + sorted.getHighscore() + "\n");
			stringToBeDisplayed += String.format("%d) %s - %d\n", j, sorted.getName(), sorted.getHighscore() );
			j++;
		}

		JOptionPane.showMessageDialog(null, "Top 5 Highscores: \n" + stringToBeDisplayed, "Top 5 Highscores", JOptionPane.INFORMATION_MESSAGE );
		//System.out.println(stringToBeDisplayed);
		
	}

	public String getName() {
		return name;
	}

	public int getHighscore() {
		return highscore;
	}

	@Override
	public int compareTo(ScoreBoard arg0)
	{
		if ( highscore < arg0.highscore ) 
			return -1;
		else if ( highscore > arg0.highscore ) 
			return 1; 
		else return 0;
	}
	
	
}

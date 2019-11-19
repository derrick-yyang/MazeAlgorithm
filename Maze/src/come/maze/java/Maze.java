//*****************************************************************************************
//Maze Class
//Derrick Yang
//November 2, 2019
//Java SE 1.8
//*****************************************************************************************
//<Class>
//This class creates a template for a Maze object
//<List of Identifiers>
//let StartX = x coordinate of the position of the mouse <type int>
//let StartY = y coordinate of the position of the mouse <type int>
//let CheeseX = x coordinate of the position of the cheese <type int>
//let CheeseY = y coordinate of the position of the cheese <type int>
//let ExitX = x coordinate of the position of the exit <type int>
//let ExitY = y coordinate of the position of the exit <type int>\
//let maze = 2D matrix where the maze from the text file is stored
//*****************************************************************************************

package come.maze.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
	private int StartX;
	private int StartY;
	private int CheeseX;
	private int CheeseY;
	private int ExitX;
	private int ExitY;
	private char maze[][] = new char[8][12];

	/**
	 * Default Constructor method initializing (x,y) coordinates for Mouse, Cheese, Exit
	 * @throws IOException
	 */
	public Maze() throws IOException{

		FileReader reader = new FileReader("Maze.txt");
		BufferedReader br = new BufferedReader(reader);

		for(int x=0; x<8; x++) {
			String S = br.readLine();
			S = S.replaceAll("\\s+","");
			for(int y=0; y<S.length(); y++) {
				maze[x][y]=S.charAt(y);
				if(maze[x][y]=='R' || maze[x][y]=='M') {
					setStartX(x);
					setStartY(y);
				}
				if(maze[x][y]=='C') {
					setCheeseX(x);
					setCheeseY(y);
				}
				if(maze[x][y]=='X') {
					setExitX(x);
					setExitY(y);
				}
			}
		}
		br.close();
	}//end of Maze

	/**
	 * Accessor returns maze
	 * 
	 * @return maze - the maze <char array>
	 */
	public char[][]getMaze(){
		return maze;
	}//end of getMaze

	/**
	 * Accessor returns x coordinate of Exit
	 * 
	 * @return ExitX - x coordinate of Exit <int>
	 */
	public int getExitX() {
		return ExitX;
	}//end of getExitX
	/**
	 * Mutator sets a value for ExitX
	 * @param exitX <int>
	 */
	public void setExitX(int exitX) {
		ExitX = exitX;
	}//end of setExitX
	/**
	 * Accessor returns y coordinate of Exit
	 * 
	 * @return ExitY - y coordinate of Exit <int>
	 */
	public int getExitY() {
		return ExitY;
	}//end of getExitY
	/**
	 * Mutator sets a value for ExitY
	 * @param exitY <int>
	 */
	public void setExitY(int exitY) {
		ExitY = exitY;
	}//end of setExitY
	/**
	 * Accessor returns x coordinate of Mouse
	 * 
	 * @return StartY - x coordinate of Mouse <int>
	 */
	public int getStartY() {
		return StartY;
	}//end of getStartY
	/**
	 * Mutator sets a value for StartY
	 * @param startY <int>
	 */

	public void setStartY(int startY) {
		StartY = startY;
	}//end of setStartY
	/**
	 * Accessor returns x coordinate of Mouse
	 * 
	 * @return StartX - x coordinate of Mouse <int>
	 */
	public int getStartX() {
		return StartX;
	}//end of getStartX
	/**
	 * Mutator sets a value for StartX
	 * @param startX <int>
	 */
	public void setStartX(int startX) {
		StartX = startX;
	}//end of setStartX
	/**
	 * Accessor returns x coordinate of Cheese
	 * 
	 * @return CheeseX - x coordinate of Cheese <int>
	 */
	public int getCheeseX() {
		return CheeseX;
	}//end of getCheeseX
	/**
	 * Mutator sets a value for CheeseX
	 * @param cheeseX <int>
	 */
	public void setCheeseX(int cheeseX) {
		CheeseX = cheeseX;
	}//end of setCheeseX
	/**
	 * Accessor returns y coordinate of Cheese
	 * 
	 * @return CheeseY - y coordinate of Cheese <int>
	 */
	public int getCheeseY() {
		return CheeseY;
	}//end of getCheeseY
	/**
	 * Mutator sets a value for CheeseY
	 * @param cheeseY <int>
	 */
	public void setCheeseY(int cheeseY) {
		CheeseY = cheeseY;
	}//end of setCheeseY

}//end of Maze class




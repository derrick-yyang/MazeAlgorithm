/*==========================================================================================
MazeSolver																					
Derrick Yang
November 2, 2019
Java SE 1.8
============================================================================================
Problem Definition: Find the shortest path the the cheese, then to the exit in an 8x12 Maze
Input - Program file reads a text file containing the maze
Output - the shortest number of moves to reach the cheese, then the exit.
Process: Recursion and backtracking
============================================================================================
 */
package come.maze.java;

import java.io.IOException;
/**
 * 
 * @author Derrick Yang
 *
 */
public class MazeSolver {

	private static final int X = 8;
	private static final int Y = 12;
	public static char solution[][] = new char[X][Y];
	/**isSafe method
	 * This functional method is a check to see if cell (x,y) int the mazeis a wall ('B') or visited

	 * @param maze <char[][]>
	 * @param visited <int [][]>
	 * @param x <int>
	 * @param y <int>
	 * @return boolean
	 */
	private static boolean isSafe(char[][] maze, int visited[][], int x, int y)
	{
		return !(maze[x][y] == 'B' || visited[x][y] != 0);
	}//end of isSafe

	/**Boundaries method
	 * This functional method is a check to see if cell (x,y) in the maze is out of bounds
	 *
	 * @param x <int>
	 * @param y <int>
	 * @return boolean
	 */
	private static boolean Boundaries(int x, int y)
	{
		return (x < X && y < Y && x >= 0 && y >= 0);
	}//end of Boundaries

	/**copyArray method
	 * This functional method clones one array onto another
	 * 
	 * @param a <char[][]>
	 * @param b <char[][]>
	 * @return a - cloned copy of b
	 */

	public static char[][]copyArray(char[][]a, char[][]b){
		for(int h=0; h<8; h++) {
			for(int k=0; k<12; k++) {
				a[h][k]=b[h][k];
			}
		}
		return a;
	}//end of copyArray

	/**ShortestPath method
	 * this functional method recursively finds the shortest path from one location to another in a matrix
	 * 
	 * @param maze <char[][]>
	 * @param visited <int[][]>
	 * @param i <int>
	 * @param j <int>
	 * @param x <int>
	 * @param y <int>
	 * @param minDistance <int>
	 * @param dist <int>
	 * @return minimum distance
	 */

	public static int ShortestPath(char[][]maze, int[][]visited,  
			int i, int j, int x, int y, int minDistance, int dist)
	{
		//if destination is found, update minDistance of necessary
		if (i == x && j == y)
		{
			if(dist<minDistance) {
				copyArray(solution, maze);
				for(int h=0; h<8; h++) {
					for(int k=0; k<12; k++) {
						if(visited[h][k]==1 && solution[h][k]!='C' && solution[h][k]!='X' && solution[h][k]!='M' && solution[h][k] !='R') {
							solution[h][k]='*';
						}
					}
				}
			}

			return Integer.min(dist, minDistance);
		}

		// set (i, j) cell as visited
		visited[i][j] = 1;

		// travel down
		if (Boundaries(i + 1, j) && isSafe(maze, visited, i + 1, j)) {
			minDistance = ShortestPath(maze, visited, i + 1, j, x, y,
					minDistance, dist + 1);
		}

		// travel right
		if (Boundaries(i, j + 1) && isSafe(maze, visited, i, j + 1)) {
			minDistance = ShortestPath(maze, visited, i, j + 1, x, y,
					minDistance, dist + 1);
		}

		// travel up
		if (Boundaries(i - 1, j) && isSafe(maze, visited, i - 1, j)) {
			minDistance = ShortestPath(maze, visited, i - 1, j, x, y,
					minDistance, dist + 1);
		}

		// travel left
		if (Boundaries(i, j - 1) && isSafe(maze, visited, i, j - 1)) {
			minDistance = ShortestPath(maze, visited, i, j - 1, x, y,
					minDistance, dist + 1);
		}

		// Backtracking, remove (i, j) from visited matrix
		visited[i][j] = 0;

		return minDistance;
	}//end of ShortestPath
	
	/**outputIntro method
	 * This procedural method prints the introduction to the problem this program solves
	 * 
	 * @param maze <char[][]>
	 */
	public static void outputIntro(char [][]maze) {
		System.out.println("Many experiments in psychology involve rats attempting to travel through a maze. To represent the\r\n" + 
				"maze,the symbol ‘.’ represents the path, ‘B’ represents the barrier, ‘C’ the cheese, and ‘X’ represents\r\n"+ 
				"an exit. ‘R’ or ‘M’ should represent the Rat or Mouse.\r\n");
		for(int y=0; y<8; y++) {
			for(int x=0; x<12; x++) {
				System.out.print(maze[y][x]);
			}
			System.out.println();
		}
		System.out.println("\r\nThe purpose of the program would be to find the shortest path to the cheese, then to the exit.");
		System.out.println("The program would print out a replica maze but replacing '.' with '*' for the shortest path solution.\r\n");

	}//end of outputIntro
	/**outputCheesePath method
	 * This procedural method prints the minimum distance from mouse to cheese, and the maze with the shortest path
	 * 
	 * @param visited <int[][]>
	 * @param maze <char[][]>
	 * @param mazeobj <Maze Object>
	 */
	public static void outputCheesePath(int visited[][], char [][]maze, Maze mazeobj) {
		System.out.print("The minimum distance to get to C: ");
		if(ShortestPath(maze, visited, mazeobj.getStartX(), mazeobj.getStartY(), mazeobj.getCheeseX(), mazeobj.getCheeseY(), Integer.MAX_VALUE, 0)!=Integer.MAX_VALUE) {
			System.out.println(ShortestPath(maze, visited, mazeobj.getStartX(), mazeobj.getStartY(), mazeobj.getCheeseX(), mazeobj.getCheeseY(), Integer.MAX_VALUE, 0));

			for(int h=0; h<8; h++) {
				for(int k=0; k<12; k++) {
					System.out.print(solution[h][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
		else {
			System.out.println("\r\nNo solution\r\n");
		}
	}//end of outputCheesePath

	/**outputExitPath method
	 * This procedural method prints out the shortest distance from cheese to exit, and the maze with the shortest path
	 * 
	 * @param visited <int[][]>
	 * @param maze <char[][]>
	 * @param mazeobj <Maze Object>
	 */
	public static void outputExitPath(int visited[][], char [][]maze, Maze mazeobj) {
		System.out.print("The minimum distance to get from C to X: ");
		if(ShortestPath(maze, visited, mazeobj.getCheeseX(),  mazeobj.getCheeseY(), mazeobj.getExitX(), mazeobj.getExitY(), Integer.MAX_VALUE, 0)!=Integer.MAX_VALUE) {
			System.out.println(ShortestPath(maze, visited, mazeobj.getCheeseX(),  mazeobj.getCheeseY(), mazeobj.getExitX(), mazeobj.getExitY(), Integer.MAX_VALUE, 0));

			for(int h=0; h<8; h++) {
				for(int k=0; k<12; k++) {
					System.out.print(solution[h][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
		else {
			System.out.println("\r\nNo solution\r\n");
		}
	}//end of outputExitPath

	/**checkCheese method
	 * This functional method checks the number of cheese in the maze
	 * 
	 * @param maze <char[][]>
	 * @return
	 */
	public static int checkCheese(char[][]maze) {
		int count = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 12; j++) {
				if(maze[i][j]=='C') {
					count++;
				}
			}
		}
		return count;
	}//end of checkCheese
	/**checkExit method
	 * This functional method checks the number of exits in the maze
	 * 
	 * @param maze <char[][]>
	 * @return
	 */
	public static int checkExit(char[][]maze) {
		int count = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 12; j++) {
				if(maze[i][j]=='X') {
					count++;
				}
			}
		}
		return count;
	}//end of checkExit
	/**checkMouse method
	 * This functional method checks the number of Mouse in the maze
	 * 
	 * @param maze <char[][]>
	 * @return
	 */
	public static int checkMouse(char[][]maze) {
		int count = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 12; j++) {
				if(maze[i][j]=='M'|| maze[i][j]=='R') {
					count++;
				}
			}
		}
		return count;
	}//end of checkMouse

	/**main method:
	 * This procedural method is called automatically and is used to organize the calling of other methods defined in the class
	 * 
	 * List of Local Variables
	 * mazeobj - an object used to access non-static methods defined in class Maze
	 * maze - 2D char array used to store the maze from the text file
	 * visited - 2D int array used in the recursion formula (backtracking)
	 * 
	 * @param args <type String>
	 * @throws IOException
	 * @return void
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Maze mazeobj = new Maze();
		char maze[][] = new char[8][12];
		int[][] visited = new int[X][Y];
		maze=mazeobj.getMaze();

		outputIntro(maze);
		if(checkMouse(maze)>1 || checkExit(maze)>1 || checkCheese(maze)>1) {
			System.out.println("Error!!! Please check your maze, It must have one mouse, one cheese, and one exit.");

		}
		else {
			outputCheesePath(visited, maze, mazeobj);
			outputExitPath(visited, maze, mazeobj);
		}

	}//end of main

}//end of MazeSolver Class
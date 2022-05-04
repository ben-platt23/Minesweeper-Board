import java.util.Random;
import java.util.Scanner;

/*
 * Author: Ben Platt
 * Date: April 4, 2022
 * This program creates a minesweeper board, with the bombs and indicators in full
 * view to the user.
 */
public class Lab4 {

	public static void main(String[] args) {
		// initialize objects and dimensions string
		Scanner input = new Scanner(System.in);
		Lab4 l = new Lab4();
		String dimensions;
		
		// get user input for board size.
		System.out.println("Welcome to minesweeper! "
				+ "What dimensions would you like for your board? (e.g. 2x2, "
				+ "4x3, 8x8, ...) max = 9x9");
		dimensions = input.nextLine();
		
		// extrapolate rows and columns from input
		int rows = Character.getNumericValue(dimensions.charAt(0));
		int cols = Character.getNumericValue(dimensions.charAt(2));
		
		// get the empty board
		int[][] emptyBoard = l.createEmptyBoard(rows, cols);
		
		// place mines randomly
		int[][] boardWithMines = l.placeMines(emptyBoard);
		
		// place indicators based on mine locations
		int[][] finishedBoard = l.placeWarnings(boardWithMines);
		for (int i = 0; i < finishedBoard.length; i++) {
			for (int j = 0; j < finishedBoard[i].length; j++) {
				System.out.print(finishedBoard[i][j]+" ");
			}
			System.out.println();
		}
	}
	// takes in number of rows and columns and 
	// returns an empty 2D array of zeros of that size
	public int[][] createEmptyBoard(int rows, int cols){
		int[][] emptyBoard = new int[rows][cols];
		return emptyBoard;
	}
	// takes in an array of zeros and randomly places 9's. Number of 9's is rows/2
	public int[][] placeMines(int[][] emptyBoard) {
		// get number of mines and initialize rng
		Random rand = new Random();
		int numMines = (emptyBoard.length)/2;
		
		// place random 9's the number of mines times
		for(int i = 0; i < numMines; i++) {
			int x = rand.nextInt(emptyBoard[0].length);
			int y = rand.nextInt(emptyBoard.length);
			emptyBoard[y][x] = 9;
		}
		
		// return board with mines
		return emptyBoard;
	}
	
	// takes in an array of 0's with occasional 9's and returns a board with indicators
	// The indicator is how many mines are in the immediate vicinity of the square
	public int[][] placeWarnings(int[][] board){
		// loop through board and place warnings, considers edge cases
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// continue to next iteration immediately if the coordinate is a bomb
				if(board[i][j] == 9) {
					continue;
				}
				// initialize indicator
				int num = 0;
				// top left corner edge case
				if(i == 0 && j == 0) {
					// right
					if(board[i][j+1] == 9) {
						num++;
					}
					// right down diagonal
					if(board[i+1][j+1] == 9) {
						num++;
					}
					// down
					if(board[i+1][j] == 9) {
						num++;
					}
				}
				// top right corner edge case
				else if(i == 0 && j == board[i].length-1) {
					// left
					if(board[i][j-1] == 9) {
						num++;
					}
					// left down diagonal
					if(board[i+1][j-1] == 9) {
						num++;
					}
					// down
					if(board[i+1][j] == 9) {
						num++;
					}
				}
				// bottom left corner edge case
				else if(i == board.length-1 && j == 0) {
					// up
					if(board[i-1][j] == 9) {
						num++;
					}
					// right up diagonal
					if(board[i-1][j+1] == 9) {
						num++;
					}
					// right
					if(board[i][j+1] == 9) {
						num++;
					}
				}
				// bottom right corner edge case
				else if(i == board.length-1 && j == board[i].length-1) {
					// left
					if(board[i][j-1] == 9) {
						num++;
					}
					// left up diagonal
					if(board[i-1][j-1] == 9) {
						num++;
					}
					// up
					if(board[i-1][j] == 9) {
						num++;
					}
				}
				// left bound edge case
				else if(j == 0) {
					// up
					if(board[i-1][j] == 9) {
						num++;
					}
					// down
					if(board[i+1][j] == 9) {
						num++;
					}
					// right
					if(board[i][j+1] == 9) {
						num++;
					}
					// right up diagonal
					if(board[i-1][j+1] == 9) {
						num++;
					}
					// right down diagonal
					if(board[i+1][j+1] == 9) {
						num++;
					}
				}
				// top bound edge case
				else if(i == 0) {
					// left
					if(board[i][j-1] == 9) {
						num++;
					}
					// left down diagonal
					if(board[i+1][j-1] == 9) {
						num++;
					}
					// down
					if(board[i+1][j] == 9) {
						num++;
					}
					// right down diagonal
					if(board[i+1][j+1] == 9) {
						num++;
					}
					// right
					if(board[i][j+1] == 9) {
						num++;
					}
				}
				// right bound edge case
				else if(j == board[i].length-1) {
					// up
					if(board[i-1][j] == 9) {
						num++;
					}
					// left up diagonal
					if(board[i-1][j-1] == 9) {
						num++;
					}
					// left
					if(board[i][j-1] == 9) {
						num++;
					}
					// left down diagonal
					if(board[i+1][j-1] == 9) {
						num++;
					}
					// down
					if(board[i+1][j] == 9) {
						num++;
					}
				}
				// bottom bound edge case
				else if(i == board.length-1) {
					// left
					if(board[i][j-1] == 9) {
						num++;
					}
					// left up diagonal
					if(board[i-1][j-1] == 9) {
						num++;
					}
					// up
					if(board[i-1][j] == 9) {
						num++;
					}
					// right up diagonal
					if(board[i-1][j+1] == 9) {
						num++;
					}
					// right
					if(board[i][j+1] == 9) {
						num++;
					}
				}
				// middle case
				else{
					// left
					if(board[i][j-1] == 9) {
						num++;
					}
					// left up diagonal
					if(board[i-1][j-1] == 9) {
						num++;
					}
					// up
					if(board[i-1][j] == 9) {
						num++;
					}
					// right up diagonal
					if(board[i-1][j+1] == 9) {
						num++;
					}
					// right
					if(board[i][j+1] == 9) {
						num++;
					}
					// right down diagonal
					if(board[i+1][j+1] == 9) {
						num++;
					}
					// down
					if(board[i+1][j] == 9) {
						num++;
					}
					// left down diagonal
					if(board[i+1][j-1] == 9) {
						num++;
					}
				}
				board[i][j] = num;
			}
		}
		return board;
	}

}

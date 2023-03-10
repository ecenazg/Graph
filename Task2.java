//-----------------------------------------------------
// Title: Task 2 Class
// Author: Ecenaz Güngör
// ID: 38210050188
// Section: 1
// Assignment: 2
// Description: This class test the surrounded O's by S's and converts them to X's.
//-----------------------------------------------------

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Task2 {

	static char[][] grid;
	
	/*
	 * Vertex samples: i nd position + "S/O" + j nd position
	 * 
	 */
	public static void main(String[] args) {
		grid = readInput();
		
		// Creating unidirected graph
		Graph adjGraph = initGraph(grid);
		// Convert O's to X's
		convertSurrounded(adjGraph,grid);
		// Print converted Grid
		printGrid(grid);
	}

	private static void printGrid(char[][] grid) {
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[i].length;j++) {
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void convertSurrounded(Graph adjGraph, char[][] grid) {
		//If the number of s added in this method is 8, it is converted. 
		//With the split method, I separate the O's
		Map<Vertex, Set<Vertex>> gr = adjGraph.getGraphAdj();

		for(Vertex node:gr.keySet()) {
			if(gr.get(node).size()==8) {
				String element = node.getVertexElement();
				String[] coord = element.split("O");
				int x = Integer.parseInt(coord[0]);
				int y = Integer.parseInt(coord[1]);
				grid[x][y] = 'X';
			}
		}
	}

	private static Graph initGraph(char[][] grid) {
		/*
		 I start the for loop from 1, I look up to length – 1. 
		* Because the "O" in the corners can never be the result. 
		* After finding the O's, I created a vertex with location information. 
		* I then sent this information to the visit neighbor method. 
		*/
		Graph gr = new Graph();
		for(int i=1;i<grid.length-1;i++) {
			for(int j=1;j<grid[i].length-1;j++) {
				if(grid[i][j]=='O') {
					Vertex base = new Vertex(""+i+"O"+j);
					gr.addVertex(base);
					visitNeighbours(grid, gr, i, j, base);
				}
			}
		}
		return gr;
	}
	
	private static void visitNeighbours(char[][] grid, Graph gr, int row, int col, Vertex base) {

		// i always accept the result in the 1x1 position
		//the following arrays are the neighbors of my result
		//for example, the upper left neighbor of 1x1 is 0x0, so
		//I kept it at -1 -1
		
		int[] xdir = {-1,-1,-1, 0, 0, 1, 1, 1};
		int[] ydir = {-1, 0, 1,-1, 1,-1, 0, 1};
		
		for(int i=0;i<8;i++) {
			int x = row+xdir[i];
			int y = col+ydir[i];
			if(grid[x][y] == 'S') {
				gr.addEdge(base, new Vertex(""+x+"S"+y));
			}
		}
	}

	private static char[][] readInput() {
		Scanner sc = new Scanner(System.in);
		int rowNumber = Integer.parseInt(sc.nextLine());
		int colNumber = Integer.parseInt(sc.nextLine());
		char[][] grid = new char[rowNumber][colNumber];
		
		for(int i=0;i<rowNumber;i++) {
			String line = sc.nextLine();
			int ind = 0;
			for(char c:line.toCharArray())
				if(Character.isAlphabetic(c))
					grid[i][ind++] = c;
		}
		sc.close();
		return grid;
	}

}

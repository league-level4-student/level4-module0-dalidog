package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	public static boolean hasUnvisitedNeighbors = false;

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		Cell randomcell = maze.getCell(randGen.nextInt(w), randGen.nextInt(h));

		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(randomcell);
		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell>celery=getUnvisitedNeighbors(currentCell);
		
		// C. if has unvisited neighbors,
		Random r = new Random();

		if (celery.size() > 0){
			Cell newCell = celery.get(r.nextInt(celery.size()));
			uncheckedCells.push(newCell);
			// C1. select one at random.

			// C2. push it to the stack

			// C3. remove the wall between the two cells
			removeWalls(currentCell, newCell);
			// C4. make the new cell the current cell and mark it as visited
			currentCell=newCell;
			currentCell.setVisited(true);
			selectNextPath(currentCell);
		} else {
			// D. if all neighbors are visited
			if (celery.size() == 0) {
				if (uncheckedCells.size() > 0) {
					currentCell = uncheckedCells.pop();
					selectNextPath(currentCell);
				}
			}
			// D1. if the stack is not empty

			// D1a. pop a cell from the stack

			// D1b. make that the current cell
		}
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (Math.abs(c1.getX() - c2.getX()) == 1) {
			if (c1.getY() == c2.getY()) {
				if (c2.getX() > c1.getX()) {
					c2.setWestWall(false);
					c1.setEastWall(false);
				} else {
					c2.setEastWall(false);
					c1.setWestWall(false);
				}
			}
		}
		if (Math.abs(c1.getY() - c2.getY()) == 1) {
			if (c1.getX() == c2.getX()) {
				if (c2.getY() > c1.getY()) {
					c2.setNorthWall(false);
					c1.setSouthWall(false);
				} else {
					c2.setSouthWall(false);
					c1.setNorthWall(false);
				}
			}
		}

	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> cellarray = new ArrayList<Cell>();
		for (int i = c.getX() - 1; i <= c.getX() + 1; i++) {
			for (int j = c.getY() - 1; j <= c.getY() + 1; j++) {
				if ((i == c.getX() && j == c.getY()) || (i == c.getX() - 1 && j == c.getY() - 1)
						|| (i == c.getX() + 1 && j == c.getY() + 1) || (i == c.getX() - 1 && j == c.getY() + 1)
						|| (i == c.getX() + 1 && j == c.getY() - 1)) {

				} else if (i >= 0 && i < width && j >= 0 && j < height
						&& maze.getCell(i, j).hasBeenVisited() == false) {
					cellarray.add(maze.getCell(i, j));
				}
			}
		}
		System.out.println(cellarray.size());
		return cellarray;
	
	}

}
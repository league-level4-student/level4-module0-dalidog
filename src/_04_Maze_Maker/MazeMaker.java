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
	private static ArrayList<Cell> cellarray = new ArrayList<Cell>();
	public static boolean hasUnvisitedNeighbors = false;
	static Cell newCell;

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
		getUnvisitedNeighbors(currentCell);

		// C. if has unvisited neighbors,
		if (hasUnvisitedNeighbors == true) {

			uncheckedCells.push(newCell);
			// C1. select one at random.

			// C2. push it to the stack

			// C3. remove the wall between the two cells
			removeWalls(currentCell, newCell);
			// C4. make the new cell the current cell and mark it as visited
			currentCell = newCell;
			currentCell.setVisited(true);
		} else {
			// D. if all neighbors are visited
			if (uncheckedCells.size() != 0) {
				currentCell = uncheckedCells.pop();
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
					c2.setSouthWall(false);
					c1.setNorthWall(false);
				} else {
					c2.setNorthWall(false);
					c1.setSouthWall(false);
				}
			}
		}

	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		if (maze.cells[c.getX() - 1][c.getY()].hasBeenVisited() == false) {
			cellarray.add(maze.cells[c.getX() - 1][c.getY()]);
			hasUnvisitedNeighbors = true;
		} else if (maze.cells[c.getX() + 1][c.getY()].hasBeenVisited() == false) {
			cellarray.add(maze.cells[c.getX() + 1][c.getY()]);
			hasUnvisitedNeighbors = true;
		} else if (maze.cells[c.getX()][c.getY() - 1].hasBeenVisited() == false) {
			cellarray.add(maze.cells[c.getX()][c.getY() - 1]);
			hasUnvisitedNeighbors = true;
		} else if (maze.cells[c.getX()][c.getY() + 1].hasBeenVisited() == false) {
			cellarray.add(maze.cells[c.getX()][c.getY() + 1]);
			hasUnvisitedNeighbors = true;
		}
		newCell = cellarray.get(randGen.nextInt(cellarray.size() - 1));
		return cellarray;
	}

}
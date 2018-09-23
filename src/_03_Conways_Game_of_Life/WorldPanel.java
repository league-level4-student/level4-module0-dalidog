package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;

	private Timer timer;

	// 1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] cells;

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;

		// 2. Calculate the cell size.
		cellSize = w / cellsPerRow;
		// 3. Initialize the cell array to the appropriate size.
		cells = new Cell[cpr][cpr];
		// 3. Iterate through the array and initialize each cell.
		// Don't forget to consider the cell's dimensions when
		// passing in the location.
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(i, j, cellSize);
			}
		}
	}

	public void randomizeCells() {
		// 4. Iterate through each cell and randomly set each
		// cell's isAlive memeber to true of false
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				int r = new Random().nextInt(2);
				if (r == 0) {
					cells[i][j].isAlive = true;
				} else if (r == 1) {
					cells[i][j].isAlive = false;
				}
			}
			System.out.println("random is alive");
		}
		repaint();
	}

	public void clearCells() {
		// 5. Iterated through the cells and set them all to dead.
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j].isAlive = false;
			}
		}
		repaint();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
		// 6. Iterate through the cells and draw them all
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j].draw(g);
			}
		}

		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	// advances world one step
	public void step() {
		// 7. iterate through cells and get their neighbors
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {

				cells[i][j].liveOrDie(getLivingNeighbors(i, j).size());
			}

			// 8. check if each cell should live or die

		}
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {

				if (cells[i][j].markForDeath == true) {
					cells[i][j].isAlive = false;
				} else {
					cells[i][j].isAlive = true;
				}
			}

			// 8. check if each cell should live or die

		}

		repaint();
	}

	// 9. Complete the method.
	// It returns an array list of the 8 or less neighbors of the
	// cell identified by x and y
	public ArrayList<Cell> getLivingNeighbors(int x, int y) {
		ArrayList<Cell> neigh = new ArrayList<Cell>();

		for (int j2 = x - 1; j2 <= x + 1; j2++) {
			for (int k = y - 1; k <= y + 1; k++) {

				if (j2 < 0 || j2 > cells.length - 1 || k < 0 || k > cells.length - 1 || (j2 == x && k == y)) {

				} else {
					if (cells[j2][k].isAlive == true) {

						neigh.add(cells[j2][k]);
					}
				}

			}

		}
		return neigh;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 10. Use e.getX() and e.getY() to determine
		// which cell is clicked. Then change
		// the isAlive variable for that cell.
		int x = e.getX() / cellSize;
		int y = e.getY() / cellSize;
		System.out.println("mouse pressed method");
		if (cells[x][y].isAlive == true) {
			cells[x][y].isAlive = false;
		} else if (cells[x][y].isAlive == false) {
			cells[x][y].isAlive = true;
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}

package _02_Pixel_Art;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;


public class PixelArtMaker implements MouseListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	private JButton save;
	ColorSelectionPanel csp;
	int rows;
	int columns;
	private static final String DATA_FILE = "src/_02_Pixel_Art/savestate";
	public void start() {
		gip = new GridInputPanel(this);	
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		save=new JButton("Save State");
		window.add(save);
		save.addActionListener(this);
		window.add(gip);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
	}
	public GridPanel loadPixels(){
		try (FileInputStream fis = new FileInputStream(DATA_FILE); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (GridPanel) ois.readObject();
		} catch (IOException e) {      
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// This can occur if the object we read from the file is not
			// an instance of any recognized class
			e.printStackTrace();
			return null;
		}
	}
	public void submitGridData(int w, int h, int r, int c) {
		gp = new GridPanel(w, h, r, c);
		gp=loadPixels();
		r=rows;
		c=columns;
		csp = new ColorSelectionPanel();
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		gp.repaint();
		gp.addMouseListener(this);
		window.pack();
	}
	
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try (FileOutputStream fos = new FileOutputStream(new File(DATA_FILE)); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(gp);    //makes space in file for data, inputs incoming data
		} catch (IOException j) {
			j.printStackTrace();
		}
	}
}

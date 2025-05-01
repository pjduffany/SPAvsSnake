package Controllers;

import javax.swing.*;
import java.awt.*;

import Models.GameBoard;
import Views.View;

public class SnakeGameMain implements Runnable 
{

	private static final int width = 20;
	private static final int height = 20;
	private View view;
	private Controller controller;
	
	
	public void run() 
	{
		//create our container objects
		JFrame frame = new JFrame("BFS vs Snake Game");
		Container container = frame.getContentPane();
		GameBoard gameBoard = new GameBoard(width, height);

		//setup the view
		view = new View(gameBoard);
		view.init();
		//must be initialize dimensions after the view is instantiated.
		Dimension boardDimensions = new Dimension(width * view.pixels, height * view.pixels);
		view.getPanel().setPreferredSize(boardDimensions);
		container.add(view.getPanel(), BorderLayout.CENTER);
		
		//draw elements of the game.
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		//set the controller.
		controller = new Controller(gameBoard, view);
		frame.addKeyListener(controller);
		new Thread(controller).start();
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new SnakeGameMain());

	}

}

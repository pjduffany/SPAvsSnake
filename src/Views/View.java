package Views;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

import Models.*;

public class View {

	private JPanel panel;
	private final GameBoard gameBoard;
	public  final int pixels = 20;
	
	public View(GameBoard gameBoard)
	{
		this.gameBoard = gameBoard;
	}
	
	//<summary>Draws the initial components associated with the view.</summary>
	public void init()
	{
		panel =  new JPanel() 
		{
			@Override
	        public void paintComponent(Graphics graphics) {
				drawGridBackground(graphics);
				drawSnake(graphics, gameBoard.getSnake());
				drawFood(graphics, gameBoard.getFood());
			}
		};
	}
	
	//<summary>Draws the snake.</summary>
	//<params>
	//	graphic - The graphic object.
	//	snake - The snake object.
	//</params>
	public void drawSnake(Graphics graphics, Snake snake) 
	{
		LinkedList<SnakePart> parts = snake.getBody();
		for (SnakePart sp : parts)
		{
			drawSquare(graphics, sp, Color.green);
		}
	}
	
	//<summary>Draws the Food object.</summary>
	//<params>
	//	graphic - The graphic object
	//  food -  the food object.
	//</params>
	public void drawFood(Graphics graphics, Food food)
	{
		drawSquare(graphics, food, Color.yellow);
	}
	
	//<summary>Draws the gameboard.</summary>
	//<params>
	//	graphic - The graphic object
	//</params>
	public void drawGridBackground(Graphics graphics)
	{
		for(int i = 0; i < gameBoard.getWidth(); i++)
		{
			for(int j = 0; j < gameBoard.getHeight(); j++)
			{
				drawSquare(graphics, new Node(i, j), Color.black);
			}
		}
	}
	//<summary>Draws an individual unit of the game board.</summary>
	//<params>
	//	graphic - The graphic object
	//  node - The type of node Node, SnakePart, or Food
	//  color - The color of the square.
	//</params>
	private void drawSquare(Graphics graphics, INode node, Color color)
	{ 
		graphics.setColor(color);
		graphics.fillRect(node.getX() * pixels, node.getY() * pixels, pixels - 1, pixels - 1);
	}
	
	//<summary>Displays a message that the game is overd</summary>
	public void gameOverMessage() 
	{
        JOptionPane.showMessageDialog(null, "Good Game!\n\nYour score is " + Integer.toString(gameBoard.getScore())
        + "!", "GameOver", JOptionPane.INFORMATION_MESSAGE);
    }
	
	
	//<summary>Repaints the panel where the game is rendered</summary>
	public void draw()
	{
		panel.repaint();
	}
	
	public JPanel getPanel() { return panel; }
}
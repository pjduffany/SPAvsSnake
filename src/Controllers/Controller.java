package Controllers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Models.*;
import Views.View;

public class Controller implements KeyListener, Runnable 
{

	private final GameBoard gameBoard;
	private final View view;
	int finalX;
	int finalY;
	Direction currentDirection;
	
    public Controller(GameBoard gameBoard, View view) 
    {
        this.gameBoard = gameBoard;
        this.view = view;
    }

    @Override
    //<summary>game loop<summary>
    public void run()
    {
    	boolean isRunning = true;
        while (isRunning) 
        {
        	
            try 
            {
            	int tickRate = Math.max(50, 200 - gameBoard.getScore() / 5 * 50); // increase the denominator to speed up the tick rate.
                Thread.sleep(tickRate);                   
                gameBoard.isDirectionChanged = false;
                currentDirection = gameBoard.currentDirection;
                if (gameBoard.go()) 
                {
                    finalX = gameBoard.food.getX();
                    finalY = gameBoard.food.getY();
                	
                    //---Comment the line below if you wish to play
                    controlSnake();
                    //---Comment the line above if you wish to play
                    
                    view.draw();
                } 
                else 
                {
                    System.out.print("Congratulations! Your scores: " + gameBoard.getScore());
                    view.gameOverMessage();
                    isRunning = false;
                }
            } 
            catch (InterruptedException e) 
            {
            	System.out.println(e.getMessage());
                break;
            }
        }
    }

    //<summary>programatically controls the snake<summary>
    public void controlSnake()
   	{

    	try 
    	{
			Robot robot = new Robot();

			int headX = gameBoard.snake.getHead().getX();
			int headY = gameBoard.snake.getHead().getY();
			
			int leftNode = headX - 1;
			int rightNode = headX + 1;
			int downNode = headY + 1;
			int upNode = headY - 1;
			
			//check boundaries
			if((leftNode) < 0)
			{
				leftNode = 19;
			}
			if((rightNode) > 19)
			{
				rightNode = 0;
			}
			if ((upNode) < 0)
			{
				upNode = 19;
			}
			if (downNode > 19) 
			{
				downNode = 0;
			}
			
   			
   			//determine if the next node is a snake node, we want to avoid it...
   			if(currentDirection != null)
   			{

   				switch(currentDirection)
   	   			{
   		    		
   	   				case UP:
   	   					if (gameBoard.tempGameBoard[headX][upNode].getClass() == SnakePart.class)
   	   					{
   	   						System.out.println("Collision detected at (" + headX + "," + (upNode) + ")");
   	   	   					if (gameBoard.tempGameBoard[leftNode][headY].getClass() != SnakePart.class) //LEFT
   	   	   					{
   	   	    					robot.keyPress(KeyEvent.VK_LEFT);
   	   	   					}
   	   	   					else if (gameBoard.tempGameBoard[rightNode][headY].getClass() != SnakePart.class) //right
   	   	   					{
   	   	    					robot.keyPress(KeyEvent.VK_RIGHT);
   	   	   					}
   	   	   					else
   	   	   					{
   	   	   						//nothing we can do
   	   	   						return;
   	   	   					}
   	   					}
   	   					break;
   	   				case DOWN:
   	   					if (gameBoard.tempGameBoard[headX][downNode].getClass() == SnakePart.class)
   	   					{
   	   						System.out.println("Collision detected at (" + headX + "," + (downNode) + ")");
   	   	   					if (gameBoard.tempGameBoard[leftNode][headY].getClass() != SnakePart.class) //LEFT
   	   	   					{
   	   	    					robot.keyPress(KeyEvent.VK_LEFT);
   	   	   					}
   	   	   					else if (gameBoard.tempGameBoard[rightNode][headY].getClass() != SnakePart.class) //right
   	   	   					{
   	   	    					robot.keyPress(KeyEvent.VK_RIGHT);
   	   	   					}
   	   	   					else
   	   	   					{
   	   	   						//nothing we can do
   	   	   						return;
   	   	   					}
   	   					}
   	   					break;
   	   				case LEFT:
   	   					if (gameBoard.tempGameBoard[leftNode][headY].getClass() == SnakePart.class)
   	   					{
   	   						System.out.println("Collision detected at (" + (rightNode) + "," + headY + ")");
   	   	   					if (gameBoard.tempGameBoard[headX][downNode].getClass() != SnakePart.class) //DOWN
   	   	   					{
   	   	    					robot.keyPress(KeyEvent.VK_DOWN);
     	   	   				}
   	   	   					else if (gameBoard.tempGameBoard[headX][upNode].getClass() != SnakePart.class) //UP
   	   	   					{
   	   	    					robot.keyPress(KeyEvent.VK_UP);
   	   	   					}
   	   	   					else
   	   	   					{
   	   	   						//nothing we can do
   	   	   						return;
   	   	   					}
   	   					}
   	   					break;
   	   				case RIGHT:
   	   					if (gameBoard.tempGameBoard[rightNode][headY].getClass() == SnakePart.class)
   	   					{
   	   						System.out.println("Collision detected at (" + (leftNode) + "," + (headY) + ")");
   	   						if (gameBoard.tempGameBoard[headX][downNode].getClass() != SnakePart.class) //DOWN
   	   	   					{
   	   	    					robot.keyPress(KeyEvent.VK_DOWN);
   	   	   					}
   	   	   					else if (gameBoard.tempGameBoard[headX][upNode].getClass() != SnakePart.class) //UP
   	   	   					{
   	   	    					robot.keyPress(KeyEvent.VK_UP);
   	   	   					}
   	   	   					else
   	   	   					{
   	   	   						//nothing we can do
   	   	   						return;
   	   	   					}
   	   					}
   	   					break;
   	   			}
   				int offsetX = finalX - headX;
   	   			int offsetY = finalY - headY;
   	   			if(offsetY > 0) //down
   	   			{
   	   				if (gameBoard.tempGameBoard[headX][downNode].getClass() != SnakePart.class && currentDirection != Direction.DOWN && currentDirection != Direction.UP)
   	   					robot.keyPress(KeyEvent.VK_DOWN);
   	   				return;
   	   			}
   	   			else if(offsetY < 0) //up
   	   			{
   	   				if (gameBoard.tempGameBoard[headX][upNode].getClass() != SnakePart.class  && currentDirection != Direction.UP && currentDirection != Direction.DOWN)
   	   					robot.keyPress(KeyEvent.VK_UP);
   	   				return;
   	   			}
   	   			
   	   			else if(offsetX > 0) //right
   	   			{
   	   				if (gameBoard.tempGameBoard[rightNode][headY].getClass() != SnakePart.class  && currentDirection != Direction.RIGHT && currentDirection != Direction.LEFT)
   	   					robot.keyPress(KeyEvent.VK_RIGHT);
   	   				return;
   	   			}
   	   			else if(offsetX < 0) //left
   	   			{
   	   				if (gameBoard.tempGameBoard[leftNode][headY].getClass() != SnakePart.class  && currentDirection != Direction.LEFT && currentDirection != Direction.RIGHT)
   	   					robot.keyPress(KeyEvent.VK_LEFT);
   	   				return;
   	   			}
  			}
   		}
		catch (AWTException e)
    	{
			e.printStackTrace();
		}
   	}
    
    @Override
    public void keyPressed(KeyEvent e) 
    {
        int keyCode = e.getKeyCode();
        if (gameBoard.isDirectionChanged == false) 
        {
            switch (keyCode) 
            {
                case KeyEvent.VK_UP:
                    gameBoard.changeDirection(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    gameBoard.changeDirection(Direction.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    gameBoard.changeDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    gameBoard.changeDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_ENTER:
                    break;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}

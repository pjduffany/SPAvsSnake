package Models;

import java.util.LinkedList;

public class Snake 
{
	private LinkedList<SnakePart> body = new LinkedList<SnakePart>();
	
	public SnakePart grow(SnakePart part)
	{
		body.addLast(part);
		return part;
	}
	
	//<summary>moves the snake in the current direction</summary>
	//<params>direction - the current direction</params>
	//<returns>SnakePart to set to type Node</returns>
	public SnakePart move(Direction direction)
	{
		SnakePart sp;
		int x = body.getFirst().getX();
		int y = body.getFirst().getY();
		
		switch (direction)
		{
			case UP:
				sp = new SnakePart(x, y - 1);
				break;
			case DOWN:
				sp = new SnakePart(x, y + 1);
				break;
			case LEFT:
				sp = new SnakePart(x - 1, y);
				break;
			case RIGHT:
				sp = new SnakePart(x + 1, y);
				break;
			default:
				sp = null;
				break;
		}
		body.addFirst(sp);
		return body.removeLast();
	}
	
	//<summary>moves the snake in the current direction</summary>
	//<params>food - food object</params>
	//<returns>SnakePart to set to type Node</returns>
	public boolean isFood(Food food)
	{
		SnakePart head = body.getFirst();
		return Math.abs(head.getX() - food.getX()) + Math.abs(head.getY() - food.getY()) == 0;
	}
	
	//<summary>sets the snake head</summary>
	//<params>newHead - snakepart object</params>
	//<returns>SnakePart to set to type Node</returns>
	public SnakePart setHead(SnakePart newHead)
	{
		body.addFirst(newHead);
		return body.removeLast();
	}
	
	// --- BEGIN REGION GETTERS ---
	//<summary> Get the head of the linked list</summary>
	//<returns> Get the head of the linked list</returns>	
	public SnakePart getHead()
	{
		return body.getFirst();
	}
	//<summary> Get the linked list</summary>
	//<returns>returns the head of the linked list of type SnakePart</returns>
	public LinkedList<SnakePart> getBody()
	{
		return body;
	}
	// --- END REGION GETTERS ---
	
}

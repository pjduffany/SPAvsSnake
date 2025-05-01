package SearchAlgorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import Models.*;


public class BFS {
	INode[][] gameBoard;
	boolean[][] visited;
	Snake snake;
	Food food;
	
	public BFS(INode[][] gameBoard, Snake snake, Food food)
	{
		this.gameBoard = gameBoard;
		visited =  new boolean[gameBoard.length][gameBoard.length];
		this.snake = snake;
		this.food = food;
	}
	
	//<summary>My BFS Implementation</summary>
	//<param>startingNode - the head of the snake</param>
	public void Search(INode startingNode)
	{
		System.out.println("----BEGINNING BREADTH FIRST SEARCH-----");
		LinkedList<INode> queue = new LinkedList<INode>();
		
		//no need to search on body
		for(SnakePart part : snake.getBody())
		{
			int x = part.getX();
			int y = part.getY();
			visited[x][y] = true;
		}
		queue.add(startingNode);

			
		while(queue.size() != 0)
		{
			INode current = queue.poll();
			int currx = current.getX();
			int curry = current.getY();
			System.out.println("Searching node: (" + currx + ", " + curry + ")");
			ArrayList<INode> neighbors = getNeighbors(current);
			for(INode neigh : neighbors)
			{
				int x = neigh.getX();
				int y = neigh.getY();
				 
				if (neigh.getClass() == Food.class)//the food is right next to us
				{
					System.out.println("Found Food node: (" + x + ", " + y + ")"); 
					queue.add(neigh);
					System.out.println("----END BREADTH FIRST SEARCH-----");
					return;
				}
				if (!visited[x][y])
				{
					visited[x][y] = true;
					queue.add(neigh);
				}
			}
		}
	}
	
	//<summary>retrieves the neighbors for the current node</summary>
	//<param>node - the current node</param>
	//<returns>a list containing each neighbor not part of the snake</returns>
	public ArrayList<INode> getNeighbors(INode node)
	{
		ArrayList<INode> neighbors = new ArrayList<INode>();
		int posX = node.getX();
		int posY = node.getY();
		
		//3 neighbors each time find out where snake is to avoid
		int upper = posY + 1;
		int lower = posY - 1;
		int lefter = posX - 1;
		int righter = posX + 1;
		
		if (upper > gameBoard.length -1)
			upper = 0;
		else if (lower < 0)
			lower = gameBoard.length - 1;
		if (righter > gameBoard.length -1)
			righter = 0;
		else if (lefter < 0)
			lefter = gameBoard.length - 1;
		
		neighbors.add(gameBoard[posX][upper]);
		neighbors.add(gameBoard[posX][lower]);
		neighbors.add(gameBoard[righter][posY]);
		neighbors.add(gameBoard[lefter][posY]);
		
		for(Iterator<INode> iterator =  neighbors.iterator(); iterator.hasNext();)
		{
			INode neigh = iterator.next(); 
			if (neigh.getClass() == SnakePart.class)
			{
				iterator.remove();
			}
			else if(neigh.getClass() == Food.class)
			{
				return new ArrayList<INode>() { 
					{
						add(neigh);
					} 
				};
			}
		}
		return neighbors;
	}
}

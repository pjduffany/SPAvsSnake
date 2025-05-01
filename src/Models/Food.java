package Models;

public class Food implements INode{
	
	public int x;
	public int y;
	
	public Food(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	//<summary>returns the x value of the node</summary>
	public int getX()
	{
		return x;
	}
	//<summary>returns the y value of the node</summary>
	public int getY()
	{
		return y;
	}
}

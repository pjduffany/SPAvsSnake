package Models;

public class SnakePart implements INode{
	private int x;
	private int y;
	
	public SnakePart(int  x, int y)
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

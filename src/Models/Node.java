package Models;

public class Node implements INode{
	public int x;
	public int y;
	
	public Node(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	//<summary>returns the x value of the node</summary>
	public int getX() {	return x;}
	@Override
	//<summary>returns the y value of the node</summary>
	public int getY() {	return y; }
	
	

}

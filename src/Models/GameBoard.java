package Models;

import java.util.Random;

import SearchAlgorithms.BFS;
public class GameBoard {

    public INode tempGameBoard[][];
    public final int width;
    public final int height;
    private int score = 0;

    public Snake snake;
    public Food food;

    public Direction currentDirection = Direction.LEFT; 
    public boolean isDirectionChanged = false;
	
    int finalX;
	int finalY;

    // <summary> Construct the gameBoard object </summary>
    public GameBoard(int width, int height) 
    {
        this.width = width;
        this.height = height;
        tempGameBoard = new INode[width][height];
        
        
        initBoard();
        initSnake();
        createFood();
    
    }
    // <summary> Initializes the board with empty Nodes </summary>
    public void initBoard()
    {
    	for(int i = 0; i < width; i++)
    	{
    		for (int j = 0; j < height; j++)
    		{
    			tempGameBoard[i][j] = new Node(i, j);
    		}
    	}
    }
    // <summary> Creates a default Snake object. of size 3 </summary>
 	// <returns> Returns the new Snake object. </returns>
    private Snake initSnake() 
    {                                 
        snake = new Snake();
        for (int i = 0; i < 3; i++) 
        {
        	SnakePart part = new SnakePart( i + width / 2, height / 2);
            snake.grow(part);
            tempGameBoard[i + width / 2][height / 2] = part;
        }
        return snake;
    }

    // <summary> 
    //     Creates a randomly generated piece of food
    //	   and searches for the food using BFS.
    // </summary>
    public void createFood()
    {
        int x = new Random().nextInt(width);
   		int y = new Random().nextInt(height);
   		if (tempGameBoard[x][y].getClass() != SnakePart.class)
        {
   			food = new Food(x, y);
   			tempGameBoard[x][y] = food;
   			finalX = food.getX();
   			finalY = food.getY();
   			BFS bfs = new BFS(tempGameBoard, snake, food);
            bfs.Search(snake.getHead());
        }
   		else
   		{
   			createFood();
   		}
    }

    // <summary> Checks out game state </summary>
 	// <returns> Returns true/false whether or not the move is valid </returns>
    public boolean go() 
    {                                     
        if (isMoveValid(currentDirection)) 
        {
            SnakePart move = snake.move(currentDirection);
            int oldX = move.getX();
            int oldY = move.getY();
            tempGameBoard[oldX][oldY] = new Node(oldX, oldY);
            if (snake.isFood(food)) 
            {                             
                snake.grow(move);
                createFood();
                score++;
            }
            
            return true;
        } 
        else 
        	return false;
    }

    
    

	// <summary> Checks if the current direction is valid. </summary>
	// <param> direction - the current direction </param>
	// <returns> true or false depending if the move is valid </returns>
    private boolean isMoveValid(Direction direction) 
    {
        int headX = snake.getHead().getX();
        int headY = snake.getHead().getY();
        
        switch(direction)
        {
            case UP :
                headY = headY - 1;
                break;
            case RIGHT :
                headX = headX + 1;
                break;
            case DOWN :
                headY = headY + 1;
                break;
            case LEFT :
                headX = headX -1;
                break;
        }

        //wall collision
        boolean isWallCollision = false;
        if(headX < 0)
        {
        	headX = 19;
        	isWallCollision = true;
        }
        else if(headX > 19)
        {
        	headX = 0;
        	isWallCollision = true;
        }
        else if(headY < 0)
        {
        	headY = 19;
        	isWallCollision = true;
        }
        else if(headY > 19)
        {
        	headY = 0;
        	isWallCollision = true;
        }
        else if (isSnakeCollision(headX, headY))
        {
        	return false;
        }
        SnakePart newHead = new SnakePart(headX, headY);
        if(isWallCollision)
        {
            SnakePart oldTail = snake.setHead(newHead);
            int oldX = oldTail.getX();
            int oldY = oldTail.getY();
            tempGameBoard[oldX][oldY] = new Node(oldX, oldY);
            //check if the food is on the otherside
            if(snake.isFood(food))
            {
                createFood();
                snake.grow(oldTail);
                score++;
            }
            
        }
        tempGameBoard[headX][headY] = newHead;
        return true;
    }
    
	// <summary> Checks if the current coordinates would cause a collision is valid. </summary>
	// <param> int x and int y - the current coordinates of the head </param>
	// <returns> true if snakeCollision </returns>
    public boolean isSnakeCollision(int x, int y)
    {
    	return tempGameBoard[x][y].getClass() == SnakePart.class;
    }
    
	// <summary> changes the currentDirection of the snake </summary>
	// <param>newDirection - the new direction we are turning </param>
    public void changeDirection(Direction newDirection) {
            currentDirection = newDirection;
            isDirectionChanged = true;
    }
    //--BEGIN GETTERS--
    public Snake getSnake() { return snake; }
    public Food getFood() { return food; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getScore() { return score; }
    //--END GETTERS--
}


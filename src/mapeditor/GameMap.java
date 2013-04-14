package mapeditor;

import java.awt.Graphics;
import java.awt.Point;


public class GameMap
{
	public static enum MapType
	{
		ISO,
		SQUARE
	}
	
	private MapType type = MapType.SQUARE;
	
	private String name;
	private Tile[][] tiles;
	private Tileset tileset;
	private int width;
	private int height;
	private int tileWidth;
	private int tileHeight;
	private boolean drawGrid = true;
	
	//Map position
	private int x = 0;
	private int y = 0;
	
	
	public GameMap()
	{

	}
	
	
	public void setMapType(MapType type)
	{
		this.type = type;
	}
	
	public void newMap(int width, int height, int tileWidth, int tileHeight, MapType type)
	{
		int[] mapData = new int[width*height];
		for(int i =0; i<mapData.length; i++)
		{
			mapData[i] = -1;
		}
		
		loadMap(mapData, width, height, tileWidth, tileHeight, type);
	}
	
	public void loadMap(int[] mapData, int width, int height, int tileWidth, int tileHeight, MapType type)
	{
		//this.tileset = tileset;
		this.width = width;
		this.height = height;
		this.type = type;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		
		//create and initialize the tiles based on the map data
		this.tiles = new Tile[width][height]; 
		
		int tilePosX;
		int tilePosY;
		int mapDataIndex = 0;
		
		switch(this.type)
		{
		
			case SQUARE: 
				
				for(int y = 0; y<this.height; y++)
				{
					for(int x = 0; x<this.width; x++)
					{
						tilePosX = x * tileWidth;
						tilePosY = y * tileHeight;
						this.tiles[x][y] = new Tile(tilePosX, tilePosY);
						this.tiles[x][y].setType(mapData[mapDataIndex]);
						mapDataIndex++;
					}
				}
				break;
				
			case ISO:
				
				for(int y = 0; y<this.height; y++)
				{
					for(int x = 0; x<this.width; x++)
					{
						if(y % 2 != 0) //odd row (tiles are offset to the right by half the tile width)
						{
							tilePosX = x * tileWidth + tileWidth/2;
						}
						else //even row
						{
							tilePosX = x * tileWidth;
						}
						
						tilePosY = y * (tileHeight/2); 
						this.tiles[x][y] = new Tile(tilePosX, tilePosY);
						this.tiles[x][y].setType(mapData[mapDataIndex]);
						mapDataIndex++;
					}
				}
				break;
				
			default:
				break;
		}
	}
	
	public void draw(Graphics g, Camera2D camera)
	{

		int cameraX = camera.getX();
		int cameraY = camera.getY();
		int cameraWidth = camera.getWidth();
		int cameraHeight = camera.getHeight();
		
		int leftX = (int)(cameraX/this.tileWidth);
		int topY = (int)(cameraY/this.tileHeight);
		int rightX = (int)((cameraX+cameraWidth)/this.tileWidth);
		int bottomY = (int)((cameraY+cameraHeight)/this.tileHeight);
		
		
		if(this.type == MapType.ISO)
		{
			topY = (int)(cameraY/this.tileHeight*2);
			bottomY = (int)((cameraY+cameraHeight)/this.tileHeight*2);
		}
		
		
		//Adjust the boundaries incase the map is smaller than the camera
		if(this.width-1 < rightX)
		{
			rightX = this.width-1;
		}
		
		if(this.height-1 < bottomY)
		{
			bottomY = this.height-1;
		}
		
		//only draw the tiles that are within the camera bounds
		for (int y = topY; y <= bottomY; y++)
		{
		   for (int x = leftX; x <= rightX; x++)
		   {
			
			    //only draw the tiles if texture is not null
				if(this.tileset != null)
				{
					tiles[x][y].draw(g, tileset);
				}

			    if(drawGrid)
				{
					switch(this.type)
					{
					
						case SQUARE: 
							this.drawSquareGrid(g, x, y);
							break;
							
						case ISO:
							this.drawIsoGrid(g, x, y);
							break;
							
						default:
							break;
					}
				}
		   }
		}
	}

	public void drawIsoGrid(Graphics g, int x, int y)
	{
		int tileXPos = tiles[x][y].getX();
		int tileYPos = tiles[x][y].getY();
		int halfTileWidth = tileWidth/2;
		int halfTileHeight = tileHeight/2;
		
		g.drawLine(tileXPos, tileYPos + halfTileHeight, tileXPos + halfTileWidth, tileYPos);
		g.drawLine(tileXPos + halfTileWidth, tileYPos, tileXPos+tileWidth, tileYPos + halfTileHeight);
		g.drawLine(tileXPos+tileWidth, tileYPos + halfTileHeight, tileXPos+halfTileWidth, tileYPos+tileHeight);
		g.drawLine(tileXPos+halfTileWidth, tileYPos+tileHeight, tileXPos, tileYPos + halfTileHeight);
	}
	
	public void drawSquareGrid(Graphics g, int x, int y)
	{
		
	    int tileXPos = tiles[x][y].getX();
		int tileYPos = tiles[x][y].getY();

		g.drawRect(tileXPos, tileYPos, tileWidth, tileHeight);
	}
	

	
	public Point getTileFromClick(int x, int y)
	{
		if(this.type == MapType.ISO)
		{
				//adjust the input based on map offset and map scroll
				x -= this.x;
				y -= this.y;
				int halfTileWidth = tileWidth/2;
				int halfTileHeight = tileHeight/2;
				
				
				//find out which box the mouse click is in (square grid)
				int boxX = x/tileWidth;
				int boxY = y/tileHeight;
				int isoX = boxX;
				int isoY = boxY*2;

				//find out which quadrant of the box the mouse click falls in
				int quarterBoxX =  (x - tileWidth * boxX) / halfTileWidth;
				int quarterBoxY =  (y - tileHeight * boxY) / halfTileHeight;
				
				
				//set the quadrant
				int quadrant = 0;
				if(quarterBoxX == 1 && quarterBoxY == 0) //top right
					quadrant = 1;
				else if(quarterBoxX == 0 && quarterBoxY == 0) // top left
					quadrant = 2;
				else if(quarterBoxX == 0 && quarterBoxY == 1) //bottom left
					quadrant = 3;
				else if(quarterBoxX == 1 && quarterBoxY == 1) //bottom right
					quadrant = 4;
				
				//2 points to cut the quadrant in half diagonally
				//point 1
				int x1;
				int y1;
				
				//point 2
				int x2;
				int y2;
				
				//use the cross product of the 2 points and the point that was clicked to determine which side of the diagonal line the click was on.
				//This will determine which tile was clicked
				switch (quadrant) 
				{
					//TOP RIGHT QUADRANT 1
			    	case 1:
			    		 x1 = tileWidth * boxX + halfTileWidth;
						 y1 = tileHeight * boxY;
						 x2 = tileWidth * boxX + tileWidth;
						 y2 = tileHeight * boxY + halfTileHeight;
						
						 if(((x2 - x1)*(y - y1) - (y2 - y1)*(x - x1)) < 0)
						 {
							 isoY--;;
						 }
			             break;
			         
			        //TOP LEFT QUADRANT 2
			    	case 2:  
						 x1 = tileWidth * boxX;
						 y1 = tileHeight * boxY + halfTileHeight;
						 x2 = tileWidth * boxX + halfTileWidth;
						 y2 = tileHeight * boxY;
						
						 if(((x2 - x1)*(y - y1) - (y2 - y1)*(x - x1)) < 0)
						 {
							 isoX--;	
							 isoY--;
						 }
			             break;
			        
			        //BOTTOM LEFT QUADRANT 3
			    	case 3: 
			    		 x1 = tileWidth * boxX;
			    		 y1 = tileHeight * boxY + halfTileHeight;
			    		 x2 = tileWidth * boxX + halfTileWidth;
			    		 y2 = tileHeight * boxY + tileHeight;
				
			    		 if(((x2 - x1)*(y - y1) - (y2 - y1)*(x - x1)) > 0)
			    		 {
			    			 isoX--;
							 isoY++;
			    		 }
			            break;
			         
			        //BOTTOM RIGHT QUADRANT 4
			    	case 4: 
			    		 x1 = tileWidth * boxX + halfTileWidth;
						 y1 = tileHeight * boxY + tileHeight;
						 x2 = tileWidth * boxX + tileWidth;
						 y2 = tileHeight * boxY + halfTileHeight;
						
						 if(((x2 - x1)*(y - y1) - (y2 - y1)*(x - x1)) > 0)
						 {
							 isoY++;
						 }
			             break;
			             
			    	default: 
			    		//don't change anything
				}
				
				//make sure the tile coordinates correspond to a valid tile on the map
				if(isoX >= 0 && isoX < this.width && isoY >=0 && isoY < this.height)
				{
					return new Point(isoX, isoY);
				}
				//there is no tile on the map at these coordinates
				else
				{
					return null;
				}	
		}
		
		
		//FOR SQUARE MAPS
		else if(this.type == MapType.SQUARE)
		{
			//adjust the input based on map offset and map scroll
			x -= this.x;
			y -= this.y;
			
			//find out which box the mouse click is in (square grid)
			int boxX = x/tileWidth;
			int boxY = y/tileHeight;
			
			//make sure the tile coordinates correspond to a valid tile on the map
			if(boxX >= 0 && boxX < this.width && boxY >=0 && boxY < this.height)
			{
				
				return new Point(boxX, boxY);
			}
			//there is no tile on the map at these coordinates
			else
			{
				return null;
			}	
		}
		return null;	
	}
	
	public int getTileType(int x, int y)
	{
		return this.tiles[x][y].getType();
	}
	
	public void setTileType(int x, int y, int type)
	{
		this.tiles[x][y].setType(type);
	}
	
	public void setPostion(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Tile getTile(int x, int y)
	{
		return this.tiles[x][y];
	}
	
	public void setTileset(Tileset tex)
	{
		this.tileset = tex;
	}
	
	public Tileset getTileset()
	{
		return this.tileset;
	}
	
	public boolean drawGrid()
	{
		return this.drawGrid;
	}
	
	public void setDrawGrid(boolean drawGrid)
	{
		this.drawGrid = drawGrid;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public MapType getType()
	{
		return this.type;
	}
	
	public int getTileWidth()
	{
		return  this.tileWidth;
	}
	
	public int getTileHeight()
	{
		return this.tileHeight;
	}
}
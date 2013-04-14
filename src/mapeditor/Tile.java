package mapeditor;

import java.awt.Graphics;

public class Tile 
{
	//game world coordinates
	private int x;
	private int y;
	
	private int type;
	
	public Tile(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g, Tileset tileset)
	{
		tileset.draw(g, this.x, this.y, this.type);
		
		
	}
	
	public int getWidth(Tileset tileset)
	{
		return tileset.getTileWidth();
	} 
	
	public int getHeight(Tileset tileset)
	{
		return tileset.getTileHeight();
	}
	
	public void setType(int type)
	{		
		this.type = type;
	} 
	
	public int getType()
	{
		return this.type;
		
	}
	
	public int getX()
	{
		return this.x;
		
	}
	
	public int getY()
	{
		return this.y;
	}
	

}

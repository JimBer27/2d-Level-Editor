package mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


//Just some drawing code for the map editor. It gets its own class to avoid clutter.

public class DrawClass 
{
	private MapEditor mapEditor;
	private Graphics g;
	
	public void setGraphics(Graphics g)
	{
		this.g = g;
	}
	public DrawClass(MapEditor mapEditor)
	{
		this.mapEditor = mapEditor;
	}
	
	public void drawMap()
	{
		this.mapEditor.getCurrentMap().draw(g, mapEditor.getCamera());
	}
	
	public void drawTileset()
	{
		if(this.mapEditor.getCurrentTileset() == null)
			return;
		
		g.drawImage(this.mapEditor.getCurrentTileset(), 0, 0, Color.white, null);
		
		
		int textureWidth = this.mapEditor.getCurrentTileset().getWidth();
		int textureHeight = this.mapEditor.getCurrentTileset().getHeight();
		int cellWidth = this.mapEditor.getTileWidth();
		int cellHeight = this.mapEditor.getTileHeight();
		
		int tilesAcross = textureWidth/cellWidth;
		int tilesDown = textureHeight/cellHeight;
	
		g.setColor(Color.black);
	
		//vertical lines
		for (int i = 0; i < tilesAcross+1; i++) 
		{
			g.drawLine(i * cellWidth, 0, i * cellWidth, textureHeight);
		}
	
		// the horizontal lines
		for (int i = 0; i < tilesDown+1; i++) 
		{
			g.drawLine(0, i * cellHeight, textureWidth, i * cellHeight);
		}
	}
	
	public void drawGrid()
	{
		int mapWidth = mapEditor.getCurrentMap().getWidth();
		int mapHeight = mapEditor.getCurrentMap().getHeight();
		
		int tileXPos = 0;
		int tileYPos = 0;
		int tileWidth = mapEditor.getTileset().getTileWidth();
		int tileHeight = mapEditor.getTileset().getTileHeight();
		int halfTileWidth = tileWidth/2;
		int halfTileHeight = tileHeight/2;
		g.setColor(Color.magenta);
		for(int y = 0; y<mapHeight; y++)
		{
			for(int x = 0; x<mapWidth; x++)
			{
				tileXPos = x * tileWidth;
				tileYPos = y * tileHeight;
				g.drawLine(tileXPos, tileYPos + halfTileHeight, tileXPos + halfTileWidth, tileYPos);
				g.drawLine(tileXPos + halfTileWidth, tileYPos, tileXPos+tileWidth, tileYPos + halfTileHeight);
				g.drawLine(tileXPos+tileWidth, tileYPos + halfTileHeight, tileXPos+halfTileWidth, tileYPos+tileHeight);
				g.drawLine(tileXPos+halfTileWidth, tileYPos+tileHeight, tileXPos, tileYPos + halfTileHeight);
			}
		}
		
		
		
		
		
	}
	
	public void drawObjects()
	{
		
	}
	
	public void highlightMapCursor()
	{
		if(mapEditor.getCurrentMap() == null)
			return;
		
		if(this.mapEditor.getCurrentMap().getType() == GameMap.MapType.ISO)
		{
			
			Point p = mapEditor.getCurrentMap().getTileFromClick(mapEditor.getMouseX(), mapEditor.getMouseY());
			
			if(p == null)
				return;
			
			int tileXPos = mapEditor.getCurrentMap().getTile(p.x, p.y).getX();
			int tileYPos = mapEditor.getCurrentMap().getTile(p.x, p.y).getY();
			int tileWidth = mapEditor.getCurrentMap().getTileWidth();
			int tileHeight = mapEditor.getCurrentMap().getTileHeight();
			int halfTileWidth = tileWidth/2;
			int halfTileHeight = tileHeight/2;
			g.setColor(Color.GREEN);
			g.drawLine(tileXPos, tileYPos + halfTileHeight, tileXPos + halfTileWidth, tileYPos);
			g.drawLine(tileXPos + halfTileWidth, tileYPos, tileXPos+tileWidth, tileYPos + halfTileHeight);
			g.drawLine(tileXPos+tileWidth, tileYPos + halfTileHeight, tileXPos+halfTileWidth, tileYPos+tileHeight);
			g.drawLine(tileXPos+halfTileWidth, tileYPos+tileHeight, tileXPos, tileYPos + halfTileHeight);
		}
		else if(this.mapEditor.getCurrentMap().getType() == GameMap.MapType.SQUARE)
		{
			
			Point p = mapEditor.getCurrentMap().getTileFromClick(mapEditor.getMouseX(), mapEditor.getMouseY());
			
			if(p == null)
				return;
			
			int x = mapEditor.getCurrentMap().getTile(p.x, p.y).getX();
			int y = mapEditor.getCurrentMap().getTile(p.x, p.y).getY();
			int tileWidth = mapEditor.getCurrentMap().getTileWidth();
			int tileHeight = mapEditor.getCurrentMap().getTileHeight();
			
			//g.setColor(new Color(255, 0, 255, 100)); //purple
			g.setColor(new Color(0,255,0,100)); //green
			g.fillRect(x, y, tileWidth, tileHeight);
		}
	}
	
	public void highlightSelectedTile()
	{
		if(mapEditor.getCurrentMap() == null)
			return;
		
		Tileset tileset = mapEditor.getCurrentMap().getTileset();
		
		if(tileset == null)
			return;
		
		//int selectedTile = mapEditor.getSelectedTile();
		if(mapEditor.getSelectedTile() == -1)
			return;
		
		int numTilesAcross = tileset.getTextureWidth()/tileset.getTileWidth();
		int tileWidth = tileset.getTileWidth();
		int tileHeight = tileset.getTileHeight();
		
		//convert 1D to 2D
		int x = mapEditor.getSelectedTile() % numTilesAcross;
		int y = mapEditor.getSelectedTile() / numTilesAcross;
		
		//convert from grid coordinates to screen coordinates
		x *= tileWidth;
		y *= tileHeight;
		
		g.setColor(new Color(150, 60, 180, 90)); // 50% transparent red
		g.fillRect(x, y, tileWidth, tileHeight);
		//g.setColor(new Color(255, 0, 255, 100)); //purple
		//g.setColor(new Color(0,255,0,100)); //green
		//g.fillRect(x, y, tileWidth, tileHeight);
	}
	
	public void highlightSelectedObject()
	{
		
	}
}

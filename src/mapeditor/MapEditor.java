package mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.xml.sax.InputSource;

import mapeditor.GameMap.MapType;
import mapeditor.brushstates.Brush;


public class MapEditor 
{
	public enum Mode
	{
		TILEMODE,
		OBJECTMODE;
	}
	
	public enum Draw
	{
		MAP,
		TILESET,
		OBJECTSET,
		GRID,
		HIGHLIGHT_CURSOR,
		HIGHLIGHT_TILE,
		HIGHLIGHT_OBJECT;
	}
	
	private String currentName;
	private GameMap currentMap;
	private BufferedImage tilesetImage;
	private int tileWidth =32;
	private int tileHeight = 32;
	private Camera2D camera2D;
	private Mode currentMode = Mode.TILEMODE;
	private DrawClass drawClass;
	private int mouseX;
	private int mouseY;
	private Brush brush;
	private boolean gridOn;
	private int selectedTileType = -1;
	private int selectedObjectType = -1;
	
	public void selectObject()
	{
		
	}
	public MapEditor() 
	{
		camera2D = new Camera2D();
		drawClass = new DrawClass(this);
		brush = new Brush(this);
	}
	
	public void init()
	{
		
	}
	
	public int getSelectedObject(int x, int y)
	{
		return -1;
	}
	
	public void deselectObject()
	{
		this.selectedObjectType = -1;
	}



	public void createMap(int mapWidth, int mapHeight, int tileWidth, int tileHeight, String mapName, MapType type)
	{ 
		Tileset tileset = new Tileset(this.tilesetImage, tileWidth, tileHeight);
		this.currentMap = new GameMap();
		this.currentMap.newMap(mapWidth, mapHeight, tileWidth, tileHeight, type);
		this.currentMap.setTileset(tileset);
		this.currentName = mapName;
	}
	
	public void save()
	{
		if(this.currentMap == null)
		{
			System.out.println("MAP IS NULL");
			return;
		}
			
		//this.currentMap.saveMap();
	}
	
	public void openMap(String fileName)
	{
		XMLMapReader xml = new XMLMapReader();
		xml.parse(fileName);
		
		MapTemplate template = xml.getMapTemplate();
		this.currentMap = new GameMap();
		this.currentMap.loadMap(template.data, template.mapWidth, template.mapHeight, template.tileWidth, template.tileHeight, template.mapType);
		if(this.tilesetImage != null)
		{
			Tileset tileset = new Tileset(this.tilesetImage, template.tileWidth, template.tileHeight);
			this.currentMap.setTileset(tileset);
		}
		
		this.tileWidth = template.tileWidth;
		this.tileHeight = template.tileHeight;
		this.currentName = template.mapName;
	}
	
	public void clearMap()
	{
		
		//ObjectList list = this.mapEditor.getObjectList();
		
		for(int y = 0; y < currentMap.getHeight(); y++)
		{
			for(int x = 0; x < currentMap.getWidth(); x++)
			{
				Tile currentTile = currentMap.getTile(x, y);
				if(currentTile == null)
					return;
				currentTile.setType(-1);
				//list.removeObject(x, y);
			}
		}
	}
	
	public void brush(int x, int y)
	{
		brush.brushAction(x, y);
	}
	
	
	public void drawMap(Graphics g)
	{
		
		if(currentMap == null)
			return;
		
		
		//map.setGraphics(g);
		
		//currentMap.draw();
		
		
		
		//if(map.getGrid())
		//	map.drawGrid();
		
		
		
	//	Tile tile = map.getTileFromClick(mouseX, mouseY);
	//	if(tile != null)
		//{
			//map.highlight(tile, 0);
	//	}
	}
	
	
	

	
	public void setTileset(String tilesetName)
	{
		if(currentMap == null)
			return;
		Tileset tileset =  currentMap.getTileset();
		
		
//		ObjectTemplateSetManager objectTemplateSetManager = this.mapEditor.getObjectTemplateSetManager();
//		ObjectTemplateSet objectTemplateSet = objectTemplateSetManager.getObjectTemplateSet(tilesetName);
		//this.mapEditor.getObjectPaletteLogic().setObjectTemplateSet(objectTemplateSet);
		
		
		if(currentMap == null)
			return;
		
		
	//	map.setTileset(tileset, objectTemplateSet);
	}
	
	public void addTilesetToCurrentMap(String tilesetName)
	{
		if(currentMap == null)
			return;
		
//		TilesetManager tilesetManager = this.mapEditor.getTilesetManager();
//		ObjectTemplateSetManager objectTemplateSetManager = this.mapEditor.getObjectTemplateSetManager();
//		Tileset tileset = tilesetManager.getTileset(tilesetName);
//		ObjectTemplateSet objectTemplateSet = objectTemplateSetManager.getObjectTemplateSet(tilesetName);
//		
		//map.setTileset(tileset, objectTemplateSet);
	}
	
	private int select(int coordX, int coordY, int totalWidth, int totalHeight, int cellWidth, int cellHeight)
	{
		int index = -1;
		int numAcross = totalWidth/cellWidth;
		int numDown = totalHeight/cellHeight;
		int x = coordX / cellWidth;
		int y = coordY / cellHeight;
				
		//check bounds
		if(x < numAcross &&  y < numDown)
		{
			 index = numAcross*y+x;
		}
		
		return index;
	}
	
	public void selectTile(int x, int y)
	{
		if(currentMap == null)
			return;
		
		Tileset tileset = currentMap.getTileset();
		int selectedTile = -1;
		
		int textureWidth = tileset.getTextureWidth();
		int textureHeight = tileset.getTextureHeight();
		int tileWidth = tileset.getTileWidth();
		int tileHeight = tileset.getTileHeight();
		selectedTile = select(x, y, textureWidth, textureHeight, tileWidth, tileHeight);
		if( selectedTile >= tileset.getNumFrames())
		{
			selectedTile = -1;
		}
		
		this.selectedTileType = selectedTile;
		//mapEditor.setSelectedTile(selectedTile);
	}
	
	private void highlightSelectedTile(Graphics g)
	{
		Tileset tileset = currentMap.getTileset();
		//int selectedTile = mapEditor.getSelectedTile();
		if(selectedTileType == -1)
			return;
		
		int numTilesAcross = tileset.getTextureWidth()/tileset.getTileWidth();
		int tileWidth = tileset.getTileWidth();
		int tileHeight = tileset.getTileHeight();
		//convert 1D to 2D
		int x = selectedTileType % numTilesAcross;
		int y = selectedTileType / numTilesAcross;
		
		//convert from grid coordinates to screen coordinates
		x *= tileWidth;
		y *= tileHeight;
		
		g.setColor(new Color(150, 60, 180, 90)); // 50% transparent red
		g.fillRect(x, y, tileWidth, tileHeight);
	}
	
	
	
	public void drawTileset(Graphics g)
	{
		if(currentMap.getTileset() == null)
			return;
			
				
			//tileset.drawTileset(0, 0, 1);
			//DRAW GRID
			//this.mapEditor.drawGrid(g, tileset.getTextureWidth(), tileset.getTextureHeight(), tileset.getTileWidth(), tileset.getTileHeight());
			
			//if(m_selector.getSelectedTile() < 0)
			//return;
			
			//HIGHLIGHT SELECTEDTILE
			//highlightSelectedTile(g);
	}
	
	public Tileset getTileset()
	{
		return currentMap.getTileset();
	}
	
	public int getSelectedTile()
	{
		return this.selectedTileType;
	}
	
	public void deselectTile()
	{
		this.selectedTileType = -1;
	}
	
//	public int getCurrentSelectedObjectIndex()
//	{
//		return this.mapEditor.getSelectedObject();
//	}
	
	public void setGrid(boolean isOn)
	{
		
		if(currentMap == null)
			return;
		
		this.currentMap.setDrawGrid(isOn);
	}
	
	
	public GameMap getCurrentMap()
	{
		return this.currentMap;
	}

	public void setCurrentMode(Mode m)
	{
		currentMode = m;
	}
	
	public Mode getCurrentMode()
	{
		return currentMode;
	}
	public Brush getBrush() {
		// TODO Auto-generated method stub
		return this.brush;
	}
	public void selectObject(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public int getSelectedObjectType()
	{
		return this.selectedObjectType;
	}
	
	public void setDrawClass(DrawClass drawClass)
	{
		this.drawClass = drawClass;
	}
	
	public Camera2D getCamera()
	{
		return this.camera2D;
	}
	
	public boolean gridOn()
	{
		return this.gridOn;
	}
	
	public void setMouseX(int x)
	{
		this.mouseX = x;
	}
	
	public void setMouseY(int y)
	{
		this.mouseY = y;
	}
	
	public void setMouse(int x, int y)
	{
		this.mouseX = x;
		this.mouseY = y;
	}
	
	public DrawClass getDrawClass()
	{
		return this.drawClass;
	}
	
	public void draw(Draw draw)
	{
		switch (draw) 
		{
		  case MAP: 
			  	this.drawClass.drawMap();
			  	break;
			  	
		  case TILESET: 
			 	this.drawClass.drawTileset();
			 	break;
			 	
		  case GRID: 
			  	this.drawClass.drawGrid();
			  	break;
			  	
		  case OBJECTSET: 
			  	this.drawClass.drawObjects();
			  	break;
			  	
		  case HIGHLIGHT_CURSOR: 
			  	this.drawClass.highlightMapCursor();
			  	break;
			  	
		  case HIGHLIGHT_OBJECT: 
			    this.drawClass.highlightSelectedObject();
			    break;
			    
		 case HIGHLIGHT_TILE: 
			    this.drawClass.highlightSelectedTile();
			    break;
			    
		  default: 
		   
		}
	}
	
	public MapType getMapType()
	{
		return this.currentMap.getType();
	}
	
	public void setGraphics(Graphics g)
	{
		this.drawClass.setGraphics(g);
	}
	
	public int getMouseX()
	{
		return this.mouseX;
	}
	
	public int getMouseY()
	{
		return this.mouseY;
	}
	
	public void setCurrentTileset(BufferedImage image)
	{
		this.tilesetImage = image;
	}
	
	public BufferedImage getCurrentTileset()
	{
		return this.tilesetImage;
	}
	
	public int getTileWidth()
	{
		return this.tileWidth;
	}
	
	public int getTileHeight()
	{
		return this.tileHeight;
	}
	public void setTileWidth(int width)
	{
		this.tileWidth = width;
	}
	
	public void setTileHeight(int height)
	{
		this.tileHeight = height;
	}
	
	public void setCurrentName(String name)
	{
		this.currentName = name;
	}
	
	public String getCurrentName()
	{
		return this.currentName;
	}
	
	public void reset()
	{
		this.selectedObjectType = -1;
		this.selectedTileType = -1;
	}
}

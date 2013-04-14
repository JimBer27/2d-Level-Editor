package mapeditor.brushstates;

import java.awt.Point;

import mapeditor.GameMap;
import mapeditor.MapEditor;
import mapeditor.MapEditor.Mode;

public class DrawBrush extends AbstractBrush
{
	private MapEditor mapEditor;
	
	private void drawTile(int x, int y)
	{
		GameMap map = mapEditor.getCurrentMap();
		
		if(mapEditor.getSelectedTile() == -1)
			return;
		map.setTileType(x, y, mapEditor.getSelectedTile());
		
	}
	private void drawObject(int mouseX, int mouseY)
	{
		if(mapEditor.getSelectedObjectType() == -1)
			return;
		
		
		
		Point p = mapEditor.getCurrentMap().getTileFromClick(mouseX, mouseY);
		if(p == null)
			return;
		int x = p.x;
		int y = p.y;
		
		
//		ObjectList objectList = mapEditor.getObjectList();
//		if(objectList.getMapObject(x, y) != null)
//		{
//			objectList.removeObject(x, y);
//		}
//		objectList.addObject(mapEditor.getSelectedObject(), tile.getMapCoordinates().x, tile.getMapCoordinates().y);
		
	}
	public DrawBrush(MapEditor mapEditor)
	{
		super(mapEditor);
		this.mapEditor = mapEditor;
	}
	
	@Override
	public void brushAction(int x, int y) 
	{
		GameMap map = mapEditor.getCurrentMap();
		if(map == null)
			return;
		
		Mode mode = mapEditor.getCurrentMode();
		 switch (mode) 
	        {
	            case TILEMODE:  
	            		 drawTile(x,y);
	                     break;
	                     
	            case OBJECTMODE:  
	            		 drawObject(x,y);
	                     break;
	                     
	            default: 
	                     break;
	        }
		
		
	}

}

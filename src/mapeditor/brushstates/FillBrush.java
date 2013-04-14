package mapeditor.brushstates;

import mapeditor.GameMap;
import mapeditor.MapEditor;
import mapeditor.MapEditor.Mode;

public class FillBrush extends AbstractBrush
{
	private MapEditor mapEditor;
	private GameMap map;
	
	public FillBrush(MapEditor mapEditor) 
	{
		super(mapEditor);
		this.mapEditor = mapEditor;
	}

	@Override
	public void brushAction(int mouseX, int mouseY) 
	{
		this.map = mapEditor.getCurrentMap();
		if(map == null)
			return;
		
		Mode mode = mapEditor.getCurrentMode();
	    switch (mode) 
	    {
	        case TILEMODE:  
	       		 fillTiles(mouseX, mouseY);
                 break;
	                     
	        case OBJECTMODE:  
	            		 
	             break;
	                     
	        default: 
	             break;
	    }
	}
	
	public void fillTiles(int mouseX, int mouseY)
	{
		
		int currentTileType = this.mapEditor.getCurrentMap().getTileType(mouseX, mouseY);
		
		
		for(int y = 0; y < map.getHeight(); y++)
		{
			for(int x = 0; x < map.getWidth(); x++)
			{
				
				if(map.getTileType(x, y) == currentTileType)
				{
					map.setTileType(x, y, mapEditor.getSelectedTile());
				}
			}
		}
	}
}

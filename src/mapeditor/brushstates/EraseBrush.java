package mapeditor.brushstates;

import java.awt.Point;

import mapeditor.GameMap;
import mapeditor.MapEditor;
import mapeditor.MapEditor.Mode;

public class EraseBrush extends AbstractBrush
{
	private MapEditor mapEditor;
	public EraseBrush(MapEditor mapEditor) {
		super(mapEditor);
		this.mapEditor = mapEditor;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void brushAction(int x, int y) 
	{
		
		Mode mode = mapEditor.getCurrentMode();
        
        switch (mode) 
        {
            case TILEMODE:  
            		 eraseTile(x, y);
                     break;
                     
            case OBJECTMODE:  
            		 eraseObject(x, y);
                     break;
                     
            default: 
                     break;
        }
		
	}
	
	public void eraseTile(int x, int y)
	{
		GameMap map = mapEditor.getCurrentMap();
		
		map.setTileType(x, y, -1);
	}
	
	public void eraseObject(int mouseX, int mouseY)
	{
		GameMap map = mapEditor.getCurrentMap();
		
		Point p = mapEditor.getCurrentMap().getTileFromClick(mouseX, mouseY);
		if(p == null)
			return;
		int x = p.x;
		int y = p.y;
		
		
	}

	

}

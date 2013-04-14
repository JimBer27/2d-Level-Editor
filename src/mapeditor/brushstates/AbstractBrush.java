package mapeditor.brushstates;

import mapeditor.MapEditor;

public abstract class AbstractBrush 
{
	private MapEditor mapEditor;
	
	public AbstractBrush(MapEditor mapEditor)
	{
		this.mapEditor = mapEditor;
	}
	
	public abstract void brushAction(int x, int y);
}

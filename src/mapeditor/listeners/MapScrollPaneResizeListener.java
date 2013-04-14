package mapeditor.listeners;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import mapeditor.GameMap;
import mapeditor.MapEditor;
import mapeditor.views.MapEditorGUI;

public class MapScrollPaneResizeListener extends MapEditorBase implements ComponentListener
{
	public MapScrollPaneResizeListener(MapEditor mapEditor,
			MapEditorGUI mapEditorGUI) {
		super(mapEditor, mapEditorGUI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub			
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void componentResized(ComponentEvent e) 
	{
		 Component c = (Component)e.getSource();
     
	     Dimension newSize = c.getSize();
	     
	     GameMap map = mapEditor.getCurrentMap();
	     if(map != null)
	     {
	    	 mapEditor.getCamera().setWidth(newSize.width);
	    	 mapEditor.getCamera().setHeight(newSize.height);
          	 mapEditorGUI.getDrawPanel_map().repaint();
	     }
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
	}
}

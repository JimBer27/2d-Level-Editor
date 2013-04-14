package mapeditor.listeners;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollBar;

import mapeditor.Camera2D;
import mapeditor.GameMap;
import mapeditor.MapEditor;
import mapeditor.views.MapEditorGUI;

public class MapScrollBarListener extends MapEditorBase implements AdjustmentListener
{

	public MapScrollBarListener(MapEditor mapEditor, MapEditorGUI mapEditorGUI) {
		super(mapEditor, mapEditorGUI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) 
	{
		// TODO Auto-generated method stub
		int vPos = mapEditorGUI.getScrollPane_map().getVerticalScrollBar().getValue();
		int hPos = mapEditorGUI.getScrollPane_map().getHorizontalScrollBar().getValue();
				            
		GameMap map = mapEditor.getCurrentMap();
	    if(map == null)
	    	return;
			
	    Camera2D camera = mapEditor.getCamera();
	    
	    camera.setX(hPos);
	    camera.setY(vPos);
			
		mapEditorGUI.getDrawPanel_map().repaint();	
		
	}
}

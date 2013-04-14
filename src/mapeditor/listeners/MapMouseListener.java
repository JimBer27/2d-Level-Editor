package mapeditor.listeners;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import mapeditor.MapEditor;
import mapeditor.views.MapEditorGUI;

public class MapMouseListener extends MapEditorBase implements MouseInputListener
{
	public MapMouseListener(MapEditor mapEditor, MapEditorGUI mapEditorGUI) {
		super(mapEditor, mapEditorGUI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		 //left click
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			
			if(mapEditor.getCurrentMap() == null)
				return;
			
			Point p = mapEditor.getCurrentMap().getTileFromClick(e.getX(), e.getY());
			
			if(p == null)
				return;
			mapEditor.brush(p.x, p.y);
			mapEditorGUI.getDrawPanel_map().repaint();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		//left click
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			if(mapEditor.getCurrentMap() == null)
				return;
			Point p = mapEditor.getCurrentMap().getTileFromClick(e.getX(), e.getY());
			if(p == null)
				return;
			mapEditor.brush(p.x, p.y);
			mapEditorGUI.getDrawPanel_map().repaint();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		mapEditor.setMouse(e.getX(), e.getY());
		if(mapEditor.getCurrentMap() == null)
			return;
		Point p = mapEditor.getCurrentMap().getTileFromClick(e.getX(), e.getY());
		if(p == null)
			return;
		mapEditor.brush(p.x, p.y);
		mapEditorGUI.getDrawPanel_map().repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		mapEditor.setMouse(e.getX(), e.getY());
		mapEditorGUI.getDrawPanel_map().repaint();
	}
}

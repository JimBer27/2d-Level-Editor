package mapeditor.listeners;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import mapeditor.MapEditor;
import mapeditor.views.MapEditorGUI;

public class TilesetMouseListener extends MapEditorBase implements MouseListener {

	public TilesetMouseListener(MapEditor mapEditor, MapEditorGUI mapEditorGUI) {
		super(mapEditor, mapEditorGUI);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		//left click
		if((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) 
		{
			mapEditor.selectTile(e.getX(), e.getY());
			int selectedTile = mapEditor.getSelectedTile();
			if(selectedTile == -1)
			{
				mapEditorGUI.getLabel_selectedTile().setText("Selected Tile: None");
			}
			else
			{
				mapEditorGUI.getLabel_selectedTile().setText("Selected Tile: " + mapEditor.getSelectedTile());
			}
			mapEditorGUI.getDrawPanel_tileset().repaint();
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

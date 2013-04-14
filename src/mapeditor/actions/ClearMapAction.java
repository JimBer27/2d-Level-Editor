package mapeditor.actions;

import mapeditor.MapEditor;
import mapeditor.views.MapEditorGUI;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;


public class ClearMapAction extends AbstractAction
{
	private MapEditor mapEditor;
	private MapEditorGUI mapDisplayGUI;
	
	public ClearMapAction(String text, ImageIcon icon, MapEditor mapEditor, MapEditorGUI mapEditorGUI) 
	{
		super(text, icon);
		this.mapEditor = mapEditor;
		this.mapDisplayGUI = mapEditorGUI;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(mapEditor.getCurrentMap() == null)
			return;
		mapEditor.clearMap();
		mapDisplayGUI.getDrawPanel_map().repaint();
	}
}

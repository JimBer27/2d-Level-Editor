package mapeditor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import mapeditor.MapEditor;
import mapeditor.views.MapEditorGUI;

public class CheckBoxListener extends MapEditorBase implements ActionListener
{
	public CheckBoxListener(MapEditor mapEditor, MapEditorGUI mapEditorGUI) {
		super(mapEditor, mapEditorGUI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
        if(e.getSource() == mapEditorGUI.getCheckBox_mapGrid())
        {
        	JCheckBox cb = (JCheckBox)e.getSource();
        	// Determine status
            boolean selected = cb.isSelected();
            
            if (selected) 
            {
            	mapEditor.setGrid(selected);
            	
            	mapEditorGUI.getDrawPanel_map().repaint();
            } 
            else 
            {
            	mapEditor.setGrid(selected);
            	mapEditorGUI.getDrawPanel_map().repaint();
            }
        }

        
		
	}
}

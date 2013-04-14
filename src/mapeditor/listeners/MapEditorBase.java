package mapeditor.listeners;

import mapeditor.MapEditor;
import mapeditor.views.MapEditorGUI;

//base class extend it when you want to use MapEditor and MapEditorGUI as properties
//I made this class mainly to avoid copy pasting since most all listener classes use these properties
public class MapEditorBase 
{
	protected final MapEditor mapEditor;
	protected final MapEditorGUI mapEditorGUI;
	
	public MapEditorBase(MapEditor mapEditor, MapEditorGUI mapEditorGUI)
	{
		this.mapEditor = mapEditor;
		this.mapEditorGUI = mapEditorGUI;
	}
	
}

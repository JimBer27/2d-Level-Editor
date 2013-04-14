package mapeditor.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import mapeditor.GameMap;
import mapeditor.MapEditor;
import mapeditor.XMLWriter;
import mapeditor.views.MapEditorGUI;

public class SaveAction extends AbstractAction
{
	private MapEditor mapEditor;
	private MapEditorGUI mapEditorGUI;
	
	public SaveAction(String text, ImageIcon icon, MapEditor mapEditor, MapEditorGUI mapEditorGUI)
	{
		super(text,icon);
		this.mapEditor = mapEditor;
		this.mapEditorGUI = mapEditorGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(this.mapEditor.getCurrentMap() == null)
			return;
		
		String mapName = mapEditor.getCurrentName();
		String tileHeight = Integer.toString(mapEditor.getTileHeight());
		String tileWidth = Integer.toString(mapEditor.getTileWidth());
		String mapWidth = Integer.toString(mapEditor.getCurrentMap().getWidth());
		String mapHeight = Integer.toString(mapEditor.getCurrentMap().getHeight());
		String mapType = mapEditor.getMapType().toString();
		
		XMLWriter xml = new XMLWriter();
		xml.addElement("", "Map");
		
		//map name
		xml.addElement("Map", "MapName");
		xml.addData("MapName",  mapName);
		
		//map type
		xml.addElement("Map", "MapType");
		xml.addData("MapType",  mapType);
		
		//map width
		xml.addElement("Map", "MapWidth");
		xml.addData("MapWidth",  mapWidth);
		
		//map height
		xml.addElement("Map", "MapHeight");
		xml.addData("MapHeight",  mapHeight);
		
		//tile width
		xml.addElement("Map", "TileWidth");
		xml.addData("TileWidth",  tileWidth);
		
		//tile height
		xml.addElement("Map", "TileHeight");
		xml.addData("TileHeight",  tileHeight);
		
		StringBuilder sb = new StringBuilder();
		
		GameMap map = mapEditor.getCurrentMap();
		for(int y = 0; y<map.getHeight(); y++)
		{
			for(int x = 0; x<map.getWidth(); x++)
			{
				sb.append(map.getTileType(x, y));
				sb.append(",");
			} 
		}
		
		//map data
		xml.addElement("Map", "Data");
		xml.addData("Data", sb.toString());
		
		
		System.out.println(sb);
		
		xml.outputXML(mapName + ".xml");
		
	}
}

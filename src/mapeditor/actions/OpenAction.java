package mapeditor.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import mapeditor.GameMap;
import mapeditor.MapEditor;
import mapeditor.views.MapEditorGUI;

public class OpenAction extends AbstractAction
{
	private MapEditor mapEditor;
	private MapEditorGUI mapEditorGUI;
	
	public OpenAction(String text, ImageIcon icon, MapEditor mapEditor, MapEditorGUI mapEditorGUI)
	{
		super(text,icon);
		this.mapEditor = mapEditor;
		this.mapEditorGUI = mapEditorGUI;
	}
/////////////////////////////////////////////////////////////
//Method: openMap PUBLIC
//open the filechooser to load a previously made map
/////////////////////////////////////////////////////////////
public void openMap()
{
	JFileChooser chooser = new JFileChooser("Maps/");
	int option = chooser.showOpenDialog(mapEditorGUI.getFrame());
	if(option == JFileChooser.APPROVE_OPTION) 
	{
		File theFile = chooser.getSelectedFile();
		if(theFile.exists())
		{
			mapEditor.openMap(theFile.getPath());
			
			GameMap map = mapEditor.getCurrentMap();
			int tileWidth = map.getTileWidth();
			int tileHeight = map.getTileHeight();
			
			 //resize
			Dimension newSize = mapEditorGUI.getScrollPane_map().getSize();
		    mapEditor.getCamera().setWidth(newSize.width);
			mapEditor.getCamera().setHeight(newSize.height);
			  
			mapEditorGUI.getDrawPanel_map().setPreferredSize(new Dimension(map.getWidth() * map.getTileWidth() + map.getTileWidth()/2, map.getHeight() * map.getTileHeight() +  map.getTileHeight()));
			mapEditorGUI.getDrawPanel_map().revalidate();
			  
//			int textureWidth = mapEditor.getCurrentTileset().getWidth();
//			int textureHeight = mapEditor.getCurrentTileset().getHeight(); 
//			int numTiles = mapEditor.getCurrentTileset().getWidth() / mapEditor.getTileWidth() * mapEditor.getCurrentTileset().getHeight() / mapEditor.getTileHeight();
			  
		    //set label text
			mapEditorGUI.getLabel_mapName().setText(mapEditor.getCurrentName());
			mapEditorGUI.getLabel_mapNumTiles().setText( "Number of Tiles: "+map.getWidth() * map.getHeight());
			mapEditorGUI.getLabel_mapSize().setText("Dimensions: "+map.getWidth()+"x"+map.getHeight());
			//mapEditorGUI.getLabel_tilesetNumTiles().setText("Number of Tiles: "+ numTiles);
			mapEditorGUI.getLabel_tileSize().setText("Tile Size: "+ tileWidth + "x" + tileHeight);
		  //  mapEditorGUI.getLabel_textureSize().setText("Tileset Size: "+ textureWidth + "x" + textureHeight);
		      
		    //redraw
		    mapEditorGUI.getDrawPanel_map().repaint();
			mapEditorGUI.getDrawPanel_tileset().repaint();
		    mapEditorGUI.getDrawPanel_objects().repaint();
	}
  }
}
@Override
public void actionPerformed(ActionEvent e) 
{
	// TODO Auto-generated method stub
	openMap();
}
}

package mapeditor.actions;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import mapeditor.MapEditor;
import mapeditor.Tileset;
import mapeditor.views.MapEditorGUI;

public class OpenTilesetAction extends AbstractAction
{

	private MapEditor mapEditor;
	private MapEditorGUI mapEditorGUI;
	
	public OpenTilesetAction(String text, ImageIcon icon, MapEditor mapEditor, MapEditorGUI mapEditorGUI)
	{
		super(text,icon);
		this.mapEditor = mapEditor;
		this.mapEditorGUI = mapEditorGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
			JFileChooser chooser = new JFileChooser("Tilesets/");
			int option = chooser.showOpenDialog(mapEditorGUI.getFrame());
			if(option == JFileChooser.APPROVE_OPTION) 
			{
				File theFile = chooser.getSelectedFile();
				if(theFile.exists())
				{
					BufferedImage img = null;
					try 
					{
					    img = ImageIO.read(theFile);
					    mapEditor.setCurrentTileset(img);
					    
				    	int tileWidth = mapEditor.getTileWidth();
					    int tileHeight = mapEditor.getTileHeight();
					    int textureWidth = img.getWidth();
					    int textureHeight = img.getHeight();
					    int numTiles = textureWidth / tileWidth * textureHeight / tileHeight;
					    
					    //If there is already a map, switch its tileset with the new one
					    if(mapEditor.getCurrentMap() != null)
					    	mapEditor.getCurrentMap().setTileset(new Tileset(img, tileWidth, tileHeight));
					    
					    
						//change text on labels
					    mapEditorGUI.getLabel_tilesetNumTiles().setText("Number of Tiles: "+ numTiles);
					    mapEditorGUI.getLabel_tileSize().setText("Tile Size: "+ tileWidth + "x" + tileHeight);
						mapEditorGUI.getLabel_textureSize().setText("Tileset Size: "+ textureWidth + "x" + textureHeight);
						
						
						//redraw
						mapEditorGUI.getDrawPanel_map().repaint();
				    	mapEditorGUI.getDrawPanel_tileset().repaint();
				    	mapEditorGUI.getDrawPanel_objects().repaint();
				    	
					}
					catch (IOException err) 
					{
						
					}
				}
			}
		}
}


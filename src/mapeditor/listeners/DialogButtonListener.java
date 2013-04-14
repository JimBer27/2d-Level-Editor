package mapeditor.listeners;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import mapeditor.GameMap;
import mapeditor.GameMap.MapType;
import mapeditor.MapEditor;
import mapeditor.views.MapEditorGUI;
import mapeditor.views.NewMapDialog;

//TODO:: clean up this class



public class DialogButtonListener extends MapEditorBase implements ActionListener
{
	private NewMapDialog newMapDialog;
	
	public DialogButtonListener(MapEditor mapEditor, MapEditorGUI mapEditorGUI, NewMapDialog newMapDialog) {
		super(mapEditor, mapEditorGUI);
		// TODO Auto-generated constructor stub
		this.newMapDialog = newMapDialog;
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//OK BUTTON
		if(e.getSource() == newMapDialog.getButton_ok())
		{
			try
			{
				//VERIFY INPUT
				int mapWidth = Integer.parseInt(newMapDialog.getTextField_mapWidth().getText());
				int mapHeight = Integer.parseInt(newMapDialog.getTextField_mapHeight().getText());
				int tileWidth = Integer.parseInt(newMapDialog.getTextField_tileWidth().getText());
				int tileHeight = Integer.parseInt(newMapDialog.getTextField_tileHeight().getText());
				String mapName = newMapDialog.getTextField_mapName().getText();
				
				//make sure the number the user supplied is not 0 or negative
				if(mapWidth <=0)
				{
					newMapDialog.getTextField_mapWidth().setText("");
					return;
				}
				if(mapHeight <= 0) 
			    {
					newMapDialog.getTextField_mapHeight().setText("");
			    	return;
			    }
				if(tileWidth <= 0)
				{
					newMapDialog.getTextField_tileWidth().setText("");
			    	return;
				}
				if(tileHeight <=0)
				{
					newMapDialog.getTextField_tileHeight().setText("");
					return;
				}
				    	
				 if(mapName == null || mapName.equals(""))
				 {
				    return;
				 }
				 
				 
				//Check to see if a tileset image was chosen
				if(mapEditor.getCurrentTileset() == null)
					return;
				 
				
				 MapType type = GameMap.MapType.SQUARE;
				 
				 if(this.newMapDialog.getRadio_square().isSelected())
				 {
					 type = GameMap.MapType.SQUARE;
				 }
				 else if(this.newMapDialog.getRadio_iso().isSelected())
				 {
					 type = GameMap.MapType.ISO;
				 }
				 
				mapEditor.setTileWidth(tileWidth);
				mapEditor.setTileHeight(tileHeight);
				mapEditor.setCurrentName(mapName);
				
				
				
				 //THE MAP WILL ONLY CREATE IF ALL INPUT WAS SUPPLIED CORRECTLY
			     mapEditor.createMap(mapWidth, mapHeight, tileWidth, tileHeight, mapName,type);
				  
				 GameMap map = mapEditor.getCurrentMap();

				  //resize
				  Dimension newSize = mapEditorGUI.getScrollPane_map().getSize();
				  mapEditor.getCamera().setWidth(newSize.width);
				  mapEditor.getCamera().setHeight(newSize.height);
				  
				  mapEditorGUI.getDrawPanel_map().setPreferredSize(new Dimension(map.getWidth() * map.getTileset().getTileWidth() + map.getTileset().getTileWidth()/2, map.getHeight() * map.getTileset().getTileHeight() +  map.getTileset().getTileHeight()));
				  mapEditorGUI.getDrawPanel_map().revalidate();
				  
				  int textureWidth = mapEditor.getCurrentTileset().getWidth();
				  int textureHeight = mapEditor.getCurrentTileset().getHeight(); 
				  int numTiles = mapEditor.getCurrentTileset().getWidth() / mapEditor.getTileWidth() * mapEditor.getCurrentTileset().getHeight() / mapEditor.getTileHeight();
				  
				  //set label text
				  mapEditorGUI.getLabel_mapName().setText(mapEditor.getCurrentName());
				  mapEditorGUI.getLabel_mapNumTiles().setText( "Number of Tiles: "+map.getWidth() * map.getHeight());
				  mapEditorGUI.getLabel_mapSize().setText("Dimensions: "+map.getWidth()+"x"+map.getHeight());
				  mapEditorGUI.getLabel_tilesetNumTiles().setText("Number of Tiles: "+ numTiles);
				  mapEditorGUI.getLabel_tileSize().setText("Tile Size: "+ tileWidth + "x" + tileHeight);
			      mapEditorGUI.getLabel_textureSize().setText("Tileset Size: "+ textureWidth + "x" + textureHeight);
			      
			      //redraw
			      mapEditorGUI.getDrawPanel_map().repaint();
				  mapEditorGUI.getDrawPanel_tileset().repaint();
				  mapEditorGUI.getDrawPanel_objects().repaint();
				  
			      resetInput();
			      this.newMapDialog.getDialog().dispose();
			}
			
			catch (NumberFormatException ex)
			{
				System.out.println(ex.getMessage());
				return;
			}	
		}
		else if(e.getSource() == newMapDialog.getButton_cancel())
		{
			  resetInput();
			  this.newMapDialog.getDialog().dispose();
		}
		else if(e.getSource() == newMapDialog.getButton_openTileset())
		{
			JFileChooser chooser = new JFileChooser("Tilesets/");
			int option = chooser.showOpenDialog(newMapDialog.getDialog());
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
					}
					catch (IOException err) 
					{
						
					}
				}
			}
		}
	}
	
	private void resetInput()
	{
		//reset input components
		  this.newMapDialog.getTextField_mapName().setText("");
		  this.newMapDialog.getTextField_mapWidth().setText("");
		  this.newMapDialog.getTextField_mapHeight().setText("");
		  this.newMapDialog.getTextField_tileWidth().setText("");
		  this.newMapDialog.getTextField_tileHeight().setText("");
			
		  
	}
}


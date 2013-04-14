package mapeditor.DrawPanels;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import mapeditor.MapEditor;

public class TilesetDrawPanel extends JPanel
{
	private MapEditor mapEditor;
	private final int WIDTH = 512;
	private final int HEIGHT = 512;
	
	public TilesetDrawPanel(MapEditor mapEditor)
	{
		this.mapEditor = mapEditor;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	//DO CUSTOM DRAWING
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		mapEditor.setGraphics(g);
		mapEditor.draw(MapEditor.Draw.TILESET);
		mapEditor.draw(MapEditor.Draw.HIGHLIGHT_TILE);
	}
}

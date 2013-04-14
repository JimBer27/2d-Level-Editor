package mapeditor.DrawPanels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import mapeditor.MapEditor;


public class MapDrawPanel extends JPanel
{
	private MapEditor mapEditor;
	private final int WIDTH = 512;
	private final int HEIGHT = 512;
	
	
	public MapDrawPanel(MapEditor editor)
	{
		this.mapEditor = editor;
		///this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(Color.black);
		this.setAutoscrolls(true);
		
	}
	
	//DO CUSTOM DRAWING
	public void paintComponent(Graphics g)
	{
			super.paintComponent(g);
			if(mapEditor.getCurrentMap() == null)
				return;
			g.setColor(Color.magenta);
			mapEditor.setGraphics(g);
			mapEditor.draw(MapEditor.Draw.MAP);
			mapEditor.draw(MapEditor.Draw.HIGHLIGHT_CURSOR);
	}
	
}
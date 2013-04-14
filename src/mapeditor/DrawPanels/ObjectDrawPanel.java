package mapeditor.DrawPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


import javax.swing.JPanel;

import mapeditor.MapEditor;

public class ObjectDrawPanel extends JPanel
{
	private MapEditor mapEditor;
	private final int WIDTH = 256;
	private final int HEIGHT = 256;
	
	public ObjectDrawPanel(MapEditor mapEditor) 
	{
		this.mapEditor = mapEditor;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.WHITE);
	}
		
	//DO CUSTOM DRAWING HERE
	public void paintComponent(Graphics g) 
	{	
		
		super.paintComponent(g);
		
		
	}	
}

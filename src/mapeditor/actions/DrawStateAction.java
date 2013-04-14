package mapeditor.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import mapeditor.brushstates.Brush;

public class DrawStateAction extends AbstractAction
{
	private Brush brush;
	
	public DrawStateAction(String text, ImageIcon icon, Brush brush) 
	{
		super(text, icon);
		this.brush = brush;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//sets the brush to draw state
		brush.draw();
	}
}
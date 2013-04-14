package mapeditor.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import mapeditor.brushstates.Brush;

public class EraseStateAction extends AbstractAction
{
	private Brush brush;
	
	public EraseStateAction(String text, ImageIcon icon, Brush brush) 
	{
		super(text, icon);
		this.brush = brush;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//sets the brush to erase state
		brush.erase();
	}
}

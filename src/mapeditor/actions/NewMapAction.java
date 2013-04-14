package mapeditor.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class NewMapAction extends AbstractAction
{
	private JDialog dialog;
	
	public NewMapAction(String text, ImageIcon icon, JDialog dialog) 
	{
		super(text, icon);
		this.dialog = dialog;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//opens the dialog box for creating a new map (check class DialogButtonListener)
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

}

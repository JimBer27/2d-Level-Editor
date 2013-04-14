package mapeditor.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//gui for the dialog box that is used to create a new map

public class NewMapDialog
{
	private JDialog dialog;
	private JTextField textField_mapName;
	private JTextField textField_mapWidth;
	private JTextField textField_mapHeight;
	private JButton button_ok;
	private JButton button_cancel;
	private JRadioButton radio_iso;
	private JRadioButton radio_square;
	private JButton button_openTileset;
	private JTextField textField_tileWidth;
	private JTextField textField_tileHeight;
	
	
	public NewMapDialog() 
	{
		dialog = new JDialog();
		dialog.setTitle("Create New Map");
		dialog.setResizable(false);
		dialog.setName("Create a new map");
		dialog.setModal(true);
		dialog.getContentPane().setBounds(new Rectangle(0, 0, 200, 200));
		dialog.setBounds(100, 100, 450, 400);
		dialog.getContentPane().setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label_mapName = new JLabel("Map Name:");
		label_mapName.setBounds(23, 29, 110, 20);
		panel.add(label_mapName);
		
		textField_mapName = new JTextField();
		textField_mapName.setBounds(154, 29, 241, 20);
		textField_mapName.setMinimumSize(new Dimension(6, 10));
		textField_mapName.setPreferredSize(new Dimension(6, 10));
		panel.add(textField_mapName);
		textField_mapName.setColumns(20);
		
		JLabel label_mapWidth = new JLabel("Map Width (In Tiles):");
		label_mapWidth.setBounds(23, 60, 121, 20);
		panel.add(label_mapWidth);
		
		textField_mapWidth = new JTextField();
		textField_mapWidth.setBounds(154, 60, 40, 20);
		panel.add(textField_mapWidth);
		textField_mapWidth.setColumns(10);
		
		JLabel label_mapHeight = new JLabel("Map Height (In Tiles):");
		label_mapHeight.setBounds(23, 91, 121, 20);
		panel.add(label_mapHeight);
		
		textField_mapHeight = new JTextField();
		textField_mapHeight.setBounds(154, 91, 40, 20);
		panel.add(textField_mapHeight);
		textField_mapHeight.setColumns(10);
		
		JLabel label_tileWidth = new JLabel("Tile Width:");
		label_tileWidth.setBounds(23, 122, 121, 20);
		panel.add(label_tileWidth);
		
		textField_tileWidth = new JTextField();
		textField_tileWidth.setBounds(154, 122, 40, 20);
		panel.add(textField_tileWidth);
		textField_tileWidth.setColumns(10);
		
		JLabel label_tileHeight = new JLabel("Tile Height:");
		label_tileHeight.setBounds(23, 152, 121, 20);
		panel.add(label_tileHeight);
		
		textField_tileHeight = new JTextField();
		textField_tileHeight.setBounds(154, 152, 40, 20);
		panel.add(textField_tileHeight);
		textField_tileHeight.setColumns(10);
		
		JLabel label_tileset = new JLabel("Tileset:");
		label_tileset.setBounds(23, 202, 121, 20);
		panel.add(label_tileset);
		
		button_openTileset = new JButton("Choose Tileset...");
		button_openTileset.setBounds(154, 202, 150, 20);
		panel.add(button_openTileset);
		
		//JFileChooser chooser = new JFileChooser("Maps/");
		//int option = chooser.showOpenDialog(this.dialog);
		
		JLabel label_mapType = new JLabel("Map Type:");
		label_mapType.setBounds(23, 232, 121, 20);
		panel.add(label_mapType);
		
		ButtonGroup buttonGroup_radio = new ButtonGroup();
		radio_square = new JRadioButton("Square");
		buttonGroup_radio.add(radio_square);
		radio_square.setBounds(130, 232, 100, 20);
		panel.add(radio_square);
		
		radio_iso = new JRadioButton("Isometric");
		buttonGroup_radio.add(radio_iso);
		panel.add(radio_iso);
		radio_iso.setBounds(130, 252, 100, 20);
		radio_square.setSelected(true);
		
		
		
		JPanel panel_buttonArea = new JPanel();
		panel_buttonArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
		dialog.getContentPane().add(panel_buttonArea, BorderLayout.SOUTH);
		
		button_ok = new JButton("Create Map");
		panel_buttonArea.add(button_ok);
		dialog.getRootPane().setDefaultButton(button_ok);
		
		button_cancel = new JButton("Cancel");
		panel_buttonArea.add(button_cancel);
		
	}



	public JButton getButton_openTileset() {
		return button_openTileset;
	}



	public JTextField getTextField_tileWidth() {
		return textField_tileWidth;
	}



	public JTextField getTextField_tileHeight() {
		return textField_tileHeight;
	}



	public JRadioButton getRadio_iso() {
		return radio_iso;
	}



	public JRadioButton getRadio_square() {
		return radio_square;
	}



	public JDialog getDialog()
	{
		return this.dialog;
	}

	public JTextField getTextField_mapName() {
		return textField_mapName;
	}


	public JTextField getTextField_mapWidth() {
		return textField_mapWidth;
	}


	public JTextField getTextField_mapHeight() {
		return textField_mapHeight;
	}


	public JButton getButton_ok() {
		return button_ok;
	}


	public JButton getButton_cancel() {
		return button_cancel;
	}
	
	public void setText_mapHeight(String s)
	{
		this.textField_mapHeight.setText(s);
	}
	
	public void setText_mapWidth(String s)
	{
		this.textField_mapWidth.setText(s);
	}
	
}


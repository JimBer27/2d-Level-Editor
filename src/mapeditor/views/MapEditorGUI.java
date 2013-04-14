package mapeditor.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

import mapeditor.DrawPanels.MapDrawPanel;

//this is the gui for the map editor...duh
//no listeners or logic in here...just use the getters to get whichever component you want and add an action to it that way.

public class MapEditorGUI 
{
		private JDialog newMapDialog;
		private JTabbedPane tab;
		private JFrame frame;
		private JSplitPane splitPane;
		private JPanel panel_map;
		private JPanel panel_underMap;
		private JPanel panel_mapData;
		private JScrollPane scrollPane_map;
		private JButton button_draw;
		private JButton button_erase;
		private JButton button_fill;
		private JLabel label_mapSize;
		private JLabel label_mapNumTiles;
		private JLabel label_mapName;
		private JCheckBox checkBox_mapGrid;
		private JPanel drawPanel_map;
		private JPanel panel_tileset;
		private JPanel drawPanel_tileset;
		private JLabel label_tileSize;
		private JLabel label_textureSize;
		private JLabel label_tilesetNumTiles;
		private JLabel label_selectedTile;
		private JPanel panel_tilesetData;
		private JPanel panel_underTileset;
		private JScrollPane scrollPane_tileset;
		private JPanel panel_objects;
		private JPanel drawPanel_objects;
		private JScrollPane scrollPane_objects;
		private JPanel panel_underObjects;
		private JMenuBar menuBar;
		private JMenuItem menuItem_newMap;
		private JMenuItem menuItem_openMap;
		private JMenuItem menuItem_save;
		private JMenu menu_edit;
		private JMenuItem menuItem_clear;
		private JButton button_chooseTileset;
		
		
		public MapEditorGUI() 
		
		{
			//CREATE THE MAIN FRAME
			frame = new JFrame("Map Editor");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
			splitPane = new JSplitPane();
			splitPane.setAlignmentY(Component.CENTER_ALIGNMENT);
			splitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
			frame.getContentPane().add(splitPane, BorderLayout.CENTER);
			splitPane.setDividerLocation(400);
			
			tab = new JTabbedPane(JTabbedPane.TOP);
			tab.setAlignmentX(Component.RIGHT_ALIGNMENT);

			//MENU BAR
			 menuBar = new JMenuBar();
			 frame.setJMenuBar(menuBar);
			 
			 //file
			 JMenu menu_file = new JMenu("File");
			 menuBar.add(menu_file);
			 
		     menuItem_newMap = new JMenuItem("New");
			 menu_file.add(menuItem_newMap);
			 
			 //open
			 menuItem_openMap = new JMenuItem("Open Map");
			 menu_file.add(menuItem_openMap);
			 
			 //save
			 menuItem_save = new JMenuItem("Save");
			 menu_file.add(menuItem_save);
			 
			 //edit
			 menu_edit = new JMenu("Edit");
			 menuBar.add(menu_edit);
			
			 //clear
			 menuItem_clear = new JMenuItem("Clear");
			 menu_edit.add(menuItem_clear);
			
		
			
			//MAP DISPLAY
			panel_map = new JPanel();
			panel_underMap = new JPanel();
			panel_mapData = new JPanel();
			scrollPane_map = new JScrollPane();
			
			button_draw = new JButton("Draw");
			button_erase = new JButton("Erase");
			button_fill = new JButton("Fill");
			
			panel_map.setLayout(new BorderLayout());
			label_mapSize = new JLabel("Map size:");
			label_mapNumTiles = new JLabel("Number of tiles:");
			label_mapName = new JLabel("Map Name");
			
			
			FlowLayout fl_mapBottomPanel = (FlowLayout) panel_underMap.getLayout();
			fl_mapBottomPanel.setAlignment(FlowLayout.LEFT);
			fl_mapBottomPanel.setHgap(20);
			
			
			panel_mapData.setBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.desktop, null));
			panel_underMap.add(panel_mapData);
			panel_mapData.setLayout(new GridLayout(0, 1, 0, 0));
			panel_mapData.add(label_mapSize);
			panel_mapData.add(label_mapNumTiles);
			
			checkBox_mapGrid = new JCheckBox("Grid On");
			checkBox_mapGrid.setToolTipText("Checking the box turns the map grid on.");
			checkBox_mapGrid.setSelected(true);
			panel_underMap.add(checkBox_mapGrid);
			
			scrollPane_map.setBackground(SystemColor.menu);
			//scrollPane_map.setViewportView(drawPanel_map);
			
			panel_map.add(scrollPane_map, BorderLayout.CENTER);
			panel_map.add(panel_underMap, BorderLayout.SOUTH);
			JPanel panel_topOfMap = new JPanel();
			panel_topOfMap.add(label_mapName);
			panel_topOfMap.add(button_draw);
			panel_topOfMap.add(button_erase);
			panel_topOfMap.add(button_fill);
			panel_map.add(panel_topOfMap, BorderLayout.NORTH);
			
			
			
			//TILESET PALETTE
			panel_tileset = new JPanel();
			panel_tileset.setLayout(new BorderLayout(0, 0));
			panel_tileset.setPreferredSize(new Dimension(512,512));
				
			label_tileSize = new JLabel("Tile Size: 32x32 ");
			label_textureSize = new JLabel("Texture Size: ");
			label_tilesetNumTiles = new JLabel("Number of Tiles: ");
			label_selectedTile = new JLabel("Selected Tile: None");
				
			panel_tilesetData = new JPanel();
			panel_tilesetData.setBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.desktop, null));
			panel_tilesetData.setLayout(new GridLayout(0, 1, 0, 0));
			panel_tilesetData.add(label_textureSize);
			panel_tilesetData.add(label_tileSize);
			panel_tilesetData.add(label_tilesetNumTiles);
			panel_tilesetData.add(label_selectedTile);
				
			panel_underTileset = new JPanel();
			FlowLayout fl_tilesetBottomPanel = (FlowLayout) panel_underTileset.getLayout();
			fl_tilesetBottomPanel.setHgap(20);
			fl_tilesetBottomPanel.setAlignment(FlowLayout.LEFT);
			fl_tilesetBottomPanel.setAlignOnBaseline(true);
			panel_underTileset.setMinimumSize(new Dimension(20, 20));
			panel_underTileset.add(panel_tilesetData);
			button_chooseTileset = new JButton("Choose Tileset...");
			panel_underTileset.add(button_chooseTileset);
			
				
			scrollPane_tileset = new JScrollPane();

				
			panel_tileset.add(scrollPane_tileset, BorderLayout.CENTER);
			panel_tileset.add(panel_underTileset, BorderLayout.SOUTH); 
			//scrollPane_tileset.setViewportView(drawPanel_tileset);

			
			//OBJECT PALETTE
			panel_objects = new JPanel();
			//drawPanel_objects = new ObjectDrawPanel();
			//drawPanel_objects.setAutoscrolls(true);
			
			panel_objects.setLayout(new BorderLayout(0, 0));
			scrollPane_objects = new JScrollPane();
			panel_objects.add(scrollPane_objects, BorderLayout.CENTER);
			//scrollPane_objects.setViewportView(drawPanel_objects);
			panel_underObjects = new JPanel();
			panel_objects.add(panel_underObjects, BorderLayout.SOUTH);
			
			
			JPanel panel_rightSide = new JPanel();
			panel_rightSide.setLayout(new BorderLayout());
			panel_rightSide.add(tab, BorderLayout.CENTER);
			
			tab.addTab("Tileset", panel_tileset);
			tab.addTab("Objects", panel_objects);
			
			splitPane.setLeftComponent(panel_map);
			splitPane.setRightComponent(panel_rightSide);
			 
			frame.pack();            // Preferred size of MapEditor
	        frame.setVisible(true);  // Show it 
		}
			

		public JPanel getDrawPanel_map() {
			return drawPanel_map;
		}

		public void setDrawPanel_map(MapDrawPanel drawPanel_map) {
			this.drawPanel_map = drawPanel_map;
			this.scrollPane_map.setViewportView(drawPanel_map);
		}

		public JPanel getDrawPanel_tileset() {
			return drawPanel_tileset;
		}

		public void setDrawPanel_tileset(JPanel drawPanel_tileset) {
			this.drawPanel_tileset = drawPanel_tileset;
			this.scrollPane_tileset.setViewportView(this.drawPanel_tileset);
		}

		public JPanel getDrawPanel_objects() {
			return drawPanel_objects;
		}

		public void setDrawPanel_objects(JPanel drawPanel_objects) {
			this.drawPanel_objects = drawPanel_objects;
			this.scrollPane_objects.setViewportView(this.drawPanel_objects);
		}


		public JDialog getNewMapDialog() {
			return newMapDialog;
		}

		public JTabbedPane getTab() {
			return tab;
		}

		public JFrame getFrame() {
			return frame;
		}

		public JSplitPane getSplitPane() {
			return splitPane;
		}

		public JPanel getPanel_map() {
			return panel_map;
		}

		public JPanel getPanel_underMap() {
			return panel_underMap;
		}

		public JPanel getPanel_mapData() {
			return panel_mapData;
		}

		public JScrollPane getScrollPane_map() {
			return scrollPane_map;
		}

		public JButton getButton_draw() {
			return button_draw;
		}

		public JButton getButton_erase() {
			return button_erase;
		}

		public JButton getButton_fill() {
			return button_fill;
		}

		public JLabel getLabel_mapSize() {
			return label_mapSize;
		}

		public JLabel getLabel_mapNumTiles() {
			return label_mapNumTiles;
		}

		public JLabel getLabel_mapName() {
			return label_mapName;
		}

		public JCheckBox getCheckBox_mapGrid() {
			return checkBox_mapGrid;
		}

		public JPanel getPanel_tileset() {
			return panel_tileset;
		}

		public JLabel getLabel_tileSize() {
			return label_tileSize;
		}

		public JLabel getLabel_textureSize() {
			return label_textureSize;
		}

		public JLabel getLabel_tilesetNumTiles() {
			return label_tilesetNumTiles;
		}

		public JLabel getLabel_selectedTile() {
			return label_selectedTile;
		}

		public JPanel getPanel_tilesetData() {
			return panel_tilesetData;
		}

		public JPanel getPanel_underTileset() {
			return panel_underTileset;
		}

		public JScrollPane getScrollPane_tileset() {
			return scrollPane_tileset;
		}

		public JPanel getPanel_objects() {
			return panel_objects;
		}

		public JScrollPane getScrollPane_objects() {
			return scrollPane_objects;
		}

		public JPanel getPanel_underObjects() {
			return panel_underObjects;
		}

		public JMenuBar getMenuBar() {
			return menuBar;
		}

		public JMenuItem getMenuItem_newMap() {
			return menuItem_newMap;
		}

		public JMenuItem getMenuItem_openMap() {
			return menuItem_openMap;
		}

		public JMenuItem getMenuItem_save() {
			return menuItem_save;
		}

		public JMenu getMenu_edit() {
			return menu_edit;
		}

		public JMenuItem getMenuItem_clear() {
			return menuItem_clear;
		}
		
		public JButton getButton_chooseTileset()
		{
			return this.button_chooseTileset;
		}
		
		
		
}

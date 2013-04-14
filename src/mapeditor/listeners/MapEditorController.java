package mapeditor.listeners;

import mapeditor.MapEditor;
import mapeditor.DrawPanels.MapDrawPanel;
import mapeditor.DrawPanels.ObjectDrawPanel;
import mapeditor.DrawPanels.TilesetDrawPanel;
import mapeditor.actions.ClearMapAction;
import mapeditor.actions.DrawStateAction;
import mapeditor.actions.EraseStateAction;
import mapeditor.actions.FillStateAction;
import mapeditor.actions.NewMapAction;
import mapeditor.actions.OpenAction;
import mapeditor.actions.OpenTilesetAction;
import mapeditor.actions.SaveAction;
import mapeditor.views.MapEditorGUI;
import mapeditor.views.NewMapDialog;

public class MapEditorController extends MapEditorBase
{
	
	public MapEditorController(MapEditor mapEditor, MapEditorGUI mapEditorGUI) {
		super(mapEditor, mapEditorGUI);
		// TODO Auto-generated constructor stub
	}

	public void initControls()
	{
		//create dialogs
		NewMapDialog newMapDialog = new NewMapDialog();
		
		//set up custom drawing
		MapDrawPanel mapDrawPanel = new MapDrawPanel(mapEditor);
		TilesetDrawPanel tilesetDrawPanel = new TilesetDrawPanel(mapEditor);
		ObjectDrawPanel objectDrawPanel = new ObjectDrawPanel(mapEditor);
		
		//attach draw panels to GUI
		mapEditorGUI.setDrawPanel_map(mapDrawPanel);
		mapEditorGUI.setDrawPanel_tileset(tilesetDrawPanel);
		mapEditorGUI.setDrawPanel_objects(objectDrawPanel);
		
		
		

		//create actions
		NewMapAction newMapAction = new NewMapAction("New Map", null, newMapDialog.getDialog());
		DrawStateAction drawStateAction = new DrawStateAction("Draw", null, mapEditor.getBrush());
		EraseStateAction eraseStateAction = new EraseStateAction("Erase", null, mapEditor.getBrush());
		FillStateAction fillStateAction = new FillStateAction("Fill", null, mapEditor.getBrush());
		ClearMapAction clearMapAction  = new ClearMapAction("Clear Map", null, mapEditor, mapEditorGUI);
		SaveAction saveAction = new SaveAction("Save", null,  mapEditor, mapEditorGUI);
		OpenAction openAction = new OpenAction("Open", null, mapEditor, mapEditorGUI);
		OpenTilesetAction openTilesetAction = new OpenTilesetAction("Choose Tileset...", null, mapEditor, mapEditorGUI);
		
		//attach them to the gui
		mapEditorGUI.getMenuItem_newMap().setAction(newMapAction);
		mapEditorGUI.getMenuItem_openMap().setAction(openAction);
		mapEditorGUI.getMenuItem_save().setAction(saveAction);
		mapEditorGUI.getMenuItem_clear().setAction(clearMapAction);
		mapEditorGUI.getButton_draw().setAction(drawStateAction);
		mapEditorGUI.getButton_erase().setAction(eraseStateAction);
		mapEditorGUI.getButton_fill().setAction(fillStateAction);
		mapEditorGUI.getButton_chooseTileset().setAction(openTilesetAction);
		//newMapDialog.getButton_openTileset().setAction(openTilesetAction);
		
		
		//create listeners
		CheckBoxListener cbl = new CheckBoxListener(mapEditor, mapEditorGUI);
		DialogButtonListener dbl = new DialogButtonListener(mapEditor, mapEditorGUI, newMapDialog);
		MapMouseListener mml = new MapMouseListener(mapEditor, mapEditorGUI);
		MapScrollBarListener msbl = new MapScrollBarListener(mapEditor, mapEditorGUI);
		MapScrollPaneResizeListener msrl = new MapScrollPaneResizeListener(mapEditor, mapEditorGUI);
		ObjectMouseListener oml = new ObjectMouseListener(mapEditor, mapEditorGUI);
		TilesetMouseListener tml = new TilesetMouseListener(mapEditor, mapEditorGUI);
		
		//attach them to the gui
		mapEditorGUI.getCheckBox_mapGrid().addActionListener(cbl);
		mapEditorGUI.getDrawPanel_map().addMouseListener(mml);
		mapEditorGUI.getDrawPanel_map().addMouseMotionListener(mml);
		mapEditorGUI.getDrawPanel_tileset().addMouseListener(tml);
		mapEditorGUI.getDrawPanel_objects().addMouseListener(oml);
		mapEditorGUI.getScrollPane_map().getVerticalScrollBar().addAdjustmentListener(msbl);
		mapEditorGUI.getScrollPane_map().getHorizontalScrollBar().addAdjustmentListener(msbl);
		mapEditorGUI.getScrollPane_map().addComponentListener(msrl);
		
		newMapDialog.getButton_cancel().addActionListener(dbl);
		newMapDialog.getButton_ok().addActionListener(dbl);
		newMapDialog.getButton_openTileset().addActionListener(dbl);

		
		
		
		
		
		
	}
	
}

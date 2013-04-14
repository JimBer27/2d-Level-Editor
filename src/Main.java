
import java.awt.EventQueue;

import mapeditor.MapEditor;
import mapeditor.XMLWriter;
import mapeditor.listeners.MapEditorController;
import mapeditor.views.MapEditorGUI;



public class Main 
{
	public static void main(String[] args) 
	{
//		XMLWriter xml = new XMLWriter();
//		xml.addElement("", "SECTION1");
//		xml.addElement("SECTION1", "SECTION2");
//		xml.addElement("SECTION1", "SECTION3");
//		xml.addData("SECTION2",  "lol");
//		xml.addData("SECTION3",  "GFG");
//		xml.addComment("SECTION3", "lol sweet a comment");
//		xml.outputXML("test.xml");
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					//CREATE MAP EDITOR LOGIC INTERFACE
					MapEditor mapEditor = new MapEditor();
					
					//CREATE MAP EDITOR GUI
					MapEditorGUI mapEditorGUI = new MapEditorGUI();
					
					//CREATE CONTROLS (Listeners and Actions) TO ATTACH LOGIC EVENTS TO THE GUI
					MapEditorController mapEditorController = new MapEditorController(mapEditor, mapEditorGUI);
					mapEditorController.initControls();
					
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}

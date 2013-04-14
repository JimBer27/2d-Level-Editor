package mapeditor;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import mapeditor.GameMap.MapType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class XMLMapReader extends DefaultHandler
{
	private SAXParser sp;
	private MapTemplate template;
	private String tmp;
	
	public XMLMapReader()
	{
		//get a factory
				SAXParserFactory spf = SAXParserFactory.newInstance();
				try {

					//get a new instance of parser
					 sp = spf.newSAXParser();

				

				}catch(SAXException se) {
					se.printStackTrace();
				}catch(ParserConfigurationException pce) {
					pce.printStackTrace();
				}
	}
	//a new reader should be created for each xml parsed
	public void parse(String fileName)
	{
		try 
		{
			//parse the file and also register this class for call backs
			sp.parse(fileName, this);
			
		}
		catch (SAXException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void startDocument()
	{
		
	}
	
	@Override
	public void endDocument()
	{
		
	}
	
	@Override
	public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
        // if current element is book , create new book
        // clear tmpValue on start of element

        if (elementName.equalsIgnoreCase("Map")) 
        {
            template = new MapTemplate();
            System.out.println("create new template");
        }
        
        
    }
	
	@Override
    public void endElement(String s, String s1, String element) throws SAXException {
        
        if (element.equalsIgnoreCase("mapname")) {
            template.mapName = tmp;
            System.out.println(tmp);
        }
        if (element.equalsIgnoreCase("mapwidth")) {
            template.mapWidth = Integer.parseInt(tmp);
            System.out.println(tmp);
        }
        if (element.equalsIgnoreCase("mapheight")) {
            template.mapHeight = Integer.parseInt(tmp);
            System.out.println(tmp);
        }
        if (element.equalsIgnoreCase("tilewidth")) {
            template.tileWidth = Integer.parseInt(tmp);
            System.out.println(tmp);
        }
        if (element.equalsIgnoreCase("tileHeight")) {
            template.tileHeight = Integer.parseInt(tmp);
            System.out.println(tmp);
        }
        if (element.equalsIgnoreCase("maptype")) {
        	if(tmp.equalsIgnoreCase("square")){
        		template.mapType = MapType.SQUARE;
        	}
        	else if(tmp.equalsIgnoreCase("iso"))
        	{
        		template.mapType = MapType.ISO;
        	}
            System.out.println(tmp);
        }
        if (element.equalsIgnoreCase("data")) {
        	System.out.println(tmp);
        	String[] str = tmp.split(",");
        	int[] data = new int[str.length];
        	for(int i = 0; i<str.length; i++)
        	{
        		data[i] = Integer.parseInt(str[i]);
        	}
        	template.data = data;
            
        }
    }

	@Override
	public void characters (char ch[], int start, int length)
	{
		tmp = new String(ch, start, length);
	}
	
	public MapTemplate getMapTemplate()
	{
		return this.template;
	}
	
}

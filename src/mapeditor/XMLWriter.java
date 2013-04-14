package mapeditor;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


//class for creating xml documents
//use this to create the map xml file
public class XMLWriter 
{
	
	private Document doc;
	private HashMap<String, Element> parentMap = new HashMap<String, Element>();
	
	public XMLWriter()
	{
		 try {
	            /////////////////////////////
	            //Creating an empty XML Document

	            //We need a Document
			 	DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	            doc = docBuilder.newDocument();
	            doc.setXmlStandalone(true);
		 }
		 catch (Exception e) 
		 {
	           System.out.println(e);
	     }
	}
	
	public void addElement(String parent, String name)
	{
		if(this.parentMap.containsKey(parent))
		{
			Element section = parentMap.get(parent);
			Element child = doc.createElement(name);
			section.appendChild(child);
			this.parentMap.put(name, child);
		}
		else
		{
			Element newParent = doc.createElement(name);
	        doc.appendChild(newParent);
	        parentMap.put(name, newParent);
		}
		
	}
	
	public void addData(String parent, String data)
	{
		//get parent
		Element section = parentMap.get(parent);
		
		//add a text element to the child
        Text text = doc.createTextNode(data);
        section.appendChild(text);
	}
	
	public void addComment(String parent, String text)
	{
		//create a comment and put it in the root element
        Comment comment = doc.createComment(text);
        Element section = this.parentMap.get(parent);
        section.appendChild(comment);
	}
	
	public void outputXML(String fileName)
	{
		 /////////////////
        //Output the XML
		try 
		{ 
			//prepare document for writing 
			Source source = new DOMSource(doc);

			//prepare output file
			String userHomeFolder = System.getProperty("user.dir");
			File dest = new File(userHomeFolder,"Maps");
			
			 // if the directory does not exist, create it
			  if (!dest.exists())
			  {
			    boolean result = dest.mkdirs();  
			    if(result)
			    {    
			       System.out.println("DIR created");  
			     }
			    else
			    {
			    	System.out.println("FAIL");
			    }

			  }
			
			  File file = new File(dest.getPath()+"/"+fileName);
			 Result result = new StreamResult(file);
			 
			 //write dom doc to file
			 Transformer transformer = TransformerFactory.newInstance().newTransformer();
			 
			 //set up indentation
			 transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			 transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		       
		    //create string from xml tree
		    //StringWriter sw = new StringWriter();
		    try 
		    {
		    	transformer.transform(source, result);
			}
		    catch (TransformerException e) 
		    {
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
			}
		    
		  
		} 
		catch (TransformerConfigurationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}
}
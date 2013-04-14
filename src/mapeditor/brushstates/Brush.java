package mapeditor.brushstates;

import mapeditor.MapEditor;

public class Brush extends AbstractBrush
{	
	private AbstractBrush currentState;
	
	//States
	private AbstractBrush drawBrush;
	private AbstractBrush eraseBrush;
	private AbstractBrush fillBrush;
	
	public Brush(MapEditor editor)
	{
		super(editor);
		drawBrush = new DrawBrush(editor);
		eraseBrush = new EraseBrush(editor);
		fillBrush = new FillBrush(editor);
		this.currentState = drawBrush;
	}
	
	public void erase()
	{
		currentState = eraseBrush;
	}
	
	public void draw()
	{
		currentState = drawBrush;
	}
	
	public void fill()
	{
		currentState = fillBrush;
	}


	@Override
	public void brushAction(int x, int y) 
	{
		if(currentState == null)
			return;
		currentState.brushAction(x, y);
		
	}
}

package mapeditor;

//This class will be used to represent the viewable area of the map (it will be set to the same size
//as the map scroll panel. Whenever the scroll panel size changes this class needs to update.
public class Camera2D
{
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Camera2D()
	{
		 
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	


	
	
}

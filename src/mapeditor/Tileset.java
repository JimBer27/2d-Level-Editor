package mapeditor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tileset 
{
	
	private BufferedImage texture;
	private int textureWidth;
	private int textureHeight;
	private int tileWidth;
	private int tileHeight;
	private int numFrames;
	
	public Tileset(BufferedImage texture, int tileWidth, int tileHeight)
	{
		this.texture = texture;
		
		this.textureWidth = texture.getWidth();
		this.textureHeight = texture.getHeight();
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.numFrames = textureWidth/tileWidth * textureHeight/tileHeight;
	}
	

	
	public void draw(Graphics g, int x, int y, int type)
	{
		if(type == -1)
			return;
		
		int srcStartX = tileWidth * (type % (this.textureWidth/this.tileWidth));
		int srcStartY = tileHeight * (type / (this.textureWidth/this.tileWidth));
		int srcEndX =   srcStartX + tileWidth;
		int srcEndY = srcStartY + tileHeight;
		
		g.drawImage(texture, x, y, x+tileWidth, y+tileHeight, srcStartX, srcStartY, srcEndX, srcEndY, null);
	}
	
	
	public BufferedImage getTexture() {
		return texture;
	}

	public int getTextureWidth() {
		return textureWidth;
	}

	public int getTextureHeight() {
		return textureHeight;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public int getNumFrames() {
		return numFrames;
	}
}

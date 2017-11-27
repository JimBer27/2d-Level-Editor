Author: James Bergeron
2d-Level-Editor
===============

## Introduction
2d Level Editor provides an easy to use GUI for creating, loading, and outputting tile maps that can be used to create 2d video games. The GUI was coded using Java's Swing library. The level editor can create tile maps in both 2D Rectangular grid style, as well as Isometric style. Below are some descriptions and code samples of how I chose to represent tile maps, place the tiles, and draw them to the screen.

 
![image](https://github.com/JimBer27/2d-Level-Editor/raw/master/readme_images/iso_grid.png)
Note: The images shown are using sample tilesets, you load any tileset png file into the Level Editor GUI.

## Creating a Map
Tile map: I chose to represent the map as a 2 dimensional array of tiles with sizes based on the width and height of the map. Each tile object keeps track of its game world coordinates and has the ability to draw itself. This makes it simple to get map coordinates. For example: when the map is drawn out in a grid, tiles[0][0] would refer to the top-left most tile.

``` Private Tile[][] tiles; ```

In order to create a map we need to know some initial information about the map.
- The width of the map (in tiles)
- The height of the map (int tiles)
- The tile width (this will depend on the tileset used)
- The tile height (this will depend on the tileset used)
- Map type (Square tiles or Isometric tiles)
- Map data (This tells which tiles from the tileset we are using. If its a new map each tile will have a default value of -1)


![image](https://github.com/JimBer27/2d-Level-Editor/raw/master/readme_images/new_map_square.png "Create square map")![image](https://github.com/JimBer27/2d-Level-Editor/raw/master/readme_images/new_map_iso.png "Create iso map")

## Square Map
Creating a Square Map is pretty simple. We use a nested for loop and with each iteration multiply the tile's x position by the width of the tile, and the tile's y position by the height of the tile. This will create a grid of square tiles.

```
for(int y = 0; y < mapHeight; y++){
	for(int x = 0; x < mapWidth; x++){
		tilePosX = x * tileWidth;
		tilePosY = y * tileHeight;
		tiles[x][y] = new Tile(tilePosX, tilePosY);
		tiles[x][y].setType(mapData[mapDataIndex]);
		mapDataIndex++;
	}
}
```

![Example of an empty square map](https://github.com/JimBer27/2d-Level-Editor/raw/master/readme_images/square_emptygrid.png "Example of an empty square map")



## Isometric Map
Creating an Isometric Map needs some modifications. The isometric map I use has a staggered pattern with tiles are twice as wide as they are tall and diamond shaped. In order to piece the tiles together we need to offset every other row by half the width of the tile. Also we must move the y position of each tile down by only half of its height.
```
for(int y = 0; y < mapHeight; y++){
	for(int x = 0; x < mapWidth; x++){
		if(y % 2 != 0) //odd row (tiles are offset to the right by half the tile width){
			tilePosX = x * tileWidth + tileWidth/2;
		}
		else {//even row
			tilePosX = x * tileWidth;
		}			
		tilePosY = y * (tileHeight/2); 
		this.tiles[x][y] = new Tile(tilePosX, tilePosY);
		this.tiles[x][y].setType(mapData[mapDataIndex]);
		mapDataIndex++;
	}
}
```

![Example of an empty isometric map](https://github.com/JimBer27/2d-Level-Editor/raw/master/readme_images/iso_emptygrid.png "Example of an empty isometric map")


## Drawing the Map
The idea behind drawing the map is that we only want to draw what can actually be seen. Imagine if we have a 1000x1000 tile map? That's 1,000,000 tiles which would be very costly performance-wise. The solution I used was to create a 2d camera class. The camera is essentially the viewport, or simply a box with a width and a height set to the size of the screen. Only the tiles within the camera viewport are actually drawn. The camera can also be used to simulate scrolling, since we can move the camera around to draw different parts of the map.

```
public void draw(Graphics g, Camera2D camera){
  //get size and position of viewport
  int cameraX = camera.getX();
  int cameraY = camera.getY();
  int cameraWidth = camera.getWidth();
  int cameraHeight = camera.getHeight();

  //get boundaries of the map within the camera
  int leftX = (int)(cameraX/tileWidth);
  int topY = (int)(cameraY/tileHeight);
  int rightX = (int)((cameraX+cameraWidth)/tileWidth);
  int bottomY = (int)((cameraY+cameraHeight)/tileHeight);

  //adjust for isometric tiles since the height is only 1/2 the width
  if(this.type == MapType.ISO){
    topY = (int)(cameraY/tileHeight*2);
    bottomY = (int)((cameraY+cameraHeight)/tileHeight*2);
  }

  //Adjust the boundaries incase the map is smaller than the camera
  if(mapWidth - 1 < rightX){
    rightX = mapWidth - 1;
  }

  if(mapHeight - 1 < bottomY){
    bottomY = mapHeight - 1;
  }

  //only draw the tiles that are within the camera bounds
  for (int y = topY; y <= bottomY; y++){
     for (int x = leftX; x <= rightX; x++){
        //only draw the tiles if tileset texture is not null
      if(tileset != null){
        tiles[x][y].draw(g, tileset);
      }
    }
  }
}
```

## Selecting a Tile
Lets say we need to select a specific tile on the screen. For example we need to do this when selecting a tile from the tileset and placing it on the grid. Using the mouse coordinates we can figure out which tile we are in.


## Square Map
With square tiles its simple, to find out the x coordinate of the selected tile we do inputX divided by the tile width. To find out the y coordinate of the selected tile we do inputY divided by the tile height.

For an example: Lets say we are using 32 x 32 tiles. We click on coordinates x=65 and y=40.
65/32 = 2.03
40/32 = 1.25
Since we are using 'int', there are no decimals, which results in tile[2][1] being selected.


```
public Point getTile(int x, int y) //x and y represent the exact coordinates of the mouse pointer when we click{
	//adjust the input based on map offset and map scroll
	x -= mapPositionX;
	y -= mapPositionY;
		
	//find out which box the mouse click is in
	int boxX = x/tileWidth;
	int boxY = y/tileHeight;
		
	//make sure the tile coordinates correspond to a valid tile on the map
	if(boxX >= 0 && boxX < mapWidth && boxY >=0 && boxY < mapHeight){
		return new Point(boxX, boxY);
	}
	//there is no tile on the map at these coordinates
	else{
		return null;
	}	
}
```

![Square map with grid on](https://github.com/JimBer27/2d-Level-Editor/raw/master/readme_images/square_grid.png "Square map with grid on")


![Square map with grid off](https://github.com/JimBer27/2d-Level-Editor/raw/master/readme_images/square_nogrid.png "Square map with grid off")



## Selecting a Tile from Isometric Map
Selecting a tile from an isometric map is more difficult. We start out by dividing the grid into rectangles, just as we would with 2D square tiles. From here we can find out which rectangle the mouse point lies in. However, due to the way we placed the isometric tiles (every other row is offset half the height of the tile), we can't determine exactly which tile was selected. See the image below. Say we clicked within the first rectangle. We still need to determine if we clicked the red tile, or if we clicked the green tile.

![image](https://github.com/JimBer27/2d-Level-Editor/raw/master/readme_images/iso2.png)

Once we know which rectangle the point is in, we can then cut the rectangle into 4 quadrants. See the image below. Say we clicked somewhere in the first rectangle. If the point lies any quadrant other than the lower righthand quadrant then we can say it was the red tile was selected. However, if the point lies within the lower righthand quadrant, it can either be the red tile, or the green tile. 


![image](https://github.com/JimBer27/2d-Level-Editor/raw/master/readme_images/iso3.png)

Once we narrow it down to a single quadrant, all we need to know is which side of the diagonal the point lies on. To do this we can use the two points of the diagonal, and the point of the mouse click to calculate the cross product, which will give us either a negative answer or a positive answer. Depending on which answer we get, we now know which side of the line the point lies. 


![image](https://github.com/JimBer27/2d-Level-Editor/raw/master/readme_images/iso4.png)

```
public Point getTile(int x, int y){
	//adjust the input based on map offset and map scroll
	x -= mapPositionX;
	y -= mapPositionY;
	int halfTileWidth = tileWidth/2;
	int halfTileHeight = tileHeight/2;
						
	//find out which box the mouse click is in (square grid)
	int boxX = x/tileWidth;
	int boxY = y/tileHeight;
	int isoX = boxX;
	int isoY = boxY*2;

	//find out which quadrant of the box the mouse click falls in
	int quarterBoxX =  (x - tileWidth * boxX) / halfTileWidth;
	int quarterBoxY =  (y - tileHeight * boxY) / halfTileHeight;
						
	//set the quadrant
	int quadrant = 0;
	if(quarterBoxX == 1 && quarterBoxY == 0) //top right
		quadrant = 1;
	else if(quarterBoxX == 0 && quarterBoxY == 0) // top left
		quadrant = 2;
	else if(quarterBoxX == 0 && quarterBoxY == 1) //bottom left
		quadrant = 3;
	else if(quarterBoxX == 1 && quarterBoxY == 1) //bottom right
		quadrant = 4;
				
	//2 points to cut the quadrant in half diagonally
	//point 1
	int x1;
	int y1;
				
	//point 2
	int x2;
	int y2;
				
	//use the cross product of the 2 points and the point that was clicked to determine which side of the diagonal line the click was on.
	//This will determine which tile was clicked
	switch (quadrant) {
		//TOP RIGHT QUADRANT 1
		case 1:
			x1 = tileWidth * boxX + halfTileWidth;
			y1 = tileHeight * boxY;
			x2 = tileWidth * boxX + tileWidth;
			y2 = tileHeight * boxY + halfTileHeight;
						
			if(((x2 - x1)*(y - y1) - (y2 - y1)*(x - x1)) < 0){
				isoY--;;
			}
			break;
			         
			//TOP LEFT QUADRANT 2
			case 2:  
				x1 = tileWidth * boxX;
				y1 = tileHeight * boxY + halfTileHeight;
				x2 = tileWidth * boxX + halfTileWidth;
				y2 = tileHeight * boxY;
						
				if(((x2 - x1)*(y - y1) - (y2 - y1)*(x - x1)) < 0){
					isoX--;	
					isoY--;
				}
			    break;
			        
			//BOTTOM LEFT QUADRANT 3
			case 3: 
			    x1 = tileWidth * boxX;
			    y1 = tileHeight * boxY + halfTileHeight;
			    x2 = tileWidth * boxX + halfTileWidth;
			    y2 = tileHeight * boxY + tileHeight;
				
			    if(((x2 - x1)*(y - y1) - (y2 - y1)*(x - x1)) > 0){
			    	isoX--;
					isoY++;
			    }
			    break;
			         
			//BOTTOM RIGHT QUADRANT 4
			case 4: 
			  x1 = tileWidth * boxX + halfTileWidth;
				y1 = tileHeight * boxY + tileHeight;
				x2 = tileWidth * boxX + tileWidth;
				y2 = tileHeight * boxY + halfTileHeight;
						
				if(((x2 - x1)*(y - y1) - (y2 - y1)*(x - x1)) > 0){
					isoY++;
				}
			  break;
			             
			default: 
			//don't change anything
	}
				
	//make sure the tile coordinates correspond to a valid tile on the map
	if(isoX >= 0 && isoX < this.width && isoY >=0 && isoY < this.height){
		return new Point(isoX, isoY);
	}
	//there is no tile on the map at these coordinates
	else{
		return null;
	}	
}
```

![image](https://github.com/JimBer27/2d-Level-Editor/raw/master/readme_images/iso_grid.png)
![image](https://github.com/JimBer27/2d-Level-Editor/raw/master/readme_images/iso_nogrid.png)


## Loading/Saving Maps
The benifit of creating my own Level Editor is that I can choose and modify how the map is outputted. For convience I am using XML to save map data. Choosing File -> Save in the Level Editor will generate an XML file representing that specific map. Each number in the "Data" tag represents a corresponding tile from the tilesheet. -1 represents an empty tile. The XML code below shows a sample of how map data is stored. You can use the Level Editor to create an XML file, or simply create your own then open it up in the editor.

To open a pre-made map, just choose an XML map file and a tileset and the map will be recreated using the map data. I use a SAX Parser to read the XML map file and grab the values from it. The values can then be passed into a Map Object to create the level.

```xml
<?xml version="1.0" encoding="UTF-8"?>
					
<Map>
  <MapName>SampleName</MapName>
  <MapType> ISO </MapType>
  <MapWidth> 5 </MapWidth>
  <MapHeight> 5 </MapHeight>
  <TileWidth> 64 </TileWidth>
  <TileHeight> 32 </TileHeight>
  <Data> 17,16,16,0,0,16,16,16,16,16,16,16,0,0,16,12,16,16,16,16,16,16,8,16,16 </Data>
</Map>
</dl>
```

## TODO: 
add map objects/doodads in the object tab separate from tilesets

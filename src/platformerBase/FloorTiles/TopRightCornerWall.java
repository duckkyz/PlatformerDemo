package platformerBase.FloorTiles;

import java.awt.image.BufferedImage;

public class TopRightCornerWall extends Wall {
	private static BufferedImage img;
	
	public TopRightCornerWall(int x, int y){
		super(x,y);
		
		setActionStep((int) (7 + Math.floor(Math.random() * 0)));		//column
		setActionSequence((int) (0 + Math.floor(Math.random() * 0)));	//row
		this.setTileHeight(128);
	}
}

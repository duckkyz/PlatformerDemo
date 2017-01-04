package platformerBase.FloorTiles;

import java.awt.image.BufferedImage;

public class LeftWall extends Wall {
	private static BufferedImage img;
	
	public LeftWall(int x, int y){
		super(x,y);
		
		setActionStep((int) (5 + Math.floor(Math.random() * 0)));		//column
		setActionSequence((int) (2 + Math.floor(Math.random() * 0)));	//row
		setRightHitBox(28);
	}
}


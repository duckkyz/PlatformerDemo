package platformerBase.FloorTiles;

import java.awt.image.BufferedImage;

public class RightWall extends Wall {
	private static BufferedImage img;
	
	public RightWall(int x, int y){
		super(x,y);
		
		setActionStep((int) (7 + Math.floor(Math.random() * 0)));		//column
		setActionSequence((int) (2 + Math.floor(Math.random() * 0)));	//row
		setLeftHitBox(28);
	}
}

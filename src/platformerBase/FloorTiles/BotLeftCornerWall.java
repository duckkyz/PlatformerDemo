package platformerBase.FloorTiles;

import java.awt.image.BufferedImage;

public class BotLeftCornerWall extends Wall {
	private static BufferedImage img;
	
	public BotLeftCornerWall(int x, int y){
		super(x,y);
		
		setActionStep((int) (5 + Math.floor(Math.random() * 0)));		//column
		setActionSequence((int) (3 + Math.floor(Math.random() * 0)));	//row
		setTopHitBox(24);
		setRightHitBox(28);
	}
}

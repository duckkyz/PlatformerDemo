package platformerBase.FloorTiles;

import java.awt.image.BufferedImage;

public class BotRightCornerWall extends Wall {
	private static BufferedImage img;
	
	public BotRightCornerWall(int x, int y){
		super(x,y);
		
		setActionStep((int) (7 + Math.floor(Math.random() * 0)));		//column
		setActionSequence((int) (3 + Math.floor(Math.random() * 0)));	//row	
		}
}

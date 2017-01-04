package platformerBase.FloorTiles;

import java.awt.image.BufferedImage;

public class BotWall extends Wall {
	private static BufferedImage img;
	
	public BotWall(int x, int y){
		super(x,y);
		
		setActionStep((int) (6 + Math.floor(Math.random() * 0)));		//column
		setActionSequence((int) (3 + Math.floor(Math.random() * 0)));	//row
		setTopHitBox(24);
	}
}

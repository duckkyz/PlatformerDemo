package platformerBase.FloorTiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.DrawableClasses.Drawable;

public class Wall extends Drawable {
	private static BufferedImage img;
	
	public Wall(int x, int y){
		if(img == null){
			try {
				img = (ImageIO.read(new File("Background_Images/dungeon_sheet.png")));
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Removed these because by default drawable has hitbox sizes of 0, so its redundant to add them here.
		
		this.setXPos(x);
		this.setYPos(y);
		setGraphic(img);
	}
	
	public void paint(Graphics imageGraphics){
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos() + getTileWidth(), getYPos() + getTileHeight(), getActionStep() * getTileWidth(), getActionSequence() * getTileHeight(), getActionStep() * getTileWidth() + getTileWidth(), getActionSequence() * getTileHeight() + getTileHeight(), null);
	}
}

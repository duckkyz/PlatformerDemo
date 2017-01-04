package platformerBase.FloorTiles;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.DrawableClasses.Drawable;

public class GrassTile extends Drawable{
	
	public GrassTile(int x, int y){
		super(x,y);
		try{
			setXPos(x);
			setYPos(y);
			setGraphic(ImageIO.read(new File("Background_Images/dungeon_sheet.png")));
		}
		catch(IOException ex){
		
		}
	}
	public void paint(Graphics imageGraphics) {
		
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos() + getTileWidth(), getYPos() + getTileHeight(), getActionStep() * getTileWidth(), getActionSequence() * getTileHeight(), getActionStep() * getTileWidth() + getTileWidth(), getActionSequence() * getTileHeight() + getTileHeight(), null);
	}
}

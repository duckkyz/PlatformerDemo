package platformerBase.TitleScreenSpash;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformerBase.Game;
import platformerBase.DrawableClasses.Drawable;

public class TitleText extends Drawable {
	private static BufferedImage img;

	private int frameCounter = 0;
	private boolean forwardAnimation = true;
	public TitleText(int x, int y){
		if(img == null){
			try {
				img = (ImageIO.read(new File("Background_Images/TitleText_test.png")));
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.setXPos(x);
		this.setYPos(y);
		setGraphic(img);
		
		setActionStep((int) (0 + Math.floor(Math.random() * 0)));		//column
		setActionSequence((int) (0 + Math.floor(Math.random() * 0)));	//row
		this.setTileHeight(64 * 6);
		this.setTileWidth((64 * 44));
		setTopHitBox(0);
		setBotHitBox(0);
		setLeftHitBox(0);
		setRightHitBox(0);
	}
	
	public void move(){
		if((frameCounter % 6 == 0) & (frameCounter > 0)){
			if(forwardAnimation){
				if(((Math.random() * 100) > 75) & (getActionSequence() > 0)){
					setActionSequence(getActionSequence() - 1);
				}
				else{
					setActionSequence(getActionSequence() + 1);
				}
				if(getActionSequence() >= 2){
					setActionSequence(2);
					forwardAnimation = false;
					frameCounter = 0;
				}
			}
			else{
				if(((Math.random() * 100) > 75) & (getActionSequence() < 2)){
					setActionSequence(getActionSequence() + 1);
				}
				else{
					setActionSequence(getActionSequence() - 1);
				}
				if(getActionSequence() <= 0){
					setActionSequence(0);
					forwardAnimation = true;
					frameCounter = 0;
				}
			}
		}
		frameCounter++;
		
		if(Game.getCurrentWave() > 1){
			Game.removeDrawable(this);
		}
	}
	
	public void paint(Graphics imageGraphics){
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos() + getTileWidth(), getYPos() + getTileHeight(), getActionStep() * getTileWidth(), getActionSequence() * getTileHeight(), getActionStep() * getTileWidth() + getTileWidth(), getActionSequence() * getTileHeight() + getTileHeight(), null);
	}
}

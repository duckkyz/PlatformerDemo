package platformerBase.Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
//
//Try using source folder to load things locally
//
//
//

import platformerBase.Game;
import platformerBase.DrawableClasses.*;
import platformerBase.ImplementedEntities.*;


public class TitleScreen extends GameState{

	public TitleScreen() {		
		try {
			setBackgroundImage(ImageIO.read(new File("Background_Images/Map.png")));
		} 
		catch(IOException ex) {
			
		}
		//TODO Maybe move this
		setBlankImage(new BufferedImage(getBackgroundImage().getWidth(this), getBackgroundImage().getHeight(this), BufferedImage.TYPE_INT_ARGB));

		makeMap();
		scaling = (double) getResolutionSizes()[0][0]/ (double) getImageWidth();
		
		scaling = scaling*2.25;																			//Added for Ben's testing. Reverted other values
		
		scaledBackgroundImageWidth = (int) (getBackgroundImage().getWidth(this) / (scaling));		
		scaledBackgroundImageHeight = (int) (getBackgroundImage().getHeight(this) / (scaling));	
		bottomBarYLocation = (getMonitorHeight()-getImageHeight()) / 2 + getImageHeight();
		rightBarXLocation = (getMonitorWidth()-getImageWidth()) / 2 + getImageWidth();
		halfDiffDisImgHeight = (getMonitorHeight()-getImageHeight()) / 2;
		halfDiffDisImgWidth = (getMonitorWidth()-getImageWidth()) / 2;
		
		Game.setHero(new Wizard(180, Game.getMapWidth()/2, Game.getMapHeight()/2));
		Game.setIsTitleScreen(true);
		getRootContainer().doLayout();
		getRootContainer().repaint();
	}
	
	public void setupResolution(int[][] resolutionSizes, int numResolutionSizes) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setMonitorHeight(screenSize.height);
		setMonitorWidth(screenSize.width);
		
		for(int x = 0;x < numResolutionSizes; x++) {
			if(getMonitorWidth() >= resolutionSizes[x][0])
				if(getMonitorHeight() >= resolutionSizes[x][1]) {
					getFrame().setSize(resolutionSizes[x][0], resolutionSizes[x][1]);
					break;
				}
		}
		//TODO remove this later, I want it for testing.
		//getFrame().setSize(1024, 576);
		
		setImageWidth(getFrameWidth());
		setImageHeight(getFrameHeight());
		getFrame().setLocation((getMonitorWidth()-getFrameWidth())/2, (getMonitorHeight()-getFrameHeight())/2);
		//frame.setUndecorated(true);
		getFrame().setVisible(true);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		getKeySet().add(keyEvent.getKeyCode());

		if (keyEvent.getKeyCode()==KeyEvent.VK_S) {
			HeroSelectScreen heroSelectScreen = new HeroSelectScreen();
			getRootContainer().remove(this);
			getRootContainer().add(heroSelectScreen);
			getRootContainer().doLayout();
			getRootContainer().repaint();
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_H) {
			HelpScreen helpScreen = new HelpScreen();
			getRootContainer().remove(this);
			getRootContainer().add(helpScreen);
			getRootContainer().doLayout();
			getRootContainer().repaint();
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}

	}

	
	public void keyReleased(KeyEvent keyEvent) {
		getKeySet().remove(keyEvent.getKeyCode());
		
	}

	
	public void keyTyped(KeyEvent arg0) {
	
	}

	
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	/*
	public void paint(Graphics graphics) {
		requestFocus();
		super.paint(graphics);
		Graphics imageGraphics = getBlankImage().getGraphics();		//use imageGraphics to draw on the image
															
		//paint() should only be edited between these comments
		imageGraphics.drawImage(this.getBackgroundImage(), 0, 0, this);
		//paint() should only be edited between these comments
		
		if(getFullScreen()==true) {
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, getMonitorWidth(), getMonitorHeight());
			graphics.drawImage(getBlankImage(), (getMonitorWidth()-getImageWidth())/2, (getMonitorHeight()-getImageHeight())/2, getImageWidth(), getImageHeight(), this);	//resize this to fit current resolution
		}
		else
			graphics.drawImage(getBlankImage(), 0, 0, getFrameWidth(), getFrameHeight(), this);	//resize this to fit current resolution
		imageGraphics.dispose();
	}
	*/
	
	public void prepaint(Sprite hero, ArrayList<Drawable> drawables) {
		setImageGraphics(getBlankImage().getGraphics());		//use imageGraphics to draw on the image
		getImageGraphics().drawImage(this.getBackgroundImage(), 0, 0, this);
		
		for(int x = 0;x < drawables.size();x++) {
			if(drawables.get(x) == null){
				continue;
			}
			drawables.get(x).paint(getImageGraphics());
		}	
	}
	
	public void paint(Graphics graphics) {
		requestFocus();
		super.paint(graphics);
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, getFrameWidth(), getFrameHeight());		//makes whatever is outside the image black
		if(getFullScreen()==true) {
			graphics.drawImage(getBlankImage(), (getMonitorWidth()-getImageWidth())/2, (getMonitorHeight()-getImageHeight())/2, getImageWidth(), getImageHeight(), this);
			graphics.fillRect(0, 0, getMonitorWidth(), halfDiffDisImgHeight);																			//top
			graphics.fillRect(0, bottomBarYLocation, getMonitorWidth(), halfDiffDisImgHeight);														//bottom
			graphics.fillRect(0, 0, halfDiffDisImgWidth, getMonitorHeight());																			//left
			graphics.fillRect(rightBarXLocation, 0, halfDiffDisImgWidth, getMonitorHeight());															//right
			
			graphics.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
			graphics.setColor(Color.BLACK);
			graphics.drawString("Start (s)", getMonitorWidth()/2-75, (int)(getMonitorHeight()/1.5));
			graphics.drawString("Help (h)", getMonitorWidth()/2-75, (int)(getMonitorHeight()/1.4+10));
		}
		else{
			graphics.drawImage(getBlankImage(), (int) ((-1)*(Game.getMapWidth()/2)/(scaling) + getImageWidth()/2), (int) ((-1)*(Game.getMapHeight()/2)/(scaling)+getImageHeight()/2), scaledBackgroundImageWidth, scaledBackgroundImageHeight, this);
			
			graphics.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
			graphics.setColor(Color.BLACK);
			graphics.drawString("Start (s)", getImageWidth()/2-75, (int)(getImageHeight()/1.5));
			graphics.drawString("Help (h)", getImageWidth()/2-75, (int)(getImageHeight()/1.4+10));
		}
	}

}

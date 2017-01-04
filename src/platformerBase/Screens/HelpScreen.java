package platformerBase.Screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HelpScreen extends GameState{

	public HelpScreen() {
		try {
			setBackgroundImage(ImageIO.read(new File("Background_Images/Help.png")));
		} catch(IOException ex) {}
		
		setBlankImage(new BufferedImage(getBackgroundImage().getWidth(this), getBackgroundImage().getHeight(this), BufferedImage.TYPE_INT_ARGB));
	}

	public void keyPressed(KeyEvent keyEvent) {
		if(keyEvent.getKeyCode()==KeyEvent.VK_ESCAPE) {
				TitleScreen titleScreen = new TitleScreen();
				getRootContainer().remove(this);
				getRootContainer().add(titleScreen);
				getRootContainer().doLayout();
				getRootContainer().repaint();				
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_0) {
			if(getFullScreen() == true) {
				getFrame().dispose();
				getFrame().setSize(getImageWidth(), getImageHeight());
				getFrame().setLocation((getMonitorWidth()-getFrameWidth())/2, (getMonitorHeight()-getFrameHeight())/2);
				getFrame().setUndecorated(false);
				getFrame().setVisible(true);
				setFullScreen(false);
				getRootContainer().repaint();
			}
			else {
				getFrame().dispose();
				getFrame().setSize(getMonitorWidth(), getMonitorHeight());
				getFrame().setLocation(0, 0);
				getFrame().setUndecorated(true);
				getFrame().setVisible(true);
				setFullScreen(true);
				getRootContainer().repaint();
			}
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_1) {
			if((getMonitorWidth()>=getResolutionSizes()[0][0])&(getMonitorHeight()>=getResolutionSizes()[0][1])) {
				setImageWidth(getResolutionSizes()[0][0]);
				setImageHeight(getResolutionSizes()[0][1]);
				if(getFullScreen()==false) {
					getFrame().setSize(getImageWidth(), getImageHeight());
					getFrame().setLocation((getMonitorWidth()-getFrameWidth())/2, (getMonitorHeight()-getFrameHeight())/2);
				}
				getRootContainer().repaint();
			}
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_2) {
			if((getMonitorWidth()>=getResolutionSizes()[1][0])&(getMonitorHeight()>=getResolutionSizes()[1][1])) {
				setImageWidth(getResolutionSizes()[1][0]);
				setImageHeight(getResolutionSizes()[1][1]);
				if(getFullScreen()==false) {
					getFrame().setSize(getImageWidth(), getImageHeight());
					getFrame().setLocation((getMonitorWidth()-getFrameWidth())/2, (getMonitorHeight()-getFrameHeight())/2);
				}
				getRootContainer().repaint();
			}
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_3) {
			if((getMonitorWidth()>=getResolutionSizes()[2][0])&(getMonitorHeight()>=getResolutionSizes()[2][1])) {
				setImageWidth(getResolutionSizes()[2][0]);
				setImageHeight(getResolutionSizes()[2][1]);
				if(getFullScreen()==false) {
					getFrame().setSize(getImageWidth(), getImageHeight());
					getFrame().setLocation((getMonitorWidth()-getFrameWidth())/2, (getMonitorHeight()-getFrameHeight())/2);
				}
				getRootContainer().repaint();
			}
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_4) {
			if((getMonitorWidth()>=getResolutionSizes()[3][0])&(getMonitorHeight()>=getResolutionSizes()[3][1])) {
				setImageWidth(getResolutionSizes()[3][0]);
				setImageHeight(getResolutionSizes()[3][1]);
				if(getFullScreen()==false) {
					getFrame().setSize(getImageWidth(), getImageHeight());
					getFrame().setLocation((getMonitorWidth()-getFrameWidth())/2, (getMonitorHeight()-getFrameHeight())/2);
				}
				getRootContainer().repaint();
			}
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_5) {
			if((getMonitorWidth()>=getResolutionSizes()[4][0])&(getMonitorHeight()>=getResolutionSizes()[4][1])) {
				setImageWidth(getResolutionSizes()[4][0]);
				setImageHeight(getResolutionSizes()[4][1]);
				if(getFullScreen()==false) {
					getFrame().setSize(getImageWidth(), getImageHeight());
					getFrame().setLocation((getMonitorWidth()-getFrameWidth())/2, (getMonitorHeight()-getFrameHeight())/2);
				}
				getRootContainer().repaint();
			}
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_6) {
			if((getMonitorWidth()>=getResolutionSizes()[5][0])&(getMonitorHeight()>=getResolutionSizes()[5][1])) {
				setImageWidth(getResolutionSizes()[5][0]);
				setImageHeight(getResolutionSizes()[5][1]);
				if(getFullScreen()==false) {
					getFrame().setSize(getImageWidth(), getImageHeight());
					getFrame().setLocation((getMonitorWidth()-getFrameWidth())/2, (getMonitorHeight()-getFrameHeight())/2);
				}
				getRootContainer().repaint();
			}
		}
	}
	public void keyReleased(KeyEvent keyEvent) {
		getKeySet().remove(keyEvent.getKeyCode());
		
	}
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
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
}

package platformerBase.Screens;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import platformerBase.FloorTiles.GrassTile;

public abstract class GameState extends JPanel implements ActionListener, KeyListener{
	
	private static BufferedImage blankImage;									//gets painted on
	private static Image backgroundImage;
	private static JFrame frame;
	private static JPanel rootContainer;
	private static Graphics imageGraphics;
	private static boolean fullScreen = false;
	private static int imageHeight;
	private static int imageWidth;
	private static int monitorHeight;
	private static int monitorWidth;
	private static int numResolutionSizes = 6;
	private static int resolutionSizes[][] = { {1920, 1080},  {1600, 900}, {1366, 768}, {1280, 720}, {1152, 648}, {1024, 576} };			//All resolutions will be scaled assuming that resolutionSizes[0] is the standard
	private static Set keySet = new HashSet();
	private boolean isInGame = true;
	
	protected double scaling;
	protected int scaledBackgroundImageWidth;				
	protected int scaledBackgroundImageHeight;
	protected int bottomBarYLocation;
	protected int rightBarXLocation;
	protected int halfDiffDisImgHeight;
	protected int halfDiffDisImgWidth;
	
	
	public GameState() {
		addKeyListener(this);
		isInGame = false;
		keySet.clear();
	}
	
	public BufferedImage getBlankImage() {
		return blankImage;
	}
	public Image getBackgroundImage() {
		return backgroundImage;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JPanel getRootContainer() {
		return rootContainer;
	}
	public Graphics getImageGraphics() {
		return imageGraphics;
	}
	public boolean getFullScreen() {
		return fullScreen;
	}
	public int getImageHeight() {
		return imageHeight;
	}
	public int getImageWidth() {
		return imageWidth;
	}
	public int getFrameHeight() {
		return frame.getHeight();
	}
	public int getFrameWidth() {
		return frame.getWidth();
	}
	public int getMonitorHeight() {
		return monitorHeight;
	}
	public int getMonitorWidth() {
		return monitorWidth;
	}
	public int getNumResolutionSizes() {
		return numResolutionSizes;
	}
	public int[][] getResolutionSizes() {
		return resolutionSizes;
	}
	public Set getKeySet() {
		return keySet;
	}
	
	public boolean getIsInGame(){
		return isInGame;
	}
	
	public void setBlankImage(BufferedImage blankImage) {
		GameState.blankImage = blankImage;
	}
	public void setBackgroundImage(Image backgroundImage) {
		GameState.backgroundImage = backgroundImage;
	}
	public static void setFrame(JFrame frame) {
		GameState.frame = frame;
	}
	public static void setRootContainer(JPanel rootContainer) {
		GameState.rootContainer = rootContainer;
	}
	public void setImageGraphics(Graphics imageGraphics) {
		this.imageGraphics = imageGraphics;
	}
	public void setFullScreen(boolean fullScreen) {
		GameState.fullScreen = fullScreen;
	}
	public void setImageHeight(int imageHeight) {
		GameState.imageHeight = imageHeight;
	}
	public void setImageWidth(int imageWidth) {
		GameState.imageWidth = imageWidth;
	}
	public void setMonitorHeight(int monitorHeight) {
		GameState.monitorHeight = monitorHeight;
	}
	public void setMonitorWidth(int monitorWidth) {
		GameState.monitorWidth = monitorWidth;
	}
	
	public void makeMap(){
		setImageGraphics(getBlankImage().getGraphics());
		BufferedImage img = new BufferedImage(getBackgroundImage().getWidth(this), getBackgroundImage().getHeight(this), BufferedImage.TYPE_INT_ARGB);
		GrassTile g = new GrassTile(0,0);
		
		for(int i = 0; i < 60; ++i){
			for(int j = 0; j < 34; ++j){
				g.setXPos(i*64);
				g.setYPos(j*64);
				//g.setActionStep((int) (384 + Math.floor(Math.random() * 0)));		//column
				//g.setActionSequence((int) (128 + Math.floor(Math.random() * 0)));	//row
				g.setActionStep((int) (6 + Math.floor(Math.random() * 0)));		//column
				g.setActionSequence((int) (2 + Math.floor(Math.random() * 0)));	//row
				g.paint(img.getGraphics());
				if(Math.random() * 100 > 98){
					g.setActionStep((int) (15 + Math.floor(Math.random() * 4)));		//column
					g.setActionSequence((int) (7 + Math.floor(Math.random() * 0)));	//row
					g.paint(img.getGraphics());
				}
			}
		}
		setBackgroundImage(img);
	}
	
	
}
////////////////////////////////////////////////////
// Drawables are the parent class for every thing //
// that will get drawn to the screen:			  //
//   # Walls								.	  //
//	 # Other terrain							  //
//	 # Sprites									  //
//												  //
//												  //
//												  //
////////////////////////////////////////////////////


package platformerBase.DrawableClasses;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Comparator;

public class Drawable implements Comparable<Drawable>{
	private int topHitBox = 0;
	private int botHitBox = 0;	
	private int leftHitBox = 0;
	private int rightHitBox = 0;
	private int xPos = 0;
	private int yPos = 0;
	private int tileWidth = 64;
	private int tileHeight = 64;
	private int actionSequence = 0;									//The current row.
	private int actionStep = 0;										//The current column.
	private BufferedImage graphic;
	
	public Drawable() {
		
	}
	
	public Drawable(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public int getTopHitBox() {
		return topHitBox;
	}
	
	public int getBotHitBox() {
		return botHitBox;
	}	
	
	public int getLeftHitBox() {
		return leftHitBox;
	}
	
	public int getRightHitBox() {
		return rightHitBox;
	}
	
	public int getTileWidth() {
		return tileWidth;
	}
	
	public int getTileHeight() {
		return tileHeight;
	}
	
	public int getActionSequence() {
		return actionSequence;
	}
	
	public int getActionStep() {
		return actionStep;
	}
	
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	
	public void setTopHitBox(int topHitBox){
		this.topHitBox = topHitBox;
	}
	
	public void setBotHitBox(int botHitBox){
		this.botHitBox = botHitBox;
	}

	public void setLeftHitBox(int leftHitBox){
		this.leftHitBox = leftHitBox;
	}
	
	public void setRightHitBox(int rightHitBox){
		this.rightHitBox = rightHitBox;
	}
	
	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}
	
	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}
	
	public void setActionSequence(int actionSequence) {
		this.actionSequence = actionSequence;
	}
	
	public void setActionStep(int actionStep) {
		this.actionStep = actionStep;
	}
	
	public void setGraphic(BufferedImage graphic){
		this.graphic = graphic;
	}
	
	public BufferedImage getGraphic(){
		return graphic;
	}
	
	public int compareTo(Drawable compareDrawable){
		int compareXPos = ((Drawable) compareDrawable).getXPos();
		
		int yDif = this.getYPos() - compareDrawable.getYPos();
		return yDif - (this.getXPos() - compareXPos);
		
	}
	
	public static Comparator<Drawable> PosComparator = new Comparator<Drawable>(){
		public int compare(Drawable d1, Drawable d2){
			return d1.compareTo(d2);
		}
	};
	
	public void move(){
		
	}
	
	public void doLogic() {
		
	}
	
	public void paint(Graphics graphics) {
	}

}
package platformerBase.FloorTiles;

public class TikiGolem extends Wall {

	public TikiGolem(int x, int y){
		super(x,y);
		
		setActionStep((int) (5 + Math.floor(Math.random() * 7)));		//column
		setActionSequence((int) (0 + Math.floor(Math.random() * 0)));	//row
		this.setTileHeight(64 * 3);
		this.setTileWidth(64 * 2);
		setTopHitBox(28);
		setBotHitBox(28);
		setLeftHitBox(24);
		setRightHitBox(24);
	}
}

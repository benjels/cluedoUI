package cluedo_game;

/**
 * A dummy player class to use for movement testing.
 * @author Tim
 *
 */
public class DummyPlayer {

	private Tile t;
	
	public DummyPlayer(Tile t){
		this.t=t;
	}
	
	public Tile getTile(){
		return t;
	}
	
	public void setTile(Tile t){
		this.t=t;
	}
}

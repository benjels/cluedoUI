package cluedo_ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cluedo_game.Board;
import cluedo_game.CharacterCard;
import cluedo_game.DummyPlayer;
import cluedo_game.HallTile;
import cluedo_game.PlayerTile;
import cluedo_game.RoomCard.Room;
import cluedo_game.RoomTile;
import cluedo_game.Tile;

/**
 * this is the pane of the ui that basically just shows visual state of the game board
 * @author 8)
 *
 */

public class BoardPanel extends JPanel implements MouseListener, MouseMotionListener {

	public static final int gridStartX = -1;
	public static final int gridStartY = 7;
	public static final float tileSize = 36.3f;
	private BufferedImage boardImg;
	private Board board;
	
	private Color greenTileColor;
	private Tile mouseTile;
	
	/** TO USE FOR TESTING ONLY.*/
	private DummyPlayer dummy;
	
	public BoardPanel(Board board, int width, int height){
		super();
		
		//FOR TESTING ONLY
		dummy = new DummyPlayer(new PlayerTile(CharacterCard.Character.MISSSCARLET, "Tim", 10, 10));
		
		this.board=board;
		
		greenTileColor = new Color(0.1f, 0.8f, 0.1f, 0.4f);
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		
		//take care of settings
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());//!!!not really sure what layout this pane should actually have. shouldnt matter too much cause p sure it just hasa one componenet which is the jlabel that board and player tokens drawm opn
		this.setBackground(new Color(0.7f, 0.2f, 0.5f, 0.6f)); //!!! placeholder color for debug etc
		this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		
		/*Load image for board..*/
		try {
			boardImg = ImageIO.read(new File("assets/cluedo-board.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(this.getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int xOffset = 0;
		int yOffset = 0;
		float scale = 1;
		
		//Draw the board
		if(board != null){

			Graphics2D ig = (Graphics2D)g;
			 ig.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		     ig.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		     ig.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
			
			//Check what the highest out of width and height are - constrain both to the lowest of the two
			int w = 0;
			int h = 0;
			if(getWidth() > getHeight()){
				w = getHeight();
				h = getHeight();
			} else {
				w = getWidth();
				h = getWidth();
			}
			scale = w/(boardImg.getWidth()*1.0f);
			xOffset += getWidth()/2-w/2;
			yOffset += getHeight()/2-h/2;
			
			ig.drawImage(boardImg, xOffset, yOffset, w, h, this);
		}
		
		for(int x = 0; x < board.getTiles().length; x++){
				for(int y = 0; y < board.getTiles()[x].length; y++){
					Tile t = board.getTiles()[x][y];
					float scaledSize = (tileSize*(scale));
					if(t == null){
						continue;
					}
					if(t instanceof HallTile){
						g.setColor(t.getOutlineColor());
						
						t.draw(g, (int)(gridStartX+xOffset+x*scaledSize), (int)(gridStartY+yOffset+y*scaledSize), scale);
					}
					
					//If it's where the mouse is, draw a green overlay if player can move there, or red if they can not.
					Tile playerLoc = getCurrentPlayerLocation();
					if(mouseTile != null && x == mouseTile.getX() && y == mouseTile.getY()){
						if(board.canMove(playerLoc, t)){
							g.setColor(greenTileColor);
							g.fillRect((int)(gridStartX+xOffset+x*scaledSize), (int)(gridStartY+yOffset+y*scaledSize), (int)(tileSize*scale), (int)(tileSize*scale));
						}
					}
					
				}
		}
		
		//Finally draw the dummy player FOR TESTING ONLY
		g.setColor(Color.MAGENTA);
		Tile p = getScreenLocation(this.getCurrentPlayerLocation());
		g.fillOval(p.getX(), p.getY(), (int)(tileSize*scale), (int)(tileSize*scale));
		
	}
	
	public Tile getScreenLocation(Tile tile){
		int w = 0;
		int h = 0;
		if(getWidth() > getHeight()){
			w = getHeight();
			h = getHeight();
		} else {
			w = getWidth();
			h = getWidth();
		}
		float scale = w/(boardImg.getWidth()*1.0f);
		int xOffset = getWidth()/2-w/2;
		int yOffset = getHeight()/2-h/2;
		float scaledSize = (tileSize*(scale));
		return new HallTile((int)(gridStartX+xOffset+tile.getX()*scaledSize), (int)(gridStartY+yOffset+tile.getY()*scaledSize));
	}
	
	public Tile getGameLocation(int screenX, int screenY){
		int xOffset = 0;
		int yOffset = 0;
		int w = 0;
		int h = 0;
		if(getWidth() > getHeight()){
			w = getHeight();
			h = getHeight();
		} else {
			w = getWidth();
			h = getWidth();
		}
		float scale = w/(boardImg.getWidth()*1.0f);
		xOffset += getWidth()/2-w/2;;
		yOffset += getHeight()/2-h/2;
		float scaledSize = (tileSize*(scale));
		int x = (int)((screenX-(gridStartX+xOffset))/scaledSize);
		int y = (int)((screenY-(gridStartY+yOffset))/scaledSize);
		if(x < 0 || y < 0 || x >= board.getTiles().length || y >= board.getTiles()[x].length){
			return null;
		}
		return board.getTiles()[x][y];
	}
	
	public Tile getCurrentPlayerLocation(){
		return dummy.getTile();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Tile oldTile = getCurrentPlayerLocation();
		if(mouseTile != null && oldTile != null && board.canMove(oldTile, mouseTile)){
			dummy.setTile(mouseTile);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		/*Work out what tile the cursor is over, if any.  Set our mouseTile to this tile, or null if it's not over a tile. */
		
		mouseTile = getGameLocation(e.getX(), e.getY());
		repaint();
	}


}

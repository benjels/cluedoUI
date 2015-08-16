package cluedo_ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * this is the pane of the ui that basically just shows visual state of the game board
 * @author 8)
 *
 */

public class BoardPanel extends JPanel {

	private static final int gridStartX = 34;
	private static final int gridStartY = 43;
	private static final int tileSize = 36;
	private BufferedImage board;
	
	public BoardPanel(int width, int height){
		super();
		//take care of settings
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());//!!!not really sure what layout this pane should actually have. shouldnt matter too much cause p sure it just hasa one componenet which is the jlabel that board and player tokens drawm opn
		this.setBackground(new Color(0.7f, 0.2f, 0.5f, 0.6f)); //!!! placeholder color for debug etc
		this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		
		/*Load image for board..*/
		try {
			board = ImageIO.read(new File("cluedo-board.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(this.getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		
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
			ig.drawImage(board, getWidth()/2-w/2, getHeight()/2-h/2, w, h, this);
		}
	}


}

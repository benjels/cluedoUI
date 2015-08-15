import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * this is the pane of the ui that basically just shows visual state of the game board
 * @author 8)
 *
 */

public class BoardPanel extends JPanel {

	
	BoardPanel(int width, int height){
		//take care of settings
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());//!!!not really sure what layout this pane should actually have. shouldnt matter too much cause p sure it just hasa one componenet which is the jlabel that board and player tokens drawm opn
		this.setBackground(Color.RED); //!!! placeholder color for debug etc
		this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		
	}


}

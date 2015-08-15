import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
/**
 * the pane that displays the current player's cards. remianing moves, player token, name , turn statusetc
 * @author user
 *
 */

public class PlayerPanel extends JPanel {

	PlayerPanel(int width, int height){
		//set settings for this pane
		this.setLayout(new GridLayout());
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.BLUE); //dummy color for debugging purposes
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	
	
	}
	
	
}

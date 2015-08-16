package cluedo_ui;

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

	public PlayerPanel(int width, int height){
		super();
		//set settings for this pane
		this.setLayout(new GridLayout());
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(new Color(0.2f, 0.2f, 0.7f, 0.6f)); //dummy color for debugging purposes
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	
	
	}
	
	
}

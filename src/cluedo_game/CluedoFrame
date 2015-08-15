import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicBorders;

/**
 * the frame for the standard cluedo board that is 
 * visible for the entire duration of the game
 * has the following components all contained inside its content pane
 * - a JMenuBar for help/options menus etc
 * - a JPanel for drawing the actual board/players etc
 * - a JPanel for drawing information about the player whose turn it currently is
 * @author user
 *
 */

public class CluedoFrame extends JFrame{

	
	
	//TODO: WILL ALMOST CERTAINLY NEED TO MAKE MY OWN VERSIONS OF THE PLAYERPANEL AND BOARDPANEL JPANELS BUT TRY WITHOUT
	
	CluedoFrame(Dimension contentPanePrefSize){
		//CREATE ACONTENT PANE THAT ALL THE OTHER COMPONENETS ARE ADDED TO
		//this is because it is nicer to work with a content pane that is a JComponent rather than
		//just a Container which it is by default
		//!!! note that from now on when we call add/remove/setLayout on this frame, it will actually call
		//those methods on this "all encompassing" Jpanel "cpntent pane"
		JPanel contentPane = new JPanel();
		contentPane.setPreferredSize(contentPanePrefSize);
		contentPane.setLayout(new GridLayout());//!!! i think that the layout of the content pane may be causing the panes to be on top of each other???
		contentPane.setBorder(new BasicBorders.MarginBorder());
		this.setContentPane(contentPane);
		
		//CREATE TOOLBAR AND ADD IT TO THE FRAME DIRECTLY
		this.setJMenuBar(new CluedoGameMenuBar());
	
		//CREATE MAIN GAME PANEL AND ADD IT TO THE CONTENT PANE
		this.add(new BoardPanel(this.getContentPane().getWidth(),(int)(this.getContentPane().getHeight()/1.3f) ), 0); 
		
		//CREATE THE PLAYER INFO PANEL AND ADD IT TO THE CONTENT PANE
		this.add(new PlayerPanel(this.getContentPane().getWidth(), (int)(this.getContentPane().getHeight() - this.getContentPane().getHeight()/1.3f)), 1); //player pane is part of frame's content pane
		
		//FINALLY PACK/DISPLAY THE FRAME AND ITS CONTENT PANE (AND CONSEQUENTLY EVERYTHING IN THIS DISPLAY)
		this.pack();
		this.setVisible(true);
	}
	
	
	//DUMMY MAIN METHOD FOR JUST LOOKING AT OUR UI
	public static void main(String[] args){
		new CluedoFrame(new Dimension(800, 800));
	}
	
	
	
	
}

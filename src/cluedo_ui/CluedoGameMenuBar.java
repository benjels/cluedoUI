package cluedo_ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JMenu;
import javax.swing.JMenuBar;



/**
 * the menu bar that sits at the top of the screen and has game options
 * note that this is directyl a child of the Frame itself
 * @author user
 *
 */

public class CluedoGameMenuBar extends JMenuBar {

	//some dummy versions of the buttons that cause drop down menus to appear
	private JMenu fileMenu;//i dont even know what this does probably has buttons like: restartgame
	private JMenu gameMenu;//probably has buttons like: rules, controls
	private Color buttonCol = new Color(0.9f, 0.9f, 0.9f, 0.9f);
	
	
	public CluedoGameMenuBar(){
		super();
		//the two highest level menu buttons on the menu bar
		fileMenu = new JMenu("file");
		fileMenu.setForeground(buttonCol);
		gameMenu = new JMenu("game");
		gameMenu.setForeground(buttonCol);
		//add our two high level buttons to this jcomponent i.e. the menu bar
		this.add(fileMenu);
		this.add(gameMenu);
		//alter settings for this menubar
		this.setBackground(new Color(12/255f, 9/255f, 10/255f, 0.8f)); //off black
		this.setPreferredSize(new Dimension(200, 25));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		
	}
	
	
	
}

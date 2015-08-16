package cluedo_game;

import java.awt.Color;
import java.awt.Graphics;

import cluedo_ui.BoardPanel;

/**
 * A simple abstract Tile class that represents an x and y coordinate
 * 
 * @author Tim King
 */
public abstract class Tile implements Drawable {

	private int x, y;
	private Color outline;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		setOutlineColor(new Color(0f, 0f, 0f, 0.5f));
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void draw(Graphics g, int xOffset, int yOffset, float scale) {
		g.drawRect(0 + xOffset, 0 + yOffset,
				(int) (BoardPanel.tileSize * scale),
				(int) (BoardPanel.tileSize * scale));
	}

	public abstract String getIcon();

	public Color getOutlineColor() {
		return outline;
	}

	public void setOutlineColor(Color outline) {
		this.outline = outline;
	}

}

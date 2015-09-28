package gameworld.location;

import java.awt.Point;
import java.io.Serializable;

import gameworld.tile.Tile;

public abstract class Location implements Serializable{

	private static final long serialVersionUID = 1L;
	protected String name;
	protected String description;
	protected Tile[][] tiles;

	public Location(String name, String description, Tile[][] tiles) {
		this.name = name;
		this.description = description;
		this.tiles = tiles;
	}

	/**
	 * @return the name of the location
	 */
	public String name() {
		return name;
	}

	/**
	 * @return the description of the location
	 */
	public String descritpion() {
		return description;
	}

	/**
	 * @return the tiles that make up this location
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * @return width of the location
	 */
	public int width() {
		return tiles[0].length;
	}

	/**
	 * @return height of the location
	 */
	public int height() {
		return tiles.length;
	}

	/**
	 * Get the tile at a position in the location
	 * @param pos - position of the tile
	 * @return the tile at the position
	 */
	public Tile getTileAt(Point pos) {
		return tiles[pos.y][pos.x];
	}
	
	public void setTiles(Tile[][] tiles){
		this.tiles = tiles;
	}
	
	public void setTile(int x, int y, Tile t){
		tiles[y][x] = t;
	}
	
	public String toString() {
		String s = null;
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[0].length; j++) {
				s+=tiles[i][j].getName();
			}
			s+="\n";
		}
		return s;
	}
	
}

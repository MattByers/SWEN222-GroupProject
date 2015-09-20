package ui;

import gameworld.Game;
import gameworld.Game.Direction;
import gameworld.Player;
import gameworld.location.Location;
import gameworld.location.OutsideLocation;
import gameworld.tile.BuildingTile;
import gameworld.tile.EntranceTile;
import gameworld.tile.FloorTile;
import gameworld.tile.Tile;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class RenderingWindow extends JPanel{
	
	private Image backgroundImage;
	private Image grass;
	private Image building;
	private Image water;
	private Image rock;
	private Image room;
	private Image doorUD;
	private Image doorLR;
	private Image roofLR;
	private Image roofUD;
	private Image roofCornerO;
	private Image roofCornerI;
	
	private Player player;
	
	private int TILESIZE = 64;
	
	Game.Direction direction = Direction.NORTH;
	
	public RenderingWindow(){
		setLayout(null);
		setBounds(50,30,950,750);
		
		setImages();

	}
	
	/**
	 * Setting images to files in images folder. Will be changed when map parser is working for some tiles. (grass, water, rock will be gone).
	 */
	private void setImages() {
		try{
			backgroundImage = ImageIO.read(new File("src/ui/images/renderingWindowTemp.jpg"));
			grass = ImageIO.read(new File("src/ui/images/terrain/Grass.png"));
			building = ImageIO.read(new File("src/ui/images/buildings/Room.png"));
			water = ImageIO.read(new File("src/ui/images/terrain/Water.png"));
			rock = ImageIO.read(new File("src/ui/images/terrain/Rock.png"));
			room = ImageIO.read(new File("src/ui/images/buildings/Room.png"));
			doorUD = ImageIO.read(new File("src/ui/images/buildings/DoorUD.png"));
			doorLR = ImageIO.read(new File("src/ui/images/buildings/DoorLR.png"));
			roofLR = ImageIO.read(new File("src/ui/images/buildings/RoofLR.png"));
			roofUD = ImageIO.read(new File("src/ui/images/buildings/RoofUD.png"));
			roofCornerO = ImageIO.read(new File("src/ui/images/buildings/RoofCornerO.png"));
			roofCornerI = ImageIO.read(new File("src/ui/images/buildings/RoofCornerI.png"));
			
			
		}catch(IOException e){
			System.out.println(e.getLocalizedMessage());
		}
	}

	/**
	 * Rotates tile arrays depending on the current viewing direction and then calls the isometric
	 * renderer on the resulting arrays
	 */
	public void paint( Graphics g ) { 
		super.paint(g);
		
		Image offscreen = createImage(this.getWidth(), this.getHeight());
		Graphics offgc = offscreen.getGraphics();

//		Location l = player.getLocation();
//		Tile[][] tiles = l.getTiles();
//		//Tile[][] rooms = l.getRooms();
//		//Item[][] items = l.getItems();
//		

		
		// Example location. To be changed later.
		Location l = null;
		Tile g1 = new FloorTile(l, new Point(0,0), grass);
		Tile g2 = new FloorTile(l, new Point(5,5), grass);
		Tile w1 = new FloorTile(l, new Point(0,0), water);
		Tile r1 = new FloorTile(l, new Point(0,0), rock);
		Tile[][] tiles = {
				{g2,g2,g2,g2,g1,g1,g1,g1,g1,g1,g1,g1,g1,g1,g1},
				{g2,g2,g2,g2,g1,g1,g1,g1,g1,g1,g1,g1,g1,g1,g1},
				{g2,g2,g2,g2,g1,g1,g1,g1,g1,g1,g1,g1,g1,w1,g1},
				{g2,g2,g2,g2,g1,g1,g1,g1,g1,g1,g1,g1,w1,w1,g1},
				{g1,g1,g1,g1,g1,g1,g1,g1,g2,g1,g1,w1,w1,w1,g1},
				{g1,g1,r1,g1,g1,r1,r1,r1,g2,g2,g1,w1,w1,w1,g1},
				{r1,r1,r1,r1,g1,r1,r1,g2,g2,g2,g1,g1,w1,w1,g1},
				{r1,r1,r1,r1,r1,r1,r1,g2,g2,g2,g1,w1,w1,g1,g1},
				{w1,w1,r1,r1,g1,g1,g1,g1,g1,g1,g1,w1,g1,g1,g1},
				{w1,w1,w1,r1,g1,g1,g1,g1,g1,w1,w1,w1,g1,g1,g1},
				{g1,w1,r1,r1,r1,g1,g1,g1,g1,w1,w1,w1,g1,g1,g1},
				{g1,w1,w1,w1,w1,w1,g1,g1,g1,g1,g1,w1,w1,g1,g1},
				{g1,w1,w1,g1,w1,w1,g1,g1,g1,g1,g1,g1,w1,w1,g1},
				{g1,g1,g1,g1,w1,w1,g1,g1,g1,g1,g1,g1,w1,w1,g1},
				{g1,g1,g1,g1,w1,w1,g1,g1,g1,g1,g1,g1,w1,g1,g1},
				};
		
		Tile r = new BuildingTile(l, new Point(1,2), building);
		Tile d = new EntranceTile(null, new Point(1,2), null);
		Tile[][] rooms = {	{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
							{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
							{null,null,null,null,r,r,r,r,r,r,r, r,r,null,null},
							{null,null,null,null,d,r,r,r,r,r,r,r,r,null,null},
							{null,null,null,null,r,r,null,null,null,null,null,r,r,null,null},
							{null,null,null,null,r,r,null,null,null,null,null,r,r,null,null},
							{null,null,null,null,r,r,null,null,null,null,null,r,r,null,null},
							{null,null,null,null,r,r,null,null,null,null,null,r,r,null,null},
							{null,null,null,null,r,r,null,null,null,null,null,r,r,null,null},
							{null,null,null,null,r,d,null,null,null,null,null,r,r,null,null},
							{null,null,null,null,null,null,null,null,null,null,null,r,r,null,null},
							{null,null,null,null,null,null,null,null,null,d,r,r,r,null,null},
							{null,null,null,null,null,null,null,null,null,r,r,d,r,null,null},
							{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
							{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
						};
		
		l = new OutsideLocation("Test", "This is a test Location", tiles, rooms);
		
		
		

		// Rotates array depending on direction and then rendering
		switch(direction){
		case NORTH:
			isometric(tiles,rooms, offgc);
			break;
		case EAST:
			isometric(rotate(tiles), rotate(rooms), offgc);		
			break;			
		case SOUTH:
			isometric(rotate(rotate(tiles)), rotate(rotate(rooms)), offgc);		
			break;	
		case WEST:
			isometric(rotate(rotate(rotate(tiles))), rotate(rotate(rotate(rooms))), offgc);	
			break;
				
		}

			
			g.drawImage(offscreen,0,0,null);

		}
		
	
		
		/**
		 * Iterates through arrays of tiles drawing the map terrain, then iterates through
		 * room tiles to draw buildings depending on the arrangement of tiles.
		 * 
		 * new x position = x/2 + y/2 ( + or - constants to fit properly such as imageHeight)
		 * new y position = y/4 - x/4 ( + or - constants to fit properly such as imageHeight)
		 * 
		 * 
		 * @param tiles - 2D array of terrain to be drawn
		 * @param rooms - 2D array of rooms to be drawn
		 * @param g - the graphics
		 */
		public void isometric(Tile[][] tiles, Tile[][] rooms, Graphics g){

			Player p = new Player("Jim");
			Point playerPoint = new Point(5,5);
			
			g.fillRect(0,0,this.getWidth(), this.getHeight());
			
			// outside tiles
			for(int i = 0; i < tiles.length; i++){
				for(int j = tiles[i].length-1; j >=0 ; j--){
					Tile t = tiles[i][j];
					if(t!=null) {
						// DRAWING TERRAIN
						
						Image image = t.getImg();
						g.drawImage(image, (j*TILESIZE/2) + (i*TILESIZE/2), ((i*TILESIZE/4)-(j*TILESIZE/4)) + this.getHeight()/2 , null);
						
						// DRAWING PLAYER 
						if(t.getPos().equals(playerPoint)){
							Image playerImage = null;
							try {
								playerImage = ImageIO.read(new File("src/ui/images/player/0.png"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							
							g.drawImage(playerImage, (j*TILESIZE/2) + (i*TILESIZE/2) + playerImage.getWidth(null)/2, ((i*TILESIZE/4)-(j*TILESIZE/4)) + this.getHeight()/2  - playerImage.getHeight(null)/2, null);
						}
						
						// DRAWING ROOMS
						
						Tile r = rooms[i][j];
						if(r!=null) {					
							// Drawing 2 block high walls
							if(r instanceof EntranceTile){
								System.out.println("found door");
								if(j-1 >= 0 && rooms[i][j-1]==null){
									System.out.println("pls");
									g.drawImage(doorUD, (j*TILESIZE/2) + (i*TILESIZE/2), ((i*TILESIZE/4)-(j*TILESIZE/4)) + this.getHeight()/2 -TILESIZE/2, null);
								} else {
									g.drawImage(doorLR, (j*TILESIZE/2) + (i*TILESIZE/2), ((i*TILESIZE/4)-(j*TILESIZE/4)) + this.getHeight()/2 -TILESIZE/2, null);
								}
							}
							else{
								g.drawImage(building, (j*TILESIZE/2) + (i*TILESIZE/2), ((i*TILESIZE/4)-(j*TILESIZE/4)) + this.getHeight()/2 -TILESIZE/2, null);
							}
							g.drawImage(building, (j*TILESIZE/2) + (i*TILESIZE/2), ((i*TILESIZE/4)-(j*TILESIZE/4)) + this.getHeight()/2 -TILESIZE, null);
							

							image = building;
							
							// Western most point of building
							if(j-1 >= 0 && rooms[i][j-1] == null){
								image = roofUD;
							}

							// Northern most point of building
							if(i+1 < rooms.length && rooms[i+1][j]==null){
								image = roofLR;
							}

							// Outwards corner roof
							if(j-1 >= 0 && i+1<rooms.length && rooms[i][j-1]==null && rooms[i+1][j]==null){
								image = roofCornerO;
							}
							// Inwards corner roof
							if(j-1 >= 0 && i+1 != rooms.length && rooms[i+1][j-1]==null && rooms[i][j-1] != null && rooms[i+1][j]!=null){
								image = roofCornerI;
							}
							
						
							g.drawImage(image, (j*TILESIZE/2) + (i*TILESIZE/2), (int) (((i*TILESIZE/4)-(j*TILESIZE/4)) + this.getHeight()/2 - (TILESIZE*1.5)), null);

						}
					}
				}

			}
		}

		/**
		 * 
		 * Rotates given array of tiles 90 degrees counter-clockwise.
		 * @param tiles - array to be rotated
		 * @return rotated array
		 */
		public Tile[][] rotate(Tile[][] tiles){
			Tile[][] newTiles = new Tile[tiles.length][tiles[0].length];
			for(int i = 0; i < tiles.length; i++){
				for(int j = 0; j < tiles[i].length; j++){
					newTiles[(newTiles.length-1)-j][i] = tiles[i][j];
				}
			}
			return newTiles;
		}
		
		
		/**
		 * Sets direction of renderer. May possibly end up being stored in another class?
		 * @param d - new direction
		 */
		public void setDirection(Game.Direction d){
			direction = d;
		}
		
		/**
		 * @return current direction of renderer/camera
		 */
		public Direction getDirection(){
			return direction;
		}
}


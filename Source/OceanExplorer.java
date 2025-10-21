package application;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class OceanExplorer extends Application{
private final int dimension=10;//10*10 grid
private final int scale=50;

private OceanMap oceanMap;
private Ship ship;
private PirateShip pirate1;

private PirateShip pirate3;
private PirateShip pirate4;

private ImageView shipImageView;
private ImageView pirate1ImageView;
private ImageView pirate3ImageView;
private ImageView pirate4ImageView;

private AnchorPane root;
private Scene scene;
private boolean[][] oceanGrid;

public static void main(String[] args){
launch(args);
}
public void start(Stage oceanStage) {
System.out.println("====START METHOD CALLED====");
oceanMap=new OceanMap(dimension);
ship= new Ship(1,1,dimension);

pirate1 = new PirateShip(8,8, dimension, oceanMap);

pirate3 = new PirateShip(4,7,dimension, oceanMap);
pirate4 = new PirateShip(2,7,dimension, oceanMap);

ship.attach(pirate1);

ship.attach(pirate3);
ship.attach(pirate4);

System.out.println("Pirates registered as observers!");
oceanMap.setShip(ship);
oceanMap.placeIslands(10);
root=new AnchorPane();
scene=new Scene(root,dimension*scale, dimension* scale);
drawMap();
loadShipImage();
loadPirate1Image();
loadPirate3Image();
loadPirate4Image();

oceanStage.setScene(scene);
oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue");
oceanStage.show();
System.out.println("====WINOW SHOWN===");
startSailing();
}
private void drawMap() {
oceanGrid=oceanMap.getMap();
 for(int x=0;x<dimension; x++){
for( int y=0;y<dimension;y++){
	if(oceanGrid[x][y]) {
		try {
			Image islandImage =new Image("file:island.jpg",scale,scale,true,true);
			ImageView islandView=new ImageView(islandImage);
			islandView.setX(x*scale);
			islandView.setY(y*scale);
			root.getChildren().add(islandView);
		} catch(Exception e) {
			Rectangle rect =new Rectangle(x*scale,y*scale,scale,scale);
			rect.setStroke(Color.BLACK);
			rect.setFill(Color.GREEN);
			root.getChildren().add(rect);
		 }
		}else {
   
        Rectangle rect=new Rectangle(x * scale, y*scale, scale, scale);
         
        rect.setStroke(Color.BLACK);
         rect.setFill(Color.PALETURQUOISE);//ocean
         root.getChildren().add(rect);
          }
       }
 }
 System.out.println("Ocean map drawn successfully1");
}
private void loadShipImage() {
try {
Image shipImage=new Image("file:ship.png", scale,scale,true,true);
shipImageView=new ImageView (shipImage);
shipImageView.setX(ship.getShipLocation().x*scale);
shipImageView.setY(ship.getShipLocation().y*scale);
root.getChildren().add(shipImageView);
System.out.println("Ship image loaded successfully!");
} catch(Exception e) {
System.out.println("Could not load ship image. Using colored rectangle instead.");

System.out.println("Make sure ship.png is in the project root folder!");
Rectangle shipRect= new Rectangle(ship.getShipLocation().x* scale,ship.getShipLocation().y*scale,scale,scale);
shipRect.setFill(Color.RED);
root.getChildren().add(shipRect);
shipImageView = new ImageView();
shipImageView.setX(ship.getShipLocation().x * scale);
shipImageView.setY(ship.getShipLocation().y * scale);
}
} 

private void loadPirate1Image() {
	try {
		Image pirateImage=new Image("file:pirateShip.png",scale,scale,true,true);
		pirate1ImageView=new ImageView(pirateImage);
		
		pirate1ImageView.setX(pirate1.getLocation().x*scale);
		pirate1ImageView.setY(pirate1.getLocation().y*scale);
		root.getChildren().add(pirate1ImageView);
		System.out.println("Pirate Ship image loaded suceessfully");
	}
		catch(Exception e) {
			System.out.println("pirate imange not found");
			Rectangle pirateRect=new Rectangle( pirate1.getLocation().x*scale,pirate1.getLocation().y*scale,scale,scale);
			pirateRect.setFill(Color.BLACK);
			root.getChildren().add(pirateRect);
			pirate1ImageView=new ImageView();
			pirate1ImageView.setX(pirate1.getLocation().x*scale);
			pirate1ImageView.setY(pirate1.getLocation().y*scale);
		}
}
	
	private void loadPirate3Image() {
		try {
			Image pirateCharImage=new Image("file:pirateisland.png",scale,scale,true,true);
			pirate3ImageView=new ImageView(pirateCharImage);
			
			pirate3ImageView.setX(pirate3.getLocation().x*scale);
			pirate3ImageView.setY(pirate3.getLocation().y*scale);
			root.getChildren().add(pirate3ImageView);
			System.out.println("Pirate Ship image loaded suceessfully");
		}
			catch(Exception e) {
				System.out.println("pirate imange not found");
				Rectangle pirateRect=new Rectangle( pirate3.getLocation().x*scale,pirate3.getLocation().y*scale,scale,scale);
				
				root.getChildren().add(pirateRect);
				pirate3ImageView=new ImageView();
				pirate3ImageView.setX(pirate3.getLocation().x*scale);
				pirate3ImageView.setY(pirate3.getLocation().y*scale);
			}
	}
	
	private void loadPirate4Image() {
		try {
			Image pirateCharImage=new Image("file:pirateisland.png",scale,scale,true,true);
			pirate4ImageView=new ImageView(pirateCharImage);
			
			pirate4ImageView.setX(pirate4.getLocation().x*scale);
			pirate4ImageView.setY(pirate4.getLocation().y*scale);
			root.getChildren().add(pirate4ImageView);
			System.out.println("Pirate Ship image loaded suceessfully");
		}
			catch(Exception e) {
				System.out.println("pirate imange not found");
				Rectangle pirateRect=new Rectangle( pirate4.getLocation().x*scale,pirate4.getLocation().y*scale,scale,scale);
				
				root.getChildren().add(pirateRect);
				pirate4ImageView=new ImageView();
				pirate4ImageView.setX(pirate4.getLocation().x*scale);
				pirate4ImageView.setY(pirate4.getLocation().y*scale);
			}
	}
private void startSailing(){
scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
public void handle(KeyEvent ke){
 switch(ke.getCode()){
case RIGHT:
ship.goEast(oceanMap);
break;
case LEFT:
ship.goWest(oceanMap);
break;
case UP:
ship.goNorth(oceanMap);
break;
case DOWN:
ship.goSouth(oceanMap);
break;
 default:
break;
 }
//ship position
shipImageView.setX(ship.getShipLocation().x*scale);
shipImageView.setY(ship.getShipLocation().y*scale);

pirate1ImageView.setX(pirate1.getLocation().x*scale);
pirate1ImageView.setY(pirate1.getLocation().y*scale);


}
 });
System.out.println("keyboard controls activated");
System.out.println("up arrow:move north");
System.out.println("down arrow:move south");
System.out.println("left arrow:move west");
System.out.println("right arrow move east");

 }
}
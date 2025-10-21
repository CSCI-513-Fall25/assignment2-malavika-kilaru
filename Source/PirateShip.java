package application;
import java.awt.Point;

public class PirateShip implements ShipObserver {
 private Point currentLocation;
 private Point targetLocation;
 private int dimension;
 private OceanMap oceanMap;

public PirateShip(int x, int y ,int dimension, OceanMap oceanMap){
 this.currentLocation=new Point(x,y);
 this.dimension=dimension;
 this.targetLocation=new Point(x,y);
 this.oceanMap=oceanMap;
}

public Point getLocation() {
 return currentLocation;
}

public void update(Point shipLocation) {
 targetLocation =new Point(shipLocation.x ,shipLocation.y);
chase();
}

private void chase() {
 int dx=targetLocation.x -currentLocation.x;
 int dy=targetLocation.y -currentLocation.y;
 
 if(dx==0&& dy==0) {
	 return;
}

  if(Math.abs(dx)>Math.abs(dy)) {
 if(dx>0) {
   moveEast();
} else if(dx<0) {
  moveWest();
 }
}else if(Math.abs(dy)>0) {
 if(dy>0) {
   moveSouth();
} else if(dy<0) {
   moveNorth();
  }
 }
}
private void moveNorth() {
	int newY=currentLocation.y-1;
	if(newY>=0 && !oceanMap.isIsland(currentLocation.x,newY)&& !isColumbusAt(currentLocation.x,newY)) {
 
  currentLocation.y--;
 } 
}
private boolean isColumbusAt(int x, int y) {
	Point shipLocation=oceanMap.getShipLocation();
	if(shipLocation!=null) {
		return(shipLocation.x==x&&shipLocation.y==y);
	}
	return false;
}

private void moveSouth() {
	int newY=currentLocation.y+1;
	if(newY<dimension && oceanMap.isIsland(currentLocation.x, newY)&& !isColumbusAt(currentLocation.x,newY))
   {
   currentLocation.y++;
 }
}
private void moveEast() {
	int newX=currentLocation.x+1;
	if(newX<dimension && oceanMap.isIsland(currentLocation.y, newX)&& !isColumbusAt(currentLocation.y,newX))
   {
 
  currentLocation.x++;
 }
}
private void moveWest() {
	int newX=currentLocation.x-1;
	if(newX>=0 && !oceanMap.isIsland(currentLocation.y, newX)&& !isColumbusAt(currentLocation.y,newX))
   {
 
  currentLocation.x--;
  }
 }
}
 

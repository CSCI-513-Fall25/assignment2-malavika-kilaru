package application;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Ship {
  private Point currentLocation;
  private int dimension;
  private List <ShipObserver> observers;

public Ship(int x, int y, int dimension) {
 this.currentLocation = new Point(x, y);
 this.dimension=dimension;
 this.observers=new ArrayList<>();
}

public Point getShipLocation() {
  return currentLocation;
}
 

public void attach(ShipObserver observer) {
 observers.add(observer);
}

public void detach(ShipObserver observer) {
 observers.remove(observer);
}
 private void notifyObservers() {
  for( ShipObserver observer :observers) {
   observer.update(currentLocation);
 }
}
// moves north(up)
public void goNorth(OceanMap map) {
  int newY=currentLocation.y-1;

if(newY>=0 && !map.isIsland(currentLocation.x,newY)) {
  currentLocation.y=newY;
   notifyObservers();
  }
 }
//moves south(down)
public void goSouth(OceanMap map) {
 int newY=currentLocation.y+1;

if(newY <dimension && !map.isIsland(currentLocation.x,newY)) {
  currentLocation.y=newY;
  notifyObservers();
 }
}
//moves east(right)
public void goEast(OceanMap map)
{
  int newX=currentLocation.x+1;
 
   if(newX<dimension && !map.isIsland(newX,currentLocation.y)) {
    currentLocation.x=newX;
    notifyObservers();
 }
}
 //moves west(left)
public void goWest(OceanMap map) {
  int newX=currentLocation.x-1;

if(newX>=0&&!map.isIsland(newX,currentLocation.y)) {
  currentLocation.x=newX;
 notifyObservers();
  }
 }
}

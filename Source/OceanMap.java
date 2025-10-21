package application;

import java.awt.Point;
import java.util.Random;

public class OceanMap{
  private boolean[][] myGrid;
  private int dimension;
  private Ship ship;
  private Random random;


public OceanMap(int dimension) {
 this.dimension=dimension;
 this.myGrid=new boolean[dimension][dimension];
 this.random=new Random();


for(int i=0;i<dimension;i++) {
  for(int j=0;j<dimension;j++) {
   myGrid[i][j]=false;
   }
 }
}

public boolean[][] getMap() {
  return myGrid;
}

public void setShip(Ship ship) {
   this.ship=ship;
}
  
public Point getShipLocation() {
  if(ship !=null) {
   return ship.getShipLocation();
 }
return null;
}
//random island
public void placeIslands(int count) {
  int islandsPlaced=0;
 while(islandsPlaced< count) {
   int x=random.nextInt(dimension);
    int y=random.nextInt(dimension);


   if(!myGrid[x][y] && (ship ==null || (x != ship.getShipLocation().x||y !=ship.getShipLocation().y))) {
  myGrid[x][y]=true;
   islandsPlaced++;
   }
  }
}

public Boolean isIsland(int x,int y) {
  if(x>=0 && x< dimension && y>=0 && y<dimension) {
    return myGrid[x][y];
}
 return false;
}
 public boolean isINBounds(int x,int y) {
	 return x>=0&&x<dimension&&y>=0&&y<dimension;
 
 }
}
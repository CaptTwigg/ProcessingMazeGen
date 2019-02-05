import processing.core.PApplet;

public class Main extends PApplet {
  public static PApplet processing;
  boolean[][] mazeArray;
  boolean[][] visitedArray;

  int boxWidth = 20;
  int boxHeight = 20;
  int worldWidth = 20;
  int worldHeight = 20;
  int playerPosX = worldWidth / 2;
  int playerPosY = worldHeight / 2;

  public static void main(String[] args) {
    PApplet.main(Main.class, args);
  }


  @Override
  public void settings() {
    size(worldWidth * boxWidth, worldHeight * boxHeight);  // Size should be the first statement

  }

  @Override
  public void setup() {
    processing = this;
    background(100);
    mazeArray = new boolean[worldWidth][worldHeight];

    for (int x = 0; x < worldWidth; x++) {
      for (int y = 0; y < worldHeight; y++) {
//        if((x == 0 || x == mazeArray.length-1) || (y == 0 || y == height) ){
//          mazeArray[x][y] = true;
//          continue;
//        }
//        float ran = random(1);
//        if(ran <.5)
        mazeArray[x][y] = true;


      }
    }
    mazeArray[playerPosX][playerPosY] = false;
  }

  public void draw() {
    for (int x = 0; x < worldWidth; x++) {
      for (int y = 0; y < worldHeight; y++) {

        if (mazeArray[x][y]) {
          noFill();
          stroke(0);
          rect(x * 20, y * 20, boxWidth, boxHeight);

        }
//        else {
//          stroke(255);
//          rect(x * 20, y * 20, boxWidth, boxHeight);
//        }
        fill(0);
        stroke(255);
        rect((playerPosX) * boxWidth, (playerPosY) * boxHeight, boxWidth, boxHeight);
      }
    }
  }

  public void keyPressed() {
    if (key == CODED) {
//      mazeArray[playerPosX][playerPosY] = true;
      if (keyCode == UP & playerPosY > 0) {
        mazeArray[playerPosX][playerPosY - 1] = false;
        playerPosY--;
      } else if (keyCode == DOWN & playerPosY < worldHeight - 1) {
        mazeArray[playerPosX][playerPosY + 1] = false;
        playerPosY++;
      } else if (keyCode == LEFT & playerPosX > 0) {
        mazeArray[playerPosX - 1][playerPosY] = false;
        playerPosX--;
      } else if (keyCode == RIGHT & playerPosX < worldWidth - 1) {
        mazeArray[playerPosX + 1][playerPosY] = false;
        playerPosX++;
      }
    } else {
      System.out.println("Key not coded");
    }
  }
}

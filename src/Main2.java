import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;

public class Main2 extends PApplet {
  public static PApplet processing;


  int wallLength = 20;
  ArrayList<Cell> grid = new ArrayList<>();
  Cell currentCell;
  int cols;
  int rows;

  public static void main(String[] args) {
    PApplet.main(Main2.class, args);
  }


  @Override
  public void settings() {
    size(100, 100);  // Size should be the first statement
    cols = width / wallLength;
    rows = height / wallLength;
  }

  @Override
  public void setup() {
    processing = this;
    background(51);
    frameRate(5);


    for (int x = 0; x < cols; x++) {
      for (int y = 0; y < rows; y++) {
        grid.add(new Cell(x, y));
      }
    }
    currentCell = grid.get(0);
  }

  public void draw() {

    for (int i = 0; i < grid.size(); i++) {
      grid.get(i).show();
    }

    currentCell.visited = true;

    currentCell = currentCell.checkNeighbors();

  }


  // Cell class
  //
  public class Cell {
    int x, y;
    boolean walls[] = {true, true, true, true};
    boolean visited = false;
    int num = 1;


    public Cell(int x, int y) {
      this.x = x;
      this.y = y;

    }

    public int index(int x, int y) {
      if (x < 0 | y < 0 | x > cols - 1 | y > rows - 1)
        return -1;
      return x + y * cols;
    }

    public Cell checkNeighbors() {
      ArrayList<Cell> neighbors = new ArrayList<>();

      int topIndex = index(x, y - 1);
      int rightIndex = index(x + 1, y);
      int bottomIndex = index(x, y + 1);
      int leftIndex = index(x - 1, y);
      Cell top;
      Cell right;
      Cell bottom;
      Cell left;

      if (topIndex != -1) {
        top = grid.get(topIndex);
        if (!top.visited) {
          neighbors.add(top);
        }
      }

      if (rightIndex != -1) {
        right = grid.get(rightIndex);
        if (!right.visited) {
          neighbors.add(right);
        }
      }

      if (bottomIndex != -1) {
        bottom = grid.get(bottomIndex);
        if (!bottom.visited) {
          neighbors.add(bottom);
        }
      }

      if (leftIndex != -1) {
        left = grid.get(leftIndex);
        if (!left.visited) {
          neighbors.add(left);
        }
      }

      if (neighbors.size() > 0) {
        num++;
        int randomN = floor(random(0, neighbors.size()));
        System.out.println(neighbors.get(randomN));
        return neighbors.get(randomN);
      }
      return null;
    }

    public void show() {
      int x = this.x * wallLength;
      int y = this.y * wallLength;
      noFill();
      stroke(255);
      if (walls[0])
        line(x, y, x + wallLength, y);
      if (walls[1])
        line(x + wallLength, y, x + wallLength, y + wallLength);
      if (walls[2])
        line(x + wallLength, y + wallLength, x, y + wallLength);
      if (walls[3])
        line(x, y + wallLength, x, y);

      if (visited) {
        fill(0, 255, 0);
        rect(x, y, wallLength, wallLength);
        fill(0);
        text(num, x + 5, y - 5);
      }

    }

    @Override
    public String toString() {
      return String.format("x: %s y: %s walls: %s visited: %s "
        , this.x, this.y, Arrays.toString(this.walls), this.visited);
    }
  }
}

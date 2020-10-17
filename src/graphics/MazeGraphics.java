package graphics;

import Model.MazePoint;
import javafx.scene.layout.*;

public class MazeGraphics extends GridPane {
    public MazeGraphics(MazePoint[][] maze, int width, int height, ProblemInterface problemInstance) {

        int size = maze.length;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                MazePointGraphics square = new MazePointGraphics((double)width/size, (double)height/size, maze[row][col].isWall(), row, col, problemInstance);
                add(square, col, row);
            }
        }

    }
}

package graphics;

import Model.MazePoint;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;
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

    public void drawPath(List<MazePoint> pointList) {
        pointList.subList(1, pointList.size()).forEach(x -> {
            MazePointGraphics square = getNodeFromGrid(x.getY(), x.getX());
            if(square != null) {
                square.setFill(Color.GREEN);
                square.isImportantPoint = true;
            }
        });
    }

    private MazePointGraphics getNodeFromGrid(int col, int row) {
        for (Node node : getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (MazePointGraphics)node;
            }
        }
        return null;
    }
}

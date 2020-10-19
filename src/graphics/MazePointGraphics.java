package graphics;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MazePointGraphics extends Rectangle {
    public boolean isImportantPoint;
    public MazePointGraphics(double width, double height, boolean isWall, int row, int col, ProblemInterface problemInstance) {
        super(width, height);
        isImportantPoint = false;
        if (isWall) {
            setFill(Color.BLACK);
        } else {
            setFill(Color.WHITE);
            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(!isImportantPoint) {
                        setFill(Color.YELLOW);
                    }
                }
            });
            setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(problemInstance.getFirstPoint() == null) {
                        problemInstance.setFirstPoint(row, col);
                        setFill(Color.RED);
                        isImportantPoint = true;
                    } else if(problemInstance.getSecondPoint() == null) {
                        problemInstance.setLastPoint(row, col);
                        setFill(Color.BLUE);
                        isImportantPoint = true;
                    }
                }
            });
            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(!isImportantPoint) {
                        setFill(Color.WHITE);
                    }
                }
            });
        }
    }
}

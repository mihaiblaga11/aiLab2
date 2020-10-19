package graphics;

import Model.MazePoint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import solvingAlgorithms.DFSSolution;
import solvingAlgorithms.HillClimbSolution;
import java.util.List;

public class MazeProblemGraphics extends HBox {
    private MazeGraphics mazeGraphics;
    public MazeProblemGraphics(MazePoint[][] maze, int width, int height, ProblemInterface problemInstance) {
        mazeGraphics = new MazeGraphics(maze, width, height, problemInstance);
        Button DFSButton = new Button("DFS Solve");
        DFSButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                problemInstance.solve(new DFSSolution(maze, problemInstance.getFirstPoint(), problemInstance.getSecondPoint()));
            }
        });
        Button HillclimbButton = new Button("Hillclimb Solve");
        HillclimbButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                problemInstance.solve(new HillClimbSolution(maze, problemInstance.getFirstPoint(), problemInstance.getSecondPoint()));
            }
        });
        VBox buttonsBox = new VBox();
        buttonsBox.getChildren().addAll(DFSButton, HillclimbButton);
        getChildren().addAll(mazeGraphics, buttonsBox);
    }

    public void showPath(List<MazePoint> path) {
        this.mazeGraphics.drawPath(path);
    }
}

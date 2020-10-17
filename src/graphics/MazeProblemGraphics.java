package graphics;

import Model.MazePoint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import solvingAlgorithms.DFSSolution;
import solvingAlgorithms.HillClimbSolution;

public class MazeProblemGraphics extends HBox {
    public MazeProblemGraphics(MazePoint[][] maze, int width, int height, ProblemInterface problemInstance) {
        MazeGraphics mazeGraphics = new MazeGraphics(maze, width, height, problemInstance);
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
}

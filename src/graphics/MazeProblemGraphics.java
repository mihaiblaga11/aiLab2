package graphics;

import Model.MazePoint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import solvingAlgorithms.BFSSolution;
import solvingAlgorithms.BacktrackingSolution;
import solvingAlgorithms.HillClimbSolution;
import java.util.List;

public class MazeProblemGraphics extends HBox {
    private MazeGraphics mazeGraphics;
    public MazeProblemGraphics(MazePoint[][] maze, int width, int height, ProblemInterface problemInstance) {
        mazeGraphics = new MazeGraphics(maze, width, height, problemInstance);
        Button BacktrackingButton = new Button("Backtracking Solve");
        BacktrackingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("backtracking call");
                problemInstance.solve(new BacktrackingSolution(maze, problemInstance.getFirstPoint(), problemInstance.getSecondPoint()));
            }
        });
        Button HillclimbButton = new Button("Hillclimb Solve");
        HillclimbButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hillclimb call");
                problemInstance.solve(new HillClimbSolution(maze, problemInstance.getFirstPoint(), problemInstance.getSecondPoint()));
            }
        });
        Button BFSButton = new Button("BFS Solve");
        BFSButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("bfs call");
                problemInstance.solve(new BFSSolution(maze, problemInstance.getFirstPoint(), problemInstance.getSecondPoint()));
            }
        });
        VBox buttonsBox = new VBox();
        buttonsBox.getChildren().addAll(BacktrackingButton, BFSButton, HillclimbButton);
        getChildren().addAll(mazeGraphics, buttonsBox);
    }

    public void showPath(List<MazePoint> path) {
        this.mazeGraphics.drawPath(path);
    }
}

import Model.CurrentState;
import Model.MazePoint;
import graphics.MazeGraphics;
import graphics.MazePointGraphics;
import graphics.MazeProblemGraphics;
import graphics.ProblemInterface;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mazeGenerator.MazeGenerator;
import solvingAlgorithms.HillClimbSolution;
import solvingAlgorithms.Solution;

import  java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class Problem extends Application implements ProblemInterface {
    private static MazePoint[][] maze;
    private static MazePoint firstPoint, lastPoint;
    private static final int dimensions = 10;
    private static CurrentState currentState;
    private static MazeProblemGraphics graphics;
    public static void main(String[] args) {

        maze = new MazePoint[dimensions][dimensions];
        MazeGenerator mazeGenerator = new MazeGenerator(dimensions);
        mazeGenerator.generateMaze();
        int[][] rawMaze = mazeGenerator.getMaze();

        for (int x = 0; x < rawMaze.length; x++)
            for (int y = 0; y < rawMaze.length; y++)
                maze[x][y] = new MazePoint(x, y, rawMaze[x][y] == 1);


        for (MazePoint[] mazeRow : maze)
            System.out.println(Arrays.toString(Arrays.stream(mazeRow).map(x -> x.isWall() ? 1 : 0).toArray()));
        launch(args);

        HillClimbSolution dfsSolution = new HillClimbSolution(maze, firstPoint, lastPoint);
        System.out.println(dfsSolution.getSolution());
    }

    public static MazePoint setPoint() {
        System.out.print("Enter point coordinates: ");
        Scanner scan = new Scanner(System.in);
        MazePoint point = maze[scan.nextInt()][scan.nextInt()];
        while (point.isWall()) {
            System.out.println("That is a wall. Try again!");
            System.out.print("Enter point coordinates: ");
            point = maze[scan.nextInt()][scan.nextInt()];
        }
        System.out.println("Point set!");
        return point;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final int height = 500;
        final int width = 500;
        graphics = new MazeProblemGraphics(maze, width, height, this);
        primaryStage.setHeight(height+38);
        primaryStage.setWidth(width+210);
        primaryStage.setScene(new Scene(graphics, width, height));
        primaryStage.show();
    }

    @Override
    public void setFirstPoint(int row, int col) {
        firstPoint = maze[row][col];
        currentState = new CurrentState(firstPoint.getX(), firstPoint.getY());
    }

    @Override
    public void setLastPoint(int row, int col) {
        lastPoint = maze[row][col];
    }

    @Override
    public MazePoint getFirstPoint() {
        return  firstPoint;
    }

    @Override
    public MazePoint getSecondPoint() {
        return  lastPoint;
    }

    @Override
    public void solve(Solution solution) {
        List<MazePoint> path = solution.getSolution();
        System.out.println(path);
        graphics.showPath(path);
    }
}

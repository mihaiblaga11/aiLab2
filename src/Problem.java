import Model.CurrentState;
import Model.MazePoint;
import mazeGenerator.MazeGenerator;
import solvingAlgorithms.BFSSolution;
import solvingAlgorithms.DFSSolution;
import solvingAlgorithms.Solution;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Problem {
    private static MazePoint[][] maze;
    private static MazePoint firstPoint, lastPoint;
    private static final int dimensions = 6;
    private static CurrentState currentState;

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

        firstPoint = setPoint();
        currentState = new CurrentState(firstPoint.getX(), firstPoint.getY());
        lastPoint = setPoint();

        Solution solution = new DFSSolution(maze, firstPoint, lastPoint);
        List<MazePoint> path = solution.getSolution();

        if (path.isEmpty()) {
            System.out.println("There is no possible solution!");
        } else {
            for (int i = 0; i < path.size() - 1; i++) {
                System.out.print(path.get(i) + " -> ");
            }
            System.out.println(path.get(path.size() - 1));
        }
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
}

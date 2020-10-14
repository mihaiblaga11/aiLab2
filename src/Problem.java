import Model.CurrentState;
import Model.MazePoint;
import mazeGenerator.MazeGenerator;

import java.util.Arrays;
import java.util.Scanner;

public class Problem {
    private static MazePoint[][] maze;
    private static MazePoint firstPoint, lastPoint;
    private static final int dimensions = 30;
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
    }

    public static MazePoint setPoint() {
        Scanner scan = new Scanner(System.in);
        MazePoint point = maze[scan.nextInt()][scan.nextInt()];
        while (point.isWall()) {
            System.out.println("that is a wall try again!");
            point = maze[scan.nextInt()][scan.nextInt()];
        }
        System.out.println("Point set!");
        return point;
    }

    public static boolean isFinalPoint(MazePoint point) {
        return point.getX() == lastPoint.getX() && point.getY() == lastPoint.getY();
    }
}

package solvingAlgorithms;

import Model.MazePoint;

import java.util.ArrayList;
import java.util.List;

public class DFSSolution extends Solution {

    private final List<MazePoint> path;
    private final boolean[][] visited;

    public DFSSolution(MazePoint[][] maze, MazePoint startPoint, MazePoint endPoint) {
        super(maze, startPoint, endPoint);
        path = new ArrayList<>();
        visited = new boolean[maze.length][maze.length];
    }

    @Override
    public List<MazePoint> getSolution() {
        boolean found = findPath(startPoint, visited);
        return path;
    }

    private boolean findPath(MazePoint currentPoint, boolean[][] visited) {
        path.add(currentPoint);
        if (currentPoint == endPoint) {
            return true;
        }
        for (int i = 0; i < dX.length; i++) {
            MazePoint point = maze[currentPoint.getX() + dX[i]][currentPoint.getY() + dY[i]];
            if (isValidPoint(point.getX(), point.getY(), visited)) {
                visited[point.getX()][point.getY()] = true;
                boolean found = findPath(point, visited);
                if (found)
                    return true;
                path.remove(point);
            }
        }

        return false;
    }

    private boolean isValidPoint(int x, int y, boolean[][] visited) {
        return x > 0 && x < maze.length && y > 0 && y < maze.length && !maze[x][y].isWall() && !visited[x][y];
    }
}

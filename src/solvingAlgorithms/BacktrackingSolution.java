package solvingAlgorithms;
import Model.MazePoint;
import java.util.ArrayList;
import java.util.List;
public class BacktrackingSolution extends Solution {
    private final List<MazePoint> path;
    private final boolean[][] visited;
    public BacktrackingSolution(MazePoint[][] maze, MazePoint startPoint, MazePoint endPoint) {
        super(maze, startPoint, endPoint);
        path = new ArrayList<>();
        visited = new boolean[maze.length][maze.length];
    }
    @Override
    public List<MazePoint> getSolution() {
        boolean found = findPath(startPoint);
        if (!found) {
            return new ArrayList<>();
        }
        return path;
    }
    private boolean findPath(MazePoint currentPoint) {
        path.add(currentPoint);
        if (currentPoint == endPoint) {
            return true;
        }
        for (int i = 0; i < dX.length; i++) {
            if (isValidPoint(currentPoint.getX() + dX[i], currentPoint.getY() + dY[i], visited)) {
                MazePoint point = maze[currentPoint.getX() + dX[i]][currentPoint.getY() + dY[i]];
                visited[point.getX()][point.getY()] = true;
                boolean found = findPath(point);
                if (found)
                    return true;
                path.remove(point);
            }
        }
        return false;
    }
}
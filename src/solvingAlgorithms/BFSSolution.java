package solvingAlgorithms;
import Model.MazePoint;
import java.util.*;
public class BFSSolution extends Solution {
    private final List<MazePoint> path;
    private final boolean[][] visited;
    public BFSSolution(MazePoint[][] maze, MazePoint startPoint, MazePoint endPoint) {
        super(maze, startPoint, endPoint);
        path = new ArrayList<>();
        visited = new boolean[maze.length][maze.length];
    }
    @Override
    public List<MazePoint> getSolution() {
        Queue<MazePoint> pointQueue = new LinkedList<>();
        pointQueue.add(startPoint);
        Map<MazePoint, MazePoint> parents = new HashMap<>();
        parents.put(startPoint, null);
        while (!pointQueue.isEmpty()) {
            MazePoint currentPoint = pointQueue.remove();
            if (currentPoint == endPoint) {
                computePath(parents);
                return path;
            }
            for (int i = 0; i < dX.length; i++) {
                if (isValidPoint(currentPoint.getX() + dX[i], currentPoint.getY() + dY[i], visited)) {
                    MazePoint point = maze[currentPoint.getX() + dX[i]][currentPoint.getY() + dY[i]];
                    visited[point.getX()][point.getY()] = true;
                    if (!parents.containsKey(point)) {
                        parents.put(point, currentPoint);
                    }
                    pointQueue.add(point);
                }
            }
        }
        return new ArrayList<>();
    }
    private void computePath(Map<MazePoint, MazePoint> parentsMap) {
        MazePoint point = endPoint;
        while (point != null) {
            path.add(point);
            point = parentsMap.get(point);
        }
        Collections.reverse(path);
    }
}
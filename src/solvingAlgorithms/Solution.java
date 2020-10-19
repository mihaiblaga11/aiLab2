package solvingAlgorithms;

import Model.MazePoint;

import java.util.List;

public abstract class Solution {

    public final int[] dX = {0, 1, 0, -1};
    public final int[] dY = {1, 0, -1, 0};

    protected MazePoint[][] maze;
    protected MazePoint startPoint, endPoint;

    public Solution(MazePoint[][] maze, MazePoint startPoint, MazePoint endPoint) {
        this.maze = maze;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    protected boolean isValidPoint(int x, int y, boolean[][] visited) {
        return x > 0 && x < maze.length && y > 0 && y < maze.length && !maze[x][y].isWall() && !visited[x][y];
    }

    public abstract List<MazePoint> getSolution();
}

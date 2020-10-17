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

    public abstract List<MazePoint> getSolution();
}

package solvingAlgorithms;

import Model.MazePoint;

import java.util.List;

public interface Solution {
    List<MazePoint> getSolution(MazePoint[][] maze, MazePoint start, MazePoint end);
}

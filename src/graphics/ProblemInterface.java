package graphics;

import Model.MazePoint;
import solvingAlgorithms.Solution;

public interface ProblemInterface {
    void setFirstPoint(int row, int col);
    void setLastPoint(int row, int col);
    MazePoint getFirstPoint();
    MazePoint getSecondPoint();
    void solve(Solution solution);
}

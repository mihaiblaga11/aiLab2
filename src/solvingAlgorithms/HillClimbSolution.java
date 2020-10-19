package solvingAlgorithms;

import Model.MazePoint;

import java.util.ArrayList;
import java.util.List;

public class HillClimbSolution extends  Solution {

    public HillClimbSolution(MazePoint[][] maze, MazePoint startPoint, MazePoint endPoint) {
        super(maze, startPoint, endPoint);
    }

    @Override
    public List<MazePoint> getSolution() {
        List<MazePoint> path = new ArrayList<>();
        MazePoint currentState = startPoint;
        path.add(currentState);
        while (currentState.getX() != endPoint.getX() && currentState.getY() != endPoint.getY()) {
            boolean foundNextState = false;
            for(MazePoint state : this.getNextPossibleStates(currentState)) {
                if(isBetter(state, currentState)) {
                    path.add(state);
                    currentState = state;
                    foundNextState = true;
                }
            }
            if(!foundNextState) {
                return path;
            }
        }
        path.add(endPoint);
        return path;
    }

    private List<MazePoint> getNextPossibleStates(MazePoint currentState) {

        //de completat cu ce era pe tutorial
        List<MazePoint> nextPosibleStates = new ArrayList<>();
        if(currentState.getX() > 0 &&  !maze[currentState.getX()-1][currentState.getY()].isWall()) {
            nextPosibleStates.add(maze[currentState.getX()-1][currentState.getY()-1]);
        }
        if(currentState.getY() < maze.length-1 && !maze[currentState.getX()][currentState.getY()+1].isWall()) {
            nextPosibleStates.add(maze[currentState.getX()][currentState.getY()+1]);
        }
        if(currentState.getX() < maze.length-1 && !maze[currentState.getX()+1][currentState.getY()].isWall()) {
            nextPosibleStates.add(maze[currentState.getX()+1][currentState.getY()]);
        }
        if(currentState.getY() > 0 && !maze[currentState.getX()][currentState.getY()-1].isWall()) {
            nextPosibleStates.add( maze[currentState.getX()][currentState.getY()-1]);
        }
        return nextPosibleStates;
    }

    private boolean isBetter(MazePoint firstState, MazePoint secondState) {
        int firstValue = this.getStateValue(firstState);
        int secondValue = this.getStateValue(secondState);
        if(firstValue == secondValue) {
            return firstState.getX() > secondState.getX();
        }
        return firstValue < secondValue;
    }

    private int getStateValue(MazePoint state) {
        return Math.abs(endPoint.getX()-state.getX()) + Math.abs(endPoint.getY()-state.getY());
    }
}

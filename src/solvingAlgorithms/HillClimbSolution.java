package solvingAlgorithms;

import Model.MazePoint;

import java.util.ArrayList;
import java.util.Arrays;
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
        while (currentState.getX() != endPoint.getX() || currentState.getY() != endPoint.getY()) {
            boolean foundNextState = false;
            System.out.println("current in " + currentState + " endpoint " + endPoint);
            System.out.println(currentState.getX() != endPoint.getX() && currentState.getY() != endPoint.getY());
            List<MazePoint> nextPosibleStates = this.getNextPossibleStates(currentState);
            System.out.println("can go in" + Arrays.toString(nextPosibleStates.toArray()));
            for(MazePoint state : nextPosibleStates) {
                System.out.println("check" + state);
                if(isBetter(state, currentState)) {
                    path.add(maze[state.getX()][ state.getY()]);
                    System.out.println("found");
                    System.out.println(state);
                    currentState = state;
                    foundNextState = true;
                    break;
                }
            }
            System.out.println(currentState);
            System.out.println(currentState.getX() != endPoint.getX() || currentState.getY() != endPoint.getY());
            if(foundNextState) {
                continue;
            }
            if(!foundNextState) {
                return  path;
            }
        }
        return path;
    }

    private List<MazePoint> getNextPossibleStates(MazePoint currentState) {

        //de completat cu ce era pe tutorial
        List<MazePoint> nextPosibleStates = new ArrayList<>();
        if(currentState.getX() > 0 &&  !maze[currentState.getX()-1][currentState.getY()].isWall()) {
            nextPosibleStates.add(maze[currentState.getX()-1][currentState.getY()]);
        }
        if(currentState.getY() < maze.length && !maze[currentState.getX()][currentState.getY()+1].isWall()) {
            nextPosibleStates.add(maze[currentState.getX()][currentState.getY()+1]);
        }
        if(currentState.getX() < maze.length && !maze[currentState.getX()+1][currentState.getY()].isWall()) {
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
        System.out.println("is better");
        System.out.print(firstValue);
        System.out.print(secondValue);
        System.out.println();
        if(firstValue == secondValue) {
            return firstState.getX() > secondState.getX();
        }
        return firstValue < secondValue;
    }

    private int getStateValue(MazePoint state) {
        return Math.abs(endPoint.getX()-state.getX()) + Math.abs(endPoint.getY()-state.getY());
    }
}

public class CurrentState extends MazePoint {

    public CurrentState(int x, int y) {
        super(x, y, false);
    }

    public boolean setNextState(MazePoint nextPoint, MazePoint[][] maze) {
        if (MazePoint.isValid(nextPoint.getX(), nextPoint.getY(), maze) && this.getNeighbors().contains(nextPoint)) {
            this.x = nextPoint.getX();
            this.y = nextPoint.getY();
            this.neighbors = nextPoint.getNeighbors();
            return true;
        }
        return false;
    }
}

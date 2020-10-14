package Model;

public class MazePoint {
    public static int[] movementX = {-1, 0, 1, 0};
    public static int[] movementY = {0, -1, 0, 1};
    protected int x;
    protected int y;
    private final boolean isWall;

    public MazePoint(int x, int y, boolean isWall1) {
        this.x = x;
        this.y = y;
        this.isWall = isWall1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWall() {
        return isWall;
    }

    public static boolean isValid(int pointX, int pointY, MazePoint[][] maze) {
        return pointX >= 0 && pointX < maze.length && pointY >= 0 && pointY < maze.length && !maze[pointX][pointY].isWall();
    }
}

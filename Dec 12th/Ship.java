public class Ship {
    protected int facing = 0;
    protected int northDistance = 0;
    protected int eastDistance = 0;

    public int getManhattanDistance() {
        return Math.abs(northDistance) + Math.abs(eastDistance);
    }

    public void turnRight(int degrees) {
        facing += degrees;
        if (facing >= 360)
            facing -= 360;
        else if (facing < 0)
            facing += 360;
    }

    public void moveNorth(int distance) {
        northDistance += distance;
    }

    public void moveEast(int distance) {
        eastDistance += distance;
    }

    public void moveForward(int distance) {
        switch (facing) {
            case 0:
                eastDistance += distance;
                break;
            case 90:
                northDistance -= distance;
                break;
            case 180:
                eastDistance -= distance;
                break;
            case 270:
                northDistance += distance;
                break;
        }
    }
}

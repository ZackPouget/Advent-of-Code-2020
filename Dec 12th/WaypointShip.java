public class WaypointShip extends Ship {
    private int shipEastDistance = 0;
    private int shipNorthDistance = 0;

    WaypointShip() {
        eastDistance = 10;
        northDistance = 1;
    }

    @Override
    public int getManhattanDistance() {
        return Math.abs(shipEastDistance) + Math.abs(shipNorthDistance);
    }

    @Override
    public void moveForward(int distance) {
        shipEastDistance += distance * eastDistance;
        shipNorthDistance += distance * northDistance;
    }

    @Override
    public void turnRight(int degrees) {
        if (degrees < 0)
            degrees += 360;
        else if (degrees >= 360)
            degrees -= 360;
        
        int temp;
        switch (degrees) {
            case 90:
                temp = -eastDistance;
                eastDistance = northDistance;
                northDistance = temp;
                break;
            case 180:
                eastDistance *= -1;
                northDistance *= -1;
                break;
            case 270:
                temp = -northDistance;
                northDistance = eastDistance;
                eastDistance = temp;
                break;
        }
    }
}

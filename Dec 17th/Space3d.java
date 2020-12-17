public class Space3d {
    private int size;
    private boolean[][][] space;

    public Space3d(int size) {
        this.size = size;
        space = new boolean[size][size][size];
    }

    public void setState(int x, int y, int z, boolean state) {
        space[x + size/2][y + size/2][z + size/2] = state;
    }

    public boolean getState(int x, int y, int z) {
        if (x + size/2 < 0 || x + size/2 >= size || y + size/2 < 0 || y + size/2 >= size || z + size/2 < 0 || z + size/2 >= size)
            return false;
        return space[x + size/2][y + size/2][z + size/2];
    }

    public int getActiveNeighbors(int x, int y, int z) {
        int activeCount = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if (i == 0 && j == 0 && k == 0) {
                        continue;
                    } else {
                        activeCount += getState(x + i, y + j, z + k) ? 1 : 0;
                    }
                }
            }
        }
        return activeCount;
    }

    public int getActiveCubes() {
        int activeCount = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    activeCount += space[i][j][k] ? 1 : 0;
                }
            }
        }
        return activeCount;
    }
}

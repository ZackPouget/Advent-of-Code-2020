public class Space4d {
    private int size;
    public Space3d[] space;

    public Space4d(int size) {
        this.size = size;
        space = new Space3d[size];
        for (int i = 0; i < size; i++) {
            space[i] = new Space3d(size);
        }
    }

    public void setState(int x, int y, int z, int w, boolean state) {
        space[w + size/2].setState(x, y, z, state);
    }

    public boolean getState(int x, int y, int z, int w) {
        if (w + size/2 < 0 || w + size/2 >= size)
            return false;
        return space[w + size/2].getState(x, y, z);
    }

    public int getActiveNeighbors(int x, int y, int z, int w) {
        int activeCount = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (i == 0 && j == 0 && k == 0 && l == 0) {
                            continue;
                        } else {
                            activeCount += getState(x + i, y + j, z + k, w + l) ? 1 : 0;
                        }
                    }
                }
            }
        }
        return activeCount;
    }

    public int getActiveCubes() {
        int activeCount = 0;
        for (int i = 0; i < size; i++) {
            activeCount += space[i].getActiveCubes();
        }
        return activeCount;
    }
}

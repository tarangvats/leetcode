import java.util.*;

class Solution {
    static class State {
        int r, c, energy, mask, steps;
        State(int r, int c, int energy, int mask, int steps) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
            this.steps = steps;
        }
    }

    public int minMoves(String[] classroom, int maxEnergy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int sr = -1, sc = -1;
        
        List<int[]> litters = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    sr = i; sc = j;
                } else if (ch == 'L') {
                    litters.add(new int[]{i, j});
                }
            }
        }
        
        int numLitters = litters.size();
        if (numLitters == 0) return 0;
        int targetMask = (1 << numLitters) - 1;

        // Map litter coordinates to bit index
        int[][] litterIdx = new int[m][n];
        for (int[] arr : litterIdx) Arrays.fill(arr, -1);
        for (int k = 0; k < numLitters; k++) {
            litterIdx[litters.get(k)[0]][litters.get(k)[1]] = k;
        }

        // State tracking: visited[r][c][energy][mask]
        boolean[][][][] visited = new boolean[m][n][maxEnergy + 1][1 << numLitters];
        
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(sr, sc, maxEnergy, 0, 0));
        visited[sr][sc][maxEnergy][0] = true;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            if (curr.mask == targetMask) {
                return curr.steps;
            }

            for (int[] d : dirs) {
                int nr = curr.r + d[0];
                int nc = curr.c + d[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                char cell = classroom[nr].charAt(nc);
                if (cell == 'X') continue;

                int nextEnergy = curr.energy - 1;
                if (nextEnergy < 0) continue; // Cannot move if out of energy

                if (cell == 'R') {
                    nextEnergy = maxEnergy; // Recharge
                }

                int nextMask = curr.mask;
                if (cell == 'L') {
                    int idx = litterIdx[nr][nc];
                    nextMask |= (1 << idx);
                }

                if (!visited[nr][nc][nextEnergy][nextMask]) {
                    visited[nr][nc][nextEnergy][nextMask] = true;
                    queue.add(new State(nr, nc, nextEnergy, nextMask, curr.steps + 1));
                }
            }
        }

        return -1;
    }
}

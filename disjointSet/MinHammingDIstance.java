package disjointSet;
import java.util.*;
class MinHammingDistance {
    public static void main(String[] args) {
        int[] source = {1, 2, 3, 4};
        int[] target = {2, 1, 4, 5};
        int[][] allowedSwaps = {{0, 1}, {2, 3}};
        MinHammingDistance solution = new MinHammingDistance();
        int result = solution.minimumHammingDistance(source, target, allowedSwaps);
        System.out.println("Minimum Hamming Distance: " + result);
    }
    class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x]!= x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) return;
            if (rank[rx] < rank[ry]) parent[rx] = ry;
            else if (rank[rx] > rank[ry]) parent[ry] = rx;
            else { parent[ry] = rx; rank[rx]++; }
        }
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        DSU dsu = new DSU(n);
        for (int[] s : allowedSwaps) dsu.union(s[0], s[1]);

        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        int ans = 0;
        for (List<Integer> group : groups.values()) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int i : group) cnt.put(source[i], cnt.getOrDefault(source[i], 0) + 1);

            for (int i : group) {
                if (cnt.getOrDefault(target[i], 0) > 0) {
                    cnt.put(target[i], cnt.get(target[i]) - 1);
                } else {
                    ans++;
                }
            }
        }
        return ans;
    }
}
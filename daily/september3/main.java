import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        UniformParitySolution solution = new UniformParitySolution();

        int[][] testCases = {
            {1, 4, 7},
            {2, 3},
            {4, 6},
            {3, 5, 8}
        };

        for (int[] nums1 : testCases) {
            boolean result = solution.uniformArray(nums1);
            System.out.println("nums1 = " + Arrays.toString(nums1)
                    + ", possible = " + result);
        }
    }
}

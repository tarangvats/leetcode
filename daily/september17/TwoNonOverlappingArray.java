package daily.september17;

// Leetcode Solutions

public class TwoNonOverlappingArray {
    public static void main(String[] args) {
        int[] arr = {3, 2, 2, 4, 3};
        int target = 3;
        int result = minSumOfLengths(arr, target);

        System.out.println("Minimum sum of lengths: " + result);
    }

    public static int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] bestBefore = new int[n];
        int infinity = n + 1;
        int bestLength = infinity;
        int answer = infinity;
        int left = 0;
        int windowSum = 0;
        bestBefore[0] = infinity;

        for (int right = 0; right < n; right++) {
            windowSum += arr[right];

            while (windowSum > target) {
                windowSum -= arr[left++];
            }

            if(right>0)
                bestBefore[right] = bestBefore[right - 1];

            if (windowSum == target) {
                int currentLength = right - left + 1;

                if (left > 0 && bestBefore[left - 1] < infinity) {
                    answer = Math.min(
                            answer,
                            currentLength + bestBefore[left - 1]);
                }

                bestLength = Math.min(bestLength, currentLength);
                bestBefore[right] = Math.min(bestBefore[right], bestLength);
            }
        }

        return answer == infinity ? -1 : answer;
    }
}

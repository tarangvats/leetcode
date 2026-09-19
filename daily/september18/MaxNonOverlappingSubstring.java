package daily.september18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxNonOverlappingSubstring {
    public static void main(String[] args) {
        String s = "ababa";
        List<String> result = maxNumOfSubstrings(s);
        System.out.println("Maximum Number of Non-Overlapping Substrings: " + result);
    }

    public static List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        // 1. Record the first and last occurrence index of each character
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        // 2. Expand intervals and collect all valid [start, end] ranges
        List<int[]> validIntervals = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;

            int left = first[i];
            int right = last[i];
            boolean isValid = true;

            for (int j = left; j <= right; j++) {
                int ch = s.charAt(j) - 'a';
                
                // If a character inside starts before our current left, range is invalid
                if (first[ch] < left) {
                    isValid = false;
                    break;
                }
                
                // Expand right boundary to include all occurrences of the character
                right = Math.max(right, last[ch]);
            }

            if (isValid) {
                validIntervals.add(new int[]{left, right});
            }
        }

        // 3. Sort intervals by their end position ascending
        Collections.sort(validIntervals, (a, b) -> Integer.compare(a[1], b[1]));

        // 4. Greedily select maximum non-overlapping substrings
        List<String> ans = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : validIntervals) {
            int start = interval[0];
            int end = interval[1];

            if (start > prevEnd) {
                ans.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return ans;
    }
}

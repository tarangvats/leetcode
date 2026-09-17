package daily.september17;

import java.util.ArrayList;
import java.util.List;

class TwoNonOverlappingSubArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 2;
        int result = maxSumTwoNoOverlap(arr, target);
        System.out.println("Minimum Sum of Two Non-Overlapping Subarrays: " + result);
    }

    public static int maxSumTwoNoOverlap(int[] arr, int target) {
        int start = 0;
        int end = 0;
        int sum = 0;
        int n = arr.length;
        List<List<Integer>>targetList = new ArrayList<>();
        for(int i = 0; i<n; i++){
            sum = sum + arr[i];
            end = i;
            while(sum>target){
                sum = sum - arr[start];
                start = start+1;
            }
            if(sum==target){
                List<Integer>list = new ArrayList<>();
                list.add(start);
                list.add(end);
                targetList.add(list);
                sum = sum - arr[start];
                start++;

            }
            
        }
        int overallMin = n+1;
        System.out.println(targetList);
        if(targetList.size()<2)
            return -1;

        for(int i = 0; i<targetList.size() ; i++){
            for(int j=0; j<targetList.size() ; j++){
                if(i!=j){
                    int start1 = (targetList.get(i)).get(0);
                    int end1 =(targetList.get(i)).get(1);
                    int start2 = (targetList.get(j)).get(0);
                    int end2 =(targetList.get(j)).get(1);
                    if(end1 < start2) {
                        int s = (end1 - start1 + 1) + (end2 - start2 + 1);
                        overallMin = Math.min(overallMin, s);
                    }
                }
            }
        }
        if(overallMin==n+1)
            return -1;

        return overallMin;
    }
}
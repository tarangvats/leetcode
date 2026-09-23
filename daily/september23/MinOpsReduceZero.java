package daily.september23;

public class MinOpsReduceZero {
    public static void main(String[] args) {
        int[] nums = {1,1,4,2,3};
        int x = 5;
        MinOpsReduceZero minOpsReduceZero = new MinOpsReduceZero();
        int ans = minOpsReduceZero.minOperations(nums, x);
        System.out.println(ans); 
    }

    public MinOpsReduceZero(){

    }

    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        // int[][] mem = new int[n+1][n+1];
        // int ans = minOps(0,nums.length-1,nums,x, mem);

        int totalSum = 0;
        for(int num: nums)
            totalSum +=num;
        if(totalSum==x)
            return n;
        if(x > totalSum)
            return -1;
        int target = totalSum - x;
        int csum = 0;
        int start = 0;
        int localAnswer = -1;

        for(int end = 0; end<n ; end++){
            csum =csum + nums[end];

            if(csum > target){

                while(csum>target && start<end){
                    csum = csum - nums[start];
                    start++;
                }
            }
            if(csum==target){
                localAnswer = Math.max(localAnswer, end-start+1);
            }
        }

        if(localAnswer!=-1){
            localAnswer = n-localAnswer;
        }
        return localAnswer;
    }
    
}

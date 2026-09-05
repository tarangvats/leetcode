package daily.september5;
class SmallestStableIndex2 {
    public static void main(String[] args) {
        // SmallestStableIndex2Solution solution = new SmallestStableIndex2Solution();
        int[] nums = {5,0,1,4};
        int k = 3;
        int result = firstStableIndex2(nums, k);
        System.out.println("Smallest Stable Index: " + result);
    }

    
    public static int firstStableIndex2(int[] nums, int k) {
        int n = nums.length;
        int[] maxScore = new int[n];
        int[] minScore = new int[n];

        maxScore[0] = nums[0];
        minScore[n-1] = nums[n-1];

        for(int i = 1; i<nums.length; i++){
            maxScore[i] = Math.max(maxScore[i-1],nums[i]);

            minScore[n-i-1] = Math.min(minScore[n-i],nums[n-i-1]);
        }

        for(int i = 0; i<nums.length; i++){
            if((maxScore[i] - minScore[i])<=k)
                return i;
        }

        return -1;
        
    }



}
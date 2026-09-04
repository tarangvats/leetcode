package daily.september4;

public class SmallestStableIndex {
    public static void main(String[] args) {
        // SmallestStableIndexSolution solution = new SmallestStableIndexSolution();
        int[] nums = {3,2,1};
        int result = firstStableIndex(nums, 1);
        System.out.println("Smallest Stable Index: " + result);
    }
    public static int firstStableIndex(int[] nums, int k) {
        if(nums.length==0)
            return -1;
        if(nums.length==1)
            return 0;
        int[] minTemp = new int[nums.length];
        int[] maxTemp = new int[nums.length];
        maxTemp[0] = nums[0];
        minTemp[nums.length-1] = nums[nums.length-1];


        for(int i = 1; i<nums.length ; i++){
          maxTemp[i] = Math.max(maxTemp[i-1],nums[i]);

          minTemp[nums.length-1-i] = Math.min(minTemp[nums.length - i],nums[nums.length -1 - i]);
        }

        


        int globalInstabilityScore = Integer.MAX_VALUE;
        int localInstabilityScore = Integer.MAX_VALUE;
        int ans = -1;
        for(int i = 0; i<nums.length; i++){
            localInstabilityScore = maxTemp[i] - minTemp[i];

            if(localInstabilityScore<=k)
                return i;

            
            if(localInstabilityScore < globalInstabilityScore)
            {
                globalInstabilityScore = localInstabilityScore;
                ans = i;
            }
            

        }


        if(globalInstabilityScore <=k)
            return ans;
        
        return -1;


        
    }

}

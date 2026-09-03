import java.util.Arrays;

class UniformParitySolution {
    public boolean uniformArray(int[] nums1) {

        int min = Arrays.stream(nums1).min().getAsInt();

        long evenCount = (int)Arrays.stream(nums1).filter(x -> x%2==0).count();
        

        if(evenCount==nums1.length)
            return true;
        
        
        if(min%2==0)
            return false;
        return true;
        
    }
}
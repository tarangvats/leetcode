public class LongestPalindromeSolution {
    public Boolean isPalindrome(String str){
        String reverse = new StringBuilder(str).reverse().toString();
        if(str.equals(reverse))
            return true;
        return false;
    }

    private int expandAroundCenter(String str, int left, int right){
        int ans = 1;
        System.out.println("left :"+left + " Right: "+right);

        while( left>=0 && right<str.length() && str.charAt(left)==str.charAt(right)){
            ans = right-left+1;
            left--;
            right++;
        }

        System.out.println(ans);
        return ans;
        
    }

    
    public String longestPalindrome(String s) {

        int globalAnswer = 1;
        int localAnswer = 0;
        String longestPalindrome = "" + s.charAt(0);
        for(int i = 0; i<s.length()-1 ; i++){

            localAnswer = Math.max(expandAroundCenter(s, i,i),expandAroundCenter(s,i,i+1));
             
            if(localAnswer>globalAnswer){
                int firstIndex;
                if(localAnswer%2==1)
                    firstIndex = i - (localAnswer/2);
                else
                    firstIndex = i - (localAnswer/2) + 1;
                
                globalAnswer = localAnswer;
                longestPalindrome = s.substring(firstIndex,firstIndex + localAnswer);

            }
        }

        return longestPalindrome;
    }
    
}

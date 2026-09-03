class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindromeSolution solution = new LongestPalindromeSolution();
        String s = "abccccdd";
        String result = solution.longestPalindrome(s);
        System.out.println("Longest Palindrome Length: " + result);
    }
    
}

package daily.september16;

class KNonOverlap {

    public static void main(String[] args) {
    ;
        int n = 30;
        int k = 7;
        int result = numberOfSets(n, k);
        System.out.println("Number of Sets: " + result);
    }

    private static final int MOD = 1000000007;

    public static long countWays(int n, int k, long[][] mem) {
        if(k>n)
            return 0;
        if(k==n)
            return 1;
        if(k==0)
            return 1;
        if(mem[n+1][k] != -1)
            return mem[n+1][k];

        long count = 0;
        for(int i = 1; i<=n ; i++){
            count = count + countWays(n - i ,k-1, mem) + countWays(n-i , k, mem);
        }
        System.out.println("For n:"+n+" and k:"+k+", count: "+count);
        return mem[n+1][k] = count;
        
    }
    public static int numberOfSets(int n, int k) {
        long[][] mem = new long[n + 1][k + 1];
        for(int i = 0; i<=n ; i++){
            for(int j = 0; j<=k ; j++){
                mem[i][j] = -1;
            }
        }
        for(int i=0; i<n+1; i++){
            mem[i][0] = 1;
        }

        for(int i = 0; i<k+1; i++)
        {
            mem[i+1][i] = 1;
        }

        return (int)countWays(n-1,k, mem)%MOD;
    }
}
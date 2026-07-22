/*Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

 

Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag
 

Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters. */
import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter s: ");
        String s =sc.nextLine();
        System.out.println("Enter t: ");
        String t =sc.nextLine();

        Solution sol= new Solution();
        System.out.println(sol.numDistinct(s,t));
        sc.close();
    }
    Integer dp[][];
    public int numDistinct(String s, String t) {
        int n=s.length();
        int m =t.length();
        dp=new Integer[n+1][m+1];
        return solve(0,0,s,t,n, m);
    }
    int solve(int i, int j, String s, String t, int n, int m){
        if(j==m)return 1;
        if(i==n)return 0;
        
        if(dp[i][j]!=null)return dp[i][j];
        int ans=0;
        if(s.charAt(i)==t.charAt(j)){
            ans+=solve(i+1,j+1,s,t,n,m);
            ans+=solve(i+1,j,s,t,n,m);
        }
        else ans+=solve(i+1,j,s,t,n,m);

        return dp[i][j]=ans;
    }
}
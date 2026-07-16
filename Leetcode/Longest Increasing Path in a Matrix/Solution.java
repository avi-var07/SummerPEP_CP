/*Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

 

Example 1:


Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:


Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
Example 3:

Input: matrix = [[1]]
Output: 1
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 231 - 1 */
import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of rows: ");
        int m = sc.nextInt();
        
        System.out.println("Enter number of cols: ");
        int n = sc.nextInt();

        int arr[][] = new int[m][n];
        System.out.println("Enter "+m*n+" elements: ");
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++)arr[i][j] = sc.nextInt();
        }
        System.out.println(longestIncreasingPath(arr));
        sc.close();
    }
    static int drow[]={-1,1,0,0};
    static int dcol[]={0,0,-1,1};
    static Integer dp[][];
    public static int longestIncreasingPath(int[][] matrix) {
        int m =matrix.length;
        int n =matrix[0].length;

        int ans = Integer.MIN_VALUE;

        dp=new Integer[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++)ans=Math.max(ans, dfs(i,j,matrix, m, n));
        }
        return ans;
    }
    static int dfs(int sr, int sc, int arr[][], int m, int n){
        if(dp[sr][sc]!=null)return dp[sr][sc];

        int curr=1;
        for(int i=0;i<4;i++){
            int nrow=sr+drow[i];
            int ncol=dcol[i]+sc;

            if(nrow>=0&&nrow<m&&ncol>=0&&ncol<n&&arr[sr][sc]>arr[nrow][ncol])curr=Math.max(curr, 1+dfs(nrow, ncol, arr, m, n));
            
        }
        return dp[sr][sc]=curr;
    }
}
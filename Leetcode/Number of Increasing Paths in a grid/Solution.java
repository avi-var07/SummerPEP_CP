/*You are given an m x n integer matrix grid, where you can move from a cell to any adjacent cell in all 4 directions.

Return the number of strictly increasing paths in the grid such that you can start from any cell and end at any cell. Since the answer may be very large, return it modulo 109 + 7.

Two paths are considered different if they do not have exactly the same sequence of visited cells.

 

Example 1:


Input: grid = [[1,1],[3,4]]
Output: 8
Explanation: The strictly increasing paths are:
- Paths with length 1: [1], [1], [3], [4].
- Paths with length 2: [1 -> 3], [1 -> 4], [3 -> 4].
- Paths with length 3: [1 -> 3 -> 4].
The total number of paths is 4 + 3 + 1 = 8.
Example 2:

Input: grid = [[1],[2]]
Output: 3
Explanation: The strictly increasing paths are:
- Paths with length 1: [1], [2].
- Paths with length 2: [1 -> 2].
The total number of paths is 2 + 1 = 3.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 1000
1 <= m * n <= 105
1 <= grid[i][j] <= 105 */

import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of rows: ");
        int m = sc.nextInt();
        System.out.println("Enter number of rows: ");
        int n = sc.nextInt();

        int arr[][] = new int[m][n];
        System.out.println("Enter "+m*n+" elements: ");
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++)arr[i][j] = sc.nextInt();
        }

        System.out.println(countPaths(arr));
        sc.close();
    }
    static int drow[]={-1,1,0,0};
    static int dcol[] = {0,0,-1,1};
    static Long dp[][];
    static int MOD= 1000000007;
    public static int countPaths(int[][] grid) {
        int m =grid.length;
        int n=grid[0].length;
        long ans=0;
        dp=new Long[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++)ans=(ans+dfs(i,j,grid,m, n))%MOD;
        }
        return (int)ans;
    }
    static long dfs(int sr, int sc, int arr[][], int m, int n){
        if(dp[sr][sc]!=null)return dp[sr][sc];
        
        long path=1;
        for(int i=0;i<4;i++){
            int nrow=sr+drow[i];
            int ncol =dcol[i]+sc;

            if(nrow>=0&&ncol>=0&&nrow<m&&ncol<n&&arr[nrow][ncol]>arr[sr][sc])path=(path+dfs(nrow, ncol, arr, m, n))%MOD;
        }
        return dp[sr][sc]=path;
    }
}
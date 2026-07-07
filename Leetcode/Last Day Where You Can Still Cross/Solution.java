/*There is a 1-based binary matrix where 0 represents land and 1 represents water. You are given integers row and col representing the number of rows and columns in the matrix, respectively.

Initially on day 0, the entire matrix is land. However, each day a new cell becomes flooded with water. You are given a 1-based 2D array cells, where cells[i] = [ri, ci] represents that on the ith day, the cell on the rith row and cith column (1-based coordinates) will be covered with water (i.e., changed to 1).

You want to find the last day that it is possible to walk from the top to the bottom by only walking on land cells. You can start from any cell in the top row and end at any cell in the bottom row. You can only travel in the four cardinal directions (left, right, up, and down).

Return the last day where it is possible to walk from the top to the bottom by only walking on land cells.

 

Example 1:


Input: row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
Output: 2
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 2.
Example 2:


Input: row = 2, col = 2, cells = [[1,1],[1,2],[2,1],[2,2]]
Output: 1
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 1.
Example 3:


Input: row = 3, col = 3, cells = [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
Output: 3
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 3.
 

Constraints:

2 <= row, col <= 2 * 104
4 <= row * col <= 2 * 104
cells.length == row * col
1 <= ri <= row
1 <= ci <= col
All the values of cells are unique. */
import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of rows: ");
        int m = sc.nextInt();
        System.out.println("Enter number of cols: ");
        int n = sc.nextInt();

        int arr[][] = new int[m][n];
        System.out.println("Enter "+n+" elements: ");
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++)arr[i][j] = sc.nextInt();
        }

        Solution sol = new Solution();
        System.out.println(sol.latestDayToCross(m, n, arr));
        sc.close();
    }
    int drow[]={-1,1,0,0};
    int dcol[] = {0,0,-1,1};
    public int latestDayToCross(int m, int n, int[][] cells) {
        int ans =-1;
        int low =0;
        int high=cells.length;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(possible(mid, cells, m, n)){
                ans=mid;
                low=mid+1;
            }
            else high=mid-1;
        }
        return ans;
    }
    boolean possible(int mid, int cells[][], int m, int n){
        int matrix[][] = new int[m][n];
        for(int i=0;i<mid;i++){
            int row=cells[i][0]-1; //1 based
            int col=cells[i][1]-1;
            matrix[row][col]=1;
        }

        boolean vis[][] = new boolean[m][n];
        Queue<int[]>queue=new LinkedList<>();
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[0][j]==0){
                    queue.add(new int[]{0,j});
                    vis[0][j]=true;
                }

            }
        }

        while(!queue.isEmpty()){
            int curr[]= queue.poll();
            int row=curr[0];
            int col=curr[1];

            if(row==m-1)return true;
            for(int i=0;i<4;i++){
                int nrow=row+drow[i];
                int ncol=col+dcol[i];

                if(nrow>=0&&nrow<m&&ncol>=0&&ncol<n&&!vis[nrow][ncol]&&matrix[nrow][ncol]==0){
                    vis[nrow][ncol]=true;
                    queue.add(new int[]{nrow, ncol});
                }
            }
        }
        return false;
    }
}
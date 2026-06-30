/*We have an array of integers, nums, and an array of requests where requests[i] = [starti, endi]. The ith request asks for the sum of nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi]. Both starti and endi are 0-indexed.

Return the maximum total sum of all requests among all permutations of nums.

Since the answer may be too large, return it modulo 109 + 7.

 

Example 1:

Input: nums = [1,2,3,4,5], requests = [[1,3],[0,1]]
Output: 19
Explanation: One permutation of nums is [2,1,3,4,5] with the following result: 
requests[0] -> nums[1] + nums[2] + nums[3] = 1 + 3 + 4 = 8
requests[1] -> nums[0] + nums[1] = 2 + 1 = 3
Total sum: 8 + 3 = 11.
A permutation with a higher total sum is [3,5,4,2,1] with the following result:
requests[0] -> nums[1] + nums[2] + nums[3] = 5 + 4 + 2 = 11
requests[1] -> nums[0] + nums[1] = 3 + 5  = 8
Total sum: 11 + 8 = 19, which is the best that you can do.
Example 2:

Input: nums = [1,2,3,4,5,6], requests = [[0,1]]
Output: 11
Explanation: A permutation with the max total sum is [6,5,4,3,2,1] with request sums [11].
Example 3:

Input: nums = [1,2,3,4,5,10], requests = [[0,2],[1,3],[1,1]]
Output: 47
Explanation: A permutation with the max total sum is [4,10,5,3,2,1] with request sums [19,18,10].
 

Constraints:

n == nums.length
1 <= n <= 105
0 <= nums[i] <= 105
1 <= requests.length <= 105
requests[i].length == 2
0 <= starti <= endi < n */
import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of Elements: ");
        int n = sc.nextInt();

        int arr[] = new int[n];
        System.out.println("Enter "+n+" elements: ");
        for(int i=0;i<n;i++) arr[i] = sc.nextInt();

        System.out.println("Enter number of requests: ");
        int m=sc.nextInt();

        int requests[][]  =new int[m][2];

        for(int i=0;i<m;i++){
            requests[i][0]=sc.nextInt();
            requests[i][1]=sc.nextInt();
        }

        System.out.println(maxSumRangeQuery(arr, requests));
        sc.close();
    }
    public static int maxSumRangeQuery(int[] nums, int[][] requests) {
        //jo index baar baar access horha uspe maximum element rkhdete hai. 2nd maximum baar access hone wale pe second maximum ...so on....

        //using DAT

        long MOD= 1000000007;
        int n=nums.length;
        int diff[] = new int[n];

        for(int ele[]: requests){
            int l=ele[0];
            int r=ele[1];
            diff[l]++;
            if(r+1<n)diff[r+1]--;
        }

        int ps[] = new int[n];
        ps[0]=diff[0];
        for(int i=1;i<n;i++)ps[i]=ps[i-1]+diff[i];

        Arrays.sort(nums); //maximum element k liye;
        Arrays.sort(ps); 

        long ans=0;
        for(int i=0;i<n;i++)ans=(ans+1L*ps[i]*nums[i])%MOD;

        return (int)ans;
    }
}
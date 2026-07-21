/*Given an integer array nums, handle multiple queries of the following types:

Update the value of an element in nums.
Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
void update(int index, int val) Updates the value of nums[index] to be val.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 

Example 1:

Input
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
Output
[null, 9, null, 8]

Explanation
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
numArray.update(1, 2);   // nums = [1, 2, 5]
numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 

Constraints:

1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
0 <= index < nums.length
-100 <= val <= 100
0 <= left <= right < nums.length
At most 3 * 104 calls will be made to update and sumRange. */
import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        NumArray obj = new NumArray(arr);

        while (true) {
            System.out.println("\nChoose Operation:");
            System.out.println("1. Update");
            System.out.println("2. Sum Range");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter index and new value: ");
                    int index = sc.nextInt();
                    int val = sc.nextInt();
                    obj.update(index, val);
                    System.out.println("Updated Successfully!");
                    break;

                case 2:
                    System.out.print("Enter left and right index: ");
                    int left = sc.nextInt();
                    int right = sc.nextInt();
                    System.out.println("Sum = " + obj.sumRange(left, right));
                    break;

                case 3:
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
class NumArray {
    SegmentTree st;
    public NumArray(int[] nums) {
        st=new SegmentTree(nums);
    }
    
    public void update(int index, int val) {
        st.update(index, val);
    }
    
    public int sumRange(int left, int right) {
        return st.query(left, right);
    }
    class SegmentTree{
        int n;
        int st[];

        SegmentTree(int arr[]){
            n=arr.length;
            st=new int[4*n];
            buildTree(0,n-1,0,arr);
        }
        void buildTree(int left, int right, int ind, int arr[]){
            if(left==right){
                st[ind]=arr[left];
                return;
            }
            int mid =left+(right-left)/2;

            buildTree(left, mid, 2*ind+1, arr);
            buildTree(mid+1, right, 2*ind+2, arr);

            st[ind]=st[2*ind+1]+st[2*ind+2];
        }
        //wrapper
        void update(int ind, int val){
            update(0,n-1,0,ind,val);
        }
        void update(int left, int right, int ind, int i, int val){
            if(left==right){
                st[ind]=val;
                return;
            }

            int mid=left+(right-left)/2;
            if(i<=mid)update(left, mid, 2*ind+1, i, val); //decide krega kis side jaana hai
            else update(mid+1, right, 2*ind+2, i, val);

            st[ind]=st[2*ind+1]+st[2*ind+2];
        }
        int query(int left, int right){
            return query(0,n-1, 0, left, right);
        }
        int query(int left, int right, int ind, int start, int end){
            if(right<start||left>end)return 0; //no overlap

            if(left>=start&&right<=end)return st[ind]; //complete overlap

            int mid =left+(right-left)/2;

            int leftSum=query(left, mid, 2*ind+1, start, end);
            int rightSum=query(mid+1, right, 2*ind+2, start, end);

            return leftSum+rightSum;
        }

    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
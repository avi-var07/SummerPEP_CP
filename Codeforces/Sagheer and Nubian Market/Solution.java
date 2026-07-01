import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Enter number of Elements: ");
        int n = sc.nextInt();
        // System.out.println("Enter total money: ");
        int S = sc.nextInt();
        int arr[] = new int[n];
        //System.out.println("Enter "+n+" elements: ");
        for(int i=0;i<n;i++) arr[i] = sc.nextInt();

        int low= 1, high=n;
        int ans=0;
        long mini=0;
        while(low<=high){
            int mid =low+(high-low)/2;
            long c=cost(mid, arr, n);
            if(c<=S){
                ans=mid;
                mini=c;
                low=mid+1;
            }
            else high=mid-1;
        }
        System.out.println(ans+" "+mini);
        sc.close();
    }
    static long cost(int mid, int arr[], int n){
        long sum=0;
        long ans[] = new long[n];

        for(int i=0;i<n;i++)ans[i]=arr[i]+(1L*(i+1)*mid);

        Arrays.sort(ans);

        for(int i=0;i<mid;i++)sum+=ans[i];

    
        return sum;
    }
}
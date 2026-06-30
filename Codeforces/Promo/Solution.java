import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Enter number of Elements: ");
        int n = sc.nextInt();
        //System.out.println("Enter number of queries: ");
        int q = sc.nextInt();

        int arr[] = new int[n];
        //System.out.println("Enter "+n+" elements: ");
        for(int i=0;i<n;i++) arr[i] = sc.nextInt();

        //brute force:
        
        // int queries[][] = new int[q][2];
        // for(int i=0;i<q;i++){
        //     //System.out.println("Enter x: ");
        //     int x = sc.nextInt();
        //     //System.out.println("Enter y: ");
        //     int y = sc.nextInt();

        //     queries[i][0]=x;
        //     queries[i][1]=y;

        // }
        // Arrays.sort(arr);
        // for(int ele[]: queries){
            //     int x=ele[0];
            //     int y=ele[1];
        //     long sum=0;
        
        //     int start=n-x;
        //     for(int i=start;i<start+y;i++)sum+=arr[i];

        //     System.out.println(sum);
        // }
        
        //optimal: prefix sum:
        
        Arrays.sort(arr);
        long ps[] = new long[n];
        ps[0]=arr[0];

        for(int i=1;i<n;i++)ps[i]=ps[i-1]+arr[i];

        while(q-->0){
            int x= sc.nextInt();
            int y= sc.nextInt();

            int left=n-x;
            int right=left+y-1;

            long ans=0;
            if(left==0)ans=ps[right];
            else ans=ps[right]-ps[left-1];

            System.out.println(ans);

        }

        sc.close();
    }
}
/*In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

 

Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]
 

Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
There are no repeated edges.
The given graph is connected. */
import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of Elements: ");
        int n = sc.nextInt();

        int arr[][] = new int[n][2];
        System.out.println("Enter "+n+" elements: ");
        for(int i=0;i<n;i++) {
            System.out.println("Enter u: ");
            arr[i][0] = sc.nextInt();
            System.out.println("Enter v: ");
            arr[i][1] = sc.nextInt();
        }
        Solution sol=new Solution();
        System.out.println(sol.findRedundantConnection(arr));
        sc.close();
    }
    class DSU{
        int V;
        int parent[];
        int size[];
        DSU(int V){
            this.V=V;
            parent = new int[V+1];
            size=new int[V+1];

            for(int i=0;i<=V;i++){
                parent[i]=i;
                size[i]=1;
            }
        }

        boolean union(int a, int b){
            int pa =findParent(a);
            int pb=findParent(b);

            if(pa==pb)return false;

            if(size[pa]>=size[pb]){
                parent[pb]=pa;
                size[pa]+=size[pb];
            }
            else{
                parent[pa]=pb;
                size[pb]+=size[pa];
            }

            return true;
        }
        int findParent(int x){
            if(parent[x]==x)return x;
            return findParent(parent[x]);
        }
    }
    public int[] findRedundantConnection(int[][] edges) {
        int n=edges.length;
        DSU d= new DSU(n);
        for(int ele[]: edges){
            int u=ele[0];
            int v=ele[1];

            if(!d.union(u,v))return new int[]{u,v};
        }
        return new int[]{-1,-1};
    }
}
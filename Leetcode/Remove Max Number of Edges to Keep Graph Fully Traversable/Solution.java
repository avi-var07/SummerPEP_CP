/*Alice and Bob have an undirected graph of n nodes and three types of edges:

Type 1: Can be traversed by Alice only.
Type 2: Can be traversed by Bob only.
Type 3: Can be traversed by both Alice and Bob.
Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi, find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.

Return the maximum number of edges you can remove, or return -1 if Alice and Bob cannot fully traverse the graph.

 

Example 1:



Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
Output: 2
Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
Example 2:



Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
Output: 0
Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.
Example 3:



Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
Output: -1
Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1. Therefore it's impossible to make the graph fully traversable.
 

 

Constraints:

1 <= n <= 105
1 <= edges.length <= min(105, 3 * n * (n - 1) / 2)
edges[i].length == 3
1 <= typei <= 3
1 <= ui < vi <= n
All tuples (typei, ui, vi) are distinct. */
import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of Elements: ");
        int n = sc.nextInt();

        int arr[][] = new int[n][3];
        System.out.println("Enter "+n+" elements: ");
        for(int i=0;i<n;i++){
            System.out.println("Enter type: ");
            arr[i][0] = sc.nextInt();
            System.out.println("Enter u: ");
            arr[i][1] = sc.nextInt();
            System.out.println("Enter v: ");
            arr[i][2] = sc.nextInt();
        } 

        Solution sol = new Solution();
        System.out.println(sol.maxNumEdgesToRemove(n, arr));
        sc.close();
    }
    class DSU{
        int V;
        int parent[];
        int size[];
        int compo;
        DSU(int V){
            this.V=V;
            compo=V;
            parent =new int[V+1];
            size= new int[V+1];

            for(int i=1;i<=V;i++){
                parent[i]=i;
                size[i]=1;
            }
        }
        boolean union(int a, int b){
            int pa=parent(a);
            int pb=parent(b);

            if(pa==pb)return false;

            if(size[pa]>=size[pb]){
                parent[pb]=pa;
                size[pa]+=size[pb];
            }
            else{
                parent[pa]=pb;
                size[pb]+=size[pa];
            }
            compo--;
            return true;
        }
        int parent(int x){
            if(parent[x]==x)return x;
            return parent[x]=parent(parent[x]);
        }
    }
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        DSU a=new DSU(n);
        DSU b=new DSU(n);
        int ans=0;
        for(int ele[]: edges){
            int type=ele[0];
            int u=ele[1];
            int v=ele[2];

            if(type==3){
                boolean one=a.union(u,v);
                boolean two =b.union(u,v);

                if(!one&&!two)ans++;
            }
        }
        for(int ele[]: edges){
            int type=ele[0];
            int u=ele[1];
            int v=ele[2];

            if(type==1){
                if(!a.union(u,v))ans++;
            }
        }
        for(int ele[]: edges){
            int type=ele[0];
            int u=ele[1];
            int v=ele[2];

            if(type==2){
                if(!b.union(u,v))ans++;
            }
        }

        if(a.compo!=1||b.compo!=1)return -1;
        return ans;
    }
}
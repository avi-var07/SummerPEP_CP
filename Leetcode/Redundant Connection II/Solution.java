/*In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge connecting nodes ui and vi, where ui is a parent of child vi.

Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

 

Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
Output: [4,1]
 

Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ui, vi <= n
ui != vi */
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
        Solution sol = new Solution();
        System.out.println(sol.findRedundantDirectedConnection(arr));
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
            return parent[x]=findParent(parent[x]);
        }
    }
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n=edges.length;
        int indegree[] = new int[n+1];

        for(int ele[]: edges)indegree[ele[1]]++;

        //last se traverse kyuki last input return krna hai many wale mei

        for(int i=n-1;i>=0;i--){
            //check incoming >1

            int v=edges[i][1];
            if(indegree[v]>1){
                if(check(edges, i, n))return edges[i];
            }
        }
        DSU d= new DSU(n);

        //check cycle:
        for(int ele[]: edges){
            int u=ele[0];
            int v=ele[1];

            if(!d.union(u,v))return new int[]{u,v};
        }

        return new int[]{-1, -1};
    }
    boolean check(int arr[][], int skip, int n){
        //skip krne k baad bhi 1 hi component hai
        DSU d= new DSU(n);

        for(int i=0;i<n;i++){
            if(i==skip)continue;
            int u=arr[i][0];
            int v=arr[i][1];

            d.union(u,v);
        }

        int cnt=0;
        for(int i=1;i<=n;i++)if(d.findParent(i)==i)cnt++;
        
        return cnt==1;
    }
}
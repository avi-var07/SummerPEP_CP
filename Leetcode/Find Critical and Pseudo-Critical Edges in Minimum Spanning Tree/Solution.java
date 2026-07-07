/*Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1, and an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional and weighted edge between nodes ai and bi. A minimum spanning tree (MST) is a subset of the graph's edges that connects all vertices without cycles and with the minimum possible total edge weight.

Find all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST). An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. On the other hand, a pseudo-critical edge is that which can appear in some MSTs but not all.

Note that you can return the indices of the edges in any order.

 

Example 1:



Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
Output: [[0,1],[2,3,4,5]]
Explanation: The figure above describes the graph.
The following figure shows all the possible MSTs:

Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.
Example 2:



Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
Output: [[],[0,1,2,3]]
Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.
 

Constraints:

2 <= n <= 100
1 <= edges.length <= min(200, n * (n - 1) / 2)
edges[i].length == 3
0 <= ai < bi < n
1 <= weighti <= 1000
All pairs (ai, bi) are distinct. */
import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter V: ");
        int V = sc.nextInt();

        System.out.println("Enter number of edges: ");
        int e = sc.nextInt();
        int arr[][] = new int[e][3];
        System.out.println("Enter "+e+" edges: ");
        for(int i=0;i<e;i++) {
            
            System.out.println("Enter u: ");
            arr[i][0] = sc.nextInt();
            System.out.println("Enter v: ");
            arr[i][1] = sc.nextInt();
            System.out.println("Enter w: ");
            arr[i][2] = sc.nextInt();
        }
        List<List<Integer>>ans = findCriticalAndPseudoCriticalEdges(V, arr);

        for(var ele: ans)System.out.print(ele+" ");

        sc.close();
    }
    static class DSU{
        int parent[];
        int size[];

        DSU(int n){
            parent=new int[n];
            size=new int[n];

            for(int i=0;i<n;i++){
                parent[i]=i;
                size[i]=1;
            }

        }
        int findParent(int vtx){
            if(parent[vtx]==vtx)return vtx;

            return parent[vtx]=findParent(parent[vtx]);
        }
        boolean union(int u, int v){
            int pu = findParent(u);
            int pv = findParent(v);

            if(pu==pv)return false; //ye cycle bana rha

            else{
                if(size[pu]>=size[pv]){
                    parent[pv]=pu;
                    size[pu]+=size[pv];
                }
                else{
                    parent[pu]=pv;
                    size[pv]+=size[pu];
                }
            }
            return true; //koi dikkat nhi hai
        }
    }
    public static List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<int[]>sorted = new ArrayList<>();
        int e = edges.length;
        for(int i=0;i<e;i++)sorted.add(new int[]{edges[i][0], edges[i][1], edges[i][2], i}); //edges mei index store krdiya taaki index ka track rhe

        //ab wt k basis pe sort krenge:

        Collections.sort(sorted, (a,b)->a[2]-b[2]);
        
        List<Integer>pseudo = new ArrayList<>();
        List<Integer>critical = new ArrayList<>();
        List<List<Integer>>ans = new ArrayList<>();

        int baseCost = kruskal(sorted, n, -1,-1); //without any inclusion/exclusion


        //critical: edge exclude k baad cost bad jaye ya MST possible na ho
        //pseudo: edge k rehne na rehne se kuch frk nhi pd rha jiska mtlb ye ki cost baseCost k barabar hai.... atleast ek baar ye edge jaani hi chahiye

        for(int i=0;i<e;i++){ //saari edges pe jayenge
            int costWithout = kruskal(sorted, n, i, -1);
            if(costWithout>baseCost)critical.add(sorted.get(i)[3]);
            else{
                int costWith = kruskal(sorted, n, -1, i);
                if(costWith==baseCost)pseudo.add(sorted.get(i)[3]);
            }
        }
        ans.add(critical);
        ans.add(pseudo);

        return ans;
    }

    static int kruskal(List<int[]>sorted, int n, int exclude, int include){
        DSU d = new DSU(n);
        int sum =0;
        int edgesCount =0; //taki MST possible hai bhi ya nhi ye check kre for critical edge

        if(include!=-1){ //indlude krna hai
            int e[] = sorted.get(include);

            if(d.union(e[0], e[1])){ //agar include krne k baad cycle nhi ban rhi
                sum+=e[2];
                edgesCount++;
            }
        }

        for(int i=0;i<sorted.size();i++){
            int e[]  =sorted.get(i);

            if(i==exclude)continue;  //exclude krna hai bola

            if(d.union(e[0], e[1])){
                sum+=e[2];
                edgesCount++;
            }
        }

        if(edgesCount==n-1)return sum; 

        return Integer.MAX_VALUE; //exclude krdiya ya include krdiya jisse MST possible hi n hua to Cost Integer.MAX_VALUE return krdo jo batayega ki cost bad gaya
    }
}
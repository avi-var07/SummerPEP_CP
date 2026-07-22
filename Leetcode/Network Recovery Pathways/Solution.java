/*You are given a directed acyclic graph of n nodes numbered from 0 to n − 1. This is represented by a 2D array edges of length m, where edges[i] = [ui, vi, costi] indicates a one‑way communication from node ui to node vi with a recovery cost of costi.

Some nodes may be offline. You are given a boolean array online where online[i] = true means node i is online. Nodes 0 and n − 1 are always online.

A path from 0 to n − 1 is valid if:

All intermediate nodes on the path are online.
The total recovery cost of all edges on the path does not exceed k.
For each valid path, define its score as the minimum edge‑cost along that path.

Return the maximum path score (i.e., the largest minimum-edge cost) among all valid paths. If no valid path exists, return -1.

 

Example 1:

Input: edges = [[0,1,5],[1,3,10],[0,2,3],[2,3,4]], online = [true,true,true,true], k = 10

Output: 3

Explanation:



The graph has two possible routes from node 0 to node 3:

Path 0 → 1 → 3

Total cost = 5 + 10 = 15, which exceeds k (15 > 10), so this path is invalid.

Path 0 → 2 → 3

Total cost = 3 + 4 = 7 <= k, so this path is valid.

The minimum edge‐cost along this path is min(3, 4) = 3.

There are no other valid paths. Hence, the maximum among all valid path‐scores is 3.

Example 2:

Input: edges = [[0,1,7],[1,4,5],[0,2,6],[2,3,6],[3,4,2],[2,4,6]], online = [true,true,true,false,true], k = 12

Output: 6

Explanation:



Node 3 is offline, so any path passing through 3 is invalid.

Consider the remaining routes from 0 to 4:

Path 0 → 1 → 4

Total cost = 7 + 5 = 12 <= k, so this path is valid.

The minimum edge‐cost along this path is min(7, 5) = 5.

Path 0 → 2 → 3 → 4

Node 3 is offline, so this path is invalid regardless of cost.

Path 0 → 2 → 4

Total cost = 6 + 6 = 12 <= k, so this path is valid.

The minimum edge‐cost along this path is min(6, 6) = 6.

Among the two valid paths, their scores are 5 and 6. Therefore, the answer is 6.

 

Constraints:

n == online.length
2 <= n <= 5 * 104
0 <= m == edges.length <= min(105, n * (n - 1) / 2)
edges[i] = [ui, vi, costi]
0 <= ui, vi < n
ui != vi
0 <= costi <= 109
0 <= k <= 5 * 1013
online[i] is either true or false, and both online[0] and online[n − 1] are true.
The given graph is a directed acyclic graph.
 
 */
import java.util.*;

class Solution{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of Elements: ");
        int n = sc.nextInt();

        int arr[][] = new int[n][3];
        System.out.println("Enter "+n+" elements: ");
        for(int i=0;i<n;i++) {
            System.out.println("Enter u: ");
            arr[i][0] = sc.nextInt();
            System.out.println("Enter v: ");
            arr[i][1] = sc.nextInt();
            System.out.println("Enter cost: ");
            arr[i][2] = sc.nextInt();
        }
        System.out.println("Ente "+n+" online values: ");
        boolean online[] =new boolean[n];
        for(int i=0;i<n;i++)online[i]=sc.nextBoolean();
        
        System.out.println("Enter k: ");
        int k =sc.nextInt();

        Solution sol=new Solution();
        System.out.println(sol.findMaxPathScore(arr, online, k));

        
        sc.close();
    }
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        
     int n = online.length;

        List<int[]>graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        int[] indegree = new int[n];

        int maxCost = 0;

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            maxCost = Math.max(maxCost, e[2]);
        }

        // Topological order
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        int[] topo = new int[n];
        int idx = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;

            for (int[] e : graph[u]) {
                if (--indegree[e[0]] == 0)
                    q.offer(e[0]);
            }
        }

        int lo = 0, hi = maxCost;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (check(mid, graph, topo, online, k)) {
                ans = mid;
                lo = mid + 1;
            } 
            else hi = mid - 1;
            
        }

        return ans;
    }

    private boolean check(int minEdge, List<int[]>[] graph, int[] topo, boolean[] online, long k) {

        int n = graph.length;
        long INF = Long.MAX_VALUE / 4;

        long[] dp = new long[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int u : topo) {

            if (dp[u] == INF) continue;

            if (u != 0 && u != n - 1 && !online[u]) continue;

            for (int[] e : graph[u]) {

                int v = e[0];
                int cost = e[1];

                if (cost < minEdge) continue;

                if (v != n - 1 && v != 0 && !online[v]) continue;

                dp[v] = Math.min(dp[v], dp[u] + cost);
            }
        }

        return dp[n - 1] <= k;
    }
}
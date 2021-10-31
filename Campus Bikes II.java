/*On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.

Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: 4
Explanation: 
We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.
 

Note:

0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
All worker and bike locations are distinct.
1 <= workers.length <= bikes.length <= 10 */

class Solution {
    public int assignBikes(int[][] workers, int[][] bikes) {
        Set<String> visited = new HashSet<>();
        int n = workers.length, m = bikes.length;
        //cost, worker, mask
        //mask is a binary representation of the bike usage
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0]-b[0]);
        pq.add(new int[]{0,0,0});
        while(!pq.isEmpty()){
            int[] t = pq.poll();//pop out the tripple with lowest cost
            int c = t[0], w = t[1], mask = t[2];
            if(w==n) return c;
            String key = w+"$"+mask;
            if(visited.contains(key)) continue;
            visited.add(key);
            for(int j=0; j<m; ++j){
                //if the bike j is not used
                if((mask & (1<<j))==0){
                    int d = Math.abs(workers[w][0]-bikes[j][0])+Math.abs(workers[w][1]-bikes[j][1]);
                    pq.add(new int[]{c+d, w+1, mask|(1<<j)});
                }
            }
        }
        return 0;
    }
}

 

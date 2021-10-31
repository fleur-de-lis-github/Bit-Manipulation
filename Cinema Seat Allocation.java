/*A cinema has n rows of seats, numbered from 1 to n and there are ten seats in each row, labelled from 1 to 10 as shown in the figure above.

Given the array reservedSeats containing the numbers of seats already reserved, for example, reservedSeats[i] = [3,8] means the seat located in row 3 and labelled with 8 is already reserved.

Return the maximum number of four-person groups you can assign on the cinema seats. A four-person group occupies four adjacent seats in one single row. Seats across an aisle (such as [3,3] and [3,4]) are not considered to be adjacent, but there is an exceptional case on which an aisle split a four-person group, in that case, the aisle split a four-person group in the middle, which means to have two people on each side.

 

Example 1:

Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
Output: 4
Explanation: The figure above shows the optimal allocation for four groups, where seats mark with blue are already reserved and contiguous seats mark with orange are for one group.
Example 2:

Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
Output: 2
Example 3:

Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
Output: 4
 

Constraints:

1 <= n <= 10^9
1 <= reservedSeats.length <= min(10*n, 10^4)
reservedSeats[i].length == 2
1 <= reservedSeats[i][0] <= n
1 <= reservedSeats[i][1] <= 10
All reservedSeats[i] are distinct. */

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        if(reservedSeats.length==0) return 2*n;
        Map<Integer,int[]> m=new HashMap<>();
        for(int[] a:reservedSeats){
            if(!(a[1]==1 || a[1]==10)){      //do not include 1 and 10 coz there is no use
                m.putIfAbsent(a[0],new int[3]);
                int []x=m.get(a[0]);
                if(a[1]<=3){
                    x[0]=1;
                }else if(a[1]<=5){
                    x[0]=1;x[1]=1;
                }else if(a[1]<=7){
                    x[1]=1;x[2]=1;
                }else{
                    x[2]=1;
                }
            }
        }
        int res=0;
        for(Integer b:m.keySet()){
            int []a=m.get(b);
            if(a[0]==0){
			res++;                         //No reserved seat in 2,3,4,5
                if(a[2]==0)res++;   //No reserved seat in 6,7,8,9
            }else{
                if(a[1]==0)res++;  // No reserved seat in 4,5,6,7
                else if(a[2]==0)res++; // No reserved seat in 6,7,8,9
            }
        }
        return res+((n-m.size())*2);  //If there is no reserved seat in a row, 2 families can be placed there
    }
}

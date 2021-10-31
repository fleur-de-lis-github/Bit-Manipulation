/* There is an integer array perm that is a permutation of the first n positive integers, where n is always odd.

It was encoded into another integer array encoded of length n - 1, such that encoded[i] = perm[i] XOR perm[i + 1]. For example, if perm = [1,3,2], then encoded = [2,1].

Given the encoded array, return the original array perm. It is guaranteed that the answer exists and is unique.

 

Example 1:

Input: encoded = [3,1]
Output: [1,2,3]
Explanation: If perm = [1,2,3], then encoded = [1 XOR 2,2 XOR 3] = [3,1]
Example 2:

Input: encoded = [6,5,4,6]
Output: [2,4,1,5,3]
 

Constraints:

3 <= n < 105
n is odd.
encoded.length == n - 1 */

/*Intuition
If the first element is determined,
the whole array can be decoded.
1720. Decode XORed Array

But if we enumerate the first element,
the overall complexity will be O(n^2),
which will be TLE.


Explanation
We make good use of the condition "n is odd" as follow
a1,(a2,a3),(a4,a5).....,
making the decoded into pairs.
a2^a3 = A[1]
a4^a5 = A[3]
a6^a7 = A[5]
...
so we can have the result of a2^a3^a4...^an.
And a1,a2,a3... is a permuatation of 1,2,3,4..n

so we can have
a1 = 1^2^3...^n^a2^a2^a3...^an

Then we can deduct the whole decoded array.


Complexity
Time O(n)
Space O(n) 


Java */

class Solution {
    public int[] decode(int[] A) {
        int n = A.length + 1, a = 0, res[] = new int[n];
        for (int i = 0; i <= n; ++i) {
            a ^= i;
            if (i < n && i % 2 == 1) {
                a ^= A[i];
            }
        }
        res[0] = a;
        for (int i = 0; i < n - 1; ++i) {
            res[i + 1] = res[i] ^ A[i];
        }
        return res;
    } }

# parkimovil
Instructions
1.	You must return your solution within 4 hours after you receive these instructions. Your solution is disqualified if it arrives late. Partial solutions will be considered, but a complete solution is expected.
2.	You can use any programming language you want. You must submit your code for review, zipped.
3.	Your program must read an ASCII text file as input and print its output to standard out.
4.	Include instructions for running your program. If you use a compiled language, you must include compilation instructions as well. We do not want your compiled program, we want the source code and compilation instructions, so that we can compile it ourselves.
Problem
Approximately 23 light-years from planet Earth there exists planet Parkimovil. Planet Parkimovil’s surface is covered by a great ocean dotted by many small islands. 
For this question, Parkimovil’s surface will be modeled as a 2D plane. There exist N islands scattered on this 2D plane. The ith island is located at at (Xi, Yi). 
On this plane, line segments are defined in terms of islands. A line segment is bounded by two islands.
A Parkimovil archipelago consists of two distinct but equal-length line segments which have one shared island endpoint and two distinct island endpoints. Parkimovil archipelagos are considered distinct if they're not made up of the same three islands. 
Your goal is to find out how many distinct Parkimovil archipelagos exist. 
Input
Input begins with an integer T, the number of test cases in the file. 
For each test case there is an integer N. The following N lines contain the space separated integers Xi and Yi.
Output
Print a line containing the number of Parkimovil archipelagos in the ocean.
Constraints
1 ≤ T ≤ 50 
1 ≤ N ≤ 2,000 
-10,000 ≤ Xi, Yi ≤ 10,000




Sample Input and Outputs

 
Sample Input:                                Sample Output: 
1                                                       0 
3
0     0
0     1
0     3

Sample Input:                                Sample Output: 
2                                                        4
5                                                        4
0     0
0     1
0     2
0     3
0     4
4
0     0 
1     0
0     1
-1    0


File: test_case.txt
Correct result of
archipelagos found for this
test case should be 94.

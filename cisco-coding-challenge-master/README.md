# cisco-coding-challenge
Cisco interview coding assignment

#Comments

The package structure follows a standard pattern.  I used intelliJ IDE.

I am new to lambda coding and the streams API but tried to use the streams API as much as possible where I thought I could.

#GNode Problem

<pre>
 A
 |
 |-- B
 |   |-- E
 |   |-- F
 |
 |-- C
 |   |-- G
 |   |-- H
 |   |-- I
 |
 |-- D
 </pre>

This was by far the most difficult of the two coding challneges for me.  It has been sometime since I have worked with any n-ary tree data structures and recursive algorithms.

I did not finish the problem but tried a number of differnt approaches.  I was able to get preorder, levelorder, and postorder traversal working and thought I could use levelorder traversal to create a solution.  

With respect to path construction, I got stuck on how to implement traversing up to a parent node from a child leaf node to check to see if there were more child nodes for the current parent.


#WordList Problem

This was pretty straight-forward for me.
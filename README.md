# DistanceTransform
I. Input: a binary image

II. Outputs (argv[]):
1) outFile1 – Create a distance transform image from the result of Pass-2
with updated image header (numRows numCols newRowVal newColVal)
for future processing.
2) outFile2 for visualization

- Pretty print the result of the Pass-1 of distance transform
with proper caption
- Pretty print the result of the Pass-2 of distance transform
with proper caption

*******************************
III. Data structure:
*******************************
- An ImageProcessing class
- numRows (int)
- numCols (int)
- minVal (int)
- maxVal (int)
- newMinVal (int)
- newMaxVal (int)
- ZeroFramedAry (int **)  //a 2D array, need to dynamically allocate at run time of size numRows + 2 by numCols + 2.
- NeighborAry[5] (int)
- methods:
- constructor(s)
- need to dynamically allocate ZeroFrameAry
- assign values to numRows,..., etc.

- zeroFramed
- loadImage // Read from the input file onto ZeroFrameAry at ZeroFrameAry[1][1]

- loadNeighbors     // load the respective neighbors of given pixel(i,j)

- fistPassDistance
- secondPassDistance

- prettyPrint

*******************************
III. Algorithms
*******************************
step 0: 
- read the image header
- dynamically allocate zerpFramedAry with extra 2 rows and 2 cols

step 1: 
- zeroFrame the zerpFramedAry.

Step 2: 
- loadImage

step 3: 
- firstPassDistance (…) 

step 4: 
- prettyPrintof the result of Pass-1 to outFile2 with caption indicating the result of pass-1

step 5: 
- secondPassDistance (…)

Step 6: 
- prettyPrintDistace of the result of Pass-2 

Step 7: 
- close all files.

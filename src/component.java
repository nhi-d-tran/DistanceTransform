import java.io.BufferedWriter;
import java.io.IOException;

public class component 
{
	int numRows;
	int numCols;
	int minVal;
	int maxVal;
	int newMin;
	int newMax;
	int newLabel;
	int[][] zeroFramedAry;
	int[] neighborAry;
	int[] EQAry;

	
	component(int rows, int cols, int min, int max)
	{
		numRows = rows;
		numCols = cols;
		minVal = min;
		maxVal = max;
		newLabel = 0;
		newMin = 999;
		newMax = 0;
		zeroFramedAry = new int[numRows + 2][numCols + 2];
		neighborAry = new int[5];
		int sizeAry = (numRows * numCols)/4;
		EQAry = new int[sizeAry];

		for(int i = 0; i < sizeAry; i++)
		{
			EQAry[i] = i;
		}
	}
	
	void zeroFramed()
	{
		for(int i = 1; i <= numCols + 1; i++)
		{
			zeroFramedAry[0][i] = 0;//zeroFramedAry[1][i];
			zeroFramedAry[numRows + 1][i] = 0;//zeroFramedAry[numRows][i];
		}

		for(int j = 0; j <= numRows + 1; j++)
		{
			zeroFramedAry[j][0] = 0; //zeroFramedAry[j][1];
			zeroFramedAry[j][numCols + 1] = 0; //zeroFramedAry[j][numCols];
		}
	}
	
	int getNeighbor()
	{
		int temp = 0;

		for(int i = 0; i < neighborAry.length; i++)
		{
			if(neighborAry[i] != 0)
			{
				temp = neighborAry[i];
				break;
			}
		}
		return temp;
	}
	
	boolean neighborsSame()
	{
		int temp = neighborAry[0];
		for(int i = 1; i < neighborAry.length; i++)
		{
			if(temp == 0)
			{
				if(neighborAry[i] != 0)
				{
					temp = neighborAry[i];
				}
			}
			else
			{
				if(neighborAry[i] != 0)
				{
					if(neighborAry[i] != temp)
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	boolean notSameLabel()
	{
		int temp = neighborAry[0];

		for(int i = 1; i < neighborAry.length; i++)
		{
			if(neighborAry[i] == 0 || temp == 0)
			{
				temp = neighborAry[i];
				continue;
			}
			else
			{
				if(neighborAry[i] != temp)
				{
					return false;
				}
			
			}
		}
		return true;
	}
	
	boolean allZero()
	{
		for(int i = 0; i < neighborAry.length; i++)
		{
			if(neighborAry[i] != 0)
			{
				return false;
			}
		}
		return true;
	}
	
	int findMin()
	{
		int min = 1000;
		for(int i = 0; i < neighborAry.length; i++)
		{
			if(min > neighborAry[i] && neighborAry[i] > 0)
			{
				min = neighborAry[i];	
			}
		}
		return min;
	}
	
	void ConnecCC_Pass1() 
	{
		zeroFramed();
		
		for(int i = 1; i <= numRows; i++)
		{
			for(int j = 1; j <= numCols; j++)
			{
				if(zeroFramedAry[i][j] > 0)
				{
					loadNeighborsTop(i, j);
					if(allZero() == true)
					{
						newLabel++;
						zeroFramedAry[i][j] = newLabel;
					}
					else if(neighborsSame() == true)	// case 2
					{
						zeroFramedAry[i][j] = getNeighbor();
					}
					else
					{
						int min = findMin();
						

						for(int z = 0 ; z < neighborAry.length; z++)
						{
							if(neighborAry[z] > 0 && neighborAry[z] != min)
							{
								if(neighborAry[z] != 1)
								{
									EQAry[neighborAry[z]] = min;
								}
							}
						}

						zeroFramedAry[i][j] = min;
					}
				}
			}
		}
	}
	
	void manageEQAry()
	{
		int count = 0;
		
		for(int i = 0; i < EQAry.length; i++)
		{
			if(EQAry[i] == i)
			{
				EQAry[i] = count++;
			}
			else
			{
				EQAry[i] = EQAry[EQAry[i]];
			}
		}

	}
	
	void ConnecCC_Pass2() 
	{
		for(int i = numRows; i > 0; i--)
		{
			for(int j = numCols; j > 0; j--)
			{
				if(zeroFramedAry[i][j] > 0)
				{
					loadNeighborsDown(i, j);
				
					if(notSameLabel() == true)
					{
						int min = findMin();
						for(int z = 0 ; z < neighborAry.length; z++)
						{
							if(neighborAry[z] > 0 && neighborAry[z] != min)
							{
								if(neighborAry[z] != 1)
								{
									EQAry[neighborAry[z]] = min;
								}
							}
						}
						zeroFramedAry[i][j] = findMin();
					}
				}
			}
		}


	}
	
	void ConnecCC_Pass3() 
	{
		for(int i = 1; i <= numRows; i++)
		{
			for(int j = 1; j <= numCols; j++)
			{
				zeroFramedAry[i][j] = EQAry[zeroFramedAry[i][j]];
			}
		}

	}
	
	void prettyPrint(BufferedWriter outFile)
	{
		try 
		{
			for(int i = 0; i <= numRows + 1; i++)
			{
				for(int j = 0; j <= numCols + 1; j++)
				{
					if(zeroFramedAry[i][j]  > 0)
					{
						outFile.write(zeroFramedAry[i][j]  + " ");
					}
					else
					{
						outFile.write("  ");
					}
				}
				outFile.write("\n");
			}
			outFile.write("\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void EQPrint(BufferedWriter outFile)
	{
		try 
		{
			outFile.write("EQAry: \n");
			
			for(int i = 0; i < newLabel + 1; i++)
			{
				outFile.write(EQAry[i] + " ");
			}
			outFile.write("\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void loadNeighborsDown(int i, int j)
	{
		int counter = 0;
		neighborAry[counter] = zeroFramedAry[i][j + 1];
		counter++;
		
		for(int c = j - 1; c <= j + 1; c++)
		{
			neighborAry[counter] = zeroFramedAry[i + 1][c];
			counter++;
		}
	}
	
	void loadNeighborsTop(int i, int j)
	{
		int counter = 0;

		for(int r = i - 1; r <= i; r++)
		{
			for(int c = j - 1; c <= j + 1; c++)
			{
				if(r == i && c == j)
				{
					break;
				}
				else
				{
					neighborAry[counter] = zeroFramedAry[r][c];
					counter++;
				}
			}
		}
	}
}

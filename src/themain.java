import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class themain
{
	static class Property 
	{
		int label;
		int numpixels;
		int minRow;
		int minCol;
		int maxRow;
		int maxCol;
		
		Property()
		{
			label = 0;
			numpixels = 0;
			minRow = 1000;
			minCol = 1000;
			maxRow = 0;
			maxCol = 0;
		}	
		
		Property(int i)
		{
			label = i;
			minRow = 1000;
			minCol = 1000;
			maxRow = 0;
			maxCol = 0;
		}
		
	}
	
	public static void main(String[] args) 
	{
		try
		{
			Scanner inFile = new Scanner(new FileReader(args[0]));	
			
			
			File inFile1 = new File(args[0].substring(0,args[0].length()-4)+"PrettyPrint_Three_Pass");
			BufferedWriter outFile1 = new BufferedWriter(new FileWriter(inFile1));

			File inFile2 = new File(args[0].substring(0,args[0].length()-4)+"Result_of_pass3");
			BufferedWriter outFile2 = new BufferedWriter(new FileWriter(inFile2));

			File inFile3 = new File(args[0].substring(0,args[0].length()-4)+"_Properties");
			BufferedWriter outFile3 = new BufferedWriter(new FileWriter(inFile3));
			
			
			if(args.length < 1) 
		    {
		        System.out.println("Error");
		        System.exit(1);
		    }
			
			int numRows = inFile.nextInt();
			int numCols = inFile.nextInt();
			int minVal = inFile.nextInt();
			int maxVal = inFile.nextInt();

			component image = new component(numRows, numCols, minVal, maxVal);
			
			//////////////////////Load input file/////////////////////
			while(inFile.hasNext())
			{
				for(int i = 1; i <= image.numRows; i++)
				{
					for(int j = 1; j <= image.numCols; j++)
					{
						image.zeroFramedAry[i][j] = inFile.nextInt();
					}
	
				}
			}
			
			//////////////////////PASS 1/////////////////////
			outFile1.write("----Result of Pass 1----\n");
			image.ConnecCC_Pass1();
			image.prettyPrint(outFile1);
			outFile1.write("\n");
			image.EQPrint(outFile1);
			outFile1.write("\n");
			
			//////////////////////PASS 2/////////////////////
			outFile1.write("----Result of Pass 2----\n");
			image.ConnecCC_Pass2();			
			image.prettyPrint(outFile1);
			outFile1.write("\n");
			image.EQPrint(outFile1);
			outFile1.write("\n");
			
			//////////////////////EQAry after manage/////////////////////	
			outFile1.write("----EQ After Manage---- \n");
			image.manageEQAry();
			image.EQPrint(outFile1);
			outFile1.write("\n");
			
			//////////////////////PASS 3/////////////////////
			outFile1.write("----Result of Pass 3----\n");
			image.ConnecCC_Pass3();
			image.prettyPrint(outFile1);
			outFile2.write("----Result of Pass 3----\n");
			image.prettyPrint(outFile2);
			image.EQPrint(outFile2);
			outFile1.write("\n");
			
			//Update newMin, newMax
			for(int i = 1; i <= numRows; i++)
			{
				for(int j = 1; j <= numCols; j++)
				{
					if(image.zeroFramedAry[i][j] > 0)
					{
						if(image.zeroFramedAry[i][j] > image.newMax)
						{
							image.newMax = image.zeroFramedAry[i][j];
						}
						else if(image.zeroFramedAry[i][j] < image.newMin)	
						{
							image.newMin = image.zeroFramedAry[i][j];
						}
					}
				}
			}		
			
			//////////////////////Property/////////////////////
			
			//Finding min row and min col bouding
			int[] counter = new int[image.newMax + 1];
			
			Property[] prop = new Property[image.newMax + 1];
			for(int i = 1; i < image.newMax + 1; i++)
			{
				Property p = new Property(i);
				prop[i] = p;
			}

	
			
			
			for(int i = 1; i <= numRows; i++)
			{
				for(int j = 1; j <= numCols; j++)
				{
					if(image.zeroFramedAry[i][j] > 0)
					{
						counter[image.zeroFramedAry[i][j]]++;
						
						if(prop[image.zeroFramedAry[i][j]].minRow > i)
						{
							prop[image.zeroFramedAry[i][j]].minRow = i;
						}
						if(prop[image.zeroFramedAry[i][j]].minCol > j)
						{
							prop[image.zeroFramedAry[i][j]].minCol = j;
						}
						if(prop[image.zeroFramedAry[i][j]].maxRow < i)
						{
							prop[image.zeroFramedAry[i][j]].maxRow = i;
						}
						if(prop[image.zeroFramedAry[i][j]].maxCol < j)
						{
							prop[image.zeroFramedAry[i][j]].maxCol = j;
						}
					}	
				}
			}	
			
	
			for(int i = 1; i < image.newMax + 1; i++)
			{
				prop[i].numpixels = counter[i];
				outFile3.write("Label: " + prop[i].label + "\n");
				outFile3.write("Number of pixels: " +prop[i].numpixels + "\n");
				outFile3.write("(Min row, Min col): (" +prop[i].minRow +  ", " + prop[i].minCol + ")" + "\n");
				outFile3.write("(Max row, Max col): (" +prop[i].maxRow + ", " + prop[i].maxCol + ")" + "\n");
				outFile3.write("\n");
				outFile3.write("------------------------------\n");
			}
			
			//close all file
		    inFile.close();
		    outFile1.close();
		    outFile2.close();
		    outFile3.close();
		} 	
		catch (IOException e)
		{
			e.printStackTrace();
		}	
	
	}

}

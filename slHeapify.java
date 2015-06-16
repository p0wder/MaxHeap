/*
 * Author:  Scott Gramig
 * Project: max-heap
 */
import java.util.Scanner;
import java.io.*;

public class slHeapify 
{
	public static int[] a = null;// array to hold heap
	public static String[] str;// array to hold input text data
	public static int rMax, m;// max values
	public static int count = 0, count2;// counts
	private static Scanner input;// Scanner object
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
	{
		Scanner kb = new Scanner(System.in);//input from keyboard
		
		String line = null;// var to hold input from from text file
		String fileName, newFile;
		
		System.out.print("\nEnter the file name to use:  ");//   where my file is---> /home/p0wder/workspace/crHeaper/src/numbers.txt
		fileName = kb.nextLine();//user input location of file
		System.out.print("\nName the desired output file(.txt):  ");
		newFile = kb.nextLine();//creating a file to store output
		PrintWriter pw = new PrintWriter(newFile, "UTF-8");
		
		input = new Scanner(new File(fileName));//reading the text file from user specified location
		
		while(input.hasNext())//saves data from text file and stores to line as a string
		{
			line = input.nextLine();
		}
		
		pw.print("Input: " + line);//displays raw data from txt file
		
		str = line.split(",");//splits the string into an array with regex "," to separate numbers
		count = str.length;
		count2 = count;
		
		a = new int[count];//the int array used to create a heapify
		
		for(int i = 0; i < count; i++)// parse string to int to array a
			a[i] = Integer.parseInt(str[i]);

		//make array a heapify
		for(int i = (count-1)/2; i >= 0; i--)
			heapify(i);
		
		//print original heap
		pw.print("\nMax-Heap: ");
		for(int j = 0; j < count; j++)
			pw.print(a[j] + " ");
		
		pw.print("\nSorted Array:  ");
		sortHeapArray(a);
		for(int j = 0; j < count2; j++)
			pw.print(a[j] + ",");
		
		pw.close();
	}
	//at this point it is already in a max heap
	public static void sortHeapArray(int[] a)
	{
		int max;
		int end = count2-1;
		
		for(int i = a.length-1; i >= 0; i--)
		{
			max = removeMax();
			a[end] = max;
			end--;
		}
	}
	//heapify and sift methods provide the sorting for the max-heap
	public static void heapify(int i)
	{
		int parent;
		int leftChild = 2*i+1;
		int rightChild = 2*i+2;
		
		if((leftChild < count) && (a[leftChild] > a[i]))
			parent = leftChild;
		else
			parent = i;
		
		if((rightChild < count) && (a[rightChild] > a[parent]))
			parent = rightChild;
		
		if(parent != i)
		{
			sift(i, parent);
			if(parent <= (count/2))
				heapify(parent);
		}
	}
	
	//used with heapify method to sort max-heap
	private static void sift(int i, int max)
	{
		int j = a[i];
		a[i] = a[max];
		a[max] = j;
	}
	
	//removes the maximum element(the top), moves the last element to the top and sorts the array into max-heap
	private static int removeMax()
	{
		rMax = a[0];
		a[0] = a[count-1];
		a[count-1] = rMax;

		count--;
		heapify(0);
		return rMax;
	}
}

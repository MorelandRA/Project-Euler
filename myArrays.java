import java.math.BigInteger;

public class myArrays {
	public static void main(String[] args){
		int arr[] = {253, 123, 123 ,345, 56756, 13, 345, 346 ,76, 324, 2,5,7,4,0,2,12,3,546};
		bubbleSort(arr, false);
		printArr(arr);
		
	}
	
	//TODO: Combine some of these printArr functions, or make a myPrint class
	static void printArr(boolean arr[]){ //Prints booleans; prints T for 1/true and F for 0/false.
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]?"T ":"F ");
		}
		System.out.println();
	}
	
	static void printArr(char arr[]){ //Prints chars without spaces between them.
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i]);
		}
		System.out.println();
	}
	
	static void printArr(byte arr[], boolean asChar){ //Prints bytes, or prints them as chars if asChar is true.
		//TODO: remove duplicated code between printArr(byte arr[], boolean asChar==TRUE) and printArr(char arr[])
		if(!asChar){
			for(int i = 0; i < arr.length; i++){
				System.out.print(arr[i] + " ");
			}
		} else {
			for(int i = 0; i < arr.length; i++){
				System.out.print((char)arr[i]);
			}
		}
		System.out.println();
	}
	
	static void printArr(int arr[]){ //Prints ints with a space between them
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	static void printArr(long arr[]){ //Prints longs with a space between them
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	static void printArr(BigInteger arr[]){ //Prints BigIntegers with a space between them
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i].toString() + " ");
		}
		System.out.println();
	}
	
	static void printArr(int arr[][]){ //Prints ints as a 2D array
		for(int i = 0; i < arr.length; i++){
			printArr(arr[i]);
		}
		System.out.println();
	}
	
	static void bubbleSort(int arr[], boolean descending){ //If descending is true, sorts in descending order
		boolean changeMade = true; //changeMade allows the loops to stop early if the array is sorted.
		for(int i = 0; i < arr.length && changeMade; i++){
			changeMade = false;
			for(int j = 0; j < arr.length-i-1; j++){
				changeMade = bubbleSortCompare(arr, i, j, descending) | changeMade; // Order here is important; the compare has to be made regardless of if changeMade is already true
			}
		}
	}
	
	private static boolean bubbleSortCompare(int arr[], int i, int j, boolean descending){ //returns if a switch was necessary
		if((!descending && arr[j] > arr[j+1]) || (descending && arr[j] < arr[j+1])){
			/*
			 *  goal is to sort in   | int is less than || Switch?
			 *  in descending order? | the next int?    ||
			 *  ==================================================
			 *     FALSE             | FALSE            || FALSE
			 *     FALSE             | TRUE             || TRUE
			 *     TRUE              | FALSE            || TRUE
			 *     TRUE              | TRUE             || FALSE
			 * 
			 */
			
			
			int temp = arr[j];
			arr[j] = arr[j+1];
			arr[j+1] = temp;
			return true;
		}
		return false;
	}
}

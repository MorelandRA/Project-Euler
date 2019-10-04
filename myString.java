/* A collection of generic String-related functions
 * 
 * Randall Moreland
 */

public class myString {
	public static void main(String[] args){

	}
	
	static String nthPerm(int n, String str){ //Finds the nth permutation of a string. Recursive O(n) algorithm
		/*
		 * Example, permutations of 'abc'
		 * n | result
		 * ---------
		 * 0 | abc
		 * 1 | acb
		 * 2 | bac
		 * 3 | bca
		 * 4 | cab
		 * 5 | cba
		 * 
		 */
				
		if(str.length() <= 1) //If the string is length 1 or 0, all permutations will result in the original string.
			return str;
		
		int factorial = (int)myMath.factorial(str.length());
		if(n >= factorial){ //if n > the number of permutations, throw an exception
			throw new ArithmeticException(n + " is greater than the number of permutations");
		}
		
		
		factorial /= str.length(); 
		int nOverFactorial = n/factorial;
		
		int newN = (int) (n % factorial);
	
		String newString = str.substring(0, nOverFactorial)  
						 + str.substring( nOverFactorial + 1); //Cuts out the 'nOverFactorial'th digit
		
		//Takes the 'nOverFactorial'th digit, puts it at the front, and permutes the remaining digits.
		
		return str.charAt(nOverFactorial) + nthPerm(newN, newString);
	}
	
	static String rotate(String str){ //Moves the last character to the front
		return (str.substring(1) + str.charAt(0));
	}
	
	static boolean isPalindrome(String string){ //Checks if a string is a palindrome
		for(int i = 0; i < string.length()/2; i++)
			if(string.charAt(i) != string.charAt(string.length()-1-i))
					return false;
		return true;
	}
	
	static boolean isPermutation(String string1, String string2)  { //Checks if two strings are permuations of each other
		int counts[] = new int[256];
		
		//Takes the characters of string1 and puts them into buckets.
		for(int i = 0; i < string1.length(); i++){
			counts[string1.charAt(i)]++;
		}
		
		//Then, removes characters from the buckets to build string2
		for(int i = 0; i < string2.length(); i++){
			counts[string2.charAt(i)]--;
		}
		
		//If there are letters remaining, or a bucket is more-than-empty, then they are not permuations
		for(int i = 0; i < counts.length; i++){
			if(counts[i] != 0) return false;
		}
		
		return true;
	}

	public static String reverse(String string) { //Returns the string, backwards.
		String reverse = "";
		
		for(int i = string.length()-1; i >= 0; i--){
			reverse += string.charAt(i);
		}
		
		return reverse;
	}
}

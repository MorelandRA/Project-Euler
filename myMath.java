/* A collection of generic math-related functions
 * 
 * Randall Moreland
 */

class myMath{
	public static void main(String[] args){

	}
	
	static int romanNumeralToInt(String numeral) {
		int val = getRomanCharVal(numeral.charAt(numeral.length()-1));
		
		for(int i = 0; i < numeral.length()-1; i++){
			val += getRomanCharVal(numeral.charAt(i), numeral.charAt(i+1));
		}
		
		return val;
	}
	
	private static int getRomanCharVal(char c) {
		if(c <= 'I'){ //C,D,I
			if(c < 'D') return 100; //C
			if(c == 'D') return 500; //D
			else if(c == 'I') return 1; //I
		}//L,M,V,X
		else if(c <= 'M'){ // L, M
			if(c == 'L') return 50; //L
			else if (c == 'M') return 1000; //M
		} // else, V X
		if(c == 'V') return 5; //V
		else if (c == 'X') return 10; //X
		
		throw new ArithmeticException(c + " is not a valid character for a Roman Numeral.");
	}
	
	private static int getRomanCharVal(char c1, char c2) {
		int val1 = getRomanCharVal(c1);
		int val2 = getRomanCharVal(c2);
		if(val1 < val2) return -val1;
		return val1;
	}
	
	static String toRomanNumeral(int num) {
		
		if(num >= 5000){
			throw new ArithmeticException("toRomanNumeral is not designed for numbers >= 5000. Attempted for " + num);
		}
		
		String numeral = "";
		
		while(num >= 1000){
			numeral += 'M';
			num -= 1000;
		}
		
		if(num >= 900){
			numeral += "CM";
			num -= 900;
		}
		
		if(num >= 500){
			numeral += 'D';
			num -= 500;
		}
		
		if(num >= 400){
			numeral += "CD";
			num -= 400;
		}
		
		while(num >= 100){
			numeral += 'C';
			num -= 100;
		}
		
		if(num >= 90){
			numeral += "XC";
			num -= 90;
		}
		
		if(num >= 50){
			numeral += 'L';
			num -= 50;
		}
		
		if(num >= 40){
			numeral += "XL";
			num -= 40;
		}
		
		while(num >= 10){
			numeral += 'X';
			num -= 10;
		}
		
		if(num >= 9){
			numeral += "IX";
			num -= 9;
		}
		
		if(num >= 5){
			numeral += 'V';
			num -= 5;
		}
		
		if(num >= 4){
			numeral += "IV";
			num -= 4;
		}
		
		while(num > 0){
			numeral += 'I';
			num -= 1;
		}
		
		return numeral;
	}
	
	static boolean isPrime(long num){ //Filters through primes; for large numbers of primes, use a sieve instead. 1 is considered prime.
		if(num < 0)
			throw new ArithmeticException("isPrime is not designed for negative numbers. Attempted for " + num);
		if(num == 2 || num == 3) return true; //2 and 3 are prime
		if(num < 5) return false; //all other numbers under 5 are composite
		if((num+1)%6 != 0 && (num-1)%6 != 0) return false; //all other primes are either one less than or one greater than a multiple of 6
		if(num%2 == 0) return false; //primes are not multiples of 2
		if(num%3 == 0) return false; //primes are not multiples of 3
		long sqrt = (int)Math.sqrt(num); //if a number is composite, it will have a factor less than or equal to the square root of the number
		for(int i = 5; i <= sqrt; i+= 6){ //Check every number one less than or one greater than a multiple of 6 to see if it is a factor, up to the square root of the possible prime
			if(num%i == 0) return false;
			if(num%(i+2) == 0) return false;
		}
		return true; //If a factor was not found, the number is prime.
	}
	
	static long factorial(int num){ //Calculates the factorial of a number. 
		if(num > 20)
			throw new ArithmeticException(num + "! is greater than the range for a long");
		if(num <= 1)
			return 1;
		
		//There's only 19 between 2 and 20 inclusive, so writing out a binary search is feasible, and faster than calculating.
			
		if(num <= 11){ //2-11
			if(num <= 5){ //2-5
				if(num <= 3){ //2, 3
					if(num == 2){ //2
						return 2;
					} else { //3
						return 6;
					}
				} else { // 4, 5
					if(num == 4){ //4
						return 24;
					} else { //5
						return 120;
					}
				}
			} else { //6-11
				if(num <= 8){ //6,7,8
					if(num <= 7){ //6,7
						if(num == 6){ //6
							return 720;
						} else { //7
							return 5040;
						}
					} else { //8
						return 40320;
					}
				} else { // 9,10,11
					if(num <= 10){ //9,10
						if(num == 9){ //9
							return 362880;
						} else { //10
							return 3628800;
						}
					} else { //11
						return 39916800;
					}
				}
			}
		} else { //12-20
			if(num <= 16){ //12-16
				if(num <= 13){ //12,13
					if(num == 12){ //12
						return 479001600;
					} else { //13
						return 6227020800L;
					}
				} else { // 14-16
					if(num <= 15){ //14,15
						if(num == 14){
							return 87178291200L;
						} else {
							return 1307674368000L;
						}
					} else { //16
						return 20922789888000L;
					}
				}
			} else { //17-20
				if(num <= 18){ //17,18
					if(num == 17){ //17
						return 355687428096000L;
					} else { //18
						return 6402373705728000L;
					}
				} else { // 19, 20
					if(num == 19){ //19
						return 121645100408832000L;
					} else { //20
						return 2432902008176640000L;
					}
				}
			}
		}		
	}
	
	static int[] getTotientArray(int range){
		
		// The totient of a number N is the number of integers that N is relatively prime to.
		
		int phi[] = new int[range+1];
		phi[1] = 1;
		
		for(int i = 2; i <= range; i++){
			int sqrt = (int) Math.sqrt(i);
			boolean found = false;
			for(int j = 2; j <= sqrt && !found; j++){
				if(i%j == 0){ //A factor was found
					found = true;
					int factor = j;
					while(factor < i && i%(factor*j) == 0){
						factor*= j;
					}
					
					if(factor == i){// j^x = i
						phi[i] = i-(i/j);
					}
					
					else{// i has two factors; calculate with memo 
						phi[i] = phi[factor]*phi[i/factor];
					}
				}
			}
			if(!found){//prime
				phi[i] = i-1;
			}
		}
		
		return phi;
	}
	
	static boolean[] getPrimeSieve(int size){
		
		//Returns a boolean array where arr[i] == true if i is prime
		
		boolean isPrime[] = new boolean[size];
		
		//Assume all numbers are prime
		for(int i = 0; i < size; i++)
			isPrime[i] = true;
		
		//For each prime, recognize all multiples of the prime as composite numbers.
		for(int i = 2; i < size; i++){
			if(isPrime[i]){
				for(int j = 2; j*i < size; j++){
					isPrime[j*i] = false;
				}
			}
		}
		
		return isPrime;
	}
	
	//Returns if a number is pandigital
	//For example, 123654789 is pandigital complete
	//123654 is pandigital
	static boolean isPandigital(int num, boolean complete){
		int arr[] = {num};
		return isPandigital(arr, complete);
	}
	
	//Returns if a group of numbers are pandigital
	//For example, {123,654,789} is pandigital complete
	// {123,654} is pandigital
	
	//TODO: Make this more generic
	static boolean isPandigital(int nums[], boolean complete){
		
		//Buckets to check each value 1-9
		boolean wasFound[] = new boolean[9];
		for(int i = 0; i < nums.length; i++){
			int val = nums[i];
			while(val > 0){
				if(val%10 == 0) return false; //Not pandigital if there's a 0
				if(wasFound[(val-1)%10]) return false; //If there is a duplicate, it isn't pandigital.
				wasFound[(val-1)%10] = true;
				val /= 10;
			}
		}
		
		if(!complete) return true;
		
		for(int i = 0; i < wasFound.length; i++){ //Check to see if it's complete
			if(!wasFound[i]) return false;
		}
		
		return true;
		
	}

	/*
	 * Champernownes contant is a number with the pattern
	 * 0.123456789101112131415161718192021...
	 */
	public static int getDigitOfChampernownesConstant(int digit) {
		int index = 1;
		int numlen = 1;
		while(index + 9*(numlen)*Math.pow(10,numlen-1) <= digit){
			index += 9*(numlen)*Math.pow(10,numlen-1);
			numlen++;
		}
		int depth = 0;
		int val = 1;
		while(index < digit){
			while(index + numlen*Math.pow(10, numlen-1-depth)<=digit){
				index += numlen*Math.pow(10, numlen-1-depth);
				val++;
			}
			if((digit-index)%numlen==0)index=digit;
			else{
				val = 0;
				depth++;
				index++;
			}
		}
		return val;
	}

	/*
	 * A hamming number of type X is a number whose divisors are all
	 * less than or equal to X
	 */
	public static boolean[] getHammingSieve(int type, int range) {
		boolean isHamming[] = new boolean[range+1];
		
		isHamming[1] = true;
		
		for(int i = 1; i <= range; i++){
			if(isHamming[i]){
				for(int j = 2; j*i <= range && j <= type; j++){
					isHamming[i*j] = true;
				}
			}
		}
		
		return isHamming;
	}
}

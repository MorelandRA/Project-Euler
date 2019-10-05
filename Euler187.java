class Euler187{
	
	// https://projecteuler.net/problem=187
	// Randall Moreland
	
	static void run(){
		
		// A number calculated by multiplying together two primes
		// will never be divisible by a 3rd prime.
		
		// That is, the factors of X*Y, where X and Y are primes,
		// are 1, X, Y, and X*Y
		
		int count = 0; //Counts how many integers have 2 prime factors
		int range = 100000000; //The range for the prime sieve, and the range for n
		boolean isPrime[] = myMath.getPrimeSieve(range/2); //Generates a prime sieve. Can't be smaller than this.
		int sqrt = (int)Math.sqrt(range); //The lesser factor will always be less than sqrt(range)
		for(int i = 2; i <= sqrt; i++){ //So test every number up to or equal to sqrt ...
			if(isPrime[i]){ // ... that is also prime, and use it as the lesser factor
				for(int j = i; i*j < range; j++){ // Test every other factor that produces a product within range... 
					if(isPrime[j]){ // ... and confirm that the second factor is also prime
						count++; //At this point, we have multiplied two primes together that produce a product within range.
					}			 // Because we know this product is unique, we have found a valid candidate, so increment count.
				}
			}
		}
		
		//We finish once we have counted every valid combination of prime factors.
		System.out.println(count);
	}
}

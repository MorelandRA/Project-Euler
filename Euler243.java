class Euler243{
	
	// https://projecteuler.net/problem=243
	// Randall Moreland
	
	static void run(){
		
		//Jumps between the lowest points on the resilience graph until an acceptable resilience is found
		
		double goal = 15499/(double)94744; //The threshold for acceptable resilience
		double min = 1; // The lowest resilience that has been found so far
		int phiGuess = 1; //An approximator for phi
		int phiGuessIncrement = phiGuess; //Used to calculate the next phiGuess
		int denom = 2; //The test denominator
		int denomIncrement = denom;  //Denominator increment, used to calculate the next test denominator
		int prime = 3;
		int count = 2;
		while(min > goal){ //While we still haven't found a suitable fraction
			denom += denomIncrement; //check the next denominator
			if(count == 1) denomIncrement = denom; //Reset the increment when count is reset
			if(count++ < prime) phiGuess += phiGuessIncrement; //calculate the new resilience numerator
			
			min = phiGuess/(double)(denom-1); //Calculate the new resilience
			
			if(count == prime){ //When count reaches prime,
				count = 1; //Reset count to 1
				prime++; //Set prime to the next prime number
				while(!myMath.isPrime(prime)) prime++;
				phiGuessIncrement = phiGuess; //Recalculate the numerator increment
				phiGuess = 0; //Reset the resilience numerator
			}
		}
		//Once out of the loop, an acceptable resilience was found
		System.out.println(denom);
	}
	
}

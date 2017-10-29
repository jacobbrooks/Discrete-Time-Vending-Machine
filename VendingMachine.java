public class VendingMachine{
	
	private int quarters;
	private int dimes;
	private int nickels;
	private int value;

	public VendingMachine(){
		quarters = 0;
		dimes = 0;
		nickels = 0;
		value = 0;
	}

	public void deltaInternal(){
		String change;
		if(value > 100){
			change = makeChange(value % 100);
		}else{
			change = makeChange(value);
		}
		for(int i = 0; i < change.length(); i++){
			if(change.charAt(i) == 'q'){
				quarters--;
			}else if(change.charAt(i) == 'd'){
				dimes--;
			}else if(change.charAt(i) == 'n'){
				nickels--;
			}	
		}
		value = 0;
	}

	public void deltaExternal(char coin){
		if(coin == 'q'){
			quarters++;
			value += 25;
		}else if(coin == 'd'){
			dimes++;
			value += 10;
		}else{
			nickels++;
			value += 5;
		}
	}

	public void deltaConfluent(char coin){
		deltaInternal();
		deltaExternal(coin);
	}

	public String[] lambda(){
		String[] outputBag = new String[2];
		int coffees = (value - (value % 100)) / 100;
		String coffeeOutput;
		String change;
		if(coffees != value){
			if(coffees > 1){
				coffeeOutput = coffees + " coffees";
			}else{
				coffeeOutput = coffees + " coffee";
			}
		}else{
			coffeeOutput = "0 coffees";
		}
		if(value % 100 != 0){
			if(value > 100){
				change = makeChange(value % 100);
			}else{
				change = makeChange(value);
			}
		}else{
			change = null;
		}
		outputBag[0] = coffeeOutput;
		outputBag[1] = change;
		return outputBag;
	}

	public int timeAdvance(){
		if(value == 0){
			return Integer.MAX_VALUE;
		}else{
			return 2;
		}
	}

	private String makeChange(int value){
		int[] coinCounts = {quarters, dimes, nickels};
		int[] coinVals = {25, 10, 5};
		String[] stringVals = {"q", "d", "n"};
		String output = "";
		int leftOver = value;
		for(int i = 0; i < 3; i++){
			int remainder = leftOver % coinVals[i];
			if(remainder != leftOver){
				int thisMany = (leftOver - remainder) / coinVals[i];
				while(thisMany > coinCounts[i]){
					thisMany--;
				}
				if(value % 10 != 0 && value % 25 != 0){ 
					if(dimes != 0 && nickels == 0){
						if(i == 0 && thisMany > 1){
							thisMany--;
						}
					}
				}
				for(int j = 0; j < thisMany; j++){
					output += stringVals[i] + ",";
				}
				leftOver -= coinVals[i] * thisMany;
				if(leftOver == 0){
					break;
				}
			}
		}
		if(leftOver > 0){
			throw new AtomicModelException("There are not enough coins in the machine to make change.");
		}
		return output.substring(0, output.length() - 1);
	}

	public int getQuarters(){
		return quarters;
	}

	public int getNickels(){
		return nickels;
	}

	public int getDimes(){
		return dimes;
	}

	public int getValue(){
		return value;
	}

}
public class PrintingModule{

	public void printOutput(int quarters, int dimes, int nickels, int value, float realTime, int discreteTime, char in, String[] out){
		if(out[0] == null){
			out[0] = "-";
		}
		if(out[1] == null){
			out[1] = "-";
		}
		System.out.println("|t: " + realTime + ", " + discreteTime + "| |s: q = " + quarters + ", d = " + dimes + ", n = " 
			+ nickels + ", v = " + value + "| |in: " + in + " | |out: " + out[0] + ", " + out[1] + " |");
	}

}
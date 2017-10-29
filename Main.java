import java.io.FileNotFoundException;

public class Main{
	public static void main(String[] args) throws FileNotFoundException{
		Scheduler s = new Scheduler("inputs.txt");
		s.scheduleAndVend();
	}
}
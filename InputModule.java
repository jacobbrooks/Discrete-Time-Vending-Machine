import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputModule{

	private File f;
	private ArrayList<String> inputs;
	private Scanner stringScanner;

	public InputModule(String fileName) throws FileNotFoundException{
		f = new File(fileName);
		inputs = new ArrayList<String>();
		parseFile();
	}

	private void parseFile() throws FileNotFoundException{
		Scanner fileScanner = new Scanner(f);
		while(fileScanner.hasNextLine()){
			String input = fileScanner.nextLine();
			inputs.add(input);
		}
	}

	public float timeAt(int i){
		String input = inputs.get(i);
		stringScanner = new Scanner(input);
		String time = stringScanner.next();
		return Float.valueOf(time);
	}

	public char coinAt(int i){
		String input = inputs.get(i);
		stringScanner = new Scanner(input);
		String coin = " ";
		while(stringScanner.hasNext()){
			coin = stringScanner.next();
		}
		char c = coin.charAt(0);
		return c;
	}

	public int inputCount(){
		return inputs.size();
	}

}
import java.io.FileNotFoundException;

public class Scheduler{

	private VendingMachine vm;
	private InputModule inputModule;
	private PrintingModule pm;
	private int inputCount;
	
	public Scheduler(String fileName) throws FileNotFoundException{
		vm = new VendingMachine();
		inputModule = new InputModule(fileName);
		pm = new PrintingModule();
		inputCount = inputModule.inputCount();
	}

	public void scheduleAndVend(){
		float oldTime = 0;
		float newTime = 0;
		float elapsedTime = 0;
		int discreteTime = 1;
		char coin = ' ';
		for(int i = 0; i < inputCount; i++){
			newTime = inputModule.timeAt(i);
			elapsedTime = newTime - oldTime;
			coin = inputModule.coinAt(i);
			if(oldTime == newTime){
				discreteTime += 2;
			}else{
				discreteTime = 1;
			}
			if(elapsedTime < vm.timeAdvance()){
				vm.deltaExternal(coin);
				oldTime = newTime;
				String[] noOutput = {"-", "-"};
				pm.printOutput(vm.getQuarters(), vm.getDimes(), vm.getNickels(), vm.getValue(), newTime, discreteTime, coin, noOutput);
			}else if(elapsedTime > vm.timeAdvance()){
				String[] output = vm.lambda();
				oldTime += vm.timeAdvance();
				pm.printOutput(vm.getQuarters(), vm.getDimes(), vm.getNickels(), vm.getValue(), oldTime, 0, '-', output);
				vm.deltaInternal();
				i--;
			}else{
				String[] output = vm.lambda();
				vm.deltaConfluent(coin);
				oldTime = newTime; 
				discreteTime++;
				pm.printOutput(vm.getQuarters(), vm.getDimes(), vm.getNickels(), vm.getValue(), newTime, discreteTime, coin, output);
			}
			System.out.println();
		}
	}

}
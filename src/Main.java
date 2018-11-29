import TI.BoeBot;

public class Main {
	public static void main(String[] args) {
		Robot robot = new Robot();
		while(true) {
		 	robot.step();
			BoeBot.wait(1);
		}
		//System.out.println("Test");
	}

}

package hardware;

import TI.Servo;
import TI.Timer;

/**
 * @author Daan Pierik
 *
 * This class is the controller for a servo engine.
 *
 */

public class Engine implements Updatable {

	private MotorStateChange motorStateChange;
	private int snelheid;
	private int doelSnelheid;
	private Timer timer;
	private Servo s1;

	public Engine(int pin, MotorStateChange motorStateChange) {
		this.s1= new Servo(pin);
		this.snelheid = 0;
		this.doelSnelheid = 0;
		this.timer = new Timer(50);
		this.motorStateChange = motorStateChange;
	}

	public void update() {
		if(timer.timeout()) {
			if (this.snelheid != this.doelSnelheid) {
				if (this.snelheid > this.doelSnelheid) {
					this.snelheid -= 10;
					this.s1.update(snelheid+1500);
				} else {
					this.snelheid += 10;
					this.s1.update(snelheid+1500);
				}
				if(this.snelheid == this.doelSnelheid) {
					motorStateChange.onMotorAtSpeed(this.snelheid);
				}
				if(this.doelSnelheid == 0 && this.snelheid == 0) {
					motorStateChange.onMotorStopped();
				}
			}
		}
	}

	public void accelerate(){
		if(this.doelSnelheid != 150){
			this.doelSnelheid += 50;
		} else {
			System.out.println("You have reached the maximal speed of the R0b@rt!");
		}
	}

	public void slowdown(){
		if(this.doelSnelheid != 50){
			this.doelSnelheid -= 50;
		} else {
			System.out.println("You have reached the minimal speed of the R0b@rt!");
		}
	}

	public void forward(){
		//Setting the speed at 50.
		this.doelSnelheid = 50;
	}

	public void backwards(){
		// Setting the speed at -50.
		this.doelSnelheid = -50;
	}

	public void stop() {
		// First check if the speed is not 0, then changing it to 0.
		// Else display a console message.
		if(this.doelSnelheid != 0){
			this.doelSnelheid = 0;
		} else {
			System.out.println("You cant stop moving if you ain't moving!");
		}
	}

	public void emergencyStop(){
		// First check what the speed is, if it isnt 0 then brake instantly
		// Else display a console message.
		if(this.doelSnelheid != 0){
			this.snelheid = 0;
			this.doelSnelheid = 0;
			s1.update(1500);
		}
	}

}

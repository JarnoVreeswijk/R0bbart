package hardware;

import TI.Servo;
import TI.Timer;

/**
 * @author Daan Pierik
 *
 * This class is the controller for a servo engine.
 *
 */

// Class opening with implementation.
public class Engine implements Updatable {

	// Declaring the following variables.
	private MotorStateChange motorStateChange;
	private int currentSpeed;
	private int targetSpeed;
	private Timer timer;
	private Servo s1;

	public Engine(int pin, MotorStateChange motorStateChange) {
		// This gives an engine the following properties.
		this.s1= new Servo(pin);
		this.currentSpeed = 0;
		this.targetSpeed = 0;
		this.timer = new Timer(50);
		this.motorStateChange = motorStateChange;
	}

	public void update() {
		// Checking if the timer has ended.
		if(timer.timeout()) {
			// If current speed isn't equal to target speed then
			// change the current speed in the needed way.
			if (this.currentSpeed != this.targetSpeed) {
				if (this.currentSpeed > this.targetSpeed) {
					this.currentSpeed -= 5;
					this.s1.update(currentSpeed+1500);
				} else {
					this.currentSpeed += 5;
					this.s1.update(currentSpeed+1500);
				}
				// Checks the motorStateChange.
				if(this.currentSpeed == this.targetSpeed) {
					motorStateChange.onMotorAtSpeed(this.currentSpeed);
				}
				if(this.targetSpeed == 0 && this.currentSpeed == 0) {
					motorStateChange.onMotorStopped();
				}
			}
		}
	}

	public void accelerate(){
		// Checking if the speed inst already 50, if not then speed += 50.
		// Else display an error message in the console.
		if(this.targetSpeed != 50){
			this.targetSpeed += 50;
		} else {
			System.out.println("You have reached the maximal speed of the R0b@rt!");
		}
	}

	public void slowdown(){
		// Checking if the speed inst already -50, if not then speed -= 50.
		// Else display an error message in the console.
		if(this.targetSpeed != 50){
			this.targetSpeed -= 50;
		} else {
			System.out.println("You have reached the minimal speed of the R0b@rt!");
		}
	}

	public void forward(){
		//Setting the speed at 50.
		this.targetSpeed = 50;
	}

	public void backwards(){
		// Setting the speed at -50.
		this.targetSpeed = -50;
	}

	public void stop() {
		// First check if the speed is not 0, then changing it to 0.
		// Else display an error message in the console.
		if(this.targetSpeed != 0){
			this.targetSpeed = 0;
		} else {
			System.out.println("You cant stop moving if you ain't moving!");
		}
	}

	public void emergencyStop(){
		// First check what the speed is, if it isn't 0 then brake instantly
		// Else display an error message in the console.
		if(this.targetSpeed != 0){
			this.currentSpeed = 0;
			this.targetSpeed = 0;
			s1.update(1500);
		}
	}

}

package hardware;

/**
 * @author Daan Pierik
 *
 * This Interface make the conncection between DriveManager and Engine.
 *
 */

public interface MotorStateChange {
	void onMotorStopped();
	void onMotorAtSpeed(int speed);
}

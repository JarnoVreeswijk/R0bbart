package Interface;

import TI.BoeBot;
import hardware.Engine;
import hardware.MotorStateChange;
import hardware.Updatable;

import java.util.ArrayList;

/**
 * @author Daan Pierik
 * <p>
 * This class is the controller for the wheels on the BoeBot
 */

public class DriveManagement implements MotorStateChange {

    private Engine engineRight = new Engine(12, this);
    private Engine engineLeft = new Engine(13, this);
    private String movementState = "";
    private boolean isMoving = false;
    private ArrayList<Updatable> updatables = new ArrayList<>();
    private boolean cantDriveForward = false;
    private boolean hasStopped = false;

    public DriveManagement() {
        updatables.add(engineRight);
        updatables.add(engineLeft);
    }

    public void updateEngines() {
        for (Updatable updatable : updatables) {
            updatable.update();
        }
    }

    public void CollisionReceiver(boolean input) {
        this.cantDriveForward = input;
        if (cantDriveForward) {
            stopEngine();
        }
    }

    public void Receiver(String input, int checkedFront) {
        if (input.equals("back")) {
            System.out.println("[ Moving backwards ]");
            moveBackwards();
            this.hasStopped = false;
        } else {
            if (!cantDriveForward) {
                if (input.equals("forward")) {
                    System.out.println("[ Moving forward ]");
                    moveForward();
                } else if (input.equals("right")) {
                    System.out.println("[ Making a right turn ]");
                    moveRight();
                } else if (input.equals("left")) {
                    System.out.println("[ Making a left turn ]");
                    moveLeft();
                }
            }

            if (input.equals("faster")) {
                System.out.println("[ Gotta go fast! ]");
                speedUp(movementState);
            } else if (input.equals("slower")) {
                System.out.println("[ Ah, but i want to go as fast as i can. :frowning: ]");
                slowDown(movementState);
            } else if (input.equals("stop")) {
                System.out.println("[ Stop!?, i'm not an old lady ]");
                stopEngine();
            }
        }

    }

    public void stopEngine() {
        engineRight.setSpeedGoal(0);
        engineLeft.setSpeedGoal(0);
        engineRight.setServoSpeed(1500);
        engineLeft.setServoSpeed(1500);
        isMoving = false;
    }

    private void slowDown(String input) {
        if (input.equals("forward")) {
            engineRight.slowdown();
            engineLeft.accelerate();
        }
        if (input.equals("backwards")) {
            engineRight.accelerate();
            engineLeft.slowdown();
        }
    }

    private void speedUp(String input) {
        if (input.equals("forward")) {
            engineRight.accelerate();
            engineLeft.slowdown();
        }
        if (input.equals("backwards")) {
            engineRight.slowdown();
            engineLeft.accelerate();
        }
    }

    private void moveForward() {
        movementState = "forward";
        System.out.println(isMoving);
        if (isMoving) {
            engineRight.stop();
            engineLeft.stop();
            engineRight.backwards();
            engineLeft.forward();
        } else {
            engineRight.backwards();
            engineLeft.forward();
            isMoving = true;
        }
    }

    private void moveRight() {
        engineRight.stop();
        engineLeft.forward();
        isMoving = true;
    }

    private void moveLeft() {
        engineLeft.stop();
        engineRight.backwards();
        isMoving = true;
    }

    private void moveBackwards() {
        if (isMoving) {
            stopEngine();
            movementState = "backwards";
            engineLeft.backwards();
            engineRight.forward();

        } else {
            engineRight.forward();
            engineLeft.backwards();
            isMoving = true;
        }
    }

    @Override
    public void onMotorStopped() {
        System.out.println("Engine is gestopt");
    }

    @Override
    public void onMotorAtSpeed(int speed) {
        System.out.println("De motor is op doelsnelheid " + speed);
    }
}

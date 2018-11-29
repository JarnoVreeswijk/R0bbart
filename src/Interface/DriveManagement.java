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
        if (this.cantDriveForward) {
            stopEngine();
        }
    }

    public void Receiver(String input, int checkedFront) {
        if (input.equals("back")) {
            System.out.println("[ Moving backwards ]");
            moveBackwards();
            this.hasStopped = false;
        } else {
            if (!this.cantDriveForward) {
                if (input.equals("forward")) {
                    System.out.println("[ Moving forward ]");
                    moveForward();
                } else if (input.equals("right")) {
                    System.out.println("[ Making a right turn ]");
                    moveRight();
                } else if (input.equals("left")) {
                    System.out.println("[ Making a left turn ]");
                    moveLeft();
                } else if (input.equals("sharprigth")) {
                    System.out.println("[ Making a sharp right turn ]");
                    sharpRight();
                } else if (input.equals("sharpleft")) {
                    System.out.println("[ Making a sharp left turn ]");
                    sharpLeft();
                }
            }

            if (input.equals("faster")) {
                System.out.println("[ Gotta go fast! ]");
                speedUp(this.movementState);
            } else if (input.equals("slower")) {
                System.out.println("[ Ah, but i want to go as fast as i can. :frowning: ]");
                slowDown(this.movementState);
            } else if (input.equals("stop")) {
                System.out.println("[ Stop!?, i'm not an old lady ]");
                stopEngine();
            }
        }

    }

    public void stopEngine() {
        this.engineRight.setSpeedGoal(0);
        this.engineLeft.setSpeedGoal(0);
        this.engineRight.setServoSpeed(1500);
        this.engineLeft.setServoSpeed(1500);
        this.isMoving = false;
    }

    private void slowDown(String input) {
        if (input.equals("forward")) {
            this.engineRight.slowdown();
            this.engineLeft.accelerate();
        }
        if (input.equals("backwards")) {
            this.engineRight.accelerate();
            this.engineLeft.slowdown();
        }
    }

    private void speedUp(String input) {
        if (input.equals("forward")) {
            this.engineRight.accelerate();
            this.engineLeft.slowdown();
        }
        if (input.equals("backwards")) {
            this.engineRight.slowdown();
            this.engineLeft.accelerate();
        }
    }

    private void moveForward() {
        this.movementState = "forward";
        System.out.println(this.isMoving);
        if (this.isMoving) {
            this.engineRight.stop();
            this.engineLeft.stop();
            this.engineRight.backwards();
            this.engineLeft.forward();
        } else {
            this.engineRight.backwards();
            this.engineLeft.forward();
            this.isMoving = true;
        }
    }

    private void moveRight() {
        this.engineRight.stop();
        this.engineLeft.forward();
        this.isMoving = true;
    }

    private void sharpRight() {
        this.engineRight.forward();
        this.engineLeft.forward();
        this.isMoving = true;
    }

    private void moveLeft() {
        this.engineLeft.stop();
        this.engineRight.backwards();
        this.isMoving = true;
    }

    private void sharpLeft() {
        this.engineRight.backwards();
        this.engineLeft.backwards();
        this.isMoving = true;
    }

    private void moveBackwards() {
        if (this.isMoving) {
            stopEngine();
            this.movementState = "backwards";
            this.engineLeft.backwards();
            this.engineRight.forward();

        } else {
            this.engineRight.forward();
            this.engineLeft.backwards();
            this.isMoving = true;
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

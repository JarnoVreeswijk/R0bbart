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
    private int oneButtonpress = 0;

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
        if (cantDriveForward && oneButtonpress == 0) {
            stopDrivingInstant();
            oneButtonpress = 1;
        }
        if (!cantDriveForward) {
            oneButtonpress = 0;
        }
    }

    public void Receiver(String input) {
        if (!cantDriveForward) {
            switch (input) {
                case "forward":
                    System.out.println("[ Moving forward ]");
                    moveForward();
                case "right":
                    System.out.println("[ Making a right turn ]");
                    moveRight();
                case "left":
                    System.out.println("[ Making a left turn ]");
                    moveLeft();
            }
        }
        switch (input) {
            case "backwards":
                System.out.println("[ Moving backwards ]");
                moveBackwards();
            case "stop":
                System.out.println("[ Stop!?, i'm not an old lady ]");
                stopDriving();
            case "faster":
                System.out.println("[ Gotta go fast! ]");
                speedUp(movementState);
            case "slower":
                System.out.println("[ Ah, but i want to go as fast as i can. :frowning: ]");
                slowDown(movementState);
            case "intastStop":
                System.out.println("[ Stopped your ass instant! ]");
                stopDrivingInstant();
        }
    }


    private void stopDrivingInstant() {
        engineRight.emergencyStop();
        engineLeft.emergencyStop();
        isMoving = false;
    }

    public void stopDriving() {
        engineRight.stop();
        engineLeft.stop();
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
            engineRight.backwards();
            engineLeft.forward();
        } else {
            engineRight.backwards();
            engineLeft.forward();
            isMoving = true;
        }
    }

    private void moveRight() {
        if (isMoving) {
            engineRight.stop();
            engineLeft.forward();
        } else {
            engineLeft.forward();
            isMoving = true;
        }
    }

    private void moveLeft() {
        if (isMoving) {
            engineRight.stop();
            engineRight.backwards();
        } else {
            engineRight.backwards();
            isMoving = true;
        }
    }

    private void moveBackwards() {
        if (isMoving) {
            stopDriving();
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

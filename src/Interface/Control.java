package Interface;

import hardware.InfraRedSensor;

import java.util.HashMap;

public class Control {

    private int instantStop;
    private int forward;
    private int right;
    private int sharpright;
    private int back;
    private int sharpleft;
    private int left;
    private int stop;
    private int faster;
    private int slower;

    private InfraRedSensor checkConection;
    HashMap<Integer, String> movementToString = new HashMap<>();

    public Control(int forward, int right, int sharpright, int back, int sharpleft, int left, int stop, int faster, int slower, int instantStop) {
        movementToString.put(this.forward = forward, "forward");
        movementToString.put(this.right = right, "right");
        movementToString.put(this.sharpright = sharpright, "sharpright");
        movementToString.put(this.back = back, "back");
        movementToString.put(this.sharpleft = sharpleft, "sharpleft");
        movementToString.put(this.left = left, "left");
        movementToString.put(this.stop = stop, "stop");
        movementToString.put(this.faster = faster, "faster");
        movementToString.put(this.slower = slower, "slower");
        movementToString.put(this.instantStop = instantStop, "instantStop");
    }

    public String movementValuePusher() {
        if(movementToString.containsKey(checkConection.getValue())) {
            return movementToString.get(checkConection.getValue());
        } else {
            return "The infrared sensor returns a false value. ";
        }
    }
}

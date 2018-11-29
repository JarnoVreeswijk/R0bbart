package Interface;

import hardware.Remotecontrol;

import java.util.HashMap;

public class Control {

    private int forward;
    private int right;
    private int sharpright;
    private int back;
    private int sharpleft;
    private int left;
    private int stop;
    private int faster;
    private int slower;

    private Remotecontrol checkConection;
    HashMap<Integer, String> movementToString = new HashMap<>();

    public Control(int forward, int right, int sharpright, int back, int sharpleft, int left, int stop, int faster, int slower) {
        movementToString.put(this.forward = forward, "forward");
        movementToString.put(this.right = right, "right");
        movementToString.put(this.sharpright = sharpright, "sharpright");
        movementToString.put(this.back = back, "back");
        movementToString.put(this.sharpleft = sharpleft, "sharpleft");
        movementToString.put(this.left = left, "left");
        movementToString.put(this.stop = stop, "stop");
        movementToString.put(this.faster = faster, "faster");
        movementToString.put(this.slower = slower, "slower");
    }

    public String movementValuePusher() {
        if(movementToString.containsKey(checkConection.getValue())) {
            return movementToString.get(checkConection.getValue());
        } else {
            return "The infrared sensor returns a false value. ";
        }
    }
}

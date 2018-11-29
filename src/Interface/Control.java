package Interface;

import hardware.Remotecontrol;
import hardware.UpdatableControl;

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

    private UpdatableControl checkConection;

    public Control(int forward, int right, int sharpright, int back, int sharpleft, int left, int stop, int faster, int slower) {
        this.checkConection = new Remotecontrol(this.forward = forward, this.right = right, this.sharpright = sharpright, this.back = back, this.sharpleft = sharpleft, this.left = left, this.stop = stop, this.faster = faster, this.slower = slower);
    }

    public String movementValuePusher() {
       // System.out.println(checkConection.getValue());
        return checkConection.getValue();
    }
}

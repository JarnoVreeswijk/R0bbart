package hardware;

import TI.BoeBot;

import java.util.ArrayList;
import java.util.HashMap;

public class Remotecontrol implements UpdatableControl {
    private int forward;
    private int right;
    private int sharpright;
    private int back;
    private int sharpleft;
    private int left;
    private int stop;
    private int faster;
    private int slower;
    HashMap<Integer, String> movementToString = new HashMap<>();

    private int lengthes[] = new int[12];
    private int pulseLen = BoeBot.pulseIn(3, false, 6000);

    public Remotecontrol(int forward, int right, int sharpright, int back, int sharpleft, int left, int stop, int faster, int slower) {
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

    public int toNumber(ArrayList<Integer> list) {
        ArrayList<Integer> list12 = new ArrayList<>();
        if (list.size() >= 12) {
            for (int i = 0; i < 12; i++)
                list12.add(list.get(i));
        } else if (list12.size() != 12) {
            return 0;
        }
        int output = list12.get(6);
        for (int i = 0; i < 6; i++) {
            if (list12.get(i) == 1) {
                output += Math.pow(2, i);
            }
        }
        output += 1;
        return output;
    }

    @Override
    public String getValue() {
        ArrayList<Integer> binaryCodeButton = new ArrayList<>();
        for (int k = 0; k < 12; k++) {
            int pulseLen = BoeBot.pulseIn(3, false, 6000);
            if (pulseLen > 2000) {
                int lenghtes[] = new int[12];
                for (int i = 0; i < 12; i++) {
                    lenghtes[i] = BoeBot.pulseIn(3, false, 20000);
                }
                for (int i = 0; i < 12; i++) {
                    if (lenghtes[i] > 1000) {
                        binaryCodeButton.add(1);
                    } else {
                        if (lenghtes[i] > 100) {
                            binaryCodeButton.add(0);
                        }
                    }
                }
            }
        }
        if (toNumber(binaryCodeButton) == 0) {
            return "";
        } else {
            //System.out.println(this.movementToString.get(toNumber(binaryCodeButton)));
            return this.movementToString.get(toNumber(binaryCodeButton));
        }
    }


    //------------------------------------------------------------------------------------------


    public String getValueWrong() {
        ArrayList<Integer> binaryCodeButton = new ArrayList<>();
        if (this.pulseLen > 2000) {
            for (int i = 0; i < 12; i++) {
                System.out.println(this.lengthes[i] = BoeBot.pulseIn(3, false, 20000));
            }
            for (int i = 0; i < 12; i++) {
                if (this.lengthes[i] > 1000) {
                    binaryCodeButton.add(1);
                } else {
                    if (this.lengthes[i] > 100) {
                        binaryCodeButton.add(0);
                    }
                }
            }
        }
        if (this.movementToString.containsKey(toNumber(binaryCodeButton))) {
            System.out.println(this.movementToString.get(toNumber(binaryCodeButton)));
            return this.movementToString.get(toNumber(binaryCodeButton));
        }
        binaryCodeButton.clear();
        return "Null";
    }
}
package hardware;

import TI.BoeBot;

import java.util.ArrayList;
import java.util.HashMap;

public class Remotecontrol {

    public Remotecontrol() {

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

    public int getValue() {
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
            return 0;
        } else {
            //System.out.println(this.movementToString.get(toNumber(binaryCodeButton)));
            return toNumber(binaryCodeButton);
        }
    }

}
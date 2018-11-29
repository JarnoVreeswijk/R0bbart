package hardware;

import TI.BoeBot;
import TI.Timer;
import hardware.Updatable;

import java.sql.Time;

public class Ultrasone {

    private int readPin;
    private int writePin;
    private int pulse;

    public Ultrasone(int writePin, int readPin) {
        this.readPin = readPin;
        this.writePin = writePin;
    }


    public int updateFront() {
        BoeBot.digitalWrite(this.writePin, true);
        BoeBot.wait(0,500);
        BoeBot.digitalWrite(this.writePin, false);
        System.out.println(BoeBot.pulseIn(this.readPin, true, 10000));
        this.pulse = BoeBot.pulseIn(this.readPin, true, 10000);
        if(this.pulse < 0) {
            System.out.println(" The ultrasone sensor is not working. ");
        }
        return this.pulse;
    }

}
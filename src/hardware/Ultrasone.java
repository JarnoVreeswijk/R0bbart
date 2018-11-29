package hardware;

import TI.BoeBot;
import TI.Timer;
import hardware.Updatable;

import java.sql.Time;

public class Ultrasone implements Updatable {

    private Timer timer;
    private Timer pulseOff;
    private UltrasoneDetectie onDetect;
    private int readPin;
    private int writePin;
    private boolean pulseTimerOn;

    public Ultrasone(int writePin, int readPin) {
        this.timer = new Timer(100);
        this.pulseOff = new Timer(1);
        this.onDetect = onDetect;
        this.readPin = readPin;
        this.writePin = writePin;
        this.pulseTimerOn = false;
    }


    public int updateFront() {
        BoeBot.digitalWrite(this.writePin, true);
        BoeBot.wait(0,500);
        BoeBot.digitalWrite(this.writePin, false);
        //System.out.println(BoeBot.pulseIn(this.readPin, true, 10000));
        return BoeBot.pulseIn(this.readPin, true, 10000);
    }

    public void update() {}
}
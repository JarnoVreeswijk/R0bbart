import Interface.*;

public class Robot {
    DriveManagement DM;
    Control RC;
    CollisionDetection US;

    public Robot() {
        this.DM = new DriveManagement();
        this.RC = new Control(2, 3, 6, 8, 4, 1, 5, 17, 14);
        this.US = new CollisionDetection(1, 2);
    }

    public void step() {
        //if (US.checkFront() <= 500) {
        //    DM.CollisionReceiver(true);
            //System.out.println("[ Toet Toet! Get out of the way! ]");
        //} else {
         //   DM.CollisionReceiver(false);
        //}

        DM.updateEngines();
        if (!(RC.movementValuePusher().equals(""))) {
            DM.Receiver(RC.movementValuePusher());
        }
        if (US.checkFront() > 0) {
            DM.
        }
    }
}

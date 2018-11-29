package Interface;

        import hardware.Ultrasone;

public class CollisionDetection {

    private Ultrasone frotnDetection;

    public CollisionDetection(int write, int read) {
        this.frotnDetection = new Ultrasone(write, read);
    }

    public int checkFront() {
        return frotnDetection.updateFront();
    }

}

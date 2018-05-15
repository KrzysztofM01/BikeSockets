import java.io.IOException;
import java.io.ObjectInputStream;

public class Thread22 extends Thread {

    ObjectInputStream ois;
    Bike enemyBike;

    public Thread22(ObjectInputStream ois, Bike enemyBike) {
        this.ois = ois;
        this.enemyBike = enemyBike;
    }

    @Override
    public void run() {
        while (true) {
            this.turnLogic();
        }
    }

    private void turnLogic(){


    }
}


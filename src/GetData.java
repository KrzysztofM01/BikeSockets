import java.io.IOException;
import java.io.ObjectInputStream;

public class GetData implements Runnable {

    public ObjectInputStream ois;

    public GetData(ObjectInputStream ois) {
        this.ois = ois;
    }

    @Override
    public void run() {
        while (true){
            try {
                Object bikeObj = ois.readObject();
                Bike enemyBike = (Bike) bikeObj;
                System.out.println("Dostalem bike");
                System.out.println(enemyBike.toString());

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

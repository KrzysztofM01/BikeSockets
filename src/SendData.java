import java.io.IOException;
import java.io.ObjectOutputStream;

public class SendData implements Runnable {

    public ObjectOutputStream oos;

    public SendData(ObjectOutputStream oos) {
        this.oos = oos;
    }

    @Override
    public void run() {
        while (true){
            try {
                System.out.println("Czekam az wyslesz dane");
                Bike bike = Bike.createNewBike();
                oos.writeObject(bike);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

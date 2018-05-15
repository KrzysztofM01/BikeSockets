import java.io.Serializable;
import java.util.Scanner;

public class Bike implements Serializable{
    private int posX = 0;
    private int posY = 0;
    private int playerID;
    private MovementType movementType;

    public Bike() {

    }

    public Bike(int playerID){
        this.playerID = playerID;
    }

    public void moveBike(MovementType movementType, int amount){
        switch (movementType){
            case left:
                this.posX -= amount;
                break;
            case right:
                this.posX += amount;
                break;
            case up:
                this.posY += amount;
                break;
            case down:
                this.posY -= amount;
                break;
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPlayerID() {
        return playerID;
    }

    public static void whereYouWantToMove(){

    }

    public static Bike createNewBike() {
        Scanner scanner = new Scanner(System.in);
        Bike bike = new Bike();
        System.out.println("dawaj pozycje X:");
        bike.setPosX(scanner.nextInt());
        System.out.println("dawaj pozycje Y:");
        bike.setPosY(scanner.nextInt());
        bike.movementType = MovementType.down;
        return bike;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", playerID=" + playerID +
                ", movementType=" + movementType +
                '}';
    }
}

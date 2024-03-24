package zoo.entities.foods;

public class Vegetable extends BaseFood{
    private static final int VEGETABLES_CALORIES = 50;
    private static final double VEGETABLES_PRICE =5;
    public Vegetable() {
        super(VEGETABLES_CALORIES, VEGETABLES_PRICE);
    }
}

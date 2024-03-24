package zoo.entities.foods;

public class Meat extends BaseFood {

    private static final int CALORIES_MEAT = 70;
    private static final double PRICE_MEAT = 10;
    public Meat() {
        super(CALORIES_MEAT, PRICE_MEAT);
    }
}

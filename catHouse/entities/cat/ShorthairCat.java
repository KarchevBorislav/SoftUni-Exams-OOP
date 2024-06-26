package catHouse.entities.cat;

public class ShorthairCat extends BaseCat{
    private static final int SHORT_HAIR_CAT_INITIAL_KILOGRAMS = 7;
    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public void eating() {
        super.setKilograms(SHORT_HAIR_CAT_INITIAL_KILOGRAMS + 1);
    }
}

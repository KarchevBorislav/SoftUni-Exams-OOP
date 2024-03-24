package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal{
    private static final double TERRESTRIAL_ANIMAL_INITIAL_KG = 5.50;
    private static final double  TERRESTRIAL_ANIMAL_INCREASE_KG = 5.7;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, TERRESTRIAL_ANIMAL_INITIAL_KG, price);
    }

    @Override
    public void eat() {
        setKg(TERRESTRIAL_ANIMAL_INITIAL_KG + TERRESTRIAL_ANIMAL_INCREASE_KG);

    }
}

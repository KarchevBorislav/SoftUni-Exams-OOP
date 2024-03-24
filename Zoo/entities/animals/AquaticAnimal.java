package zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal {
    private static final double AQUATIC_ANIMAL_INITIAL_KG = 2.50;
    private static final double AQUATIC_ANIMAL_INCREASE_KG = 7.5;

    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, AQUATIC_ANIMAL_INITIAL_KG, price);
    }

    @Override
    public void eat() {
        super.setKg(AQUATIC_ANIMAL_INITIAL_KG + AQUATIC_ANIMAL_INCREASE_KG);


    }
}

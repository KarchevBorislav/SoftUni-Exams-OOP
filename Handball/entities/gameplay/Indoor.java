package handball.entities.gameplay;

public class Indoor extends BaseGameplay{
    private static int INDOOR_CAPACITY = 250;
    public Indoor(String name) {
        super(name, INDOOR_CAPACITY);
    }
}

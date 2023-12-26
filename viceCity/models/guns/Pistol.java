package viceCity.models.guns;

public class Pistol extends BaseGun {
    private static final int TOTAL_PISTOL_BULLETS = 100;
    private static final int BULLETS_PER_BARREL = 10;
    private static final int PISTOL_FIRE_RATE = 1;

    private String name;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_PISTOL_BULLETS);
        setName(name);


    }

    private void setName(String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }


    @Override
    public int fire() {
        if (getBulletsPerBarrel() == 0 && getTotalBullets() > 0) {
            reload();
        }

            setBulletsPerBarrel(getBulletsPerBarrel() - PISTOL_FIRE_RATE);



        return PISTOL_FIRE_RATE;
    }

    private void reload() {
        setTotalBullets(getTotalBullets() - BULLETS_PER_BARREL);
        setBulletsPerBarrel(BULLETS_PER_BARREL);
    }


}

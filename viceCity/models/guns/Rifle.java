package viceCity.models.guns;

public class Rifle extends BaseGun {
    private static final int RIFLE_BULLETS_MAGAZINE_CAPACITY = 50;
    private static final int RIFLE_TOTAL_BULLETS_COUNT = 500;
    private static final int RIFLE_SHOOTING_RATE = 5;
    String name;

    public Rifle(String name) {
        super(name, RIFLE_BULLETS_MAGAZINE_CAPACITY, RIFLE_TOTAL_BULLETS_COUNT);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int fire() {
        if (getBulletsPerBarrel() == 0 && getTotalBullets() > 0){
            reloadRifle();
    }

            setBulletsPerBarrel(getTotalBullets() -RIFLE_SHOOTING_RATE);

        
        return RIFLE_SHOOTING_RATE;
    }

    private void reloadRifle() {
        setTotalBullets(getTotalBullets() - RIFLE_BULLETS_MAGAZINE_CAPACITY);
        setBulletsPerBarrel(RIFLE_BULLETS_MAGAZINE_CAPACITY);
    }



}
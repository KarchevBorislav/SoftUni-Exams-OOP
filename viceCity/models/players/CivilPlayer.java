package viceCity.models.players;

public class CivilPlayer extends BasePlayer {
    private static final int CIVIL_PLAYER_START_LIFE_POINTS = 50;
    private String name;

    public CivilPlayer(String name) {
        super(name, CIVIL_PLAYER_START_LIFE_POINTS);
    }



}

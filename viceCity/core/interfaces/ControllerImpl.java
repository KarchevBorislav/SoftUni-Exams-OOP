package viceCity.core.interfaces;

import viceCity.common.ConstantMessages;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;

import java.util.*;
import java.util.stream.Collectors;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    private static int MAIN_PLAYER_MAX_LIFE_POINTS = 100;
    private static int CIVIL_PLAYER_MAX_LIFE_POINTS = 50;
    private static String MAIN_PLAYER_NAME = "Tommy Vercetti";

    private Player mainPlayer;
    private Map<String, Player> civilPlayers;
    private Deque<Gun> guns;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        mainPlayer = new MainPlayer();
        civilPlayers = new LinkedHashMap<>();
        guns = new ArrayDeque<>();
        neighbourhood = new GangNeighbourhood();

    }


    @Override
    public String addPlayer(String name) {
        Player civilPlayer = new CivilPlayer(name);


        civilPlayers.putIfAbsent(name, civilPlayer);
        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        switch (type) {
            case "Rifle":

                gun = new Rifle(name);
                break;
            case "Pistol":
                gun = new Pistol(name);
                break;
            default:
                return GUN_TYPE_INVALID;


        }
        guns.offer(gun);

        return String.format(ConstantMessages.GUN_ADDED, name, type);


    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gunToAdd = guns.poll();
        if (gunToAdd == null) {
            return GUN_QUEUE_IS_EMPTY;
        }

       else if (name.equals("Vercetti")) {
            mainPlayer.getGunRepository().add(gunToAdd);
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gunToAdd.getName(), MAIN_PLAYER_NAME);
        }
        Player civilPlayer = civilPlayers.get(name);
        if (civilPlayer == null ) {
            guns.offer(gunToAdd);
            return CIVIL_PLAYER_DOES_NOT_EXIST;
        }
            civilPlayers.get(name).getGunRepository().add(gunToAdd);
            return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gunToAdd.getName(), name);



    }


    @Override
    public String fight() {

        neighbourhood.action(mainPlayer, civilPlayers.values());
        if (mainPlayer.getLifePoints() == MAIN_PLAYER_MAX_LIFE_POINTS && civilPlayers.values().
                stream().allMatch(player -> player.getLifePoints() == 50)) {
            return FIGHT_HOT_HAPPENED;

        }
        List<Player> deadPlayers = civilPlayers.values().stream().filter(player ->
                        !player.isAlive())
                .collect(Collectors.toList());

        StringBuilder builder = new StringBuilder(FIGHT_HAPPENED)
                .append(System.lineSeparator())
                .append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()))
                .append(System.lineSeparator())
                .append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadPlayers.size()))
                .append(System.lineSeparator())
                .append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, civilPlayers.size() - deadPlayers.size()));

        deadPlayers.forEach(deadPlayer -> civilPlayers.remove(deadPlayer.getName()));


        return builder.toString();


    }

}
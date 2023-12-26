package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        Repository<Gun> mainPlayerGun = mainPlayer.getGunRepository();
        Deque<Gun> mainPlayerWeapons = new ArrayDeque<>(mainPlayerGun.getModels());
        Deque<Player> players = new ArrayDeque<>(civilPlayers);

        Player player = players.poll();
        Gun gun = mainPlayerWeapons.poll();
        while (player != null && gun != null) {

            while (gun.canFire() && player.isAlive()) {
                int fire = gun. fire();
                player.takeLifePoints(fire);

            }
            if (gun.canFire()) {
                player = players.poll();

            } else {
                gun = mainPlayerWeapons.poll();

            }
        }
        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.isAlive()) {

                Deque<Gun> civilPlayerWeapons = new ArrayDeque<>(civilPlayer.getGunRepository().getModels());
                Gun civilPlayerGun = civilPlayerWeapons.poll();

                while (civilPlayerGun != null) {
                    while (civilPlayerGun.canFire() && mainPlayer.isAlive()) {
                        int fire = civilPlayerGun.fire();
                        mainPlayer.takeLifePoints(fire);
                    }
                    if (!mainPlayer.isAlive()) {
                        return;
                    }

                    civilPlayerGun = civilPlayerWeapons.poll();

                }

            }

        }


    }

}


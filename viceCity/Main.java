package viceCity;

import viceCity.core.EngineImpl;
import viceCity.core.interfaces.Controller;
import viceCity.core.interfaces.ControllerImpl;
import viceCity.core.interfaces.Engine;
import viceCity.models.players.BasePlayer;
import viceCity.models.players.MainPlayer;

public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();


    }
}

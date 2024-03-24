package zoo;

import zoo.core.Engine;


public class Main {

    public static void main(String[] args) {
        Engine engine = new core.EngineImpl();
        engine.run();
    }
}

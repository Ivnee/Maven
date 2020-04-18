package server;

import java.util.logging.*;

public interface MainLogger {
    static final Logger logger = Logger.getLogger(MainLogger.class.getName());

    public static void main(String[] args) {
        Handler handler = new ConsoleHandler();
        logger.addHandler(handler);
        logger.setLevel(Level.INFO);
        handler.setLevel(Level.INFO);
    }
}

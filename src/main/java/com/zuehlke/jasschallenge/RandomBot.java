package com.zuehlke.jasschallenge;

import com.zuehlke.jasschallenge.client.RemoteGame;
import com.zuehlke.jasschallenge.client.game.Player;
import com.zuehlke.jasschallenge.client.game.strategy.RandomJassStrategy;
import com.zuehlke.jasschallenge.messages.type.SessionType;

import java.util.Arrays;

/**
 * Starts one bot in tournament mode. Add your own strategy to compete in the Jass Challenge Tournament 2017!
 * <br><br>
 * To start from CLI use
 * <pre>
 *     gradlew run [websocketUrl]
 * </pre>
 */
public class RandomBot {
    private static final String BOT_NAME = "random_bot";
    private static final RandomJassStrategy STRATEGY = new RandomJassStrategy();
    private static final String SERVER_URL = "ws://127.0.0.1:3000";

    public static void main(String[] args) throws Exception {
        String websocketUrl = parseWebsocketUrlOrDefault(args);

        Player randomPlayer = new Player(BOT_NAME, STRATEGY);

        System.out.println("Connecting... Server socket URL: " + websocketUrl);
        startGame(websocketUrl, randomPlayer, SessionType.SINGLE_GAME);
    }


    private static String parseWebsocketUrlOrDefault(String[] args) {
        if (args.length > 0) {
            System.out.println("Arguments: " + Arrays.toString(args));
            return args[0];
        }
        return SERVER_URL;
    }

    private static void startGame(String targetUrl, Player myLocalPlayer, SessionType sessionType) throws Exception {
        RemoteGame remoteGame = new RemoteGame(targetUrl, myLocalPlayer, sessionType, "tournament");
        remoteGame.start();
    }
}

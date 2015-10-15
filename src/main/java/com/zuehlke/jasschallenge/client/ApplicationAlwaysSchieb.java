package com.zuehlke.jasschallenge.client;

import com.zuehlke.jasschallenge.client.game.Player;
import com.zuehlke.jasschallenge.client.game.strategy.AlwaysShiftRandomJassStrategy;
import com.zuehlke.jasschallenge.client.game.strategy.RandomJassStrategy;
import com.zuehlke.jasschallenge.client.websocket.messages.type.SessionType;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ApplicationAlwaysSchieb {
    public static void main(String[] args) throws Exception {
        final String name = "phiber";
        final Player myLocalPlayer = new Player(name+"1", new AlwaysShiftRandomJassStrategy());
        final Player myLocalPartner = new Player(name+"2", new RandomJassStrategy());

        startTournamentGame("ws://localhost:3000", myLocalPlayer, myLocalPartner);
//        startGame("ws://gamenighthacked.herokuapp.com", myLocalPlayer, SessionType.SINGLE_GAME);
    }

    private static void startTournamentGame(String targetUrl, Player myLocalPlayer, Player myLocalPartner) throws Exception {
        Executors.newFixedThreadPool(2)
                .invokeAll(Arrays.asList(() -> startGame(targetUrl, myLocalPlayer, SessionType.TOURNAMENT),
                                         () -> startGame(targetUrl, myLocalPartner, SessionType.TOURNAMENT)))
                .stream()
                .forEach(f -> {
                    try {
                        f.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static RemoteGame startGame(String targetUrl, Player myLocalPlayer, SessionType sessionType) throws Exception {

        final RemoteGame remoteGame = new RemoteGame(targetUrl, myLocalPlayer, sessionType);
        remoteGame.start();
        return remoteGame;
    }
}
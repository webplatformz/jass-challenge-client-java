package com.zuehlke.jasschallenge.client.game;

import com.zuehlke.jasschallenge.game.cards.Card;

import java.util.List;
import java.util.stream.Collectors;

public final class GameSessions {

    public static List<Card> getCardsPlayedInCurrentRound(GameSession session) {
        return session.getCurrentRound().getMoves().stream()
                .map(Move::getPlayedCard)
                .collect(Collectors.toList());
    }
}

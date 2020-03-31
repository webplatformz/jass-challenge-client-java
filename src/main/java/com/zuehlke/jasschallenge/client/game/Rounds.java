package com.zuehlke.jasschallenge.client.game;

import com.zuehlke.jasschallenge.game.cards.Card;
import com.zuehlke.jasschallenge.game.cards.Color;

import java.util.List;

public final class Rounds {
    public static boolean isFirstCardOfRound(List<Card> playedCards) {
        return playedCards.size() < 1;
    }

    public static Color getColorOfRound(List<Card> playedCards) {
        Card firstCardOfRound = playedCards.get(0);
        return firstCardOfRound.getColor();
    }

    public static boolean isNewRound(GameSession session, int currentRoundNumber) {
        return session.getCurrentRound().getRoundNumber() != currentRoundNumber;
    }
}

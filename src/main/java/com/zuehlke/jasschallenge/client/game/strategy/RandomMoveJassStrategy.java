package com.zuehlke.jasschallenge.client.game.strategy;

import com.zuehlke.jasschallenge.client.game.Game;
import com.zuehlke.jasschallenge.client.game.GameSession;
import com.zuehlke.jasschallenge.client.game.Round;
import com.zuehlke.jasschallenge.client.game.cards.Card;
import com.zuehlke.jasschallenge.client.game.mode.Mode;

import java.util.Set;

public class RandomMoveJassStrategy implements JassStrategy {
    @Override
    public Mode chooseTrumpf(Set<Card> availableCards, GameSession session) {
        return Mode.bottomUp();
    }

    @Override
    public Card chooseCard(Set<Card> availableCards, GameSession session) {
        final Game currentGame = session.getCurrentGame();
        final Round round = currentGame.getCurrentRound();
        final Mode gameMode = round.getMode();

        return availableCards.stream()
                    .filter(card -> gameMode.canPlayCard(card, round.getPlayedCards(), round.getRoundColor(), availableCards))
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("There should always be a card to play"));
    }
}

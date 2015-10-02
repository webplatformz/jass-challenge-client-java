package com.zuehlke.jasschallenge.client.game.mode;

import com.zuehlke.jasschallenge.client.game.Game;
import com.zuehlke.jasschallenge.client.game.Move;
import com.zuehlke.jasschallenge.client.game.Player;
import com.zuehlke.jasschallenge.client.game.cards.Card;
import com.zuehlke.jasschallenge.client.game.cards.Color;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static java.lang.String.valueOf;

class BottomUpMode implements Mode{
    private static final int FACTOR = 3;
    private final GeneralRules generalRules = new GeneralRules();

    @Override
    public TrumpfName getTrumpfName() {
        return TrumpfName.UNDEUFE;
    }

    @Override
    public Color getTrumpfColor() {
        return null;
    }

    @Override
    public int calculateRoundScore(int roundNumber, Set<Card> playedCards) {
        if(roundNumber == Game.LAST_ROUND_NUMBER) {
            return generalRules.calculateLastRoundBonus(FACTOR) + calculateScore(playedCards);
        }
        return calculateScore(playedCards);
    }

    @Override
    public int calculateScore(Set<Card> playedCards) {
        return FACTOR * playedCards.stream()
                .mapToInt(card -> card.getValue().getBottomUpScore())
                .sum();
    }

    @Override
    public Player determineWinner(List<Move> moves) {
        final Comparator<Move> moveComparator = (move, move2) -> !move.getPlayedCard().isHigherThan(move2.getPlayedCard()) ? 1 : -1;

        return generalRules.determineWinner(moves, moveComparator);
    }

    @Override
    public boolean canPlayCard(Card card, Set<Card> alreadyPlayedCards, Color currentRoundColor, Set<Card> playerCards) {
        return generalRules.canPlayCard(card, alreadyPlayedCards, currentRoundColor, playerCards);
    }

    @Override
    public String toString() {
        return valueOf(getTrumpfName());
    }
}
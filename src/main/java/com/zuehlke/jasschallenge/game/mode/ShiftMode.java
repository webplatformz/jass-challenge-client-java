package com.zuehlke.jasschallenge.game.mode;

import com.zuehlke.jasschallenge.client.game.Move;
import com.zuehlke.jasschallenge.game.cards.Card;
import com.zuehlke.jasschallenge.game.cards.Color;
import com.zuehlke.jasschallenge.game.Trumpf;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ShiftMode extends Mode {

    @Override
    public int calculateRoundScore(int roundNumber, Set<Card> playedCards) {
        return 0;
    }

    @Override
    public Trumpf getTrumpfName() {
        return Trumpf.SCHIEBE;
    }

    @Override
    public Color getTrumpfColor() {
        return null;
    }

    @Override
    public int calculateScore(Set<Card> playedCards) {
        return 0;
    }

    @Override
    public Move determineWinningMove(List<Move> moves) {
        return null;
    }

    @Override
    public boolean canPlayCard(Card card, Set<Card> alreadyPlayedCards, Color currentRoundColor, Set<Card> playerCards) {
        return false;
    }

    @Override
    public int getFactor() {
        return 0;
    }

    @Override
    public Comparator<Card> createRankComparator() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShiftMode that = (ShiftMode) o;

        return getTrumpfName().equals(that.getTrumpfName());
    }

    @Override
    public int hashCode() {
        return getTrumpfName().hashCode();
    }
}

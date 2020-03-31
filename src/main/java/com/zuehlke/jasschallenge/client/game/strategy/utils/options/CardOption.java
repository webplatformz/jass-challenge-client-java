package com.zuehlke.jasschallenge.client.game.strategy.utils.options;

import com.zuehlke.jasschallenge.client.game.Rounds;
import com.zuehlke.jasschallenge.game.cards.Card;
import com.zuehlke.jasschallenge.game.cards.Color;
import com.zuehlke.jasschallenge.game.mode.Mode;
import com.zuehlke.jasschallenge.game.mode.Modes;

import java.util.List;
import java.util.stream.Collectors;

public class CardOption {
    protected Card card;

    public CardOption(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public int getRank() {
        return card.getValue().getRank();
    }

    public int getTrumpfRank() {
        return card.getValue().getTrumpfRank();
    }

    public boolean canBeAppliedTo(Mode mode, List<Card> playedCards) {
        if (Rounds.isFirstCardOfRound(playedCards)) {
            return true;
        } else {
            Color colorOfRound = Rounds.getColorOfRound(playedCards);
            Color colorOfCard = card.getColor();
            Color colorOfTrumpf = mode.getTrumpfColor();
            return colorOfCard == colorOfRound || colorOfCard == colorOfTrumpf;
        }
    }

    public boolean canWinRound(Mode mode, List<Card> playedCards) {
        if (Rounds.isFirstCardOfRound(playedCards)) {
            return true;
        } else if (!Rounds.getColorOfRound(playedCards).equals(card.getColor())
                && !card.getColor().equals(mode.getTrumpfColor())) {
            return false;
        } else {
            if (Modes.isModeObeabe(mode)) {
                return playedCards.stream()
                        .filter(card -> card.isHigherThan(this.card))
                        .count() < 1;
            } else if (Modes.isModeUndeufe(mode)) {
                return playedCards.stream()
                        .filter(card -> !card.isHigherThan(this.card))
                        .count() < 1;
            } else { // mode is trumpf
                final List<Card> playedTrumpfs = playedCards.stream()
                        .filter(card -> card.getColor().equals(mode.getTrumpfColor()))
                        .collect(Collectors.toList());
                if (playedTrumpfs.size() > 0) {
                    return playedTrumpfs.stream()
                            .filter(card -> card.isHigherTrumpfThan(this.card))
                            .count() < 1;
                } else {
                    return playedCards.stream()
                            .filter(card -> card.isHigherThan(this.card))
                            .count() < 1
                            || this.card.getColor().equals(mode.getTrumpfColor());
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardOption that = (CardOption) o;

        return card == that.card;
    }

    @Override
    public int hashCode() {
        return card.hashCode();
    }
}

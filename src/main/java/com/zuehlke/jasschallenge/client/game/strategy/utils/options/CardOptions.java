package com.zuehlke.jasschallenge.client.game.strategy.utils.options;

import com.zuehlke.jasschallenge.game.cards.Card;
import com.zuehlke.jasschallenge.game.mode.Mode;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class CardOptions {

    public static List<CardOption> from(List<Card> availableCards) {
        return availableCards.stream()
                .map(CardOption::new)
                .collect(Collectors.toList());
    }

    public static List<CardOption> from(Set<Card> availableCards) {
        return availableCards.stream()
                .map(CardOption::new)
                .collect(Collectors.toList());
    }

    public static List<CardOption> canWinRound(List<CardOption> options, Mode mode, List<Card> playedCards) {
        return filterCardOptions(options, option -> option.canWinRound(mode, playedCards));
    }

    public static List<CardOption> canBeAppliedTo(List<CardOption> options, Mode mode, List<Card> playedCards) {
        return filterCardOptions(options, option -> option.canBeAppliedTo(mode, playedCards));
    }

    public static List<CardOption> filterCardOptions(List<CardOption> options, Predicate<CardOption> cardOptionPredicate) {
        return options.stream()
                .filter(cardOptionPredicate)
                .collect(Collectors.toList());
    }

    public static CardOption getMaxOption(List<CardOption> cardOptions, Comparator<CardOption> comparing) {
        if (cardOptions == null) {
            return null;
        }
        if (cardOptions.size() == 1) {
            return cardOptions.get(0);
        }
        return cardOptions.stream()
                .max(comparing)
                .get();
    }

    public static CardOption getMinOption(List<CardOption> cardOptions, Comparator<CardOption> comparing) {
        if (cardOptions == null) {
            return null;
        }
        if (cardOptions.size() == 1) {
            return cardOptions.get(0);
        }
        return cardOptions.stream()
                .min(comparing)
                .get();
    }
}

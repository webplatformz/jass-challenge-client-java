package com.zuehlke.jasschallenge.client.game.strategy.utils.rules;

import com.zuehlke.jasschallenge.client.game.GameSessions;
import com.zuehlke.jasschallenge.client.game.strategy.utils.options.CardOption;
import com.zuehlke.jasschallenge.client.game.strategy.utils.options.CardOptions;
import com.zuehlke.jasschallenge.game.Trumpf;
import com.zuehlke.jasschallenge.game.cards.Card;
import com.zuehlke.jasschallenge.game.cards.Color;
import com.zuehlke.jasschallenge.game.mode.Mode;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChooseCardRules {

    /**
     * This rule returns the strongest card that can win the current round. If it
     */
    public static ChooseCardRule CHOOSE_STRONGEST_CARD_IF_ROUND_CAN_BE_WON = (inputOptions, gameSession, allMoves) -> {
        if (inputOptions.size() <= 1) {
            return inputOptions;
        }
        Mode mode = gameSession.getCurrentRound().getMode();
        List<Card> playedCards = GameSessions.getCardsPlayedInCurrentRound(gameSession);
        List<CardOption> canWinRound = CardOptions.canWinRound(inputOptions, mode, playedCards);
        if (canWinRound.size() <= 1) {
            return inputOptions;
        }
        boolean trumpfPlayed = playedCards.stream().anyMatch(card -> card.getColor().equals(mode.getTrumpfColor()));
        List<CardOption> trumpfOptions = canWinRound.stream()
                .filter(option -> option.getCard().getColor().equals(mode.getTrumpfColor()))
                .collect(Collectors.toList());
        if (trumpfPlayed) {
            return Collections.singletonList(
                    CardOptions.getMaxOption(inputOptions, Comparator.comparing(CardOption::getTrumpfRank))
            );
        } else if (trumpfOptions.size() > 0) {
            return Collections.singletonList(
                    CardOptions.getMaxOption(trumpfOptions, Comparator.comparing(CardOption::getTrumpfRank))
            );
        } else {
            return Collections.singletonList(
                    CardOptions.getMaxOption(inputOptions, Comparator.comparing(CardOption::getRank))
            );
        }
    };
    public static ChooseCardRule CHOOSE_WEAKEST_CARD_THAT_CAN_BE_APPLIED_TO_ROUND = (inputOptions, gameSession, allMoves) -> {
        if (inputOptions.size() <= 1) {
            return inputOptions;
        }
        Mode mode = gameSession.getCurrentRound().getMode();
        List<Card> playedCards = GameSessions.getCardsPlayedInCurrentRound(gameSession);
        List<CardOption> canBeApplied = CardOptions.canBeAppliedTo(inputOptions, mode, playedCards);
        if (canBeApplied.size() <= 1) {
            return inputOptions;
        }
        boolean trumpfPlayed = playedCards.stream()
                .anyMatch(card -> card.getColor().equals(mode.getTrumpfColor()));
        if (trumpfPlayed) {
            return Collections.singletonList(
                    CardOptions.getMinOption(canBeApplied, Comparator.comparing(CardOption::getTrumpfRank))
            );
        } else {
            return Collections.singletonList(
                    CardOptions.getMinOption(canBeApplied, Comparator.comparing(CardOption::getRank))
            );
        }
    };
    public static ChooseCardRule CHOOSE_WEAKEST_CARD = (inputOptions, gameSession, allMoves) -> {
        if (inputOptions.size() <= 1) {
            return inputOptions;
        }
        Mode mode = gameSession.getCurrentRound().getMode();
        Color trumpfColor = mode.getTrumpfColor();
        if (mode.getTrumpfName().equals(Trumpf.TRUMPF) || mode.getTrumpfName().equals(Trumpf.SCHIEBE)) {
            final Optional<CardOption> worstOptionWithoutTrumpfCards = inputOptions.stream()
                    .filter(option -> !option.getCard().getColor().equals(trumpfColor))
                    .min(Comparator.comparing(CardOption::getRank));
            if (worstOptionWithoutTrumpfCards.isPresent()) {
                CardOption worstOption = worstOptionWithoutTrumpfCards.get();
                return Collections.singletonList(worstOption);
            } else {
                CardOption worstOptionOfAllAvailableOptions = inputOptions.stream()
                        .min(Comparator.comparing(CardOption::getRank))
                        .get();
                return Collections.singletonList(worstOptionOfAllAvailableOptions);
            }
        } else {
            CardOption worstOptionOfAllAvailableOptions = inputOptions.stream()
                    .min(Comparator.comparing(CardOption::getRank))
                    .get();
            return Collections.singletonList(worstOptionOfAllAvailableOptions);
        }
    };
}

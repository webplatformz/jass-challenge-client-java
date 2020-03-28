package com.zuehlke.jasschallenge.client.game.strategy.utils.rules;

import com.zuehlke.jasschallenge.client.game.strategy.utils.options.ModeOption;
import com.zuehlke.jasschallenge.game.cards.Card;

import java.util.List;
import java.util.Set;

@FunctionalInterface
public interface ChooseTrumpfRule {
    List<ModeOption> apply(List<ModeOption> inputOptions, Set<Card> availableCards, List<ModeOption> allPossibleModes);
}

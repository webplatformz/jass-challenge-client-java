package com.zuehlke.jasschallenge.client.game.strategy.utils;

import com.zuehlke.jasschallenge.client.game.GameSession;
import com.zuehlke.jasschallenge.client.game.Move;
import com.zuehlke.jasschallenge.client.game.strategy.utils.options.CardOption;
import com.zuehlke.jasschallenge.client.game.strategy.utils.options.CardOptions;
import com.zuehlke.jasschallenge.client.game.strategy.utils.options.ModeOption;
import com.zuehlke.jasschallenge.client.game.strategy.utils.options.ModeOptions;
import com.zuehlke.jasschallenge.client.game.strategy.utils.rules.ChooseCardRule;
import com.zuehlke.jasschallenge.client.game.strategy.utils.rules.ChooseTrumpfRule;
import com.zuehlke.jasschallenge.game.cards.Card;
import com.zuehlke.jasschallenge.game.mode.Mode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Strategy {
    private List<ChooseCardRule> chooseCardRules;
    private List<ChooseTrumpfRule> chooseTrumpfRules;

    public Strategy(List<ChooseTrumpfRule> chooseTrumpfRules, List<ChooseCardRule> chooseCardRules) {
        this.chooseTrumpfRules = chooseTrumpfRules;
        this.chooseCardRules = chooseCardRules;
    }

    public Mode applyChooseTrumpfRules(Set<Card> availableCards, GameSession session, boolean isGschobe) {
        List<ModeOption> availableModeOptions = ModeOptions.forAvailableCards(new ArrayList<>(availableCards));
        List<ModeOption> allModeOptions = createAllModeOptions(isGschobe);
        for (ChooseTrumpfRule rule : chooseTrumpfRules) {
            availableModeOptions = rule.apply(availableModeOptions, availableCards, allModeOptions);
        }
        if (availableModeOptions.size() != 1) {
            throw new IllegalStateException("Invalid strategy! A single mode option must be selected.");
        }
        return availableModeOptions.get(0).getMode();
    }

    private List<ModeOption> createAllModeOptions(boolean isGschobe) {
        List<ModeOption> allModeOptions = Mode.standardModes().stream()
                .map(mode -> new ModeOption(mode, 0))
                .collect(Collectors.toList());
        if (!isGschobe) {
            allModeOptions.add(new ModeOption(Mode.shift(), 0));
        }
        return allModeOptions;
    }

    public Card applyChooseCardRules(Set<Card> availableCards, GameSession session, List<Move> allMoves) {
        List<CardOption> availableCardOptions =  CardOptions.from(availableCards);
        for (ChooseCardRule rule : chooseCardRules) {
            availableCardOptions = rule.apply(availableCardOptions, session, allMoves);
        }
        if (availableCardOptions.size() != 1) {
            throw new IllegalStateException("Invalid strategy! A single card option must be selected.");
        }
        return availableCardOptions.get(0).getCard();
    }
}

package com.zuehlke.jasschallenge.client.game.strategy.utils.rules;

import com.zuehlke.jasschallenge.client.game.strategy.utils.options.ModeOption;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class ChooseTrumpfRules {
    public static ChooseTrumpfRule CHOOSE_TRUMPF_WITH_HIGHEST_TOTAL_RANK = (inputOptions, availableCards, allPossibleModes) -> Collections.singletonList(
            inputOptions.stream()
                    .max(Comparator.comparing(ModeOption::getTotalRank))
                    .orElse(allPossibleModes.get(new Random().nextInt(allPossibleModes.size())))
    );
}

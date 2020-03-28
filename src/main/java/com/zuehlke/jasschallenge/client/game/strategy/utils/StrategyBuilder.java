package com.zuehlke.jasschallenge.client.game.strategy.utils;

import com.zuehlke.jasschallenge.client.game.strategy.utils.rules.ChooseCardRule;
import com.zuehlke.jasschallenge.client.game.strategy.utils.rules.ChooseTrumpfRule;

import java.util.ArrayList;
import java.util.List;

public class StrategyBuilder {

    private List<ChooseCardRule> chooseCardRules;
    private List<ChooseTrumpfRule> chooseTrumpfRules;

    public ChooseTrumpfRuleBuilder chooseTrumpfRules() {
        return new ChooseTrumpfRuleBuilder();
    }

    public class ChooseTrumpfRuleBuilder {

        public ChooseTrumpfRuleBuilder() {
            chooseTrumpfRules = new ArrayList<>();
        }

        public ChooseTrumpfRuleBuilder addRule(ChooseTrumpfRule chooseTrumpfRule) {
            chooseTrumpfRules.add(chooseTrumpfRule);
            return this;
        }

        public ChooseCardRuleBuilder chooseCardRules() {
            return new ChooseCardRuleBuilder();
        }
    }

    public class ChooseCardRuleBuilder {

        public ChooseCardRuleBuilder() {
            chooseCardRules = new ArrayList<>();
        }

        public ChooseCardRuleBuilder addRule(ChooseCardRule chooseCardRule) {
            chooseCardRules.add(chooseCardRule);
            return this;
        }

        public Strategy build() {
            return new Strategy(chooseTrumpfRules, chooseCardRules);
        }
    }
}


package com.zuehlke.jasschallenge.client.game.strategy;

import com.zuehlke.jasschallenge.client.game.GameSession;
import com.zuehlke.jasschallenge.client.game.Move;
import com.zuehlke.jasschallenge.client.game.strategy.utils.Strategy;
import com.zuehlke.jasschallenge.client.game.strategy.utils.StrategyBuilder;
import com.zuehlke.jasschallenge.game.cards.Card;
import com.zuehlke.jasschallenge.game.mode.Mode;

import java.util.*;

import static com.zuehlke.jasschallenge.client.game.strategy.utils.rules.ChooseCardRules.*;
import static com.zuehlke.jasschallenge.client.game.strategy.utils.rules.ChooseTrumpfRules.CHOOSE_TRUMPF_WITH_HIGHEST_TOTAL_RANK;

public class StrongestOrWeakestStrategy implements JassStrategy {

    private Strategy strategy;
    private List<Move> allMoves = new ArrayList<>();

    public StrongestOrWeakestStrategy() {
        strategy = new StrategyBuilder()
                .chooseTrumpfRules()
                .addRule(CHOOSE_TRUMPF_WITH_HIGHEST_TOTAL_RANK)
                .chooseCardRules()
                .addRule(CHOOSE_STRONGEST_CARD_IF_ROUND_CAN_BE_WON)
                .addRule(CHOOSE_WEAKEST_CARD_THAT_CAN_BE_APPLIED_TO_ROUND)
                .addRule(CHOOSE_WEAKEST_CARD)
                .build();
    }

    @Override
    public Mode chooseTrumpf(Set<Card> availableCards, GameSession session, boolean isGschobe) {
        return strategy.applyChooseTrumpfRules(availableCards, session, isGschobe);
    }

    @Override
    public Card chooseCard(Set<Card> availableCards, GameSession session) {
        return strategy.applyChooseCardRules(availableCards, session, allMoves);
    }

    @Override
    public void onGameStarted(GameSession session) {
        allMoves.clear();
    }

    @Override
    public void onMoveMade(Move move, GameSession session) {
        allMoves.add(move);
    }
}

package com.zuehlke.jasschallenge.client.game.strategy.utils.rules;

import com.zuehlke.jasschallenge.client.game.GameSession;
import com.zuehlke.jasschallenge.client.game.Move;
import com.zuehlke.jasschallenge.client.game.strategy.utils.options.CardOption;

import java.util.List;

@FunctionalInterface
public interface ChooseCardRule {
    List<CardOption> apply(List<CardOption> inputOptions, GameSession gameSession, List<Move> allMoves);
}

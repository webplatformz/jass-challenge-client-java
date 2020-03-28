package com.zuehlke.jasschallenge.client.game.strategy.utils.options;

import com.zuehlke.jasschallenge.game.Trumpf;
import com.zuehlke.jasschallenge.game.cards.Card;
import com.zuehlke.jasschallenge.game.cards.Cards;
import com.zuehlke.jasschallenge.game.cards.Color;
import com.zuehlke.jasschallenge.game.mode.Mode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ModeOptions {

    public  static List<ModeOption> forAvailableCards(List<Card> availableCards) {
        Map<Color, List<Card>> colorToCards = Cards.groupCardsByColor(availableCards);
        ArrayList<ModeOption> modeOptions = new ArrayList<>();
        for (Color color: colorToCards.keySet() ) {
            int totalNormalRank = colorToCards.get(color).stream()
                    .mapToInt(card -> card.getValue().getRank())
                    .sum();
            int totalTrumpfRank = colorToCards.get(color).stream()
                    .mapToInt(card -> card.getValue().getTrumpfRank())
                    .sum();
            modeOptions.add(new ModeOption(Mode.from(Trumpf.OBEABE, color), totalNormalRank));
            modeOptions.add(new ModeOption(Mode.from(Trumpf.UNDEUFE, color), totalNormalRank));
            modeOptions.add(new ModeOption(Mode.from(Trumpf.TRUMPF, color), totalTrumpfRank));
        }
        return modeOptions;
    }
}

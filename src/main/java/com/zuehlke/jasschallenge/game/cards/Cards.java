package com.zuehlke.jasschallenge.game.cards;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;

public final class Cards {

    public static Map<Color, List<Card>> groupCardsByColor(Set<Card> availableCards) {
        return availableCards.stream().collect(groupingBy(Card::getColor));
    }

    public static Map<Color, List<Card>> groupCardsByColor(List<Card> availableCards) {
        return availableCards.stream().collect(groupingBy(Card::getColor));
    }
}

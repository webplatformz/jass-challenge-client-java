package com.zuehlke.jasschallenge.client.game.strategy.utils.options;

import com.zuehlke.jasschallenge.client.game.strategy.utils.options.CardOption;
import com.zuehlke.jasschallenge.client.game.strategy.utils.options.CardOptions;
import com.zuehlke.jasschallenge.game.cards.Card;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CardOptionsTest {

    private List<Card> allCards;
    private List<CardOption> allOptions;

    @Before
    public void setUp() throws Exception {
        allCards = Stream.of(
                Card.DIAMOND_SIX,
                Card.DIAMOND_SEVEN,
                Card.DIAMOND_EIGHT,
                Card.DIAMOND_NINE,
                Card.DIAMOND_TEN,
                Card.DIAMOND_JACK,
                Card.DIAMOND_QUEEN,
                Card.DIAMOND_KING,
                Card.DIAMOND_ACE
        ).collect(Collectors.toList());

        allOptions = Stream.of(
                new CardOption(Card.DIAMOND_SIX),
                new CardOption(Card.DIAMOND_SEVEN),
                new CardOption(Card.DIAMOND_EIGHT),
                new CardOption(Card.DIAMOND_NINE),
                new CardOption(Card.DIAMOND_TEN),
                new CardOption(Card.DIAMOND_JACK),
                new CardOption(Card.DIAMOND_QUEEN),
                new CardOption(Card.DIAMOND_KING),
                new CardOption(Card.DIAMOND_ACE)
        ).collect(Collectors.toList());
    }
}
package com.zuehlke.jasschallenge.client.game.strategy.utils.options;

import com.zuehlke.jasschallenge.client.game.strategy.utils.options.ModeOption;
import com.zuehlke.jasschallenge.client.game.strategy.utils.options.ModeOptions;
import com.zuehlke.jasschallenge.game.Trumpf;
import com.zuehlke.jasschallenge.game.cards.Card;
import com.zuehlke.jasschallenge.game.cards.Color;
import com.zuehlke.jasschallenge.game.mode.Mode;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ModeOptionsTest {

    private List<Card> allDiamondCards;
    private List<Card> allHeartCards;
    private List<Card> allSpadeCards;
    private List<Card> allClubCards;
    private List<Card> allColors;

    @Before
    public void setUp() throws Exception {
        allDiamondCards = Stream.of(
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
        allHeartCards = Stream.of(
                Card.HEART_SIX,
                Card.HEART_SEVEN,
                Card.HEART_EIGHT,
                Card.HEART_NINE,
                Card.HEART_TEN,
                Card.HEART_JACK,
                Card.HEART_QUEEN,
                Card.HEART_KING,
                Card.HEART_ACE
        ).collect(Collectors.toList());
        allHeartCards = Stream.of(
                Card.SPADE_SIX,
                Card.SPADE_SEVEN,
                Card.SPADE_EIGHT,
                Card.SPADE_NINE,
                Card.SPADE_TEN,
                Card.SPADE_JACK,
                Card.SPADE_QUEEN,
                Card.SPADE_KING,
                Card.SPADE_ACE
        ).collect(Collectors.toList());
        allHeartCards = Stream.of(
                Card.CLUB_SIX,
                Card.CLUB_SEVEN,
                Card.CLUB_EIGHT,
                Card.CLUB_NINE,
                Card.CLUB_TEN,
                Card.CLUB_JACK,
                Card.CLUB_QUEEN,
                Card.CLUB_KING,
                Card.CLUB_ACE
        ).collect(Collectors.toList());
        allColors = Stream.of(
                Card.DIAMOND_ACE,
                Card.HEART_ACE,
                Card.SPADE_ACE,
                Card.CLUB_ACE
        ).collect(Collectors.toList());
    }

    @Test
    public void forAvailableCards_allDiamondCards_onlyDiamondColorAndRankXXAndTrumpfRankZZ() {
        List<ModeOption> modeOptions = ModeOptions.forAvailableCards(allDiamondCards);
        List<Mode> possibleModes = modeOptions.stream()
                .map(ModeOption::getMode)
                .distinct()
                .collect(Collectors.toList());
        List<Mode> expectedModes = Stream.of(
                Mode.topDown(),
                Mode.bottomUp(),
                Mode.from(Trumpf.TRUMPF, Color.DIAMONDS)
        ).collect(Collectors.toList());
        assertEquals(expectedModes, possibleModes);
        List<Integer> totalRanks = modeOptions.stream()
                .map(ModeOption::getTotalRank)
                .collect(Collectors.toList());
        assertEquals(Arrays.asList(45,45,60), totalRanks);
    }
}
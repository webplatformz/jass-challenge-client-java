package com.zuehlke.jasschallenge.client.game.strategy.utils.options;

import com.zuehlke.jasschallenge.game.Trumpf;
import com.zuehlke.jasschallenge.game.cards.Card;
import com.zuehlke.jasschallenge.game.cards.Color;
import com.zuehlke.jasschallenge.game.mode.Mode;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class CardOptionTest {
    private Mode clubGschobe;
    private Mode club;
    private Mode diamondGschobe;
    private Mode diamond;
    private Mode heartGschobe;
    private Mode heart;
    private Mode spadeGschobe;
    private Mode spade;
    private Mode obeabe;
    private Mode undeufe;

    private CardOption clubSixOption;
    private CardOption clubSevenOption;
    private CardOption clubEightOption;
    private CardOption clubNineOption;
    private CardOption clubTenOption;
    private CardOption clubJackOption;
    private CardOption clubQueenOption;
    private CardOption clubKingOption;
    private CardOption clubAceOption;

    private Set<Card> clubSixNotAvailable;
    private Set<Card> clubSevenNotAvailable;
    private Set<Card> clubEightNotAvailable;
    private Set<Card> clubNineNotAvailable;
    private Set<Card> clubTenNotAvailable;
    private Set<Card> clubJackNotAvailable;
    private Set<Card> clubQueenNotAvailable;
    private Set<Card> clubKingNotAvailable;
    private Set<Card> clubAceNotAvailable;

    private HashSet<Card> clubSixAvailable;
    private HashSet<Card> clubSevenAvailable;
    private HashSet<Card> clubEightAvailable;
    private HashSet<Card> clubNineAvailable;
    private HashSet<Card> clubTenAvailable;
    private HashSet<Card> clubJackAvailable;
    private HashSet<Card> clubQueenAvailable;
    private HashSet<Card> clubKingAvailable;
    private HashSet<Card> clubAceAvailable;

    private List<Card> noPlayedCards;
    private List<Card> highestClubPlayed;
    private List<Card> lowestClubPlayed;
    private List<Card> highestDiamondPlayed;
    private List<Card> lowestDiamondPlayed;
    private List<Card> highestHeartPlayed;
    private List<Card> lowestHeartPlayed;
    private List<Card> highestSpadePlayed;
    private List<Card> lowestSpadePlayed;

    @Before
    public void setUp() throws Exception {
        clubGschobe = Mode.from(Trumpf.SCHIEBE, null);
        club = Mode.from(Trumpf.TRUMPF, Color.CLUBS);
        diamond = Mode.from(Trumpf.TRUMPF, null);
        diamondGschobe = Mode.from(Trumpf.SCHIEBE, null);
        heartGschobe = Mode.from(Trumpf.SCHIEBE, null);
        heart = Mode.from(Trumpf.TRUMPF, Color.HEARTS);
        spadeGschobe = Mode.from(Trumpf.SCHIEBE, null);
        spade = Mode.from(Trumpf.TRUMPF, Color.SPADES);
        obeabe = Mode.topDown();
        undeufe = Mode.bottomUp();

        clubSixOption = new CardOption(Card.CLUB_SIX);
        clubSevenOption = new CardOption(Card.CLUB_SEVEN);
        clubEightOption = new CardOption(Card.CLUB_EIGHT);
        clubNineOption = new CardOption(Card.CLUB_NINE);
        clubTenOption = new CardOption(Card.CLUB_TEN);
        clubJackOption = new CardOption(Card.CLUB_JACK);
        clubQueenOption = new CardOption(Card.CLUB_QUEEN);
        clubKingOption = new CardOption(Card.CLUB_KING);
        clubAceOption = new CardOption(Card.CLUB_ACE);

        clubSixNotAvailable = Collections.emptySet();
        clubSevenNotAvailable = Collections.emptySet();
        clubEightNotAvailable = Collections.emptySet();
        clubNineNotAvailable = Collections.emptySet();
        clubTenNotAvailable = Collections.emptySet();
        clubJackNotAvailable = Collections.emptySet();
        clubQueenNotAvailable = Collections.emptySet();
        clubKingNotAvailable = Collections.emptySet();
        clubAceNotAvailable = Collections.emptySet();

        clubSixAvailable = new HashSet<>(Collections.singletonList(Card.CLUB_SIX));
        clubSevenAvailable = new HashSet<>(Collections.singletonList(Card.CLUB_SEVEN));
        clubEightAvailable = new HashSet<>(Collections.singletonList(Card.CLUB_EIGHT));
        clubNineAvailable = new HashSet<>(Collections.singletonList(Card.CLUB_NINE));
        clubTenAvailable = new HashSet<>(Collections.singletonList(Card.CLUB_TEN));
        clubJackAvailable = new HashSet<>(Collections.singletonList(Card.CLUB_JACK));
        clubQueenAvailable = new HashSet<>(Collections.singletonList(Card.CLUB_QUEEN));
        clubKingAvailable = new HashSet<>(Collections.singletonList(Card.CLUB_KING));
        clubAceAvailable = new HashSet<>(Collections.singletonList(Card.CLUB_ACE));

        noPlayedCards = Collections.emptyList();
        highestClubPlayed = Arrays.asList(Card.CLUB_ACE);
        lowestClubPlayed = Arrays.asList(Card.CLUB_SIX);
        highestDiamondPlayed = Arrays.asList(Card.DIAMOND_ACE);
        lowestDiamondPlayed = Arrays.asList(Card.DIAMOND_SIX);
        highestHeartPlayed = Arrays.asList(Card.HEART_ACE);
        lowestHeartPlayed = Arrays.asList(Card.HEART_SIX);
        highestSpadePlayed = Arrays.asList(Card.SPADE_ACE);
        lowestSpadePlayed = Arrays.asList(Card.SPADE_SIX);
    }

    @Test
    public void getCard() {
        assertEquals(Card.CLUB_SIX, clubSixOption.getCard());
        assertEquals(Card.CLUB_SEVEN, clubSevenOption.getCard());
        assertEquals(Card.CLUB_EIGHT, clubEightOption.getCard());
        assertEquals(Card.CLUB_NINE, clubNineOption.getCard());
        assertEquals(Card.CLUB_TEN, clubTenOption.getCard());
        assertEquals(Card.CLUB_JACK, clubJackOption.getCard());
        assertEquals(Card.CLUB_QUEEN, clubQueenOption.getCard());
        assertEquals(Card.CLUB_KING, clubKingOption.getCard());
        assertEquals(Card.CLUB_ACE, clubAceOption.getCard());
    }

    @Test
    public void getRank() {
        assertEquals(1, clubSixOption.getRank());
        assertEquals(2, clubSevenOption.getRank());
        assertEquals(3, clubEightOption.getRank());
        assertEquals(4, clubNineOption.getRank());
        assertEquals(5, clubTenOption.getRank());
        assertEquals(6, clubJackOption.getRank());
        assertEquals(7, clubQueenOption.getRank());
        assertEquals(8, clubKingOption.getRank());
        assertEquals(9, clubAceOption.getRank());
    }

    @Test
    public void getTrumpfRank() {
        assertEquals(1, clubSixOption.getTrumpfRank());
        assertEquals(2, clubSevenOption.getTrumpfRank());
        assertEquals(3, clubEightOption.getTrumpfRank());
        assertEquals(12, clubNineOption.getTrumpfRank());
        assertEquals(5, clubTenOption.getTrumpfRank());
        assertEquals(13, clubJackOption.getTrumpfRank());
        assertEquals(7, clubQueenOption.getTrumpfRank());
        assertEquals(8, clubKingOption.getTrumpfRank());
        assertEquals(9, clubAceOption.getTrumpfRank());
    }

    @Test
    public void canBeAppliedTo_cardAvailableAndFirstCardOfRound_true() {
        assertEquals(true, clubSixOption.canBeAppliedTo(club, noPlayedCards));
        assertEquals(true, clubSevenOption.canBeAppliedTo(diamond, noPlayedCards));
        assertEquals(true, clubEightOption.canBeAppliedTo(heart, noPlayedCards));
        assertEquals(true, clubNineOption.canBeAppliedTo(heart, noPlayedCards));
        assertEquals(true, clubTenOption.canBeAppliedTo(spade, noPlayedCards));
        assertEquals(true, clubJackOption.canBeAppliedTo(clubGschobe, noPlayedCards));
        assertEquals(true, clubQueenOption.canBeAppliedTo(diamondGschobe, noPlayedCards));
        assertEquals(true, clubKingOption.canBeAppliedTo(heartGschobe, noPlayedCards));
        assertEquals(true, clubAceOption.canBeAppliedTo(spadeGschobe, noPlayedCards));
    }

    @Test
    public void canBeAppliedTo_cardAvailableAndClubNotTrumpfAndDiamondPlayed_false() {
        assertEquals(false, clubSixOption.canBeAppliedTo(diamond, highestDiamondPlayed));
        assertEquals(false, clubSevenOption.canBeAppliedTo(heart, highestDiamondPlayed));
        assertEquals(false, clubEightOption.canBeAppliedTo(spade, highestDiamondPlayed));
        assertEquals(false, clubNineOption.canBeAppliedTo(diamondGschobe, highestDiamondPlayed));
        assertEquals(false, clubTenOption.canBeAppliedTo(heartGschobe, highestDiamondPlayed));
        assertEquals(false, clubJackOption.canBeAppliedTo(spadeGschobe, highestDiamondPlayed));
        assertEquals(false, clubQueenOption.canBeAppliedTo(diamond, highestDiamondPlayed));
        assertEquals(false, clubKingOption.canBeAppliedTo(heart, highestDiamondPlayed));
        assertEquals(false, clubAceOption.canBeAppliedTo(spade, highestDiamondPlayed));
    }

    @Test
    public void canBeAppliedTo_cardAvailableAndClubNotTrumpfAndHeartPlayed_false() {
        assertEquals(false, clubSixOption.canBeAppliedTo(diamond, lowestHeartPlayed));
        assertEquals(false, clubSevenOption.canBeAppliedTo(heart, lowestHeartPlayed));
        assertEquals(false, clubEightOption.canBeAppliedTo(spade, lowestHeartPlayed));
        assertEquals(false, clubNineOption.canBeAppliedTo(diamondGschobe, lowestHeartPlayed));
        assertEquals(false, clubTenOption.canBeAppliedTo(heartGschobe, lowestHeartPlayed));
        assertEquals(false, clubJackOption.canBeAppliedTo(spadeGschobe, lowestHeartPlayed));
        assertEquals(false, clubQueenOption.canBeAppliedTo(diamond, lowestHeartPlayed));
        assertEquals(false, clubKingOption.canBeAppliedTo(heart, lowestHeartPlayed));
        assertEquals(false, clubAceOption.canBeAppliedTo(spade, lowestHeartPlayed));
    }

    @Test
    public void canBeAppliedTo_cardAvailableAndClubNotTrumpfAndSpadePlayed_false() {
        assertEquals(false, clubSixOption.canBeAppliedTo(diamond, highestSpadePlayed));
        assertEquals(false, clubSevenOption.canBeAppliedTo(heart, highestSpadePlayed));
        assertEquals(false, clubEightOption.canBeAppliedTo(spade, highestSpadePlayed));
        assertEquals(false, clubNineOption.canBeAppliedTo(diamondGschobe, highestSpadePlayed));
        assertEquals(false, clubTenOption.canBeAppliedTo(heartGschobe, highestSpadePlayed));
        assertEquals(false, clubJackOption.canBeAppliedTo(spadeGschobe, highestSpadePlayed));
        assertEquals(false, clubQueenOption.canBeAppliedTo(diamond, highestSpadePlayed));
        assertEquals(false, clubKingOption.canBeAppliedTo(heart, highestSpadePlayed));
        assertEquals(false, clubAceOption.canBeAppliedTo(spade, highestSpadePlayed));
    }

    @Test
    public void canBeAppliedTo_cardAvailableAndClubTrumpfAndDiamondPlayed_true() {
        assertEquals(true, clubSixOption.canBeAppliedTo(club, highestDiamondPlayed));
        assertEquals(true, clubSevenOption.canBeAppliedTo(club, highestDiamondPlayed));
        assertEquals(true, clubEightOption.canBeAppliedTo(club, highestDiamondPlayed));
        assertEquals(true, clubNineOption.canBeAppliedTo(club, highestDiamondPlayed));
        assertEquals(true, clubTenOption.canBeAppliedTo(club, highestDiamondPlayed));
        assertEquals(true, clubJackOption.canBeAppliedTo(club, highestDiamondPlayed));
        assertEquals(true, clubQueenOption.canBeAppliedTo(club, highestDiamondPlayed));
        assertEquals(true, clubKingOption.canBeAppliedTo(club, highestDiamondPlayed));
        assertEquals(true, clubAceOption.canBeAppliedTo(club, highestDiamondPlayed));
    }

    @Test
    public void canBeAppliedTo_cardAvailableAndClubTrumpfAndHeartPlayed_true() {
        assertEquals(true, clubSixOption.canBeAppliedTo(club, lowestHeartPlayed));
        assertEquals(true, clubSevenOption.canBeAppliedTo(club, lowestHeartPlayed));
        assertEquals(true, clubEightOption.canBeAppliedTo(club, lowestHeartPlayed));
        assertEquals(true, clubNineOption.canBeAppliedTo(club, lowestHeartPlayed));
        assertEquals(true, clubTenOption.canBeAppliedTo(club, lowestHeartPlayed));
        assertEquals(true, clubJackOption.canBeAppliedTo(club, lowestHeartPlayed));
        assertEquals(true, clubQueenOption.canBeAppliedTo(club, lowestHeartPlayed));
        assertEquals(true, clubKingOption.canBeAppliedTo(club, lowestHeartPlayed));
        assertEquals(true, clubAceOption.canBeAppliedTo(club, lowestHeartPlayed));
    }

    @Test
    public void canBeAppliedTo_cardAvailableAndClubTrumpfAndSpadePlayed_true() {
        assertEquals(true, clubSixOption.canBeAppliedTo(club, highestSpadePlayed));
        assertEquals(true, clubSevenOption.canBeAppliedTo(club, highestSpadePlayed));
        assertEquals(true, clubEightOption.canBeAppliedTo(club, highestSpadePlayed));
        assertEquals(true, clubNineOption.canBeAppliedTo(club, highestSpadePlayed));
        assertEquals(true, clubTenOption.canBeAppliedTo(club, highestSpadePlayed));
        assertEquals(true, clubJackOption.canBeAppliedTo(club, highestSpadePlayed));
        assertEquals(true, clubQueenOption.canBeAppliedTo(club, highestSpadePlayed));
        assertEquals(true, clubKingOption.canBeAppliedTo(club, highestSpadePlayed));
        assertEquals(true, clubAceOption.canBeAppliedTo(club, highestSpadePlayed));
    }

    @Test
    public void canBeAppliedTo_cardAvailableAndAndClubPlayed_true() {
        assertEquals(true, clubSixOption.canBeAppliedTo(club, highestClubPlayed));
        assertEquals(true, clubSevenOption.canBeAppliedTo(club, highestClubPlayed));
        assertEquals(true, clubEightOption.canBeAppliedTo(club, highestClubPlayed));
        assertEquals(true, clubNineOption.canBeAppliedTo(club, highestClubPlayed));
        assertEquals(true, clubTenOption.canBeAppliedTo(club, highestClubPlayed));
        assertEquals(true, clubJackOption.canBeAppliedTo(club, highestClubPlayed));
        assertEquals(true, clubQueenOption.canBeAppliedTo(club, highestClubPlayed));
        assertEquals(true, clubKingOption.canBeAppliedTo(club, highestClubPlayed));
        assertEquals(true, clubAceOption.canBeAppliedTo(club, highestClubPlayed));
    }

    @Test
    public void canWinRound_modeObeabeAndHigherClubPlayed_false() {
        assertEquals(false, clubSixOption.canWinRound(obeabe, highestClubPlayed));
        assertEquals(false, clubSevenOption.canWinRound(obeabe, highestClubPlayed));
        assertEquals(false, clubEightOption.canWinRound(obeabe, highestClubPlayed));
        assertEquals(false, clubNineOption.canWinRound(obeabe, highestClubPlayed));
        assertEquals(false, clubTenOption.canWinRound(obeabe, highestClubPlayed));
        assertEquals(false, clubJackOption.canWinRound(obeabe, highestClubPlayed));
        assertEquals(false, clubQueenOption.canWinRound(obeabe, highestClubPlayed));
        assertEquals(false, clubKingOption.canWinRound(obeabe, highestClubPlayed));
    }

    @Test
    public void canWinRound_modeObeabeAndLowerClubPlayed_true() {
        assertEquals(true, clubSevenOption.canWinRound(obeabe, lowestClubPlayed));
        assertEquals(true, clubEightOption.canWinRound(obeabe, lowestClubPlayed));
        assertEquals(true, clubNineOption.canWinRound(obeabe, lowestClubPlayed));
        assertEquals(true, clubTenOption.canWinRound(obeabe, lowestClubPlayed));
        assertEquals(true, clubJackOption.canWinRound(obeabe, lowestClubPlayed));
        assertEquals(true, clubQueenOption.canWinRound(obeabe, lowestClubPlayed));
        assertEquals(true, clubKingOption.canWinRound(obeabe, lowestClubPlayed));
        assertEquals(true, clubAceOption.canWinRound(obeabe, lowestClubPlayed));
    }

    @Test
    public void canWinRound_modeUndeufeAndLowerClubPlayed_false() {
        assertEquals(false, clubSevenOption.canWinRound(undeufe, lowestClubPlayed));
        assertEquals(false, clubEightOption.canWinRound(undeufe, lowestClubPlayed));
        assertEquals(false, clubNineOption.canWinRound(undeufe, lowestClubPlayed));
        assertEquals(false, clubTenOption.canWinRound(undeufe, lowestClubPlayed));
        assertEquals(false, clubJackOption.canWinRound(undeufe, lowestClubPlayed));
        assertEquals(false, clubQueenOption.canWinRound(undeufe, lowestClubPlayed));
        assertEquals(false, clubKingOption.canWinRound(undeufe, lowestClubPlayed));
        assertEquals(false, clubAceOption.canWinRound(undeufe, lowestClubPlayed));
    }

    @Test
    public void canWinRound_modeUndeufeAndHigherClubPlayed_true() {
        assertEquals(true, clubSixOption.canWinRound(undeufe, highestClubPlayed));
        assertEquals(true, clubSevenOption.canWinRound(undeufe, highestClubPlayed));
        assertEquals(true, clubEightOption.canWinRound(undeufe, highestClubPlayed));
        assertEquals(true, clubNineOption.canWinRound(undeufe, highestClubPlayed));
        assertEquals(true, clubTenOption.canWinRound(undeufe, highestClubPlayed));
        assertEquals(true, clubJackOption.canWinRound(undeufe, highestClubPlayed));
        assertEquals(true, clubQueenOption.canWinRound(undeufe, highestClubPlayed));
        assertEquals(true, clubKingOption.canWinRound(undeufe, highestClubPlayed));
    }

    @Test
    public void canWinRound_modeTrumpfClubPlayed_true() {
        assertEquals(true, clubSixOption.canWinRound(club, highestDiamondPlayed));
        assertEquals(true, clubSevenOption.canWinRound(club, highestHeartPlayed));
        assertEquals(true, clubEightOption.canWinRound(club, highestSpadePlayed));
        assertEquals(true, clubNineOption.canWinRound(club, highestDiamondPlayed));
        assertEquals(true, clubTenOption.canWinRound(club, highestHeartPlayed));
        assertEquals(true, clubJackOption.canWinRound(club, highestSpadePlayed));
        assertEquals(true, clubQueenOption.canWinRound(club, highestDiamondPlayed));
        assertEquals(true, clubKingOption.canWinRound(club, highestHeartPlayed));
    }

    @Test
    public void canWinRound_modeTrumpfOtherThanClubPlayed_false() {
        assertEquals(false, clubSixOption.canWinRound(diamond, lowestDiamondPlayed));
        assertEquals(false, clubSevenOption.canWinRound(heart, lowestHeartPlayed));
        assertEquals(false, clubEightOption.canWinRound(spade, lowestSpadePlayed));
        assertEquals(false, clubNineOption.canWinRound(diamond, lowestDiamondPlayed));
        assertEquals(false, clubTenOption.canWinRound(heart, lowestHeartPlayed));
        assertEquals(false, clubJackOption.canWinRound(spade, lowestSpadePlayed));
        assertEquals(false, clubQueenOption.canWinRound(diamond, lowestDiamondPlayed));
        assertEquals(false, clubKingOption.canWinRound(heart, lowestHeartPlayed));
    }
}
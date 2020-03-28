package com.zuehlke.jasschallenge.client.game.strategy.utils.rules;

import com.zuehlke.jasschallenge.client.game.GameSession;
import com.zuehlke.jasschallenge.client.game.Move;
import com.zuehlke.jasschallenge.client.game.Player;
import com.zuehlke.jasschallenge.client.game.Team;
import com.zuehlke.jasschallenge.client.game.strategy.utils.options.CardOption;
import com.zuehlke.jasschallenge.game.Trumpf;
import com.zuehlke.jasschallenge.game.cards.Card;
import com.zuehlke.jasschallenge.game.cards.Color;
import com.zuehlke.jasschallenge.game.mode.Mode;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.zuehlke.jasschallenge.client.game.strategy.utils.rules.ChooseCardRules.*;
import static org.junit.Assert.assertEquals;

public class ChooseCardRulesTest {
    Mode trumpfClubs;
    Mode trumpfSpades;
    Mode obeabe;
    Mode undeufe;

    Set<Card> singleCardHand;
    Set<Card> weakClubHand;
    Set<Card> strongClubHand;
    Set<Card> weakClubAndStrongSpadeHand;
    Set<Card> weakSpadeHand;
    Set<Card> strongSpadeHand;

    List<CardOption> singleOptionInput;
    List<CardOption> weakClubOptions;
    List<CardOption> strongClubOptions;
    List<CardOption> weakClubAndStrongSpadeOptions;
    List<CardOption> weakSpadeOptions;
    List<CardOption> strongSpadeOptions;

    Player weakClubPlayer;
    Player strongClubPlayer;
    Player weakSpadePlayer;
    Player strongSpadePlayer;

    Team weakClubTeam;
    Team strongClubTeam;
    Team weakSpadeTeam;
    Team strongSpadeTeam;

    List<Player> strongClubThenWeakSpadePlayers;
    List<Player> strongClubThenStrongSpadePlayers;
    List<Player> weakClubThenWeakSpadePlayers;
    List<Player> weakClubThenStrongSpadePlayers;

    List<Player> strongSpadeThenWeakClubPlayers;
    List<Player> strongSpadeThenStrongClubPlayers;
    List<Player> weakSpadeThenWeakClubPlayers;
    List<Player> weakSpadeThenStrongClubPlayers;

    List<Move> allMoves = new ArrayList<>();

    @Before
    public void setUp() {
        trumpfClubs = Mode.from(Trumpf.TRUMPF, Color.CLUBS);
        trumpfSpades = Mode.from(Trumpf.TRUMPF, Color.SPADES);
        obeabe = Mode.topDown();
        undeufe = Mode.bottomUp();

        singleCardHand = Stream.of(Card.CLUB_ACE).collect(Collectors.toSet());
        singleOptionInput = singleCardHand.stream()
                .map(CardOption::new)
                .collect(Collectors.toList());

        weakClubHand = Stream.of(Card.CLUB_SIX, Card.CLUB_SEVEN).collect(Collectors.toSet());
        weakClubOptions = weakClubHand.stream().map(CardOption::new).collect(Collectors.toList());

        strongClubHand = Stream.of(Card.CLUB_JACK, Card.CLUB_NINE).collect(Collectors.toSet());
        strongClubOptions = strongClubHand.stream().map(CardOption::new).collect(Collectors.toList());

        weakClubAndStrongSpadeHand = Stream.of(Card.CLUB_SIX, Card.SPADE_ACE).collect(Collectors.toSet());
        weakClubAndStrongSpadeOptions = weakClubAndStrongSpadeHand.stream().map(CardOption::new).collect(Collectors.toList());

        weakSpadeHand = Stream.of(Card.SPADE_SIX, Card.SPADE_SEVEN).collect(Collectors.toSet());
        weakSpadeOptions = weakSpadeHand.stream().map(CardOption::new).collect(Collectors.toList());

        strongSpadeHand = Stream.of(Card.SPADE_JACK, Card.SPADE_NINE).collect(Collectors.toSet());
        strongSpadeOptions = strongSpadeHand.stream().map(CardOption::new).collect(Collectors.toList());

        weakClubPlayer = new Player("weakClubPlayer");
        weakClubPlayer.setCards(weakClubHand);

        strongClubPlayer = new Player("strongClubPlayer");
        strongClubPlayer.setCards(strongClubHand);

        weakSpadePlayer = new Player("weakSpadePlayer");
        weakSpadePlayer.setCards(weakSpadeHand);

        strongSpadePlayer = new Player("strongSpadePlayer");
        strongSpadePlayer.setCards(strongSpadeHand);

        weakClubTeam = new Team("weakClubTeam", Collections.singletonList(weakClubPlayer));
        strongClubTeam = new Team("strongClubTeam", Collections.singletonList(strongClubPlayer));

        weakSpadeTeam = new Team("weakSpadeTeam", Collections.singletonList(weakSpadePlayer));
        strongSpadeTeam = new Team("strongSpadeTeam", Collections.singletonList(strongSpadePlayer));

        strongClubThenWeakSpadePlayers = Arrays.asList(strongClubPlayer, weakSpadePlayer);
        strongClubThenStrongSpadePlayers = Arrays.asList(strongClubPlayer, strongSpadePlayer);
        weakClubThenWeakSpadePlayers = Arrays.asList(weakClubPlayer, weakSpadePlayer);
        weakClubThenStrongSpadePlayers = Arrays.asList(weakClubPlayer, strongSpadePlayer);

        strongSpadeThenWeakClubPlayers = Arrays.asList(strongSpadePlayer, weakClubPlayer);
        strongSpadeThenStrongClubPlayers = Arrays.asList(strongSpadePlayer, strongClubPlayer);
        weakSpadeThenWeakClubPlayers = Arrays.asList(weakSpadePlayer, weakClubPlayer);
        weakSpadeThenStrongClubPlayers = Arrays.asList(weakSpadePlayer, strongClubPlayer);
    }

    @Test
    public void CHOOSE_STRONGEST_CARD_IF_ROUND_CAN_BE_WON_oneInputOption_oneInputOption() {
        List<CardOption> output = CHOOSE_STRONGEST_CARD_IF_ROUND_CAN_BE_WON.apply(
                singleOptionInput,
                null,
                null
        );
        assertEquals(singleOptionInput, output);
    }

    @Test
    public void CHOOSE_WEAKEST_CARD_THAT_CAN_BE_APPLIED_TO_ROUND_oneInputOption_oneInputOption() {
        List<CardOption> output = CHOOSE_WEAKEST_CARD_THAT_CAN_BE_APPLIED_TO_ROUND.apply(
                singleOptionInput,
                null,
                null
        );
        assertEquals(singleOptionInput, output);
    }

    @Test
    public void CHOOSE_WEAKEST_CARD_oneInputOption_oneInputOption() {
        List<CardOption> output = CHOOSE_WEAKEST_CARD.apply(
                singleOptionInput,
                null,
                null
        );
        assertEquals(singleOptionInput, output);
    }

    @Test
    public void CHOOSE_STRONGEST_CARD_IF_ROUND_CAN_BE_WON_weakClubAgainstStrongSpadeWithSpadeAsTrumpf_outputEqualInput() {
        List<Team> teams = Arrays.asList(weakClubTeam, strongSpadeTeam);
        GameSession spadeAsTrumpf = new GameSession(teams, strongSpadeThenWeakClubPlayers);
        spadeAsTrumpf.startNewGame(trumpfSpades, false);
        Move moveOfStrongSpadePlayer = strongSpadePlayer.makeMove(spadeAsTrumpf);
        spadeAsTrumpf.makeMove(moveOfStrongSpadePlayer);
        List<CardOption> output = CHOOSE_STRONGEST_CARD_IF_ROUND_CAN_BE_WON.apply(
                weakClubOptions,
                spadeAsTrumpf,
                allMoves
        );
        assertEquals(weakClubOptions, output);
    }

    @Test
    public void CHOOSE_STRONGEST_CARD_IF_ROUND_CAN_BE_WON_strongClubAgainstStrongSpadeWithClubAsTrumpf_jackOfClubs() {
        List<Team> teams = Arrays.asList(strongClubTeam, strongSpadeTeam);
        GameSession clubAsTrumpf = new GameSession(teams, strongSpadeThenStrongClubPlayers);
        clubAsTrumpf.startNewGame(trumpfClubs, false);
        Move moveOfStrongSpadePlayer = strongSpadePlayer.makeMove(clubAsTrumpf);
        clubAsTrumpf.makeMove(moveOfStrongSpadePlayer);
        List<CardOption> output = CHOOSE_STRONGEST_CARD_IF_ROUND_CAN_BE_WON.apply(
                strongClubOptions,
                clubAsTrumpf,
                allMoves
        );
        CardOption strongestCard = strongClubOptions.stream().max(Comparator.comparing(CardOption::getTrumpfRank)).get();
        assertEquals(strongestCard, output.get(0));
        assertEquals(Card.CLUB_JACK, strongestCard.getCard());
    }
}
package com.zuehlke.jasschallenge.client.websocket;

import com.zuehlke.jasschallenge.client.game.*;
import com.zuehlke.jasschallenge.client.game.cards.Card;
import com.zuehlke.jasschallenge.client.websocket.messages.*;
import com.zuehlke.jasschallenge.client.websocket.messages.type.RemoteCard;
import com.zuehlke.jasschallenge.client.websocket.messages.type.RemoteColor;
import com.zuehlke.jasschallenge.client.websocket.messages.type.RemoteTeam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.zuehlke.jasschallenge.client.game.PlayingOrder.createOrder;
import static com.zuehlke.jasschallenge.client.game.PlayingOrder.createOrderStartingFromPlayer;
import static com.zuehlke.jasschallenge.client.websocket.messages.type.SessionType.AUTOJOIN;
import static com.zuehlke.jasschallenge.client.websocket.messages.type.Trumpf.OBEABE;
import static java.lang.Integer.compare;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class RemoteGameHandler {
    private final Player localPlayer;
    private final List<Team> teams;
    private final PlayerMapper playerMapper;
    private Round currentRound = Round.createRound(0);
    private PlayingOrder playingOrder;
    private List<Player> playersInPlayingOrder;
    private PlayingOrder startingPlayerOrder;

    private final static Logger logger = LoggerFactory.getLogger(RemoteGameHandler.class);

    public RemoteGameHandler(Player localPlayer) {
        this.localPlayer = localPlayer;
        this.playerMapper = new PlayerMapper(localPlayer);
        this.teams = new ArrayList<>();
    }

    Round getCurrentRound() {
        return currentRound;
    }

    List<Team> getTeams() {
        return teams;
    }

    public ChooseSession onRequestSessionChoice() {
        return new ChooseSession(AUTOJOIN);
    }

    public ChoosePlayerName onRequestPlayerName() {
        return new ChoosePlayerName(localPlayer.getName());
    }

    public void onDealCards(DealCard dealCard) {
        localPlayer.setCards(mapAllToCards(dealCard.getData()));
    }

    public void onPlayerJoined(PlayerJoined joinedPlayer) {
    }

    public void onBroadCastTeams(BroadCastTeams remoteTeams) {
        teams.addAll(remoteTeams.getData().stream()
                .map(this::toTeam)
                .collect(toList()));
        playersInPlayingOrder = remoteTeams.getData().stream()
                .flatMap(remoteTeam -> remoteTeam.getPlayers().stream())
                .sorted((remotePlayer1, remotePlayer2) -> compare(remotePlayer1.getId(), remotePlayer2.getId()))
                .map(player -> playerMapper.findPlayerByName(player.getName()))
                .collect(toList());
        logger.debug("Players in playing order: {}", playersInPlayingOrder);
        startingPlayerOrder = createOrder(playersInPlayingOrder);
        playingOrder = createOrder(playersInPlayingOrder);
    }

    public ChooseTrumpf onRequestTrumpf() {
        return new ChooseTrumpf(OBEABE);
    }

    public void onBroadCastTrumpf(BroadCastTrumpf trumpfChoice) {

    }

    public ChooseCard onRequestCard() {
        checkEquals(playingOrder.getCurrentPlayer(), localPlayer, "Order differed between remote and local state");

        final Move move = localPlayer.makeMove(getCurrentRound());
        final RemoteCard cardToPlay = mapToRemoteCard(move.getPlayedCard());

        return new ChooseCard(cardToPlay);
    }

    public void onPlayedCards(PlayedCards playedCards) {
        final int playerPosition = playedCards.getData().size() - 1;
        final RemoteCard remoteCard = playedCards.getData().get(playerPosition);

        final Player player = playingOrder.getCurrentPlayer();
        playingOrder.moveToNextPlayer();

        final Move move = new Move(player, mapToCard(remoteCard));
        this.currentRound.makeMove(move);
    }

    public void onBroadCastStich(BroadCastStich stich) {
        final Player winner = playerMapper.findPlayerByName(stich.getData().getName());
        checkEquals(winner, currentRound.getWinner(), "Local winner differs from remote");

        this.currentRound = Round.createRound(this.currentRound.getRoundNumber() + 1);
        this.playingOrder = createOrderStartingFromPlayer(this.playingOrder.getPlayersInInitialPlayingOrder(), winner);
    }

    public void onBroadGameFinished(BroadCastGameFinished gameFinished) {
        startingPlayerOrder.moveToNextPlayer();
        playingOrder = createOrderStartingFromPlayer(playersInPlayingOrder, startingPlayerOrder.getCurrentPlayer());
    }

    public void onBroadCastWinnerTeam(BroadCastWinnerTeam winnerTeam) {

    }

    public void onRejectCard(RejectCard rejectCard) {
        throw new RuntimeException("Card was rejected");
    }

    private Team toTeam(RemoteTeam remoteTeam) {
        final List<Player> players = remoteTeam.getPlayers().stream().map(playerMapper::mapPlayer).collect(toList());
        return new Team(remoteTeam.getName(), players);
    }

    private static Card mapToCard(RemoteCard remoteCard) {
        return stream(Card.values())
                .filter(card -> card.getColor() == remoteCard.getColor().getMappedColor())
                .filter(card -> card.getRank() == remoteCard.getNumber() - 5)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unable to map card"));
    }

    private static Set<Card> mapAllToCards(List<RemoteCard> remoteCards) {
        return remoteCards.stream().map(RemoteGameHandler::mapToCard).collect(toSet());
    }

    private static RemoteCard mapToRemoteCard(Card card) {
        final RemoteColor remoteColor = stream(RemoteColor.values())
                .filter(color -> color.getMappedColor() == card.getColor())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not map color"));
        return new RemoteCard(card.getRank() + 5, remoteColor);
    }

    private static void checkEquals(Object a, Object b, String errorMessage) {
        if(!a.equals(b)) {
            logger.warn("Expected {} to be equal to {}: {}", a, b, errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

}
package com.zuehlke.jasschallenge.game.mode;

import com.zuehlke.jasschallenge.game.Trumpf;
import com.zuehlke.jasschallenge.game.cards.Color;

public final class Modes {
    public static boolean isModeObeabe(Mode mode) {
        return mode.getTrumpfName().equals(Trumpf.OBEABE);
    }

    public static boolean isModeUndeufe(Mode mode) {
        return mode.getTrumpfName().equals(Trumpf.UNDEUFE);
    }

    public static boolean isModeTrumpf(Mode mode) {
        Trumpf trumpfName = mode.getTrumpfName();
        return trumpfName.equals(Trumpf.SCHIEBE) || trumpfName.equals(Trumpf.TRUMPF);
    }

    public static boolean isClubs(Mode mode) {
        return mode.getTrumpfColor().equals(Color.CLUBS);
    }

    public static boolean isDiamonds(Mode mode) {
        return mode.getTrumpfColor().equals(Color.DIAMONDS);
    }

    public static boolean isSpades(Mode mode) {
        return mode.getTrumpfColor().equals(Color.SPADES);
    }

    public static boolean isHearts(Mode mode) {
        return mode.getTrumpfColor().equals(Color.HEARTS);
    }
}

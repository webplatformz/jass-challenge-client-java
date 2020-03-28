package com.zuehlke.jasschallenge.client.game.strategy.utils.options;

import com.zuehlke.jasschallenge.game.mode.Mode;

public class ModeOption {

    private Mode mode;
    private int totalRank;

    public ModeOption(Mode mode, int totalRank) {
        this.mode = mode;
        this.totalRank = totalRank;
    }

    public Mode getMode() {
        return mode;
    }

    public int getTotalRank() {
        return totalRank;
    }
}

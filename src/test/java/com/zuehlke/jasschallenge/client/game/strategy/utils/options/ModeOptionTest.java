package com.zuehlke.jasschallenge.client.game.strategy.utils.options;

import com.zuehlke.jasschallenge.client.game.strategy.utils.options.ModeOption;
import com.zuehlke.jasschallenge.game.mode.Mode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModeOptionTest {

    ModeOption modeOption;

    @Before
    public void setUp() throws Exception {
        modeOption = new ModeOption(Mode.bottomUp(), 10);
    }

    @Test
    public void getMode() {
        assertEquals(Mode.bottomUp(), modeOption.getMode());
    }

    @Test
    public void getTotalRank() {
        assertEquals(10, modeOption.getTotalRank());
    }
}
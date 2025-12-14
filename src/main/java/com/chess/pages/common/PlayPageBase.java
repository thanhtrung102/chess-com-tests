package com.chess.pages.common;

import org.openqa.selenium.WebDriver;

/**
 * Play page base for Chess.com.
 * Game selection and play options.
 */
public abstract class PlayPageBase extends ChessBasePageBase {

    public PlayPageBase(WebDriver driver) {
        super(driver);
        setPageURL("/play");
    }

    /**
     * Click to play online
     * @return GamePageBase
     */
    public abstract GamePageBase playOnline();

    /**
     * Click to play computer
     * @return GamePageBase
     */
    public abstract GamePageBase playComputer();

    /**
     * Check if play options are displayed
     * @return true if options visible
     */
    public abstract boolean arePlayOptionsDisplayed();
}

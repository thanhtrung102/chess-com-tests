package com.chess.pages.common;

import org.openqa.selenium.WebDriver;

/**
 * Game page base for Chess.com.
 * Active chess game interface.
 */
public abstract class GamePageBase extends ChessBasePageBase {

    public GamePageBase(WebDriver driver) {
        super(driver);
    }

    /**
     * Check if chess board is displayed
     * @return true if board loaded
     */
    public abstract boolean isChessBoardDisplayed();

    /**
     * Check if game is in progress
     * @return true if game active
     */
    public abstract boolean isGameInProgress();

    /**
     * Resign from game
     */
    public abstract void resign();

    /**
     * Offer draw
     */
    public abstract void offerDraw();
}

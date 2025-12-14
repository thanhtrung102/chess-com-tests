package com.chess.pages.common;

import org.openqa.selenium.WebDriver;

/**
 * Puzzles page base for Chess.com.
 * Chess puzzle solving interface.
 */
public abstract class PuzzlesPageBase extends ChessBasePageBase {

    public PuzzlesPageBase(WebDriver driver) {
        super(driver);
        setPageURL("/puzzles");
    }

    /**
     * Start solving puzzles
     */
    public abstract void startPuzzle();

    /**
     * Check if puzzle is displayed
     * @return true if puzzle loaded
     */
    public abstract boolean isPuzzleDisplayed();

    /**
     * Get puzzle rating
     * @return puzzle difficulty rating
     */
    public abstract String getPuzzleRating();
}

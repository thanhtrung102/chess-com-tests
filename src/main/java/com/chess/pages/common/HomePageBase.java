package com.chess.pages.common;

import com.chess.components.NavigationBar;
import org.openqa.selenium.WebDriver;

/**
 * Home page base interface for Chess.com.
 * Defines contract for home page functionality.
 */
public abstract class HomePageBase extends ChessBasePageBase {

    public HomePageBase(WebDriver driver) {
        super(driver);
        setPageURL("/");
    }

    /**
     * Get navigation bar component
     * @return NavigationBar component
     */
    public abstract NavigationBar getNavigationBar();

    /**
     * Click Play button to start a game
     * @return PlayPageBase
     */
    public abstract PlayPageBase clickPlay();

    /**
     * Click Learn button
     * @return LearnPageBase
     */
    public abstract LearnPageBase clickLearn();

    /**
     * Click Puzzles button
     * @return PuzzlesPageBase
     */
    public abstract PuzzlesPageBase clickPuzzles();

    /**
     * Check if user is logged in
     * @return true if logged in
     */
    public abstract boolean isLoggedIn();

    /**
     * Get welcome message or user greeting
     * @return welcome text
     */
    public abstract String getWelcomeMessage();
}

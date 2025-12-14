package com.chess.pages.common;

import org.openqa.selenium.WebDriver;

/**
 * Learn page base for Chess.com.
 * Educational content and lessons.
 */
public abstract class LearnPageBase extends ChessBasePageBase {

    public LearnPageBase(WebDriver driver) {
        super(driver);
        setPageURL("/learn");
    }

    /**
     * Check if lessons are displayed
     * @return true if lessons visible
     */
    public abstract boolean areLessonsDisplayed();

    /**
     * Get page title
     * @return page title
     */
    public abstract String getPageTitle();
}

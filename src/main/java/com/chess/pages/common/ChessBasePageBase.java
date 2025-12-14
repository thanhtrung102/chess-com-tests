package com.chess.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

/**
 * Base page for all Chess.com pages.
 * Provides common functionality across the application.
 */
public abstract class ChessBasePageBase extends AbstractPage {

    public ChessBasePageBase(WebDriver driver) {
        super(driver);
    }

    /**
     * Check if page is opened/loaded
     * @return true if page is opened
     */
    public abstract boolean isPageOpened();
}

package com.chess.pages.desktop;

import com.chess.pages.common.PuzzlesPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Puzzles page implementation for Chess.com desktop.
 */
@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = PuzzlesPageBase.class)
public class PuzzlesPage extends PuzzlesPageBase {

    @FindBy(xpath = "//button[contains(., 'Start') or contains(., 'Solve')]")
    private ExtendedWebElement startButton;

    @FindBy(xpath = "//div[contains(@class, 'puzzle-board')] | //div[contains(@class, 'board')]")
    private ExtendedWebElement puzzleBoard;

    @FindBy(xpath = "//div[contains(@class, 'puzzle-rating')] | //span[contains(., 'Rating:')]")
    private ExtendedWebElement puzzleRating;

    public PuzzlesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return puzzleBoard.isElementPresent() || startButton.isElementPresent();
    }

    @Override
    public void startPuzzle() {
        if (startButton.isPresent()) {
            startButton.click();
        }
    }

    @Override
    public boolean isPuzzleDisplayed() {
        return puzzleBoard.isPresent();
    }

    @Override
    public String getPuzzleRating() {
        if (puzzleRating.isPresent()) {
            return puzzleRating.getText();
        }
        return "";
    }
}

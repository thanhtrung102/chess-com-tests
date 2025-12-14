package com.chess.pages.desktop;

import com.chess.pages.common.GamePageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Game page implementation for Chess.com desktop.
 */
@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = GamePageBase.class)
public class GamePage extends GamePageBase {

    @FindBy(xpath = "//div[contains(@class, 'board-layout')] | //chess-board | //div[@id='board']")
    private ExtendedWebElement chessBoard;

    @FindBy(xpath = "//button[contains(., 'Resign')]")
    private ExtendedWebElement resignButton;

    @FindBy(xpath = "//button[contains(., 'Draw')]")
    private ExtendedWebElement drawButton;

    @FindBy(xpath = "//div[contains(@class, 'game-over')] | //div[contains(@class, 'game-result')]")
    private ExtendedWebElement gameOverIndicator;

    public GamePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return chessBoard.isElementPresent();
    }

    @Override
    public boolean isChessBoardDisplayed() {
        return chessBoard.isPresent();
    }

    @Override
    public boolean isGameInProgress() {
        return chessBoard.isPresent() && !gameOverIndicator.isPresent();
    }

    @Override
    public void resign() {
        if (resignButton.isPresent()) {
            resignButton.click();
        }
    }

    @Override
    public void offerDraw() {
        if (drawButton.isPresent()) {
            drawButton.click();
        }
    }
}

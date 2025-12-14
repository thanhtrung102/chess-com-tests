package com.chess.pages.desktop;

import com.chess.pages.common.GamePageBase;
import com.chess.pages.common.PlayPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Play page implementation for Chess.com desktop.
 */
@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = PlayPageBase.class)
public class PlayPage extends PlayPageBase {

    @FindBy(xpath = "//a[contains(@href, '/play/online')]")
    private ExtendedWebElement playOnlineButton;

    @FindBy(xpath = "//a[contains(@href, '/play/computer')]")
    private ExtendedWebElement playComputerButton;

    @FindBy(xpath = "//h1[contains(., 'Play Chess')]")
    private ExtendedWebElement playPageHeader;

    public PlayPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return playPageHeader.isElementPresent() && playOnlineButton.isElementPresent();
    }

    @Override
    public GamePageBase playOnline() {
        playOnlineButton.click();
        return initPage(getDriver(), GamePageBase.class);
    }

    @Override
    public GamePageBase playComputer() {
        playComputerButton.click();
        return initPage(getDriver(), GamePageBase.class);
    }

    @Override
    public boolean arePlayOptionsDisplayed() {
        return playOnlineButton.isPresent();
    }
}

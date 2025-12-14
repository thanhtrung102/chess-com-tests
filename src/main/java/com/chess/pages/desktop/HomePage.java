package com.chess.pages.desktop;

import com.chess.components.NavigationBar;
import com.chess.pages.common.*;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Home page implementation for Chess.com desktop.
 */
@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(xpath = "//a[contains(@href, '/play') or contains(., 'Play')]")
    private ExtendedWebElement playButton;

    @FindBy(xpath = "//a[contains(@href, '/puzzles') or contains(., 'Puzzles')]")
    private ExtendedWebElement puzzlesButton;

    @FindBy(xpath = "//a[contains(@href, '/learn') or contains(., 'Learn')]")
    private ExtendedWebElement learnButton;

    @FindBy(xpath = "//div[contains(@class, 'header')] | //nav")
    private NavigationBar navigationBar;

    @FindBy(xpath = "//h1 | //div[contains(@class, 'home-headline')]")
    private ExtendedWebElement welcomeHeading;

    @FindBy(xpath = "//button[contains(., 'Log In')] | //a[contains(@href, '/login')]")
    private ExtendedWebElement loginLink;

    @FindBy(xpath = "//div[contains(@class, 'user-menu')] | //button[contains(@class, 'user-')]")
    private ExtendedWebElement userMenu;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return playButton.isElementPresent() || welcomeHeading.isElementPresent();
    }

    @Override
    public NavigationBar getNavigationBar() {
        return navigationBar;
    }

    @Override
    public PlayPageBase clickPlay() {
        playButton.click();
        return initPage(getDriver(), PlayPageBase.class);
    }

    @Override
    public LearnPageBase clickLearn() {
        learnButton.click();
        return initPage(getDriver(), LearnPageBase.class);
    }

    @Override
    public PuzzlesPageBase clickPuzzles() {
        puzzlesButton.click();
        return initPage(getDriver(), PuzzlesPageBase.class);
    }

    @Override
    public boolean isLoggedIn() {
        return userMenu.isPresent() && !loginLink.isPresent();
    }

    @Override
    public String getWelcomeMessage() {
        if (welcomeHeading.isPresent()) {
            return welcomeHeading.getText();
        }
        return "";
    }
}

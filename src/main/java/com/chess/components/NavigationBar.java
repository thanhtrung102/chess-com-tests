package com.chess.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Navigation bar component for Chess.com.
 * Contains main navigation links and user menu.
 */
public class NavigationBar extends AbstractUIObject {

    @FindBy(xpath = "//a[contains(@href, '/home') or contains(., 'Home')]")
    private ExtendedWebElement homeLink;

    @FindBy(xpath = "//a[contains(@href, '/play') or contains(., 'Play')]")
    private ExtendedWebElement playLink;

    @FindBy(xpath = "//a[contains(@href, '/puzzles') or contains(., 'Puzzles')]")
    private ExtendedWebElement puzzlesLink;

    @FindBy(xpath = "//a[contains(@href, '/learn') or contains(., 'Learn')]")
    private ExtendedWebElement learnLink;

    @FindBy(xpath = "//a[contains(@href, '/watch') or contains(., 'Watch')]")
    private ExtendedWebElement watchLink;

    @FindBy(xpath = "//a[contains(@href, '/news') or contains(., 'News')]")
    private ExtendedWebElement newsLink;

    @FindBy(xpath = "//button[contains(., 'Log In')] | //a[contains(@href, '/login')]")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//button[contains(., 'Sign Up')] | //a[contains(@href, '/register')]")
    private ExtendedWebElement signUpButton;

    @FindBy(xpath = "//div[contains(@class, 'user-menu')] | //button[contains(@class, 'user-')]")
    private ExtendedWebElement userMenu;

    public NavigationBar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    /**
     * Click Home link
     */
    public void clickHome() {
        homeLink.click();
    }

    /**
     * Click Play link
     */
    public void clickPlay() {
        playLink.click();
    }

    /**
     * Click Puzzles link
     */
    public void clickPuzzles() {
        puzzlesLink.click();
    }

    /**
     * Click Learn link
     */
    public void clickLearn() {
        learnLink.click();
    }

    /**
     * Click Watch link
     */
    public void clickWatch() {
        watchLink.click();
    }

    /**
     * Click News link
     */
    public void clickNews() {
        newsLink.click();
    }

    /**
     * Click Login button
     */
    public void clickLogin() {
        if (loginButton.isPresent()) {
            loginButton.click();
        }
    }

    /**
     * Click Sign Up button
     */
    public void clickSignUp() {
        if (signUpButton.isPresent()) {
            signUpButton.click();
        }
    }

    /**
     * Check if user is logged in
     * @return true if user menu visible
     */
    public boolean isLoggedIn() {
        return userMenu.isPresent();
    }

    /**
     * Check if login button is visible
     * @return true if login button present
     */
    public boolean isLoginButtonVisible() {
        return loginButton.isPresent();
    }
}

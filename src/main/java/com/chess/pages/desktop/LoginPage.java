package com.chess.pages.desktop;

import com.chess.pages.common.HomePageBase;
import com.chess.pages.common.LoginPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Login page implementation for Chess.com desktop.
 */
@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {

    @FindBy(xpath = "//input[@id='username' or @name='username' or @type='email' or @placeholder='Username or Email']")
    private ExtendedWebElement usernameInput;

    @FindBy(xpath = "//input[@id='password' or @name='password' or @type='password']")
    private ExtendedWebElement passwordInput;

    @FindBy(xpath = "//button[@id='login' or contains(., 'Log In') or @type='submit']")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//div[contains(@class, 'error')] | //span[contains(@class, 'error')] | //div[contains(@class, 'alert')]")
    private ExtendedWebElement errorMessage;

    @FindBy(xpath = "//h1[contains(., 'Log In')] | //h2[contains(., 'Sign In')]")
    private ExtendedWebElement pageHeading;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return loginButton.isElementPresent() && usernameInput.isElementPresent();
    }

    @Override
    public void typeUsername(String username) {
        usernameInput.type(username);
    }

    @Override
    public void typePassword(String password) {
        passwordInput.type(password);
    }

    @Override
    public HomePageBase clickLoginButton() {
        loginButton.click();
        return initPage(getDriver(), HomePageBase.class);
    }

    @Override
    public boolean isErrorDisplayed() {
        return errorMessage.isPresent();
    }

    @Override
    public String getErrorMessage() {
        if (errorMessage.isPresent()) {
            return errorMessage.getText();
        }
        return "";
    }

    @Override
    public boolean isLoginSuccessful() {
        pause(2); // Wait for redirect
        return !isPageOpened();
    }
}

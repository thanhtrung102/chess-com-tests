package com.chess.pages.common;

import org.openqa.selenium.WebDriver;

/**
 * Login page base for Chess.com.
 * Handles user authentication.
 */
public abstract class LoginPageBase extends ChessBasePageBase {

    public LoginPageBase(WebDriver driver) {
        super(driver);
        setPageURL("/login");
    }

    /**
     * Type username/email
     * @param username username or email
     */
    public abstract void typeUsername(String username);

    /**
     * Type password
     * @param password user password
     */
    public abstract void typePassword(String password);

    /**
     * Click login button
     * @return HomePageBase after successful login
     */
    public abstract HomePageBase clickLoginButton();

    /**
     * Perform complete login
     * @param username username or email
     * @param password password
     * @return HomePageBase
     */
    public HomePageBase login(String username, String password) {
        typeUsername(username);
        typePassword(password);
        return clickLoginButton();
    }

    /**
     * Check if error message is displayed
     * @return true if error present
     */
    public abstract boolean isErrorDisplayed();

    /**
     * Get error message text
     * @return error message
     */
    public abstract String getErrorMessage();

    /**
     * Check if login was successful
     * @return true if login successful
     */
    public abstract boolean isLoginSuccessful();
}

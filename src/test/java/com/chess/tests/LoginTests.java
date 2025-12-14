package com.chess.tests;

import com.chess.pages.common.HomePageBase;
import com.chess.pages.common.LoginPageBase;
import com.chess.utils.TestDataHelper;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Login test cases for Chess.com.
 * Tests user authentication functionality.
 *
 * NOTE: These tests require a valid Chess.com account.
 * Update credentials in _config.properties before running.
 */
public class LoginTests implements IAbstractTest {

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"authentication", "smoke"})
    public void testLoginPageAccessibility() {
        // Open login page
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        loginPage.open();

        // Verify page loaded
        Assert.assertTrue(loginPage.isPageOpened(), "Login page should be loaded");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"authentication", "negative"})
    public void testLoginWithInvalidCredentials() {
        // Open login page
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page should be opened");

        // Attempt login with invalid credentials
        loginPage.login("invalid_user_12345", "wrongpassword");
        pause(3); // Wait for error message

        // Verify error is displayed
        Assert.assertTrue(loginPage.isErrorDisplayed(),
            "Error message should be displayed for invalid credentials");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"authentication", "negative"})
    public void testLoginWithEmptyUsername() {
        // Open login page
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        loginPage.open();

        // Attempt login with empty username
        loginPage.typeUsername("");
        loginPage.typePassword("somepassword");
        loginPage.clickLoginButton();
        pause(2);

        // Should remain on login page
        Assert.assertTrue(loginPage.isPageOpened(),
            "Should remain on login page with empty username");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"authentication", "negative"})
    public void testLoginWithEmptyPassword() {
        // Open login page
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        loginPage.open();

        // Attempt login with empty password
        loginPage.typeUsername("testuser");
        loginPage.typePassword("");
        loginPage.clickLoginButton();
        pause(2);

        // Should remain on login page
        Assert.assertTrue(loginPage.isPageOpened(),
            "Should remain on login page with empty password");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"authentication", "smoke"})
    public void testSuccessfulLogin() {
        // Open login page
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page should be opened");

        // Perform login with Gmail credentials
        String username = TestDataHelper.getTestUsername();
        String password = TestDataHelper.getTestPassword();

        System.out.println("Attempting login with username: " + username);

        loginPage.login(username, password);
        pause(10); // Wait longer for login to complete and redirect

        // Get current URL to see where we landed
        String currentUrl = getDriver().getCurrentUrl();
        String pageTitle = getDriver().getTitle();
        System.out.println("After login - URL: " + currentUrl);
        System.out.println("After login - Title: " + pageTitle);

        // Verify we're no longer on login page (basic success check)
        Assert.assertTrue(!currentUrl.contains("/login"),
            "Should navigate away from login page after successful login. Current URL: " + currentUrl);
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"authentication", "regression"})
    public void testLoginPageElements() {
        // Open login page
        LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
        loginPage.open();

        SoftAssert softAssert = new SoftAssert();

        // Verify page is opened
        softAssert.assertTrue(loginPage.isPageOpened(),
            "Login page should be accessible");

        softAssert.assertAll();
    }
}

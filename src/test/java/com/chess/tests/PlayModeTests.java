package com.chess.tests;

import com.chess.pages.common.HomePageBase;
import com.chess.pages.common.PlayPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Play mode test cases for Chess.com.
 * Tests different play options and game modes.
 */
public class PlayModeTests implements IAbstractTest {

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"play", "regression"})
    public void testPlayPageAccessibility() {
        // Navigate directly to Play page
        PlayPageBase playPage = initPage(getDriver(), PlayPageBase.class);
        playPage.open();
        pause(2);

        // Verify page loaded
        Assert.assertTrue(playPage.isPageOpened(),
            "Play page should be accessible");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"play", "regression"})
    public void testPlayOptionsAreVisible() {
        // Navigate to Play page
        PlayPageBase playPage = initPage(getDriver(), PlayPageBase.class);
        playPage.open();
        pause(2);

        Assert.assertTrue(playPage.isPageOpened(), "Play page should be opened");

        // Verify play options are displayed
        Assert.assertTrue(playPage.arePlayOptionsDisplayed(),
            "Play options (Online, Computer, etc.) should be visible");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"play", "navigation"})
    public void testNavigationFromHomeToPlay() {
        // Start from home page
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be loaded");

        // Click Play navigation
        PlayPageBase playPage = homePage.clickPlay();
        pause(2);

        // Verify redirected to Play page
        Assert.assertTrue(playPage.isPageOpened(),
            "Should navigate to Play page from home");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"play", "url"})
    public void testPlayPageUrl() {
        // Navigate to Play page
        PlayPageBase playPage = initPage(getDriver(), PlayPageBase.class);
        playPage.open();
        pause(2);

        // Verify URL contains 'play'
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/play"),
            "URL should contain '/play'. Actual: " + currentUrl);
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"play", "title"})
    public void testPlayPageTitle() {
        // Navigate to Play page
        PlayPageBase playPage = initPage(getDriver(), PlayPageBase.class);
        playPage.open();
        pause(2);

        // Verify page title
        String pageTitle = getDriver().getTitle();
        Assert.assertTrue(pageTitle.toLowerCase().contains("play"),
            "Page title should contain 'play'. Actual: " + pageTitle);
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"play", "regression"})
    public void testPlayPageRefresh() {
        // Navigate to Play page
        PlayPageBase playPage = initPage(getDriver(), PlayPageBase.class);
        playPage.open();
        pause(2);
        Assert.assertTrue(playPage.isPageOpened(), "Play page should be loaded initially");

        // Refresh the page
        getDriver().navigate().refresh();
        pause(2);

        // Verify page still loaded after refresh
        Assert.assertTrue(playPage.isPageOpened(),
            "Play page should remain loaded after refresh");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"play", "back-navigation"})
    public void testBackNavigationFromPlay() {
        // Navigate from Home to Play
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be loaded");

        PlayPageBase playPage = homePage.clickPlay();
        pause(2);
        Assert.assertTrue(playPage.isPageOpened(), "Play page should be loaded");

        // Navigate back
        getDriver().navigate().back();
        pause(2);

        // Verify back on home page
        Assert.assertTrue(homePage.isPageOpened(),
            "Should navigate back to home page");
    }
}

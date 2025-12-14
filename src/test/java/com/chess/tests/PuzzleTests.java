package com.chess.tests;

import com.chess.pages.common.HomePageBase;
import com.chess.pages.common.PuzzlesPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Puzzle test cases for Chess.com.
 * Tests puzzle functionality and features.
 */
public class PuzzleTests implements IAbstractTest {

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"puzzles", "regression"})
    public void testPuzzlesPageAccessibility() {
        // Navigate directly to Puzzles page
        PuzzlesPageBase puzzlesPage = initPage(getDriver(), PuzzlesPageBase.class);
        puzzlesPage.open();
        pause(3); // Wait for puzzle to load

        // Verify page loaded
        Assert.assertTrue(puzzlesPage.isPageOpened(),
            "Puzzles page should be accessible");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"puzzles", "regression"})
    public void testPuzzleBoardIsDisplayed() {
        // Navigate to Puzzles page
        PuzzlesPageBase puzzlesPage = initPage(getDriver(), PuzzlesPageBase.class);
        puzzlesPage.open();
        pause(3);

        Assert.assertTrue(puzzlesPage.isPageOpened(), "Puzzles page should be opened");

        // Verify puzzle board is displayed
        Assert.assertTrue(puzzlesPage.isPuzzleDisplayed(),
            "Puzzle board should be displayed");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"puzzles", "navigation"})
    public void testNavigationFromHomeToPuzzles() {
        // Start from home page
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be loaded");

        // Click Puzzles navigation
        PuzzlesPageBase puzzlesPage = homePage.clickPuzzles();
        pause(3);

        // Verify redirected to Puzzles page
        Assert.assertTrue(puzzlesPage.isPageOpened(),
            "Should navigate to Puzzles page from home");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"puzzles", "url"})
    public void testPuzzlesPageUrl() {
        // Navigate to Puzzles page
        PuzzlesPageBase puzzlesPage = initPage(getDriver(), PuzzlesPageBase.class);
        puzzlesPage.open();
        pause(3);

        // Verify URL contains 'puzzles'
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/puzzles"),
            "URL should contain '/puzzles'. Actual: " + currentUrl);
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"puzzles", "title"})
    public void testPuzzlesPageTitle() {
        // Navigate to Puzzles page
        PuzzlesPageBase puzzlesPage = initPage(getDriver(), PuzzlesPageBase.class);
        puzzlesPage.open();
        pause(3);

        // Verify page title
        String pageTitle = getDriver().getTitle();
        Assert.assertTrue(pageTitle.toLowerCase().contains("puzzle"),
            "Page title should contain 'puzzle'. Actual: " + pageTitle);
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"puzzles", "regression"})
    public void testPuzzlesPageRefresh() {
        // Navigate to Puzzles page
        PuzzlesPageBase puzzlesPage = initPage(getDriver(), PuzzlesPageBase.class);
        puzzlesPage.open();
        pause(3);
        Assert.assertTrue(puzzlesPage.isPageOpened(), "Puzzles page should be loaded initially");

        // Refresh the page
        getDriver().navigate().refresh();
        pause(3);

        // Verify page still loaded after refresh
        Assert.assertTrue(puzzlesPage.isPageOpened(),
            "Puzzles page should remain loaded after refresh");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"puzzles", "back-navigation"})
    public void testBackNavigationFromPuzzles() {
        // Navigate from Home to Puzzles
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be loaded");

        PuzzlesPageBase puzzlesPage = homePage.clickPuzzles();
        pause(3);
        Assert.assertTrue(puzzlesPage.isPageOpened(), "Puzzles page should be loaded");

        // Navigate back
        getDriver().navigate().back();
        pause(2);

        // Verify back on home page
        Assert.assertTrue(homePage.isPageOpened(),
            "Should navigate back to home page");
    }
}

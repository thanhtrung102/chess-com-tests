package com.chess.tests;

import com.chess.pages.common.*;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Navigation test cases for Chess.com.
 * Tests main navigation flow and page accessibility.
 */
public class NavigationTests implements IAbstractTest {

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"navigation", "smoke"})
    public void testHomePageLoads() {
        // Open home page
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();

        // Verify page loaded
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be loaded");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"navigation", "smoke"})
    public void testNavigationToPlay() {
        // Open home page
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be opened");

        // Navigate to Play page
        PlayPageBase playPage = homePage.clickPlay();
        pause(2); // Wait for page load

        // Verify Play page loaded
        Assert.assertTrue(playPage.isPageOpened(), "Play page should be opened");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"navigation", "smoke"})
    public void testNavigationToPuzzles() {
        // Open home page
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be opened");

        // Navigate to Puzzles page
        PuzzlesPageBase puzzlesPage = homePage.clickPuzzles();
        pause(2);

        // Verify Puzzles page loaded
        Assert.assertTrue(puzzlesPage.isPageOpened(), "Puzzles page should be opened");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"navigation", "smoke"})
    public void testNavigationToLearn() {
        // Open home page
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be opened");

        // Navigate to Learn page
        LearnPageBase learnPage = homePage.clickLearn();
        pause(2);

        // Verify Learn page loaded
        Assert.assertTrue(learnPage.isPageOpened(), "Learn page should be opened");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"navigation", "regression"})
    public void testNavigationBarPresence() {
        // Open home page
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();

        SoftAssert softAssert = new SoftAssert();

        // Verify navigation bar components
        softAssert.assertNotNull(homePage.getNavigationBar(),
            "Navigation bar should be present");

        softAssert.assertAll();
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"navigation", "regression"})
    public void testPlayPageOptions() {
        // Navigate to Play page
        PlayPageBase playPage = initPage(getDriver(), PlayPageBase.class);
        playPage.open();
        pause(2);

        Assert.assertTrue(playPage.isPageOpened(), "Play page should be opened");

        // Verify play options are displayed
        Assert.assertTrue(playPage.arePlayOptionsDisplayed(),
            "Play options should be displayed");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"puzzles", "regression"})
    public void testPuzzlePageElements() {
        // Navigate to Puzzles page
        PuzzlesPageBase puzzlesPage = initPage(getDriver(), PuzzlesPageBase.class);
        puzzlesPage.open();
        pause(3); // Wait for puzzle to load

        Assert.assertTrue(puzzlesPage.isPageOpened(), "Puzzles page should be opened");

        // Verify puzzle is displayed
        Assert.assertTrue(puzzlesPage.isPuzzleDisplayed(),
            "Puzzle board should be displayed");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"learn", "regression"})
    public void testLearnPageContent() {
        // Navigate to Learn page
        LearnPageBase learnPage = initPage(getDriver(), LearnPageBase.class);
        learnPage.open();
        pause(2);

        Assert.assertTrue(learnPage.isPageOpened(), "Learn page should be opened");

        // Verify lessons are displayed
        Assert.assertTrue(learnPage.areLessonsDisplayed(),
            "Lessons should be displayed");
    }
}

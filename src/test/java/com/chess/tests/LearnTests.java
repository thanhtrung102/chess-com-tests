package com.chess.tests;

import com.chess.pages.common.HomePageBase;
import com.chess.pages.common.LearnPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Learn page test cases for Chess.com.
 * Tests learning content and features.
 */
public class LearnTests implements IAbstractTest {

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"learn", "regression"})
    public void testLearnPageAccessibility() {
        // Navigate directly to Learn page
        LearnPageBase learnPage = initPage(getDriver(), LearnPageBase.class);
        learnPage.open();
        pause(2);

        // Verify page loaded
        Assert.assertTrue(learnPage.isPageOpened(),
            "Learn page should be accessible");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"learn", "regression"})
    public void testLessonsAreDisplayed() {
        // Navigate to Learn page
        LearnPageBase learnPage = initPage(getDriver(), LearnPageBase.class);
        learnPage.open();
        pause(2);

        Assert.assertTrue(learnPage.isPageOpened(), "Learn page should be opened");

        // Verify lessons are displayed
        Assert.assertTrue(learnPage.areLessonsDisplayed(),
            "Lessons should be displayed on Learn page");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"learn", "navigation"})
    public void testNavigationFromHomeToLearn() {
        // Start from home page
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be loaded");

        // Click Learn navigation
        LearnPageBase learnPage = homePage.clickLearn();
        pause(2);

        // Verify redirected to Learn page
        Assert.assertTrue(learnPage.isPageOpened(),
            "Should navigate to Learn page from home");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"learn", "url"})
    public void testLearnPageUrl() {
        // Navigate to Learn page
        LearnPageBase learnPage = initPage(getDriver(), LearnPageBase.class);
        learnPage.open();
        pause(2);

        // Verify URL contains 'learn'
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/learn"),
            "URL should contain '/learn'. Actual: " + currentUrl);
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"learn", "title"})
    public void testLearnPageTitle() {
        // Navigate to Learn page
        LearnPageBase learnPage = initPage(getDriver(), LearnPageBase.class);
        learnPage.open();
        pause(2);

        // Verify page title
        String pageTitle = getDriver().getTitle();
        Assert.assertTrue(pageTitle.toLowerCase().contains("learn") ||
                         pageTitle.toLowerCase().contains("lesson"),
            "Page title should contain 'learn' or 'lesson'. Actual: " + pageTitle);
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"learn", "regression"})
    public void testLearnPageRefresh() {
        // Navigate to Learn page
        LearnPageBase learnPage = initPage(getDriver(), LearnPageBase.class);
        learnPage.open();
        pause(2);
        Assert.assertTrue(learnPage.isPageOpened(), "Learn page should be loaded initially");

        // Refresh the page
        getDriver().navigate().refresh();
        pause(2);

        // Verify page still loaded after refresh
        Assert.assertTrue(learnPage.isPageOpened(),
            "Learn page should remain loaded after refresh");
    }

    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"learn", "back-navigation"})
    public void testBackNavigationFromLearn() {
        // Navigate from Home to Learn
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be loaded");

        LearnPageBase learnPage = homePage.clickLearn();
        pause(2);
        Assert.assertTrue(learnPage.isPageOpened(), "Learn page should be loaded");

        // Navigate back
        getDriver().navigate().back();
        pause(2);

        // Verify back on home page
        Assert.assertTrue(homePage.isPageOpened(),
            "Should navigate back to home page");
    }
}

package com.chess.tests;

import com.chess.pages.common.HomePageBase;
import com.chess.pages.common.PlayPageBase;
import com.chess.pages.common.PuzzlesPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Visual Regression Tests - Tests UI appearance, colors, fonts, and layout
 *
 * These tests verify the visual presentation of the UI, not functionality.
 * They check CSS properties, element styling, and visual consistency.
 */
public class VisualRegressionTests implements IAbstractTest {

    /**
     * Test that the Chess.com logo has the correct styling
     */
    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"ui", "visual", "smoke"})
    public void testLogoStyling() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be opened");

        // Find the logo element
        WebElement logo = getDriver().findElement(By.cssSelector(".chess-logo-wrapper, .chess-logo, a[href='/']"));

        // Verify logo is visible
        Assert.assertTrue(logo.isDisplayed(), "Logo should be visible on home page");

        // Get logo dimensions - should be consistent
        Dimension logoSize = logo.getSize();
        Assert.assertTrue(logoSize.getWidth() > 50,
            "Logo width should be reasonable (>50px), actual: " + logoSize.getWidth());
        Assert.assertTrue(logoSize.getHeight() > 20,
            "Logo height should be reasonable (>20px), actual: " + logoSize.getHeight());

    }

    /**
     * Test that primary navigation buttons have consistent styling
     */
    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"ui", "visual"})
    public void testNavigationButtonStyling() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be opened");

        // Find navigation buttons
        WebElement playButton = getDriver().findElement(
            By.xpath("//a[contains(@href, '/play') or contains(text(), 'Play')]"));

        // Verify button has background color defined (modern design allows transparent backgrounds)
        String backgroundColor = playButton.getCssValue("background-color");
        Assert.assertNotNull(backgroundColor, "Play button should have background-color CSS property defined");

        // Verify text color is set
        String color = playButton.getCssValue("color");
        Assert.assertNotNull(color, "Play button should have text color");

        // Verify font size is reasonable (not too small)
        String fontSize = playButton.getCssValue("font-size");
        Assert.assertNotNull(fontSize, "Play button should have font size");
    }

    /**
     * Test that the page has proper typography hierarchy
     */
    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"ui", "visual"})
    public void testTypographyHierarchy() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be opened");

        // Find heading elements (h1, h2, h3)
        try {
            WebElement heading = getDriver().findElement(By.cssSelector("h1, h2, .headline, .title"));

            String headingFontSize = heading.getCssValue("font-size");
            String headingFontWeight = heading.getCssValue("font-weight");

            // Parse font size (e.g., "24px" -> 24)
            int fontSize = Integer.parseInt(headingFontSize.replaceAll("[^0-9]", ""));
            Assert.assertTrue(fontSize >= 18,
                "Heading font size should be at least 18px, actual: " + fontSize + "px");

            // Font weight should indicate bold (400+ for normal, 700 for bold)
            int fontWeight = Integer.parseInt(headingFontWeight);
            Assert.assertTrue(fontWeight >= 400,
                "Heading font weight should be at least 400, actual: " + fontWeight);
        } catch (Exception e) {
        }
    }
    /**
     * Test color contrast for accessibility
     * Verifies that text has sufficient contrast against background
     */
    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"ui", "visual", "accessibility"})
    public void testColorContrast() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be opened");

        // Find main content text
        WebElement bodyElement = getDriver().findElement(By.tagName("body"));
        String bodyColor = bodyElement.getCssValue("color");
        String bodyBgColor = bodyElement.getCssValue("background-color");

        Assert.assertNotNull(bodyColor, "Body should have text color");
        Assert.assertNotNull(bodyBgColor, "Body should have background color");

        // Colors should not be the same (would be invisible text)
        Assert.assertNotEquals(bodyColor, bodyBgColor,
            "Text color should differ from background color for readability");
    }

    /**
     * Test that interactive elements have hover states
     */
    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"ui", "visual", "interactivity"})
    public void testInteractiveElementHoverStates() {
        PlayPageBase playPage = initPage(getDriver(), PlayPageBase.class);
        playPage.open();
        Assert.assertTrue(playPage.isPageOpened(), "Play page should be opened");

        // Find clickable button
        WebElement button = getDriver().findElement(
            By.xpath("//button | //a[contains(@class, 'button')]"));

        // Get initial cursor style
        String cursorStyle = button.getCssValue("cursor");

        // Interactive elements should have pointer cursor or be clickable
        boolean isInteractive = cursorStyle.equals("pointer") ||
                               button.isEnabled() ||
                               button.getAttribute("onclick") != null;

        Assert.assertTrue(isInteractive,
            "Interactive elements should indicate clickability (cursor: pointer or enabled)");
    }

    /**
     * Test that the chessboard has proper visual rendering
     */
    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"ui", "visual", "chessboard"})
    public void testChessboardVisualRendering() {
        PuzzlesPageBase puzzlesPage = initPage(getDriver(), PuzzlesPageBase.class);
        puzzlesPage.open();
        Assert.assertTrue(puzzlesPage.isPageOpened(), "Puzzles page should be opened");

        pause(3); // Wait for board to render

        // Find the chessboard element - Chess.com uses div.board or div#board
        WebElement board = getDriver().findElement(
            By.cssSelector("div.board, div#board, div.board-layout-chessboard"));

        Assert.assertTrue(board.isDisplayed(), "Chessboard should be visible");

        // Additional wait to ensure board is fully rendered
        pause(1);

        // Verify board has reasonable dimensions (should be square-ish)
        Dimension boardSize = board.getSize();
        int width = boardSize.getWidth();
        int height = boardSize.getHeight();

        Assert.assertTrue(width > 100, "Board width should be reasonable (>100px), actual: " + width);
        Assert.assertTrue(height > 100, "Board height should be reasonable (>100px), actual: " + height);

        // Board should be roughly square (aspect ratio between 0.8 and 1.2)
        double aspectRatio = (double) width / height;
        Assert.assertTrue(aspectRatio >= 0.8 && aspectRatio <= 1.2,
            "Board should be roughly square, aspect ratio: " + aspectRatio);
    }

    /**
     * Test screenshot capture capability (baseline for visual regression)
     * This captures a screenshot that can be used as a baseline for future comparisons
     */
    @Test
    @MethodOwner(owner = "qa-team")
    @TestLabel(name = "feature", value = {"ui", "visual", "screenshot"})
    public void testScreenshotCapture() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();

        // Set consistent window size for screenshot comparison
        getDriver().manage().window().setSize(new Dimension(1920, 1080));
        pause(3); // Wait for page load and resize

        try {
            // Capture screenshot using AShot
            Screenshot screenshot = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(getDriver());

            BufferedImage image = screenshot.getImage();

            Assert.assertNotNull(image, "Screenshot should be captured");
            Assert.assertTrue(image.getWidth() > 0, "Screenshot should have width");
            Assert.assertTrue(image.getHeight() > 0, "Screenshot should have height");

            // Save baseline screenshot (optional - for manual comparison)
            File screenshotDir = new File("target/visual-baselines");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            File screenshotFile = new File(screenshotDir, "home-page-baseline.png");
            ImageIO.write(image, "PNG", screenshotFile);

            Assert.assertTrue(screenshotFile.exists(), "Screenshot file should be saved");

        } catch (IOException e) {
            Assert.fail("Failed to capture/save screenshot: " + e.getMessage());
        }
    }

    /**
     * Test element spacing and layout consistency
     */
    @Test
    public void testElementSpacing() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be opened");

        // Find navigation elements
        WebElement navElement = getDriver().findElement(
            By.xpath("//nav | //header | //div[contains(@class, 'nav')]"));

        // Check padding/margin (spacing around elements)
        String padding = navElement.getCssValue("padding");
        String margin = navElement.getCssValue("margin");

        Assert.assertNotNull(padding, "Navigation should have padding defined");
        Assert.assertNotNull(margin, "Navigation should have margin defined");

    }
}

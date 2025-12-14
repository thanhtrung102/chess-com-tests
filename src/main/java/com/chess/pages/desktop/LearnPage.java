package com.chess.pages.desktop;

import com.chess.pages.common.LearnPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Learn page implementation for Chess.com desktop.
 */
@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = LearnPageBase.class)
public class LearnPage extends LearnPageBase {

    @FindBy(xpath = "//div[contains(@class, 'lessons')] | //div[contains(@class, 'course')]")
    private ExtendedWebElement lessonsContainer;

    @FindBy(xpath = "//h1 | //h2")
    private ExtendedWebElement pageTitle;

    public LearnPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return lessonsContainer.isElementPresent() || pageTitle.isElementPresent();
    }

    @Override
    public boolean areLessonsDisplayed() {
        return lessonsContainer.isPresent();
    }

    @Override
    public String getPageTitle() {
        if (pageTitle.isPresent()) {
            return pageTitle.getText();
        }
        return "";
    }
}

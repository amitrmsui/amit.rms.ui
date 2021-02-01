package com.rms.ui.pages;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.rms.ui.base.BasePage;
import com.rms.ui.utils.JSUtil;
import com.rms.ui.utils.Wait;

public class CarsPage extends BasePage{
	
	
	@FindBy(xpath="//a[contains(text(),'Search for cars')]")
	private WebElement searchForCars;
	
	@FindBy(id="carClass-economy")
	private WebElement economy;
	
	@FindBy(xpath="//div[@id='search-results']//div[@id='car-offer-cards-listing-0']//a[@role='button']/span[text()='Select']")
	private WebElement carSelect;

	public CarsPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickEconomy() throws InterruptedException {

		JSUtil.jsClick(driver, economy);
	}
	
	public void selectCar() {
		JSUtil.jsClick(driver, carSelect);
	}

	
	public String bookCar() throws InterruptedException {
		this.checkTabs();
		Thread.sleep(15000);
		this.clickEconomy();
		this.selectCar();
		return driver.getTitle();
	}
	
	public void checkTabs() {
		String oldTab = driver.getWindowHandle();
	    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	    newTab.remove(oldTab);
	    // change focus to new tab
	    driver.switchTo().window(newTab.get(0));
	}

}

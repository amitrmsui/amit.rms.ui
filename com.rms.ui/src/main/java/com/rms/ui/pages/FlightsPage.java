package com.rms.ui.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.rms.ui.base.BasePage;
import com.rms.ui.utils.JSUtil;
import com.rms.ui.utils.JavaDateAdd;
import com.rms.ui.utils.Wait;

public class FlightsPage extends BasePage{
	
			
	@FindBy(xpath = "//span[contains(text(),'Flights')]")
	private WebElement flights;
	
	@FindBy(xpath = "//span[contains(text(),'Roundtrip')]")
	private WebElement roundtrip;
	
	//@FindBy(xpath = "//span[contains(text(),'Leaving from')]")
	//div[@id='location-field-leg1-origin-menu']/div[1]/span
	@FindBy(xpath = "//div[@id='location-field-leg1-origin-menu']/div[1]/button")
	//@FindBy(css="#location-field-leg1-origin-menu")
	private WebElement leavingFrom;
	
	@FindBy(xpath = "//div[@id='location-field-leg1-destination-menu']/div[1]/button")
	private WebElement goingTo;
	
	@FindBy(xpath="//span[contains(text(),'Departing')]")
	private WebElement departingFrom;
	
	@FindBy(xpath="//span[contains(text(),'Returning')]")
	private WebElement returning;
	
	@FindBy(xpath="//button[@data-testid='submit-button' and @type='submit']")
	private WebElement search;
	
	@FindBy(xpath="//button[@id='d1-btn']")
	private WebElement departingDate;
	
	@FindBy(xpath="//button[contains(text(),'Feb 20')]")
	private WebElement returningDate;
	
	@FindBy(xpath="//span[contains(text(),'Done')]")
	private WebElement done;
	
	@FindBy(xpath="//li[@class='uitk-typeahead-result-item has-subtext']")
	private WebElement lists;
	
	@FindBy(xpath="//li[@data-stid='location-field-leg1-destination-result-item' and @data-index='0']")
	private WebElement LA;
	
	@FindBy(xpath="//button[@aria-label='Jan 31, 2021.']")
	private WebElement selectedDepartingDate;
	
	@FindBy(xpath="//button[@data-stid='date-picker-paging' and @xpath='1']")
	private WebElement firstDatePicker;
	
	@FindBy(xpath="//button[@data-stid='apply-date-picker']/span")
	private WebElement applyDatepicker;
	
	@FindBy(css="#stops-0")
	private WebElement nonstop;
	
	//@FindBy(xpath="//button[@data-test-id='select-link' and @class='uitk-card-link' and @xpath='1']/parent::*")
	@FindBy(xpath="//ol[@id='flightModuleList']/li[1]/div/div/div/button")
	private WebElement selectedFlight;
	
	@FindBy(xpath="//button[contains(text(),'Continue')]")
	private WebElement continueBtn;
	
	@FindBy(xpath="//div[@class='uitk-price-lockup uitk-flex-item left-align']/section/span[1]")
	private WebElement price;
	
	@FindBy(xpath="//a[contains(text(),'Search for cars')]")
	private WebElement searchForCars;
	
	@FindBy(id="carClass-economy")
	private WebElement economy;
	
	@FindBy(xpath="//div[@id='search-results']//div[@id='car-offer-cards-listing-0']//a[@role='button']/span[text()='Select']")
	private WebElement carSelect;
	
	JavaDateAdd dates = new JavaDateAdd();
	
	public FlightsPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickFlights() {
		flights.click();
	}
	
	public void clickRoundtrip() {
		roundtrip.click();
	}
	
	public void clickNonstop() {
		nonstop.click();
	}
	
	public void clickSearch() {
		JSUtil.jsClick(driver, search);
	}
	
	public void clickEconomy() {
		JSUtil.jsClick(driver, economy);
	}
	
	public void selectCar() {
		JSUtil.jsClick(driver, carSelect);
	}

	public void clickLeavingFrom() {
		JSUtil.jsClick(driver, leavingFrom);
	}
	
	public void clickGoingTo() {
		JSUtil.jsClick(driver, goingTo);
	}
	
	
	public void typeSourceAirport(String s) {
		clickLeavingFrom();
		leavingFrom.sendKeys(s);
	}
	
	public void typeDestnAirport(String s) throws InterruptedException {
		clickGoingTo();
		goingTo.sendKeys(s);
		Thread.sleep(3000);
		LA.click();
	}
	
	public void selectAirport(String airport) throws InterruptedException {
		List<WebElement> list = driver.findElements(By.xpath("//li[@class='uitk-typeahead-result-item has-subtext' and @data-index='0']")); 
		System.out.println("list size: "+list.size()+" airport: "+airport);
		Iterator<WebElement> airportIterator = list.iterator();
		Thread.sleep(3000);
		while(airportIterator.hasNext()) {
			WebElement airportList = airportIterator.next();
			if(airportList.getText().contains(airport)) {
				airportList.click();
				Thread.sleep(5000);
			}
		}
	}
	
	public void selectDate(String date) {
		JSUtil.jsClick(driver, driver.findElement(By.xpath("//button[@aria-label='"+date+"']")));
	}
	
	public void checkPrice() {
		if(Integer.parseInt(price.getText().substring(1,3))<300)
			JSUtil.jsClick(driver, continueBtn);
	}
	
	public void checkTabs() {
		String oldTab = driver.getWindowHandle();
		System.out.println("oldTab: "+oldTab);
	    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	    System.out.println("total tabs: "+newTab.size());
	    newTab.remove(oldTab);
	    // change focus to new tab
	    driver.switchTo().window(newTab.get(0));
	    System.out.println("after removing old tab - tabs size: "+newTab.size());
	}
	
	public void selectFlight() throws InterruptedException {
		clickNonstop();
		JSUtil.jsClick(driver, selectedFlight);
		Thread.sleep(2000);
		checkPrice();
		clickNonstop();
		Thread.sleep(2000);
		JSUtil.jsClick(driver, selectedFlight);
		JSUtil.jsClick(driver, continueBtn);
		JSUtil.jsClick(driver, searchForCars);
	}
	
	public void clickDeparting(){

		JSUtil.jsClick(driver, departingDate);
		selectDate(dates.getTomorrow());
		selectDate(dates.getNextWeek());
		applyDatepicker.click();
		clickSearch();

	}
	
	public void bookCar() throws InterruptedException {
		this.checkTabs();
		Thread.sleep(15000);
		this.clickEconomy();
		this.selectCar();
	}
	
	public void flightItinerary(String source, String destn) throws InterruptedException {
		this.clickFlights();
		this.clickRoundtrip();
		this.typeSourceAirport(source);
		this.selectAirport(source);
		this.typeDestnAirport(destn);
		this.clickDeparting();
		this.selectFlight();

	}


}

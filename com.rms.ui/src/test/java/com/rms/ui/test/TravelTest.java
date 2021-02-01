package com.rms.ui.test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rms.ui.pages.CarsPage;
import com.rms.ui.pages.FlightsPage;


public class TravelTest {
	protected WebDriver driver;
	FlightsPage flightsPage;
	CarsPage carsPage;
	protected static Logger log = Logger.getLogger(TravelTest.class.getName());
	
	@BeforeClass
	public void init() {
		log.info("launching browser");
        //System.setProperty("webdriver.chrome.driver", "path to driver");
        driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		log.info("opening webpage");
		flightsPage = new FlightsPage(driver);
		carsPage = new CarsPage(driver);
	}
	
	@Test(groups="regression")
	public void bookTravel() throws InterruptedException {
		driver.get("https://www.expedia.com/");
		flightsPage.flightItinerary(" San Francisco", " Los Angeles");
		String paymentPage = carsPage.bookCar();
		Assert.assertEquals(paymentPage, "Expedia: Payment");;
	}
	
	@AfterClass
	public void cleanUp() {
		log.info("closing browser");
		driver.quit();
	}

}

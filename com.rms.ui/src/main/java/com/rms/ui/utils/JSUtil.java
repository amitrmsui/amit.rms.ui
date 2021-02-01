package com.rms.ui.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JSUtil {
	
	public static void jsClick(WebDriver driver, WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public static void jsClickById(WebDriver driver, String id) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$('#"+id+"')[0].click();");
	}

}

package com.districtbatch5.runners;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.districtbatch5.utilities.Driver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {

	@Before
	public void setUp(){
		Driver.getInstance().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	
	@After   // this before and after annotaions are refers to each scenario, and it has to be imported from cucumber.api 
	public void tearDown(Scenario scenario){
		if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) 
                    Driver.getInstance()).getScreenshotAs(OutputType.BYTES);
            	scenario.embed(screenshot, "image/png");
	}
		Driver.closeDriver();
	// hook class has to be in the same package with the step definitions 
}
}
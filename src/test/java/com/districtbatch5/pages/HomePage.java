package com.districtbatch5.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.districtbatch5.utilities.Driver;

public class HomePage {

	public HomePage(){
		PageFactory.initElements(Driver.getInstance(), this);
	}
	
	@FindBy(css="a[href='/useditems.asp']")
	public WebElement usedGear;
	
	
}

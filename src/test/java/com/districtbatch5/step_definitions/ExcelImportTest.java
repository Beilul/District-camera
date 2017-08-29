package com.districtbatch5.step_definitions;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.districtbatch5.pages.HomePage;
import com.districtbatch5.pages.UsedGearPage;
import com.districtbatch5.utilities.ConfigurationReader;
import com.districtbatch5.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ExcelImportTest {
	
	WebDriver driver = Driver.getInstance();
	 WebDriverWait wait = new WebDriverWait(driver, 30);
	   UsedGearPage usedGear;
	   HomePage homePage;

	@Given("^The user navigates to URL$")
	public void the_user_navigates_to_URL() throws Throwable {
	   driver.get(ConfigurationReader.getProperty("url"));
	}

	@When("^The user clicks -Used Gear- tab$")
	public void the_user_clicks_Used_Gear_tab() throws Throwable {
	    homePage = new HomePage();
	   homePage.usedGear.click();
	}

	@Then("^The user captures all the data and throws into new created Excel sheet each time$")
	public void the_user_captures_all_the_data_and_throws_into_new_created_Excel_sheet_each_time() throws Throwable {
	   WebDriverWait wait = new WebDriverWait(driver, 30);
	  usedGear= new UsedGearPage();
	   
	   wait.until(ExpectedConditions.visibilityOf(usedGear.dataTable));
	   String excelPath="./src/test/resources/com/districtbatch5/test_data/districtCamera.xlsx";
	   String sheetName= "usedGear";
	   
	   XSSFWorkbook workbook=new  XSSFWorkbook();
	   XSSFSheet workSheet= workbook.createSheet(sheetName);
	   XSSFRow row=null;
	   XSSFCell cell =null;
	   
	   for(int i=1;i<= usedGear.headerSize.size(); i++){
		   WebElement headerValue= driver.findElement(By.xpath("//table/thead/tr/th["+i+"]"));
		   System.out.println(headerValue.getText());
		   
		   row=workSheet.getRow(0);
		   if(row==null){
			   row=workSheet.createRow(0);
		   }
		   cell=row.getCell(i-1);
		   if (cell==null){
			   cell=row.createCell(i-1);
		   }
		   cell.setCellValue(headerValue.getText());
	   }
	   
	   for (int i = 1; i <= usedGear.rowsize.size(); i++) {
		   
		row = workSheet.getRow(i);
		if(row==null)row= workSheet.createRow(i);
		
		for (int j = 1; j <= usedGear.headerSize.size(); j++) {
			
			cell=row.getCell(j-1);
			
			if(cell==null) cell=row.createCell(j-1);
			WebElement cellValue= driver.findElement(By.xpath("//table/tbody/tr/["+i+"]/td["+j+"]"));
			
			cell.setCellValue(cellValue.getText());	
		}
	} 
	   
	    
	   
	   FileOutputStream fos= new FileOutputStream(excelPath);
	   workbook.write(fos);
	   
	   
	}
}

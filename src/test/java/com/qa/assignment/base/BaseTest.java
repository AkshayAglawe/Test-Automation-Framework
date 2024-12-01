package com.qa.assignment.base;

import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.github.javafaker.Faker;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.qa.Assigment.factory.PlaywrightFactory;
import com.qa.assignment.pages.DownTimePage;
import com.qa.assignment.pages.EditManufacturingHandoverRecordPage;
import com.qa.assignment.pages.LoginPage;
import com.qa.assignment.pages.ManufacturingHandoverRecordPage;
import com.qa.assignment.pages.MorningMeetingDashboardPage;
import com.qa.assignment.pages.ShiftHandoverReportPage;
import com.qa.assignment.pages.SidebarPage;
import com.qa.assignment.test.ManufacturingHandoverRecordTest;
//Making some changes in master branch 

public class BaseTest {
    public PlaywrightFactory playwrightFactory;
    public static Page page;
    public Properties prop;
    public  BrowserContext browserContext;
     public MorningMeetingDashboardPage morningMeetingDashboardPage;
            
    public LoginPage loginpage;
    public SidebarPage sidebarPage;
    public DownTimePage downTimePage;
    public Faker faker;
   public ManufacturingHandoverRecordPage manufacturingHandoverRecordPage;
   public EditManufacturingHandoverRecordPage editManufacturingHandoverRecordPage;
   public ShiftHandoverReportPage shiftHandoverReportPage;
 


      
    
    @BeforeMethod
    public void setup() {
        playwrightFactory = new PlaywrightFactory();
        prop = playwrightFactory.initProp();
        page = playwrightFactory.initBrowser(prop);
        loginpage=new LoginPage(page);
        faker = new Faker();
        if (loginpage.emailFieldVisible()) {
        
        	loginpage.login(prop.getProperty("email"), prop.getProperty("password"));
            page.context().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("./src/test/resources/signedInState.json")));
  	     
        }
      morningMeetingDashboardPage = new MorningMeetingDashboardPage(page);
      sidebarPage=new SidebarPage(page);
      PlaywrightAssertions.setDefaultAssertionTimeout(30000);

        
    }

    public void randomBranch() {
    	//The random testing method added to master
    }
    @AfterMethod
    public void tearDown() {
        page.context().browser().close();
       
    }
}

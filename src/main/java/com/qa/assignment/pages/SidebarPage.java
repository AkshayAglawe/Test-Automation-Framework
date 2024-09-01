package com.qa.assignment.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SidebarPage {
Page page;
	
	public SidebarPage(Page page) {
		this.page=page;
	}
	
	private String configurationMenuText="Configuration";
	private String downtimeSubMenuText="Downtime";
//	private String manufacturingHandoverRecordText="Manufacturing Handover Record";
	private String manufacturingHandoverRecordXpath="//span[text()='Manufacturing Handover Record']";
	private String rwnText="RWN";
	private String morningMeetingDashboardMenuText="Morning Meeting Dashboard";
	private String fishBonemenu="//span[text()='Fishbone']";
	private String fishBoneSubmenuRWN="RWN";  
	private String shiftHandOverSubMenuText="Shift Handover Report";
	private String reportsButtonText="Reports";		
	private String ReportsRWNsubMenuLocator=".menu-sub > div > span > .menu-title";
	
	public SidebarPage clickOnFishBoneMenu() {
		page.locator(fishBonemenu).click();
		return this;
		
	}
	
	public FishbonePage clickOnFishBoneRWNsubMenu() {
		 page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(fishBoneSubmenuRWN)).click();
		return new FishbonePage(page) ;
		
	}
	
	public SidebarPage clickOnConfigurationMenu() {
		page.getByText(configurationMenuText, new Page.GetByTextOptions().setExact(true)).click();
		return this;
	}
	
	public DownTimePage clickOnDowntimeSubMenu() {
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(downtimeSubMenuText)).click();
		return new DownTimePage(page);
		
	}
	
	public SidebarPage clickOnManufacturingHandoverRecordMenu() {
		page.locator(manufacturingHandoverRecordXpath).click();
		return this;
	}
	
	public ManufacturingHandoverRecordPage clickOnRWNSubMenu() {
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(rwnText)).click();
		return new ManufacturingHandoverRecordPage(page);
		
	}		
	
	public SidebarPage clickOnMorningMeetingDashboardMenu() {
		page.getByText(morningMeetingDashboardMenuText).click();
		return this;
	}
	
	public  MorningMeetingDashboardPage clickOnMM_RWN_Menu() {
		 page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(rwnText)).click();
		 return new MorningMeetingDashboardPage(page);
	}
	
	
	public SidebarPage clickOnReportsMenu() {
		  page.locator("span").filter(new Locator.FilterOptions().setHasText(reportsButtonText)).first().click();
		  return this;
	}
	
	
	public SidebarPage clickOnReportsRWNsubMenu() {
		page.locator(ReportsRWNsubMenuLocator).first().click();	
		return this;
	}
     
 
	
	public ShiftHandoverReportPage clickOnShiftHandoverReportSubMenu() {
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(shiftHandOverSubMenuText)).click();
      return new ShiftHandoverReportPage(page);
}
}

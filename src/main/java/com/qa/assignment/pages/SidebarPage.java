package com.qa.assignment.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SidebarPage {
Page page;
	
	public SidebarPage(Page page) {
		this.page=page;
	}
	
	private String configurationMenuText="Configuration";
	private String downtimeSubMenuText="Downtime";
	
			
			
	public SidebarPage clickOnConfigurationMenu() {
		page.getByText(configurationMenuText, new Page.GetByTextOptions().setExact(true)).click();
		return this;
	}
	
	public DownTimePage clickOnDowntimeSubMenu() {
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(downtimeSubMenuText)).click();
		return new DownTimePage(page);
		
	}
	
			
	
}

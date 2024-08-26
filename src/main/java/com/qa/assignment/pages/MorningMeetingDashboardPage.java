package com.qa.assignment.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class MorningMeetingDashboardPage {

	Page page;

    public MorningMeetingDashboardPage(Page page) {
        this.page = page;
    }
    

    
    public boolean textVisible() {
    	assertThat(page.locator("h1")).containsText("Morning Meeting Dashboard");
    	return page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Morning Meeting Dashboard")).isVisible();
    	
    }
    
    public void clickOnEditLink() {
    	   page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Edit your account information")).click();
    }

}



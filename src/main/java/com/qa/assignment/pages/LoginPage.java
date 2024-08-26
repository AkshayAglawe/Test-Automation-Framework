package com.qa.assignment.pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
public class LoginPage {

	Page page;
	
	public LoginPage(Page page) {
		this.page=page;
	}
	
	private String emailPlaceholder="Email";
	private String passwordPlaceholder="Password";
	private String loginButton="Login";
	
    public boolean emailFieldVisible() {
		
	return	page.getByPlaceholder(emailPlaceholder).isVisible();
		
	}
	
    
    
	public MorningMeetingDashboardPage login(String email ,String password ) {
		page.getByPlaceholder(emailPlaceholder).fill(email);
		page.getByPlaceholder(passwordPlaceholder).fill(password);
		  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(loginButton)).click();
		  return new MorningMeetingDashboardPage(page);
	}
  
  
	 
	
}

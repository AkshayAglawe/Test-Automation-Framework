package com.qa.assignment.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.assignment.base.BaseTest;
import com.qa.assignment.pages.MorningMeetingDashboardPage;

public class MorningMeetingDashboardTest extends BaseTest {
	
		@Test
		public void clickOnEditLink() {
			page.pause();
		boolean isVisible=	morningMeetingDashboardPage.textVisible();
		Assert.assertEquals(isVisible, false);
		
		}
		
	}


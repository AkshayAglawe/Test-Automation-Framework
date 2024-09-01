package com.qa.assignment.pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
public class MorningMeetingDashboardPage {

	Page page;

    public MorningMeetingDashboardPage(Page page) {
        this.page = page;
    }
    private String downTimeEventsEovenTableRows= "//table[@id='downtime_-1_editor']/tbody/tr";
private String downTimeEventsEovenTableRowCells="//table[@id='downtime_-1_editor']/tbody/tr/td";
private String downtimeEvenEovenFirstRowtFourthCell="//table[@id='downtime_-1_editor']/tbody/tr[1]/td[4]";
    
//    public boolean textVisible() {
//    	assertThat(page.locator("h1")).containsText("Morning Meeting Dashboard");
//    	return page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Morning Meeting Dashboard")).isVisible();
//    	
//    }
    
    
    public boolean validateSelectedRecordOnMMPage(List<String> selectedRowRecords ) {
    	
    	
    	waitForTableToLoad(downtimeEvenEovenFirstRowtFourthCell,"Downtime events eoven");
    	boolean recordFound = false;
        String mmdDowntimeEovenTableRowXpath = downTimeEventsEovenTableRows; 
        int rowCount = page.locator(mmdDowntimeEovenTableRowXpath).count();

        for (int i = 0; i < rowCount; i++) {
            String rowXpath = mmdDowntimeEovenTableRowXpath + "[" + (i + 1) + "]";
            int cellCount = page.locator(rowXpath + "/td").count();
            List<String> mmdPageRowData = new ArrayList<>();

            for (int j = 0; j < cellCount; j++) {
                String cellXpath = rowXpath + "/td[" + (j + 1) + "]";
                String cellText = page.locator(cellXpath).innerText().trim();
                mmdPageRowData.add(cellText);
            }

            System.out.println(selectedRowRecords);
            System.out.println(mmdPageRowData);
            if (selectedRowRecords.equals(mmdPageRowData)) {
                recordFound = true;
                return true;
            }
            else {
            	mmdPageRowData.clear();
            }
       
    }
 
return false;
}
  
    public void waitForTableToLoad(String cellLocator, String tableName) {
    	System.out.println("123");
		assertThat(page.locator(cellLocator)).isVisible();
		if (!page.locator(cellLocator).isVisible()) {
			System.out.println("There are no rows in the " + tableName + " table.");
		}

	}
}



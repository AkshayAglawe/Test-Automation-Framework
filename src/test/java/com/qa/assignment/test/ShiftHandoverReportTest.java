package com.qa.assignment.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.assignment.base.BaseTest;
import com.qa.assignment.pages.ShiftHandoverReportPage;

public class ShiftHandoverReportTest extends BaseTest {

//	@Test
	public void validatingActionsTableColumns() {
	boolean isAllColumnsOfActionTableVisible=sidebarPage.clickOnReportsMenu().clickOnReportsRWNsubMenu().
  clickOnShiftHandoverReportSubMenu().isAllColumnsOfActionTableVisible();
	Assert.assertEquals(isAllColumnsOfActionTableVisible, true);
	}
	
//	@Test
	public void verifyCorrectRecordHighlightedInNewTabAfterClickOnArrowButtonFromParentColumn() {
		shiftHandoverReportPage  = sidebarPage.clickOnReportsMenu().clickOnReportsRWNsubMenu().
		  clickOnShiftHandoverReportSubMenu();
	String loggedByOnShiftHandOverRecord=shiftHandoverReportPage.getLoggedByOfParentCellOfArecordWithInApprovalStatus().trim();
	System.out.println(loggedByOnShiftHandOverRecord);
	boolean rowInDowntimeColumnIsHighlightedWithBlueColour=shiftHandoverReportPage.clickOnParentCellOfArecordWithInApprovalStatus().
	rowInDowntimeColumnIsHighlightedWithBlueColourOrNot(loggedByOnShiftHandOverRecord);
	Assert.assertEquals(rowInDowntimeColumnIsHighlightedWithBlueColour, true);
	}
	
//	@Test
	public void validatingThePresenceOfButtonShiftHandoverButtonReportButton() {
		
	boolean areButtonsVisible=sidebarPage.clickOnReportsMenu().clickOnReportsRWNsubMenu().
		  clickOnShiftHandoverReportSubMenu().areButtonsVisible();
	Assert.assertEquals(areButtonsVisible, true);
	}
	
//	@Test
	public void validatingTheDataFromCurrentShiftHandoverReport() {

		
       shiftHandoverReportPage  = sidebarPage.clickOnReportsMenu().clickOnReportsRWNsubMenu().
		  clickOnShiftHandoverReportSubMenu().clickCurrentSHRbutton();
       Map<String, String> currentShiftHandoverDetailsFromCurrentSHR = shiftHandoverReportPage.captureCurrentShiftHandoverDetailsFromCurrentSHR();
       
       manufacturingHandoverRecordPage = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu();
       Map<String, String> currentShiftDetailsFromManufacturingHandoverPageTable = manufacturingHandoverRecordPage.captureCurrentShiftDetailsFromManufacturingHandoverPageTable();
	
       boolean areDetailsEqual = compareShiftDetails(currentShiftHandoverDetailsFromCurrentSHR, currentShiftDetailsFromManufacturingHandoverPageTable);
       Assert.assertEquals(areDetailsEqual, true);
	}
	
//	@Test
	public void validatingTheDataFromPreviousShiftHandoverReport() {

		
       shiftHandoverReportPage  = sidebarPage.clickOnReportsMenu().clickOnReportsRWNsubMenu().
		  clickOnShiftHandoverReportSubMenu().clickPreviousSHRbutton();
       Map<String, String> previousShiftHandoverDetailsFromCurrentSHR = shiftHandoverReportPage.capturePreviousShiftHandoverDetailsFromCurrentSHR();
       
       manufacturingHandoverRecordPage = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu();
       Map<String, String> previousShiftDetailsFromManufacturingHandoverPageTable = manufacturingHandoverRecordPage.capturePreviousShiftDetailsFromManufacturingHandoverPageTable();
	System.out.println(previousShiftHandoverDetailsFromCurrentSHR);
	System.out.println(previousShiftDetailsFromManufacturingHandoverPageTable);
       boolean areDetailsEqual = compareShiftDetails(previousShiftHandoverDetailsFromCurrentSHR, previousShiftDetailsFromManufacturingHandoverPageTable);
       Assert.assertEquals(areDetailsEqual, true);
	}
	
	@Test
	public void m1() {
		page.pause();
	}
	 private boolean compareShiftDetails(Map<String, String> map1, Map<String, String> map2) {
	        return map1.equals(map2);
	    }
}

package com.qa.assignment.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.assignment.base.BaseTest;

public class FishBoneTest extends BaseTest {

//	@Test
	public void validateDurationSumMatchesSumShownOnLabel() {

		boolean doesTheSumShownOnScreenMatchesWithSumOfDurationInTable = sidebarPage.clickOnFishBoneMenu()
				.clickOnFishBoneRWNsubMenu().doesTheSumShownOnScreenMatchesWithSumOfDurationInTable();
		Assert.assertEquals(doesTheSumShownOnScreenMatchesWithSumOfDurationInTable, true);
	}
	
	
//	@Test
	public void validateExportedExcelFileIsAccordingToAppliedFilters() throws IOException {
	
		List<String> expectedOperations = new ArrayList<String>();
		expectedOperations.add("RWN-op");
		  // all list of options available  RWN-op
		
		List<String> expectedShiftTypes = new ArrayList<String>();
		expectedShiftTypes.add("Night");
		expectedShiftTypes.add("Evening");
		// all list of options available  Day , Evening , Night

		List<String> expectedDowntimeCategories = new ArrayList<String>();
		expectedDowntimeCategories.add("Category2");
		expectedDowntimeCategories.add("Category3");
		// all list of options available  Category1  , Category2 , Category3 , Category4
		
       List<String> expectedDowntimeRootCauses = new ArrayList<String>();
       expectedDowntimeRootCauses.add("Root Couse 2");
       expectedDowntimeRootCauses.add("Root Couse 3");
       
    // all list of options available  Root Couse 1  , Root Couse 2 , Root Couse 3 , Root Couse 4
       
       List<String> expectedDowntimeReasonL1 = new ArrayList<String>();
       expectedDowntimeReasonL1.add("Record 10");

       
       List<String> expectedDowntimeReasonL2 = new ArrayList<String>();
       expectedDowntimeReasonL2.add("Reason l4");
//       expectedDowntimeReasonL2.add("Reason l3");
       
       
	boolean isExportedExcelFileAccordingToTheFilters=sidebarPage.clickOnFishBoneMenu().clickOnFishBoneRWNsubMenu().clickOnShowHideFilterButton().
		selectOperations(expectedOperations.get(0)).selectDowntimeCategory(expectedDowntimeCategories.get(0)).
		selectDowntimeCategory(expectedDowntimeCategories.get(1)).selectRootCause(expectedDowntimeRootCauses.get(0)).
		selectRootCause(expectedDowntimeRootCauses.get(1)).selectReasonL1(expectedDowntimeReasonL1.get(0))
		.selectShiftType(expectedShiftTypes.get(0)).
		selectShiftType(expectedShiftTypes.get(1)).clickOnSearchButton().clickOnExportButton().
		validateTheExportedExcelFile(expectedOperations, expectedDowntimeCategories, expectedDowntimeRootCauses, 
				expectedDowntimeReasonL2, expectedShiftTypes);
	
	Assert.assertEquals(isExportedExcelFileAccordingToTheFilters, true);
	}

	@Test
	public void validateExportedExcelFileNameIsProper() {
	    
		boolean isExportedExcelFileNameProper=sidebarPage.clickOnFishBoneMenu().clickOnFishBoneRWNsubMenu().
				clickOnExportButton().validateExportedFileName();
		Assert.assertEquals(isExportedExcelFileNameProper, true,"With test automation the file downloaded is not as per expectaion");
	}
}

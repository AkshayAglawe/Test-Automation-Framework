package com.qa.assignment.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.assignment.base.BaseTest;

public class ManufacturingHandoverRecordTest extends BaseTest {

//	@Test
	public void verifyShowAndHideFilterDisplaysAllExpectedFilterElements() {

		manufacturingHandoverRecordPage = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu();
		boolean allFilterElementsVisible = manufacturingHandoverRecordPage.clickShowHideFilter()
				.allTheFilterElementsVisible();
		Assert.assertEquals(allFilterElementsVisible, true);
	}

//	@Test
	public void validateDateSelectedOnDatePicker() {
		manufacturingHandoverRecordPage = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu();
		boolean isDateSelectedInCorrectFormatOnFromDatePicker = manufacturingHandoverRecordPage.clickShowHideFilter()
				.selectFromDate(20, 11, 2021);
		Assert.assertEquals(isDateSelectedInCorrectFormatOnFromDatePicker, true);
		boolean isDateSelectedInCorrectFormatOnToDatePicker = manufacturingHandoverRecordPage.selectToDate(23, 01,
				2023);
		Assert.assertEquals(isDateSelectedInCorrectFormatOnToDatePicker, true);

	}

//	@Test
	public void validateMultipleShiftTypesSelection() {
		manufacturingHandoverRecordPage = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu();
		boolean isSelectingMultipleShiftsPossible = manufacturingHandoverRecordPage.clickShowHideFilter()
				.selectShifts(true, true, false);
		Assert.assertEquals(isSelectingMultipleShiftsPossible, true);

	}

//	@Test
	public void validateMultipleShiftLeadersSelection() {
		manufacturingHandoverRecordPage = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu();

		boolean isSelectingMultipleShiftLeadersPossible = manufacturingHandoverRecordPage.clickShowHideFilter()
				.selectMultipleShiftLeaders();
		Assert.assertEquals(isSelectingMultipleShiftLeadersPossible, true);

	}

//	@Test
	public void validateMultipleResourcesSelection() {
		manufacturingHandoverRecordPage = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu();

		boolean isSelectingMultipleResourcesPossible = manufacturingHandoverRecordPage.clickShowHideFilter()
				.selectMultipleResources();
		Assert.assertEquals(isSelectingMultipleResourcesPossible, true);

	}

//	@Test
	public void validateMultipleRecordStatusSelection() {
		manufacturingHandoverRecordPage = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu();

		boolean isSelectingMultipleRecordStatusPossible = manufacturingHandoverRecordPage.clickShowHideFilter()
				.selectMultipleRecordStatus();
		Assert.assertEquals(isSelectingMultipleRecordStatusPossible, true);

	}

//	@Test
	public void testSearchResultsMatchAppliedFilters() {

		List<String> expectedShiftTypes = new ArrayList<String>();
		expectedShiftTypes.add("Day");
		expectedShiftTypes.add("Evening");

		List<String> expectedShiftLeaders = new ArrayList<String>();
		expectedShiftLeaders.add("akshay Glawe");
		expectedShiftLeaders.add("Dhamo Ramkey");

		List<String> expectedStatuses = new ArrayList<String>();
		expectedStatuses.add("In-use");
		expectedStatuses.add("Approved");

		manufacturingHandoverRecordPage = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
				.clickShowHideFilter();

		boolean areSearchResultsValid = manufacturingHandoverRecordPage.selectShift(expectedShiftTypes.get(0))
				.selectShift(expectedShiftTypes.get(1)).selectShiftLeader(expectedShiftLeaders.get(1))
				.selectRecordStatus(expectedStatuses.get(0)).selectRecordStatus(expectedStatuses.get(1))
				.clickSearchButton().validateTableFilters(expectedShiftTypes, expectedShiftLeaders, expectedStatuses);

		Assert.assertEquals(areSearchResultsValid, true);

	}

//	@Test
	public void verifyResetFilterButtonFuctionality() {

		boolean allFiltersResetted = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
				.clickShowHideFilter().ResetAppliedFilters();
		page.pause();
		Assert.assertEquals(allFiltersResetted, true);

	}

//	@Test
	public void verifyButtonsVisibilityOnManufacturingHandOverPage() {
		page.pause();
		boolean allButtonsVisible = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
				.selectFirstRecordCheckbox().checkTheButtonsAreVisible();

		Assert.assertEquals(allButtonsVisible, true);
	}

//	@Test
	public void verifyButtonsDisabledWhenNoCheckboxSelected() {

		boolean isEditDeletePdfButtonDisabled = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
				.isEditDeletePdfButtonDisabled();
		Assert.assertEquals(isEditDeletePdfButtonDisabled, true);
	}

//	@Test
	public void verifyButtonsEnabledWhenACheckboxSelected() {

		boolean isEditDeletePdfButtonEnabled = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
				.isEditDeletePdfButtonEnabled();
		Assert.assertEquals(isEditDeletePdfButtonEnabled, true);
	}

//	@Test
	public void validateEditingRecordWithInUseStatus() {

		boolean isEditWarningMessageHiddenForInUseRecordStatus = sidebarPage.clickOnManufacturingHandoverRecordMenu()
				.clickOnRWNSubMenu().isEditWarningMessageHiddenForInUseRecordStatus();
		Assert.assertEquals(isEditWarningMessageHiddenForInUseRecordStatus, true);

	}

//	@Test
	public void validateEditingRecordWithApprovedStatus() {

		boolean isEditWarningMessageVisibleForApprovedRecordStatus = sidebarPage
				.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
				.isEditWarningMessageVisibleForApprovedRecordStatus();
		Assert.assertEquals(isEditWarningMessageVisibleForApprovedRecordStatus, true);
	}

	@Test
	public void verifyARecordCanBeDeleted() {
		manufacturingHandoverRecordPage = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu();
		page.pause();
		List<String> firstRowContent = manufacturingHandoverRecordPage.getFirstRowContent();

		boolean deleteConfirmationPopupVisible = manufacturingHandoverRecordPage.deleteFirstRecord()
				.deleteConfirmationPopupVisible();
		Assert.assertEquals(deleteConfirmationPopupVisible, true);
		boolean recordDeletedSuccessfully = manufacturingHandoverRecordPage.closeDeleteConfirmationPopup()
				.isRecordDeletedSuccessfully(firstRowContent);
		Assert.assertEquals(recordDeletedSuccessfully, true,
				"Test case intentionally failed: Record deletion was not confirmed to avoid permanent deletion. The Close button was clicked instead.");
	}

//	@Test
//	public void readPdf() throws IOException {
//		manufacturingHandoverRecordPage	= sidebarPage.clickOnManufacturingHandoverRecordMenu()
//				.clickOnRWNSubMenu().selectFirstRecord();
//		page.pause();
////		manufacturingHandoverRecordPage.generateAndDownloadPDF();
//		manufacturingHandoverRecordPage.pdfReaderTest();
//	}

}

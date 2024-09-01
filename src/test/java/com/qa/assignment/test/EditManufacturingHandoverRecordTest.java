package com.qa.assignment.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.assignment.base.BaseTest;

public class EditManufacturingHandoverRecordTest  extends BaseTest{
	
//	@Test
	public void validatingDownTimeTableColumns() {
		boolean areAllExpectedColumnsVisibleInDownTimeTable= sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
		.selectFirstRecord().clickEditButton().areAllExpectedColumnsVisibleInDownTimeTable();
		Assert.assertEquals(areAllExpectedColumnsVisibleInDownTimeTable, true);
		
	}
	
//	@Test
	public void validateMMCheckedRecordAppearsOnMMPage() {
		 manufacturingHandoverRecordPage  = sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu().selectFirstRecord();
		editManufacturingHandoverRecordPage = manufacturingHandoverRecordPage.clickEditButton().selectMMCheckboxOfDownTimeRecord();
		List<String >recordsOfRowWhoseMMCheckboxChecked=editManufacturingHandoverRecordPage .getDowntimeRecordWhoseMMisChecked();
		editManufacturingHandoverRecordPage .clickGoBackToRecordsList();
		 
	   boolean isvisible=sidebarPage.clickOnMorningMeetingDashboardMenu().clickOnMM_RWN_Menu().validateSelectedRecordOnMMPage(recordsOfRowWhoseMMCheckboxChecked);
		Assert.assertEquals(isvisible, true); 
		 
	}
	
//	@Test
	public void validatingDownTimeEditPopupFields() {
		
		boolean allTheExpectedFieldsFromEditDowntimePopupAreVisible= sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
		.selectFirstRecord().clickEditButton().selectACheckboxOfFirstDownTimeRecordForEditOrDelete().
		clickOnEditbuttonFromDowntimeTable().allTheExpectedFieldsFromEditDowntimePopupAreVisible();
          Assert.assertEquals(allTheExpectedFieldsFromEditDowntimePopupAreVisible, true);
	}
	
//	@Test
	public void verifyMandatoryFieldValidationOnEdit() {
		
		sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
		.selectARecordWithInUseStatus().clickEditButton().selectACheckboxOfFirstDownTimeRecordForEditOrDelete().
		clickOnEditbuttonFromDowntimeTable().clearAllFieldInputFromDowntimeEditPopup();
	}

//	@Test
	public void validateIsDownTimeCategoryDropdownSingleSelect() {
		boolean isDownTimeCatorgoryDropdownSingleselect= sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
		.selectARecordWithInUseStatus().clickEditButton().selectACheckboxOfFirstDownTimeRecordForEditOrDelete().
		clickOnEditbuttonFromDowntimeTable().isDownTimeCatorgoryDropdownSingleselect();
		Assert.assertEquals(isDownTimeCatorgoryDropdownSingleselect, true);
	}
	
//	@Test
	public void validateIsRootCauseDropdownSingleSelect() {
		boolean isRootCauseDropdownSingleSelect= sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
		.selectARecordWithInUseStatus().clickEditButton().selectACheckboxOfFirstDownTimeRecordForEditOrDelete().
		clickOnEditbuttonFromDowntimeTable().isrootCauseDropdownSingleselect();
		Assert.assertEquals(isRootCauseDropdownSingleSelect, true);
	}
	
//	@Test
	public void validateIsReasonL1DropdownSingleSelect() {
		page.pause();
		boolean isReasonL1DropdownSingleselect= sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
		.selectARecordWithInUseStatus().clickEditButton().selectACheckboxOfFirstDownTimeRecordForEditOrDelete().
		clickOnEditbuttonFromDowntimeTable().isReasonL1DropdownSingleselect();
		Assert.assertEquals(isReasonL1DropdownSingleselect, true);
	}
	
//	@Test
	public void validateIsReasonL2DropdownSingleSelect() {
		boolean isReasonL2DropdownSingleselect= sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
		.selectARecordWithInUseStatus().clickEditButton().selectACheckboxOfFirstDownTimeRecordForEditOrDelete().
		clickOnEditbuttonFromDowntimeTable().isReasonL2DropdownSingleselect();
		Assert.assertEquals(isReasonL2DropdownSingleselect, true);
	}
	
//	@Test
	public void isEnteredStartDateInValidFormat() {

		boolean isDateValid= sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
				.selectARecordWithInUseStatus().clickEditButton().selectACheckboxOfFirstDownTimeRecordForEditOrDelete().
				clickOnEditbuttonFromDowntimeTable().isEnterdStartDateInValidFormat();
				Assert.assertEquals(isDateValid, true);
	}
	
	//similar for end date
	
	@Test
	public void verifyupdatingCommentWithLongInputAndVerifySuccessfulUpdate() {
//		page.pause();
		boolean isUpdateSuccesfull= sidebarPage.clickOnManufacturingHandoverRecordMenu().clickOnRWNSubMenu()
				.selectARecordWithInUseStatus().clickEditButton().selectACheckboxOfFirstDownTimeRecordForEditOrDelete().
				clickOnEditbuttonFromDowntimeTable().editingCommentIsPossibleWithLongInput();
		Assert.assertEquals(isUpdateSuccesfull, true);
	}
}

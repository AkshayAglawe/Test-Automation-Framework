package com.qa.assignment.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.assignment.base.BaseTest;
import com.qa.assignment.pages.DownTimePage;

public class DownTimeTest extends BaseTest {
	@Test
	public void validatingDownTimeResosnL1TableColumns() {

		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		boolean allColumnsArePresent = downTimePage.goToDownTimeReasonL1Table()
				.allExpectedColumnsOfDowntimeResonL1TableAreVisible();
		Assert.assertEquals(allColumnsArePresent, true);

	}

	@Test
	public void editingNameCellwithMax45Char() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		boolean isNameCellEditableWithMax45Chars = downTimePage.goToDownTimeReasonL1Table().editNameCell(45);
		Assert.assertEquals(isNameCellEditableWithMax45Chars, true);

	}

	@Test
	public void editingNameCellwith46Char() {
		
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		boolean isNameCellNotEditableWithMax46Chars = downTimePage.goToDownTimeReasonL1Table()
				.editNameCellBeyondRange(46);
		Assert.assertEquals(isNameCellNotEditableWithMax46Chars, true);

	}

	@Test
	public void editingGermanNameCellwithMax45Char() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		boolean isGermanNameCellEditableWithMax45Chars = downTimePage.goToDownTimeReasonL1Table()
				.editGermanNameCell(45);
		Assert.assertEquals(isGermanNameCellEditableWithMax45Chars, true);

	}

	@Test
	public void editingGermanNameCellwith46Char() {
		
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		boolean isGermanNameCellNotEditableWithMax46Chars = downTimePage.goToDownTimeReasonL1Table()
				.editGermanNameCellBeyondRange(46);
		Assert.assertEquals(isGermanNameCellNotEditableWithMax46Chars, true);

	}

//	@Test //Could not automate this
//	public void validatingEditingDowntimeRootCauseCell() {
//		downTimePage=sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
//		downTimePage.goToDownTimeReasonL1Table().isMultiSelectaAndEditable();
//	}

	@Test
	public void checkingIfTheEquipmentCellIsEdiable() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		boolean isEquipmentCellNotEditable = downTimePage.goToDownTimeReasonL1Table().isEquipmentCellNotEditable();
		Assert.assertEquals(isEquipmentCellNotEditable, true);
	}

	@Test
	public void editingDescriptionCellwithMax100Char() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		boolean isDescriptionCellEditableWithMax100Chars = downTimePage.goToDownTimeReasonL1Table()
				.editDescriptionCell(100);
		Assert.assertEquals(isDescriptionCellEditableWithMax100Chars, true);

	}

	@Test
	public void editingDescriptionCellwith101Char() {

		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		boolean isDescriptionCellNotEditableWithMax101Chars = downTimePage.goToDownTimeReasonL1Table()
				.editDescriptionCellBeyondRange(101);
		Assert.assertEquals(isDescriptionCellNotEditableWithMax101Chars, true);

	}

	@Test(dataProvider = "validOrderByValues")

	public void editingOrderByWithValidValues(String ordeByValue) {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		boolean isOrderByCellEditableWithValidValue = downTimePage.goToDownTimeReasonL1Table()
				.isEditingOrderByWithIntegerValuePossible(ordeByValue);
		Assert.assertEquals(isOrderByCellEditableWithValidValue, true);
	}

	@DataProvider(name = "validOrderByValues")
	public Object[] validOrderByValues() {
		return new Object[] {

				"2545685",

				"-3210946", // Negative number

		};
	}

	// Method to generate a random 9-digit number
	public int generateRandom9DigitNumber() {
		Random random = new Random();

		return 100000000 + random.nextInt(900000000);
	}

	@Test(dataProvider = "invalidOrderByValues")

	public void editingOrderByWithInvalidValues(String ordeByValue) {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		boolean isOrderByCellNotEditableWithInvalidValue = downTimePage.goToDownTimeReasonL1Table()
				.isEditingOrderByWithNonIntegerValuesPossible(ordeByValue);
		Assert.assertEquals(isOrderByCellNotEditableWithInvalidValue, true);
	}

	@DataProvider(name = "invalidOrderByValues")
	public Object[] invalidOrderByValues() {
		return new Object[] { "ab", // Alphabetic characters
				"@$", // Special characters
				"1b" // Alphanumeric

		};
	}

	@Test
	public void editingOrderByWithDuplicateValues() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu().goToDownTimeReasonL1Table();
		// Get the current row count from the table
		int rowCount = downTimePage.getRowCount();

		// Determine the number of new records to create
		// If there are already 2 or more records, don't create any new records
		int noOfNewRecordsToCreate = (rowCount >= 2) ? 0 : (2 - rowCount);

		for (int i = 1; i <= noOfNewRecordsToCreate; i++) {
			String name = generateRandomName();
			String germanName = generateRandomName();
			String downtimeRootCauseOption = "Root Couse 2";
			String equipmentOption = "EQ3";
			String orderByValue = String.valueOf(generateRandom9DigitNumber());
			String description = "The random Description";

			downTimePage.createADownTimeReasonL1RecordWithAllDetails(name, germanName, downtimeRootCauseOption,
					equipmentOption, orderByValue, description);
		}

		boolean isEditingOrderByWithDuplicateValuesNotAllowed = downTimePage
				.isEditingOrderByWithDuplicateValuesNotAllowed();
		Assert.assertEquals(isEditingOrderByWithDuplicateValuesNotAllowed, true);
	}

	@Test
	public void verifyHidingARecordFromTable() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu().goToDownTimeReasonL1Table();
		// Get the current row count from the table
		
		int rowCount = downTimePage.getRowCount();

		// Determine the number of new records to create
		// If there are already 1 or more records, don't create any new records
		int noOfNewRecordsToCreate = (rowCount >= 1) ? 0 : 1;

		for (int i = 1; i <= noOfNewRecordsToCreate; i++) {
			String name = generateRandomName();
			String germanName = generateRandomName();
			String downtimeRootCauseOption = "Root Couse 2";
			String equipmentOption = "EQ3";
			boolean isRecordHidden = downTimePage.goToDownTimeReasonL1Table()
					.createADownTimeReasonL1RecordWithOnlyRequiredDetails(name, germanName, downtimeRootCauseOption,
							equipmentOption);
		}
		boolean recordGotHiddenAfterCheckingTheHideCheckboxFromTable = downTimePage
				.isRecordGettingHiddenUsingHideCheckBoxFromTable();
		Assert.assertEquals(recordGotHiddenAfterCheckingTheHideCheckboxFromTable, true);
	}

	@Test
	public void searchingRecordFromUsingSearchBox() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu().goToDownTimeReasonL1Table();

		String name = generateRandomName();
		String germanName = generateRandomName();
		String downtimeRootCauseOption = "Root Couse 3";
		String equipmentOption = "EQ1";
		String orderByValue = String.valueOf(generateRandom9DigitNumber());
		String description = "The random Description";
		downTimePage.createADownTimeReasonL1RecordWithAllDetails(name, germanName, downtimeRootCauseOption,
				equipmentOption, orderByValue, description);
		;
		boolean recordIsShownInSearchResults = downTimePage.searchArecordUsingSearchbar(description)
				.isRecordShown(name);
	}

	@Test
	public void verifyMandatoryFieldValidationOnEdit() {
		// First Create A new Record for edit
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu().goToDownTimeReasonL1Table();

		String name = generateRandomName();
		String germanName = generateRandomName();
		String downtimeRootCauseOption = "Root Couse 3";
		String equipmentOption = "EQ1";
		String orderByValue = String.valueOf(generateRandom9DigitNumber());
		String description = "The random Description";
		downTimePage.createADownTimeReasonL1RecordWithOnlyRequiredDetails(name, germanName, downtimeRootCauseOption,
				equipmentOption);

		boolean theErrorMessagesAfterClearingMandatoryFieldWhileEditingAreShown = downTimePage
				.selectRecordForEditingOrDeletingByName(name).clickOnEditButton()
				.clearMandatoryValuesOfARecordandUpdate();

		Assert.assertEquals(theErrorMessagesAfterClearingMandatoryFieldWhileEditingAreShown, true);

	}

@Test
	public void printTableRecord() {

		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		downTimePage.goToDownTimeReasonL1Table().printTableContent();

	}

@Test
	public void validatingThePresenceOfExpectedFieldsInCreateRecordPopup() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		boolean areAllFieldsVisibleInCreateRecordPopup = downTimePage.goToDownTimeReasonL1Table()
				.allExpectedFieldsAreAvailableToCreateNewRecord();
		Assert.assertEquals(areAllFieldsVisibleInCreateRecordPopup, true);
	}

@Test
	public void verifyMandatoryFieldsValidation() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		boolean isValidationSuccessful = downTimePage.goToDownTimeReasonL1Table().verifyMandatoryFieldsValidation();
		Assert.assertEquals(isValidationSuccessful, true);
	}

@Test
	public void creatingRecordWithOnlyMandatoryFields() {

		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		String name = generateRandomName();
		String germanName = generateRandomName() + "19";
		String downtimeRootCauseOption = "Root Couse 2";
		String equipmentOption = "EQ3";

		boolean isRecordCreated = downTimePage.goToDownTimeReasonL1Table()
				.createADownTimeReasonL1RecordWithOnlyRequiredDetails(name, germanName, downtimeRootCauseOption,
						equipmentOption);
		Assert.assertEquals(isRecordCreated, true);
	}

@Test
	public void verifyHiddenRecordNotVisibleInTableAfterCreation() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		String name = generateRandomName();
		String germanName = generateRandomName();
		String downtimeRootCauseOption = "Root Couse 2";
		String equipmentOption = "EQ3";
		System.out.println(name);
		boolean isRecordHidden = downTimePage.goToDownTimeReasonL1Table()
				.createADownTimeReasonL1RecordWithHideChecboxChecked(name, germanName, downtimeRootCauseOption,
						equipmentOption);
		Assert.assertEquals(isRecordHidden, true);
	}

@Test
	public void verifyRecordCanBeDeleted() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		String name = generateRandomName();
		String germanName = generateRandomName();
		String downtimeRootCauseOption = "Root Couse 2";
		String equipmentOption = "EQ3";
		System.out.println(name);
		downTimePage.goToDownTimeReasonL1Table().createADownTimeReasonL1RecordWithOnlyRequiredDetails(name, germanName,
				downtimeRootCauseOption, equipmentOption);
		downTimePage.selectRecordForEditingOrDeletingByName(name).clickOnDeleteButton();
		boolean isdeleteConfirmationMessageVisible = downTimePage.isdeleteConfirmationMessageVisible();
		Assert.assertEquals(isdeleteConfirmationMessageVisible, true);
		boolean isRecordDeletedSuccessfully = downTimePage.clickOnConfirmDeleteButton()
				.isRecordDeletedSuccessfully(name);
		Assert.assertEquals(isRecordDeletedSuccessfully, true);

	}

	public String generateRandomName() {
		String[] names = { "Ravi", "Sam", "Chris", "Jordan", "Sharma", "Morgan", "Cummins", "Smith", "Styris",
				"Petty" };
		Random random = new Random();
		int index = random.nextInt(names.length); // Get a random index for the name
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		return names[index] + "_" + timeStamp;
	}

	@Test
	public void verifyMultiSelectFunctionalityOfEquipmentDropdown() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu();
		String name = generateRandomName();
		String germanName = generateRandomName();
		String downtimeRootCauseOption = "Root Couse 2";
		String equipmentOption = "EQ3";
		System.out.println(name);
		downTimePage.goToDownTimeReasonL1Table().createADownTimeReasonL1RecordWithOnlyRequiredDetails(name, germanName,
				downtimeRootCauseOption, equipmentOption);

		downTimePage.selectRecordForEditingOrDeletingByName(name).clickOnEditButton()
				.clearSelectedOptionFromEquipmentAndRootCauseDropdown();
		downTimePage.selectMultipleOptionsFromEquipmentAndRootCauseDropdownAndUpdate();

		Assert.assertEquals(downTimePage.getTextFromEquipmentCell(name), "EQ2, EQ3");
		Assert.assertEquals(downTimePage.getTextFromRootCauseCell(name), "Root Couse 2, Root Couse 3");

	}

	@Test
	public void validateSuccessfulUpdateWithOptionalFieldsCleared() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu().goToDownTimeReasonL1Table();

		String name = generateRandomName();
		String germanName = generateRandomName();
		String downtimeRootCauseOption = "Root Couse 3";
		String equipmentOption = "EQ1";
		String orderByValue = String.valueOf(generateRandom9DigitNumber());
		String description = "The random Description";
		downTimePage.createADownTimeReasonL1RecordWithAllDetails(name, germanName, downtimeRootCauseOption,
				equipmentOption, orderByValue, description);

		downTimePage.selectRecordForEditingOrDeletingByName(name).clickOnEditButton()
				.clearOptionalValuesOfARecordandUpdate();
		Assert.assertEquals(downTimePage.getTextFromDescriptionCell(name), " ");
		Assert.assertEquals(downTimePage.getTextFromOrderByCell(name), " ");

	}

	@Test
	public void validateEditingDescriptionWith100Chars() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu().goToDownTimeReasonL1Table();

		String name = generateRandomName();
		String germanName = generateRandomName();
		String downtimeRootCauseOption = "Root Couse 3";
		String equipmentOption = "EQ1";
		String orderByValue = String.valueOf(generateRandom9DigitNumber());
		String description = "The random Description";
		downTimePage.createADownTimeReasonL1RecordWithAllDetails(name, germanName, downtimeRootCauseOption,
				equipmentOption, orderByValue, description);

		downTimePage.selectRecordForEditingOrDeletingByName(name).clickOnEditButton().clearDescription().editDescriptionWith100Chars(100);;
		String text = downTimePage.getTextFromDescriptionCell(name);
		System.out.println(text);
		Assert.assertEquals(text.length(), 100);

	}
	@Test
	public void validateEditingDescriptionWith101Chars() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu().goToDownTimeReasonL1Table();

		String name = generateRandomName();
		String germanName = generateRandomName();
		String downtimeRootCauseOption = "Root Couse 3";
		String equipmentOption = "EQ1";
		String orderByValue = String.valueOf(generateRandom9DigitNumber());
		String description = "The random Description";
		downTimePage.createADownTimeReasonL1RecordWithAllDetails(name, germanName, downtimeRootCauseOption,
				equipmentOption, orderByValue, description);

		downTimePage.selectRecordForEditingOrDeletingByName(name).clickOnEditButton().clearDescription().editDescriptionWith101Chars(101);;
		boolean theInputTooLongMessageIsVisible= downTimePage.theInputTooLongMessageIsVisible();
		Assert.assertEquals(theInputTooLongMessageIsVisible, true);

	}
	@Test
	public void editingOrderByWithDuplicateValues2() {
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu().goToDownTimeReasonL1Table();
		// Get the current row count from the table
		int rowCount = downTimePage.getRowCount();

		// Determine the number of new records to create
		// If there are already 2 or more records, don't create any new records
		int noOfNewRecordsToCreate = (rowCount >= 2) ? 0 : (2 - rowCount);

		for (int i = 1; i <= noOfNewRecordsToCreate; i++) {
			String name = generateRandomName();
			String germanName = generateRandomName();
			String downtimeRootCauseOption = "Root Couse 2";
			String equipmentOption = "EQ3";
			String orderByValue = String.valueOf(generateRandom9DigitNumber());
			String description = "The random Description";

			downTimePage.createADownTimeReasonL1RecordWithAllDetails(name, germanName, downtimeRootCauseOption,
					equipmentOption, orderByValue, description);
		}

		 downTimePage.getMeSecondRowsOrderByValue2();
boolean isEditingOrderByWithDuplicateValuesNotAllowed= downTimePage.isEditingOrderByWithDuplicateValuesNotAllowed2();
		Assert.assertEquals(isEditingOrderByWithDuplicateValuesNotAllowed, true);
	}

	@Test
	public void editingOrderByWith11DigitValue() {
		
		downTimePage = sidebarPage.clickOnConfigurationMenu().clickOnDowntimeSubMenu().goToDownTimeReasonL1Table();

		String name = generateRandomName();
		String germanName = generateRandomName();
		String downtimeRootCauseOption = "Root Couse 3";
		String equipmentOption = "EQ1";
		String orderByValue = String.valueOf(generateRandom9DigitNumber());
		String description = "The random Description";
		downTimePage.createADownTimeReasonL1RecordWithAllDetails(name, germanName, downtimeRootCauseOption,
				equipmentOption, orderByValue, description);
		
		downTimePage.selectRecordForEditingOrDeletingByName(name).clickOnEditButton().editOrderBy("12345678901").clickOnUpdateButton();
		boolean recordUpdateWith11DigitValue=downTimePage.windowPopupHidden();
		
		Assert.assertEquals(recordUpdateWith11DigitValue, true);
	}
	
}

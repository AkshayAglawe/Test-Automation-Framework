package com.qa.assignment.pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class DownTimePage {
	Page page;

	public DownTimePage(Page page) {
		this.page = page;
	}

	private String downTimeReasonL1Text = "Downtime Reason L1";
	private String nameColumnText = "Name: activate to sort column descending";
	private String germanNameColumnText = "German Name: activate to sort";
	private String downtimeRootCauseLabelText = "Downtime Root Cause";
	private String equipmentLabelText = "Equipment";
	private String orderByColumnText = "Order By: activate to sort";
	private String descriptionColumnText = "Description";
	private String hideColumnText = "Hide: activate to sort column";
	private String firstRowFirstColumn = "//table[@id='downtime_reasonl1_editor']/tbody/tr[1]/td[1]";
	private String firstRowSecondSecondColumn = "//table[@id='downtime_reasonl1_editor']/tbody/tr[1]/td[2]";
	private String firstRowThirdColumn = "//table[@id='downtime_reasonl1_editor']/tbody/tr[1]/td[3]";
	private String firstRowFourthColumn = "//table[@id='downtime_reasonl1_editor']/tbody/tr[1]/td[4]";
	private String firstRowFifthColumn = "//table[@id='downtime_reasonl1_editor']/tbody/tr[1]/td[5]";
	private String firstRowSixthColumn = "//table[@id='downtime_reasonl1_editor']/tbody/tr[1]/td[6]";
	private String secondRowSixthColumn = "//table[@id='downtime_reasonl1_editor']/tbody/tr[2]/td[6]";
	private String firstRowSeventhColumn = "//table[@id='downtime_reasonl1_editor']/tbody/tr[1]/td[7]";
	private String firstRowEigthColumn = "//table[@id='downtime_reasonl1_editor']/tbody/tr[1]/td[8]//input";

	private String cellMaxInputErrorMessage = "The input is too long.";

	private String outsideTableArea = "#downtime_reasonl1_editor_paginate";

	private String removeItemButtonLabel = "Remove item";
	private String downTimeRootCauseOption1 = "Root Couse 1";
	private String downTimeRootCauseOption2 = "Root Couse 2";

	private String invalidOrderByValueErrorMessage = "This input must be given as a number.";
	private String duplicateOrderByValueErrorMessage = "This field must have a unique";

	private String table = "//table[@id='downtime_reasonl1_editor']";
	private String rows = "//table[@id='downtime_reasonl1_editor']/tbody/tr";
	private String cols = "//table[@id='downtime_reasonl1_editor']/tbody/tr[1]/td";

	private String newbuttonName = "New";
	private String nameFieldLabel = "Name";
	private String germanNameFieldLabel = "German Name";
	private String orderByFieldLabel = "Order By";
	private String descriptionFieldLabel = "Description";
	private String createButtonName = "Create";
	private String hideCheckBox = "#DTE_Field_conf_downtime_reason_l1-hide_0";

	private String noOfEntriesTextXpath = "//div[@id='downtime_reasonl1_editor_info']";

	private String deleteButtonXpath = "//button[span[text()='Delete']]";
	private String deleteConfirmationMessageXapth = "//div[text()='Are you sure you wish to delete 1 row?']";
	private String confirmDeleteButtonXapth = "(//button[text()='Delete'])[2]";

	private String searchBarPlaceholder = "Search records";

	public void createADownTimeReasonL1RecordWithAllDetails(String name, String germanName,
			String downtimeRootCauseOption, String equipment, String orderByValue, String description) {
		int entriesBefore = getNumberOfEntries();
		clickOnNewButton();

		enterName(name);

		enterGermanName(germanName);
		selectDowntimeRootCause(downtimeRootCauseOption);
		selectEquipmentOption(equipment);
		enterOrderByField(orderByValue);
		enterDescription(description);
		clickOnCreateButton();
		int entriesAfter = getNumberOfEntries();
	}

	public boolean createADownTimeReasonL1RecordWithOnlyRequiredDetails(String name, String germanName,
			String downtimeRootCauseOption, String equipment) {

		int entriesBefore = getNumberOfEntries();
		clickOnNewButton();
		enterName(name);
		System.out.println(germanName);
		enterGermanName(germanName);
		selectDowntimeRootCause(downtimeRootCauseOption);
		selectEquipmentOption(equipment);
		clickOnCreateButton();
		assertThat(page.locator(firstRowSecondSecondColumn)).isVisible();
		int entriesAfter = getNumberOfEntries();

		return (entriesAfter > entriesBefore);

	}

	public boolean createADownTimeReasonL1RecordWithHideChecboxChecked(String name, String germanName,
			String downtimeRootCauseOption, String equipment) {

		clickOnNewButton();
		enterName(name);
		System.out.println(germanName);
		enterGermanName(germanName);
		selectDowntimeRootCause(downtimeRootCauseOption);
		selectEquipmentOption(equipment);
		checkHideCheckbox();
		clickOnCreateButton();
		return isRecordHidden(name);

	}

	public void clickOnNewButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(newbuttonName)).click();
	}

	public void enterName(String name) {
		page.getByLabel(nameFieldLabel, new Page.GetByLabelOptions().setExact(true)).fill(name);
	}

	public void enterGermanName(String germanName) {
		page.getByLabel(germanNameFieldLabel, new Page.GetByLabelOptions().setExact(true)).fill(germanName);
	}

	public void selectDowntimeRootCause(String downtimeRootCauseOption) {
		page.locator("div")
				.filter(new Locator.FilterOptions()
						.setHasText(Pattern.compile("^Root Couse 1Root Couse 2Root Couse 3Root Couse 4$")))
				.getByRole(AriaRole.COMBOBOX).locator("span").click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(downtimeRootCauseOption)).click();
	}

	public void selectEquipmentOption(String equipment) {
		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^EQ1EQ2EQ3$")))
				.getByRole(AriaRole.COMBOBOX).locator("span").click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(equipment)).click();
	}

	public void enterOrderByField(String orderByValue) {
		page.getByLabel(orderByFieldLabel, new Page.GetByLabelOptions().setExact(true)).fill(orderByValue);
	}

	public void enterDescription(String description) {
		page.getByLabel(descriptionFieldLabel, new Page.GetByLabelOptions().setExact(true)).fill(description);
	}

	public void clickOnCreateButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(createButtonName)).click();
	}

	public void checkHideCheckbox() {
		page.locator(hideCheckBox).check();

	}

	public void uncheckHideCheckbox() {
		page.locator(hideCheckBox).uncheck();

	}

	public DownTimePage searchArecordUsingSearchbar(String searchText) {

		page.getByPlaceholder(searchBarPlaceholder).fill(searchText);
		page.waitForTimeout(5000);
		return this;
	}

	public boolean allExpectedFieldsAreAvailableToCreateNewRecord() {
		clickOnNewButton();
		assertThat(page.getByLabel("Name", new Page.GetByLabelOptions().setExact(true))).isVisible();
		assertThat(page.getByLabel("German Name", new Page.GetByLabelOptions().setExact(true))).isVisible();
		assertThat(page.getByRole(AriaRole.DIALOG).getByText("Downtime Root Cause")).isVisible();
		assertThat(page.getByRole(AriaRole.DIALOG).getByText("Equipment")).isVisible();
		assertThat(page.getByLabel("Order By", new Page.GetByLabelOptions().setExact(true))).isVisible();
		assertThat(page.getByLabel("Description", new Page.GetByLabelOptions().setExact(true))).isVisible();
		assertThat(page.locator("#DTE_Field_conf_downtime_reason_l1-hide_0")).isVisible();
		return true;
	}

	public boolean verifyMandatoryFieldsValidation() {
		clickOnNewButton();
		clickOnCreateButton();
		assertThat(page.getByText("This field is required.").nth(0)).isVisible();
		assertThat(page.getByText("minimum one selections please").nth(0)).isVisible();
		assertThat(page.getByText("This field is required.").nth(1)).isVisible();
		assertThat(page.getByText("minimum one selections please").nth(1)).isVisible();
		return true;
	}

	public DownTimePage goToDownTimeReasonL1Table() {
		page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName(downTimeReasonL1Text)).click();
		return this;
	}

	public boolean allExpectedColumnsOfDowntimeResonL1TableAreVisible() {

		assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(nameColumnText))).isVisible();
		assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(germanNameColumnText)))
				.isVisible();
		assertThat(page.getByLabel(downtimeRootCauseLabelText)).isVisible();
		assertThat(page.getByLabel(equipmentLabelText)).isVisible();
		assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(orderByColumnText))).isVisible();
		assertThat(page.getByLabel(descriptionColumnText)).isVisible();
		assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(hideColumnText))).isVisible();

		return true;
	}

	public boolean editNameCell(int inputlength) {
		page.locator(firstRowSecondSecondColumn).click();
		// Clearing the existing input
		page.locator(firstRowSecondSecondColumn).press("ControlOrMeta+a");

		for (int i = 0; i < inputlength; i++) {
			page.locator(firstRowSecondSecondColumn).press("k");
		}

		page.locator(outsideTableArea).click();

		return page.getByText(cellMaxInputErrorMessage).isHidden();
	}

	public boolean editNameCellBeyondRange(int inputlength) {
		page.locator(firstRowSecondSecondColumn).click();
		// Clearing the existing input
		page.locator(firstRowSecondSecondColumn).press("ControlOrMeta+a");

		for (int i = 0; i < inputlength; i++) {
			page.locator(firstRowSecondSecondColumn).press("k");
		}

		page.locator(outsideTableArea).click();
		return page.getByText(cellMaxInputErrorMessage).isVisible();
	}

	public boolean editGermanNameCell(int inputlength) {
		page.locator(firstRowThirdColumn).click();
		// Clearing the existing input
		page.locator(firstRowThirdColumn).press("ControlOrMeta+a");

		for (int i = 0; i < inputlength; i++) {
			page.locator(firstRowThirdColumn).press("k");
		}

		page.locator(outsideTableArea).click();

		return page.getByText(cellMaxInputErrorMessage).isHidden();
	}

	public boolean editGermanNameCellBeyondRange(int inputlength) {
		page.locator(firstRowThirdColumn).click();
//Clearing the existing input
		page.locator(firstRowThirdColumn).press("ControlOrMeta+a");

		for (int i = 0; i < inputlength; i++) {
			page.locator(firstRowThirdColumn).press("k");
		}

		page.locator(outsideTableArea).click();
		return page.getByText(cellMaxInputErrorMessage).isVisible();
	}

	public void isDowntimeRootCauseCellEditableWithMultiselectOptions() {
		page.locator(firstRowFourthColumn).click();
		while (page.getByLabel(removeItemButtonLabel).nth(0).isVisible()) {
			page.locator(removeItemButtonLabel).click();

			// Wait for the page to reload after each removal
			page.waitForLoadState(LoadState.DOMCONTENTLOADED);

			// Click on the cell again to re-open the dropdown after the reload
			page.locator(removeItemButtonLabel).click();
		}

		page.getByRole(AriaRole.COMBOBOX).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(downTimeRootCauseOption1)).click();
		page.getByRole(AriaRole.COMBOBOX).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(downTimeRootCauseOption2)).click();

		page.locator("#downtime_reasonl1_editor_paginate").click();

		System.out.println(page.locator(firstRowFourthColumn).textContent());
	}

	public boolean isEquipmentCellNotEditable() {
		String initialContent = page.locator(firstRowFifthColumn).textContent();
		page.locator(firstRowFifthColumn).click();
		String ContentAfterClick = page.locator(firstRowFifthColumn).textContent();

		if (initialContent.equals(ContentAfterClick)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean editDescriptionCell(int inputlength) {
		page.locator(firstRowSeventhColumn).click();
		// Clearing the existing input
		page.locator(firstRowSeventhColumn).press("ControlOrMeta+a");

		for (int i = 0; i < inputlength; i++) {
			page.locator(firstRowSeventhColumn).press("D");
		}

		page.locator(outsideTableArea).click();

		return page.getByText(cellMaxInputErrorMessage).isHidden();
	}

	public boolean editDescriptionCellBeyondRange(int inputlength) {
		page.locator(firstRowSeventhColumn).click();
//Clearing the existing input
		page.locator(firstRowSeventhColumn).press("ControlOrMeta+a");

		for (int i = 0; i < inputlength; i++) {
			page.locator(firstRowSeventhColumn).press("d");
		}

		page.locator(outsideTableArea).click();

		assertThat(page.getByText(cellMaxInputErrorMessage)).isVisible();

		return page.getByText(cellMaxInputErrorMessage).isVisible();
	}

	public boolean isEditingOrderByWithIntegerValuePossible(String orderByValue) {
		page.locator(firstRowSixthColumn).click();
		page.locator(firstRowSixthColumn).press("ControlOrMeta+a");
		page.locator(firstRowSixthColumn).pressSequentially(orderByValue);
		page.locator(outsideTableArea).click();
		return page.getByText(invalidOrderByValueErrorMessage).isHidden();

	}

	public boolean isEditingOrderByWithNonIntegerValuesPossible(String orderByValue) {
		page.locator(firstRowSixthColumn).click();
		page.locator(firstRowSixthColumn).press("ControlOrMeta+a");
		page.locator(firstRowSixthColumn).pressSequentially(orderByValue);
		page.locator(outsideTableArea).click();
		assertThat(page.getByText(invalidOrderByValueErrorMessage)).isVisible();

		return page.getByText(invalidOrderByValueErrorMessage).isVisible();

	}

	public boolean isEditingOrderByWithDuplicateValuesNotAllowed() {

		page.locator(firstRowSixthColumn).click();
		page.locator(firstRowSixthColumn).press("ControlOrMeta+a");

		page.locator(firstRowSixthColumn).pressSequentially(getMeSecondRowsOrderByValue());
		page.locator(outsideTableArea).click();
		assertThat(page.getByText(duplicateOrderByValueErrorMessage)).isVisible();
		return page.getByText(duplicateOrderByValueErrorMessage).isVisible();

	}

	public String getMeSecondRowsOrderByValue() {
		return page.locator(secondRowSixthColumn).innerText();
	}

//	public int getOneOfTheExistingOrderByValue() {
//		assertThat(page.locator(firstRowSecondSecondColumn)).isVisible();
//		List<Integer> existingOrderByValues = new ArrayList<>();
//
//		int rowCount = page.locator(rows).count();
//
//		// Iterate through each row and get the 6th column value
//		for (int row = 1; row <= rowCount; row++) {
//
//			String cellXPath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[" + 6 + "]";
//			String cellValue = page.locator(cellXPath).textContent().trim();
//			int orderByValue = Integer.parseInt(cellValue);
//			existingOrderByValues.add(orderByValue);
//
//		}
//
//		return existingOrderByValues;
//	}

	public int getRowCount() {
		page.waitForTimeout(5000);
		return page.locator(rows).count();
	}

	public void printTableContent() {

		assertThat(page.locator(firstRowSecondSecondColumn)).isVisible();

		int rowCount = page.locator(rows).count(); // Get the number of rows
		int colCount = page.locator(cols).count(); // Get the number of columns in the first row
		System.out.println(rowCount);
		System.out.println(colCount);
		for (int row = 1; row <= rowCount; row++) {
			for (int col = 1; col <= colCount; col++) {

				// Construct the dynamic XPath
				String dynamicXPath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[" + col + "]";

				// Get the content of each cell
				String cellContent = page.locator(dynamicXPath).textContent();

				// Print the content
				System.out.println("Row " + row + ", Column " + col + ": " + cellContent);
			}
		}

	}

	public List<Integer> getExistingOrderByValues() {
		assertThat(page.locator(firstRowSecondSecondColumn)).isVisible();
		List<Integer> existingOrderByValues = new ArrayList<>();

		int rowCount = page.locator(rows).count();

		// Iterate through each row and get the 6th column value
		for (int row = 1; row <= rowCount; row++) {

			String cellXPath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[" + 6 + "]";
			String cellValue = page.locator(cellXPath).textContent().trim();
			int orderByValue = Integer.parseInt(cellValue);
			existingOrderByValues.add(orderByValue);

		}

		return existingOrderByValues;
	}

	public int getNewOrderByValue() {
		List<Integer> existingOrderByValues = getExistingOrderByValues();
		int maxOrderByValue = Collections.max(existingOrderByValues);
		return maxOrderByValue + 1;
	}

	public class NameGenerator {

		// Method to generate a random name
		public String generateRandomName() {
			String[] names = { "Ravi", "Sam", "Chris", "Jordan", "Sharma", "Morgan", "cummins", "smith", "styris",
					"petty" };
			Random random = new Random();
			int index = random.nextInt(names.length); // Get a random index for the name
			return names[index] + random.nextInt(1000); // Append a random number to ensure uniqueness
		}

	}

	public int getNumberOfEntries() {

		page.waitForTimeout(5000); // Wait for 5 seconds
//		page.getByText("Loading").isHidden();
		// Locate the element containing the entry count
		String text = page.locator(noOfEntriesTextXpath).innerText();

		// Extract the number from the text
		// Example text: "Showing 1 to 7 of 7 entries"
		String[] parts = text.split("of");
		String numberText = parts[1].split("entries")[0].trim();

		return Integer.parseInt(numberText);
	}

	public boolean isRecordHidden(String name) {
		List<String> tableNames = getAllNamesFromTable();
		// Check if the provided name is in the table
		for (String tableName : tableNames) {
			if (tableName.equals(name)) {
				return false; // Record is not hidden, name found
			}
		}
		return true; // Record is hidden
	}

	public boolean isRecordShown(String name) {
		List<String> tableNames = getAllNamesFromTable();
		// Check if the provided name is in the table
		for (String tableName : tableNames) {
			if (tableName.equals(name)) {
				return true; // Record is present, name found
			}
		}
		return false; // Record is absent
	}

	public boolean isRecordGettingHiddenUsingHideCheckBoxFromTable() {
		String nameBeforeHidingFirstRow = getFirstRowsNameValue();
		page.locator(firstRowEigthColumn).check();
		return isRecordHidden(nameBeforeHidingFirstRow);

	}

	public String getFirstRowsNameValue() {
		return page.locator(firstRowSecondSecondColumn).innerText();
	}

	public boolean isRecordDeletedSuccessfully(String name) {
		List<String> tableNames = getAllNamesFromTable();
		// Check if the provided name is in the table
		for (String tableName : tableNames) {
			if (tableName.equals(name)) {
				return false; // Record is not hidden, name found
			}
		}
		return true; // Record is hidden
	}

	public List<String> getAllNamesFromTable() {
		assertThat(page.locator(firstRowSecondSecondColumn)).isVisible();
		List<String> allNames = new ArrayList<>();

		// Get the total number of rows in the table
		int rowCount = page.locator(rows).count();

		// Iterate through each row and get the 1st column value (assuming names are in
		// the first column)
		for (int row = 1; row <= rowCount; row++) {

			String cellXPath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[2]";
			String cellValue = page.locator(cellXPath).textContent().trim();
			allNames.add(cellValue);

		}

		return allNames;
	}

	public DownTimePage selectRecordForEditingOrDeletingByName(String recordName) {
		assertThat(page.locator(firstRowSecondSecondColumn)).isVisible();

		int rowCount = page.locator(rows).count();

		for (int row = 1; row <= rowCount; row++) {

			String nameCellXPath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[2]";
			String nameValue = page.locator(nameCellXPath).textContent().trim();

			if (nameValue.equals(recordName)) {

				String checkboxXPath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[1]";

				page.locator(checkboxXPath).click();

				break;
			}
		}
		return this;
	}

	public String getTextFromEquipmentCell(String recordName) {
		assertThat(page.locator(firstRowSecondSecondColumn)).isVisible();

		int rowCount = page.locator(rows).count();

		for (int row = 1; row <= rowCount; row++) {

			String nameCellXPath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[2]";
			String nameValue = page.locator(nameCellXPath).textContent().trim();

			if (nameValue.equals(recordName)) {

				String equipmentCellXpath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[5]";
				page.waitForTimeout(3000);

				return page.locator(equipmentCellXpath).innerText();

			}
		}
		return "NoRecordFound";
	}

	public String getTextFromRootCauseCell(String recordName) {
		assertThat(page.locator(firstRowSecondSecondColumn)).isVisible();

		int rowCount = page.locator(rows).count();

		for (int row = 1; row <= rowCount; row++) {

			String nameCellXPath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[2]";
			String nameValue = page.locator(nameCellXPath).textContent().trim();

			if (nameValue.equals(recordName)) {

				String rootCauseCellXpath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[4]";
				page.waitForTimeout(3000);
				return page.locator(rootCauseCellXpath).innerText();

			}
		}
		return "NoRecordFound";
	}

	public String getTextFromOrderByCell(String recordName) {
		assertThat(page.locator(firstRowSecondSecondColumn)).isVisible();

		int rowCount = page.locator(rows).count();

		for (int row = 1; row <= rowCount; row++) {

			String nameCellXPath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[2]";
			String nameValue = page.locator(nameCellXPath).textContent().trim();

			if (nameValue.equals(recordName)) {

				String orderByCellXpath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[6]";

				return page.locator(orderByCellXpath).innerText();

			}
		}
		return "NoRecordFound";
	}

	public String getTextFromDescriptionCell(String recordName) {
		assertThat(page.locator(firstRowSecondSecondColumn)).isVisible();

		int rowCount = page.locator(rows).count();

		for (int row = 1; row <= rowCount; row++) {

			String nameCellXPath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[2]";
			String nameValue = page.locator(nameCellXPath).textContent().trim();

			if (nameValue.equals(recordName)) {

				String descriptionCellXpath = "//table[@id='downtime_reasonl1_editor']/tbody/tr[" + row + "]/td[7]";

				return page.locator(descriptionCellXpath).innerText();

			}
		}
		return "NoRecordFound";
	}

	public DownTimePage clickOnDeleteButton() {
		page.locator(deleteButtonXpath).click();
		return this;
	}

	public boolean isdeleteConfirmationMessageVisible() {
		assertThat(page.locator(deleteConfirmationMessageXapth)).isVisible();
		return page.locator(deleteConfirmationMessageXapth).isVisible();

	}

	public DownTimePage clickOnConfirmDeleteButton() {
		page.locator(confirmDeleteButtonXapth).click();
		return this;
	}

	private String editButtonLocator = "Edit";
	private String nameFieldLocator = "Name";
	private String germanNameFieldLocator = "German Name";
	private String orderByFieldLocator = "Order By";
	private String descriptionFieldLocator = "Description";
	private String updateButtonLocator = "Update";
	private String textFieldErrorMessage = "This field is required.";
	private String dropdownsErrorMessage = "minimum one selections please";
	private String inputTooLongErrorMessage="The input is too long. 100";

	public DownTimePage clickOnEditButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(editButtonLocator)).click();
		return this;
	}

	public DownTimePage editName(String name) {
		page.getByLabel(nameFieldLocator, new Page.GetByLabelOptions().setExact(true)).fill(name);
		return this;
	}

	public void clearName() {
		page.getByLabel(nameFieldLocator, new Page.GetByLabelOptions().setExact(true)).clear();
	}

	public void editGermanName(String germanName) {
		page.getByLabel(germanNameFieldLocator, new Page.GetByLabelOptions().setExact(true)).fill(germanName);
	}

	public void clearGermanName() {
		page.getByLabel(germanNameFieldLocator, new Page.GetByLabelOptions().setExact(true)).clear();
	}

	public void clickOutsideTheElements() {
		page.getByRole(AriaRole.DIALOG).getByText("Equipment").click();
	}

	public void selectRootCause(String rootCause) {

		page.locator("div")
				.filter(new Locator.FilterOptions()
						.setHasText(Pattern.compile("^Root Couse 1Root Couse 2Root Couse 3Root Couse 4")))
				.getByRole(AriaRole.COMBOBOX).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(rootCause)).click();

	}

	public void removeASelectedRootCauseOption(String rootCause) {
		page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("Root Couse")).getByLabel("Remove item")
				.nth(0).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(rootCause)).click();

	}

	public void removeAllSelectedRootCauseOptions() {
		assertThat(page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("Root Couse"))
				.getByLabel("Remove item")).isVisible();
		int count = page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("Root Couse"))
				.getByLabel("Remove item").count();
		for (int i = 0; i < count; i++) {
			page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("Root Couse"))
					.getByLabel("Remove item").nth(i).click();
		}

	}

	public void selectEquipment(String equipment) {

		page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^EQ1EQ2EQ3")))
				.getByRole(AriaRole.COMBOBOX).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(equipment)).click();
	}

	public void removeASelectedEquipmentOption() {
		page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("EQ")).getByLabel("Remove item").nth(0)
				.click();

	}

	public void removeAllSelectedEquipmentOptions() {
		assertThat(
				page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("EQ")).getByLabel("Remove item"))
						.isVisible();
		int count = page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("EQ"))
				.getByLabel("Remove item").count();
		for (int i = 0; i < count; i++) {
			page.getByRole(AriaRole.LISTITEM, new Page.GetByRoleOptions().setName("EQ")).getByLabel("Remove item")
					.nth(i).click();
		}

	}

	public DownTimePage editOrderBy(String orderBy) {
		page.getByLabel(orderByFieldLocator, new Page.GetByLabelOptions().setExact(true)).fill(orderBy);
		return this;
	}

	public void clearOrderBy() {
		page.getByLabel(orderByFieldLocator, new Page.GetByLabelOptions().setExact(true)).clear();
		;
	}

	public void editDescription(String description) {
		page.getByLabel(descriptionFieldLocator, new Page.GetByLabelOptions().setExact(true)).fill(description);
	}

	public DownTimePage clearDescription() {
		page.getByLabel(descriptionFieldLocator, new Page.GetByLabelOptions().setExact(true)).clear();
		return this;
	}

	public void clickOnUpdateButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(updateButtonLocator)).click();
	}

	public boolean theErrorMessagesAfterClearingMandatoryFieldWhileEditingAreShown() {
		assertThat(page.getByText(textFieldErrorMessage).first()).isVisible();
		assertThat(page.getByText(textFieldErrorMessage).nth(1)).isVisible();
		assertThat(page.getByText(dropdownsErrorMessage).first()).isVisible();
		assertThat(page.getByText(dropdownsErrorMessage).nth(1)).isVisible();
		return true;
	}

	public boolean clearMandatoryValuesOfARecordandUpdate() {

		clearGermanName();
		clearName();
		removeAllSelectedEquipmentOptions();
		removeAllSelectedRootCauseOptions();
		clickOnUpdateButton();
		return theErrorMessagesAfterClearingMandatoryFieldWhileEditingAreShown();

	}

	public void clearOptionalValuesOfARecordandUpdate() {
		clearOrderBy();
		clearDescription();
		clickOnUpdateButton();

	}

	public void clearSelectedOptionFromEquipmentAndRootCauseDropdown() {

		removeAllSelectedEquipmentOptions();
		removeAllSelectedRootCauseOptions();

	}

	public void selectMultipleOptionsFromEquipmentAndRootCauseDropdownAndUpdate() {
		clickOutsideTheElements();
		selectEquipment("EQ2");
		selectEquipment("EQ3");
		clickOutsideTheElements();
		selectRootCause("Root Couse 3");
		selectRootCause("Root Couse 2");
		clickOnUpdateButton();

	}

	public void editDescriptionWith100Chars(int inputlength) {

		for (int i = 0; i < inputlength; i++) {
			page.getByLabel(descriptionFieldLocator, new Page.GetByLabelOptions().setExact(true)).press("w");

		}
		
		clickOnUpdateButton();
		page.waitForTimeout(3000);
	}
	
	public void editDescriptionWith101Chars(int inputlength) {

		for (int i = 0; i < inputlength; i++) {
			page.getByLabel(descriptionFieldLocator, new Page.GetByLabelOptions().setExact(true)).press("w");

		}
	
		clickOnUpdateButton();
	
		assertThat(page.getByText("The input is too long. 100")).isVisible();
	}
	
	public boolean theInputTooLongMessageIsVisible() {
	return	page.getByText("The input is too long. 100").isVisible();
	}
	
	public boolean isEditingOrderByWithDuplicateValuesNotAllowed2() {
		page.locator(firstRowFirstColumn).click();
		clickOnEditButton();
		clearOrderBy();
		editOrderBy(getMeSecondRowsOrderByValue());
		clickOnUpdateButton();
		assertThat(page.getByText("This field must have a unique")).isVisible();
		return page.getByText("This field must have a unique").isVisible();

	}

	public String getMeSecondRowsOrderByValue2() {
		return page.locator(secondRowSixthColumn).innerText();
	}
	
	
	public boolean windowPopupHidden() {
		assertThat(page.locator(".DTE_Body")).isHidden();
		return page.locator(".DTE_Body").isHidden();
	}
}

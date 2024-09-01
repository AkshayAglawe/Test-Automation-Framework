package com.qa.assignment.pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ManufacturingHandoverRecordPage {
	Page page;

	public ManufacturingHandoverRecordPage(Page page) {
		this.page = page;
	}

	private String showHideFilterLocator = "Show/Hide Filter";
	private String fromDatePickerPlaceholder = "From";
	private String toDatePickerPlaceholder = "To";
	private String dayToggleLocator = "Day";
	private String nightToggleLocator = "Night";
	private String eveningToggleLocator = "Evening";
	private String shiftLeaderDropdownLocator = "Shift Leader";
	private String resourcesDropdownLocator = "Resources";
	private String recordStatusDropdownLocator = "Record Status";
	private String resetButtonLocator = "Reset";
	private String searchButtonLocator = "Search";
	private String nonInteractableArea = "#filter_body";
	private String removeButtonForShiftLeaderDropdown = "//label[text()='Shift Leaders']/parent::div//span//button[@title='Remove item']";
	private String removeButtonForResourcesDropdown = "//label[text()='Resources']/parent::div//span//button[@title='Remove item']";
	private String removeButtonForRecordStatusDropdown = "//label[text()='Record Status']/parent::div//span//button[@title='Remove item']";
	private String yearShownOnDatePicker1 = "(//input[@class='numInput cur-year'])[1]";
	private String yearShownOnDatePicker2 = "(//input[@class='numInput cur-year'])[2]";
	private String arrowUpButtonForDatePicker1 = ".arrowUp";
	private String arrowUpButtonForDatePicker2 = "(//span[@class='arrowUp'])[2]";
	private String arrowDownButtonForDatePicker1 = ".arrowDown";
	private String arrowDownButtonForDatePicker2 = "(//span[@class='arrowDown'])[2]";
	private String monthDropdownDatePicker1 = "Month";
	private String monthDropdownDatePicker2 = "Month";

	private String shiftTypeColumnLocator = "//table//tbody//tr/td[3]";
	private String shiftLeaderColumnLocator = "//table//tbody//tr/td[5]";
	private String statusColumnLocator = "//table//tbody//tr/td[7]";

	private String removeButtons = "//button[@title='Remove item']";

	private String firstRowThirdColumn = "//table//tbody//tr[1]/td[3]";
	private String firstRowFirstColumn = "//table//tbody//tr[1]/td[3]";
	private String firstColumn = "//table//tbody//tr/td[1]";
	private String firstRow = "//table//tbody//tr[1]";
	private String recordStatusColumn = "//table//tbody//tr/td[7]";
	private String rows = "//table//tbody//tr";
	private String statusColumn = "//table/tbody/tr/td[7]";
	
	private String newRecordButtonLocator = "//a[@id='add_module_record']";
	private String editButtonLocator = "//a[@id='edit_module_record']";
	private String deleteButtonLocator = "//a[@id='delete_module_record']";
	private String pdfButtonLocator = "//a[@id='pdf_module_record']";

	private String editWarningText = "This record has been approved";
	private String deleteConfirmationPopupText = " Are you sure, you want to delete the record";

	public ManufacturingHandoverRecordPage clickShowHideFilter() {
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(showHideFilterLocator)).click();
		return this;
	}

	public void clickOnNonInteractableArea() {
		page.locator(nonInteractableArea).click();
	}

	public boolean selectFromDate(int day, int month, int year) {

		page.getByPlaceholder(fromDatePickerPlaceholder).click();
		adjustYear(year);
		selectMonth(String.valueOf(month - 1));
		selectDay(day, month);
		return isDateSelectedInCorrectFormat(day, month, year);

	}

	public void adjustYear(int targetYear) {
		Locator yearInput = page.locator(yearShownOnDatePicker1);
		int currentYear = Integer.parseInt(yearInput.inputValue());

		while (currentYear != targetYear) {
			if (currentYear < targetYear) {

				page.locator(arrowUpButtonForDatePicker1).first().click();
			} else {

				page.locator(arrowDownButtonForDatePicker1).first().click();
			}
			currentYear = Integer.parseInt(yearInput.inputValue());
		}
	}

	public void selectMonth(String month) {
		page.getByLabel(monthDropdownDatePicker1).first().selectOption(month);
	}

	public void selectDay(int day, int month) {

		String monthName = convertMonthNumberToName(month - 1);
		String label = monthName + " " + day + ",";
		Locator dayLocator = page.getByLabel(label).first();
		dayLocator.click();
	}

	private String convertMonthNumberToName(int month) {
		switch (month) {
		case 0:
			return "January";
		case 1:
			return "February";
		case 2:
			return "March";
		case 3:
			return "April";
		case 4:
			return "May";
		case 5:
			return "June";
		case 6:
			return "July";
		case 7:
			return "August";
		case 8:
			return "September";
		case 9:
			return "October";
		case 10:
			return "November";
		case 11:
			return "December";
		default:
			throw new IllegalArgumentException("Invalid month: " + month);
		}
	}

	public boolean isDateSelectedInCorrectFormat(int day, int month, int year) {
		// Capture the selected date from the input field
		Locator inputField = page.locator("//input[@id='from_date']"); // Update the selector as needed
		String selectedDate = inputField.inputValue().trim();

		// Convert the month and day to two-digit strings
		String monthStr = String.format("%02d", month);
		String dayStr = String.format("%02d", day);

		// Create the expected date format string
		String expectedDate = String.format("%s/%s/%d", monthStr, dayStr, year);

		// Check if the selected date matches the expected format
		return selectedDate.equals(expectedDate);
	}

	public boolean selectToDate(int day, int month, int year) {

		page.getByPlaceholder(toDatePickerPlaceholder).click();
		adjustYearDatePickerTo(year);
		selectMonthDatePickerTo(String.valueOf(month - 1));
		selectDayDatePickerTo(day, month);
		return isDateSelectedInCorrectFormat2(day, month, year);
	}

	public void adjustYearDatePickerTo(int targetYear) {
		Locator yearInput = page.locator(yearShownOnDatePicker2);
		int currentYear = Integer.parseInt(yearInput.inputValue());

		while (currentYear != targetYear) {
			if (currentYear < targetYear) {

				page.locator(arrowUpButtonForDatePicker2).first().click();
			} else {

				page.locator(arrowDownButtonForDatePicker2).first().click();
			}
			currentYear = Integer.parseInt(yearInput.inputValue());
		}
	}

	public void selectMonthDatePickerTo(String month) {
		page.getByLabel(monthDropdownDatePicker2).nth(1).selectOption(month);
	}

	public void selectDayDatePickerTo(int day, int month) {

		String monthName = convertMonthNumberToName(month - 1);
		String label = monthName + " " + day + ",";
		Locator dayLocator = page.getByLabel(label).first();
		dayLocator.click();
	}

	public boolean isDateSelectedInCorrectFormat2(int day, int month, int year) {
		// Capture the selected date from the input field
		Locator inputField = page.locator("//input[@id='to_date']"); // Update the selector as needed
		String selectedDate = inputField.inputValue().trim();

		// Convert the month and day to two-digit strings
		String monthStr = String.format("%02d", month);
		String dayStr = String.format("%02d", day);

		// Create the expected date format string
		String expectedDate = String.format("%s/%s/%d", monthStr, dayStr, year);

		// Check if the selected date matches the expected format
		return selectedDate.equals(expectedDate);
	}

	public boolean selectShifts(boolean selectDay, boolean selectNight, boolean selectEvening) {
		if (selectDay) {
			page.getByLabel(dayToggleLocator).check();

			assertThat(page.getByLabel(dayToggleLocator)).isChecked();
		}
		if (selectNight) {
			page.getByLabel(nightToggleLocator).check();
			assertThat(page.getByLabel(nightToggleLocator)).isChecked();
		}
		if (selectEvening) {
			page.getByLabel(eveningToggleLocator).check();
			assertThat(page.getByLabel(eveningToggleLocator)).isChecked();
		}

		return true;
	}

	public ManufacturingHandoverRecordPage selectDayShift() {
		page.getByLabel(dayToggleLocator).check();
		return this;
	}

	public ManufacturingHandoverRecordPage selectEveningShift() {
		page.getByLabel(eveningToggleLocator).check();
		return this;
	}

	public ManufacturingHandoverRecordPage selectNightShift() {
		page.getByLabel(nightToggleLocator).check();
		return this;
	}

	public ManufacturingHandoverRecordPage selectShift(String shift) {
		page.getByLabel(shift).check();
		return this;
	}

	public ManufacturingHandoverRecordPage selectShiftLeader(String leader) {
		page.getByRole(AriaRole.COMBOBOX).first().click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(leader)).click();
		return this;
	}

	public boolean selectMultipleShiftLeaders() {

		page.getByRole(AriaRole.COMBOBOX).nth(0).click();
		int optionCount = page.getByRole(AriaRole.OPTION).count();

		if (optionCount == 0) {
			System.out.println("No options available in the shift leaders dropdown.");
		} else if (optionCount == 1) {
			System.out.println("Only one option available. Selecting the single option.");
			page.locator("(//li[@role='option'])[ 1 ]").click();
		} else {

			for (int i = 1; i <= 2; i++) {
				page.locator("(//li[@role='option'])[" + i + "]").click();
//                page.getByRole(AriaRole.OPTION ).nth(i).click();
				page.getByRole(AriaRole.COMBOBOX).first().click();
			}
		}
		assertThat(page.locator(removeButtonForShiftLeaderDropdown)).hasCount(2);
		return page.locator(removeButtonForShiftLeaderDropdown).nth(0).isVisible();

	}

	public void selectResource(String resource) {
		page.getByRole(AriaRole.COMBOBOX).nth(1).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(resource)).click();
	}

	public boolean selectMultipleResources() {

		page.getByRole(AriaRole.COMBOBOX).nth(1).click();
		int optionCount = page.getByRole(AriaRole.OPTION).count();

		if (optionCount == 0) {
			System.out.println("No options available in the Resources dropdown.");
		} else if (optionCount == 1) {
			System.out.println("Only one option available. Selecting the single option.");
			page.locator("(//li[@role='option'])[ 1 ]").first().click();
		} else {

			for (int i = 1; i <= 2; i++) {
				page.locator("(//li[@role='option'])[" + i + "]").click();
//              page.getByRole(AriaRole.OPTION ).nth(i).click();
				page.getByRole(AriaRole.COMBOBOX).nth(1).click();
			}
		}
		assertThat(page.locator(removeButtonForResourcesDropdown)).hasCount(2);
		return page.locator(removeButtonForResourcesDropdown).nth(0).isVisible();

	}

	public ManufacturingHandoverRecordPage selectRecordStatus(String status) {
		page.getByRole(AriaRole.COMBOBOX).nth(2).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(status)).click();
		return this;
	}

	public boolean selectMultipleRecordStatus() {

		page.getByRole(AriaRole.COMBOBOX).nth(2).click();
		int optionCount = page.getByRole(AriaRole.OPTION).count();

		if (optionCount == 0) {
			System.out.println("No options available in the Record status dropdown.");
		} else if (optionCount == 1) {
			System.out.println("Only one option available. Selecting the single option.");
			page.locator("(//li[@role='option'])[ 1 ]").first().click();
		} else {

			for (int i = 1; i <= 2; i++) {
				page.locator("(//li[@role='option'])[" + i + "]").click();
//            page.getByRole(AriaRole.OPTION ).nth(i).click();
				page.getByRole(AriaRole.COMBOBOX).nth(2).click();
			}
		}
		clickOnNonInteractableArea();
		assertThat(page.locator(removeButtonForRecordStatusDropdown)).hasCount(2);
		return page.locator(removeButtonForRecordStatusDropdown).nth(0).isVisible();

	}

	public void clickResetButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(resetButtonLocator)).click();
	}

	public ManufacturingHandoverRecordPage clickSearchButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(searchButtonLocator)).click();
		return this;
	}

	public boolean allTheFilterElementsVisible() {

		assertThat(page.getByPlaceholder(fromDatePickerPlaceholder)).isVisible();
		assertThat(page.getByPlaceholder(toDatePickerPlaceholder)).isVisible();
		assertThat(page.getByLabel(dayToggleLocator)).isVisible();
		assertThat(page.getByLabel(nightToggleLocator)).isVisible();
		assertThat(page.getByLabel(eveningToggleLocator)).isVisible();
		assertThat(page.getByRole(AriaRole.COMBOBOX).nth(0)).isVisible();
		assertThat(page.getByRole(AriaRole.COMBOBOX).nth(1)).isVisible();
		;
		assertThat(page.getByRole(AriaRole.COMBOBOX).nth(2)).isVisible();
		;
		assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(searchButtonLocator)))
				.isVisible();
		assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(resetButtonLocator)))
				.isVisible();
		return true;
	}

	public boolean validateTableFilters(List<String> expectedShiftTypes, List<String> expectedShiftLeaders,
			List<String> expectedStatuses) {
		// Define locators for each column
		Locator shiftTypeColumn = page.locator(shiftTypeColumnLocator);
		Locator shiftLeaderColumn = page.locator(shiftLeaderColumnLocator);
		Locator statusColumn = page.locator(statusColumnLocator);

		// Get the number of rows
		int rowCount = shiftTypeColumn.count();

		if (rowCount == 0) {
			System.out.println("No records found matching the selected filters.");
			return true; // Assuming an empty table is a valid outcome
		}
		for (int i = 0; i < rowCount; i++) {
			String actualShiftType = shiftTypeColumn.nth(i).innerText().trim();
			String actualShiftLeader = shiftLeaderColumn.nth(i).innerText().trim();
			String actualStatus = statusColumn.nth(i).innerText().trim();

			if (!expectedShiftTypes.contains(actualShiftType)) {
				System.out.println("Mismatch found in Shift Type at row " + (i + 1) + ": expected one of "
						+ expectedShiftTypes + " but found [" + actualShiftType + "]");
				return false;
			}

			boolean leaderFound = false;
			for (String expectedLeader : expectedShiftLeaders) {
				if (actualShiftLeader.contains(expectedLeader)) {
					leaderFound = true;
					break;
				}
			}
			if (!leaderFound) {
				System.out.println("Mismatch found in Shift Leader at row " + (i + 1) + ": expected one of "
						+ expectedShiftLeaders + " but found [" + actualShiftLeader + "]");
				return false;
			}

			if (!expectedStatuses.contains(actualStatus)) {
				System.out.println("Mismatch found in Status at row " + (i + 1) + ": expected one of "
						+ expectedStatuses + " but found [" + actualStatus + "]");
				return false;
			}
		}

		System.out.println("All records match the selected filters.");
		return true;
	}

	public boolean ResetAppliedFilters() {
		selectFromDate(11, 11, 2023);
		selectToDate(8, 07, 2024);
		selectShifts(true, false, true);
		selectMultipleShiftLeaders();
		selectMultipleResources();
		selectMultipleRecordStatus();
		clickResetButton();
		assertThat(page.locator(removeButtons)).isHidden();

		return page.locator(removeButtons).isHidden();

	}

	public void clickNewRecordButton() {
		page.locator(newRecordButtonLocator).click();

	}

	public EditManufacturingHandoverRecordPage clickEditButton() {
		page.locator(editButtonLocator).click();
		return new EditManufacturingHandoverRecordPage(page);
	}

	public void clickDeleteButton() {
		page.locator(deleteButtonLocator).click();
	}

	public void clickPDFButton() {
		page.locator(pdfButtonLocator).click();
	}

	public ManufacturingHandoverRecordPage selectFirstRecordCheckbox() {
		page.locator(firstRowFirstColumn).click();
		return this;
	}

	public boolean checkTheButtonsAreVisible() {
		assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(newRecordButtonLocator)))
				.isVisible();
		assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(editButtonLocator))).isVisible();
		assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(deleteButtonLocator))).isVisible();
		assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(pdfButtonLocator))).isVisible();
		return true;
	}

	public boolean isEditDeletePdfButtonDisabled() {
		unselectSelectedCheckbox();
		String disabledColor = "rgb(205, 209, 210)";
		assertThat(page.locator(editButtonLocator)).hasCSS("background-color", disabledColor);
		assertThat(page.locator(deleteButtonLocator)).hasCSS("background-color", disabledColor);
		assertThat(page.locator(pdfButtonLocator)).hasCSS("background-color", disabledColor);

		return true;
	}

	public boolean isEditDeletePdfButtonEnabled() {
		selectFirstRecordCheckbox();
		String enabledColor = "rgb(131, 226, 255)";
		assertThat(page.locator(editButtonLocator)).hasCSS("background-color", enabledColor);
		assertThat(page.locator(deleteButtonLocator)).hasCSS("background-color", enabledColor);
		assertThat(page.locator(pdfButtonLocator)).hasCSS("background-color", enabledColor);

		return (page.locator(editButtonLocator).isEnabled() && page.locator(deleteButtonLocator).isEnabled()
				&& page.locator(pdfButtonLocator).isEnabled());
	}

//Method to uncheck any selected checkbox
	public void unselectSelectedCheckbox() {

		page.waitForTimeout(5000);
		int count = page.locator(rows).count();

		for (int i = 0; i < count; i++) {
			String classAttr = page.locator(rows).nth(i).getAttribute("class");
			if (classAttr.equals("odd selected")) {
				page.locator(firstColumn).nth(i).click();
			}
		}
	}

	public boolean isEditWarningMessageHiddenForInUseRecordStatus() {
		assertThat(page.locator(firstRowThirdColumn)).isVisible();
		int count = page.locator(recordStatusColumn).count();

		for (int i = 0; i < count; i++) {
			String cellContent = page.locator(recordStatusColumn).nth(i).innerText().trim();
			if (cellContent.equals("In-use")) {
				page.locator(firstColumn).nth(i).click();
				clickEditButton();
				assertThat(page.getByText(editWarningText)).isHidden();
				return true;

			}
		}
		System.out.println("There are no recorsd with in-use status exist in the table");
		return true;
	}

	public boolean isEditWarningMessageVisibleForApprovedRecordStatus() {
		assertThat(page.locator(firstRowThirdColumn)).isVisible();
		int count = page.locator(recordStatusColumn).count();

		for (int i = 0; i < count; i++) {
			String cellContent = page.locator(recordStatusColumn).nth(i).innerText().trim();
			if (cellContent.equals("Approved")) {
				page.locator(firstColumn).nth(i).click();
				clickEditButton();
				assertThat(page.getByText(editWarningText)).isVisible();
				return true;
			}
		}
		System.out.println("There are no recorsd with approved status exist in the table");
		return true;
	}

	public boolean isEditWarningMessageVisible() {
		return page.getByText(editWarningText).isVisible();
	}

	public ManufacturingHandoverRecordPage deleteFirstRecord() {
		page.locator(firstRowFirstColumn).click();

		clickDeleteButton();
		return this;

	}

	public List<String> getFirstRowContent() {
		assertThat(page.locator(firstRowThirdColumn)).isVisible();
		return page.locator(firstRow).allInnerTexts();
	}

	public boolean deleteConfirmationPopupVisible() {
		assertThat(page.getByText(deleteConfirmationPopupText)).isVisible();
		return true;
	}

	private String deleteConfirmButton = "Delete";

	public void clickOnDeleteConfirmButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).click();
		;
	}

	public boolean isRecordDeletedSuccessfully(List<String> beforeDeletionFirstRowContent) {
		System.out.println(beforeDeletionFirstRowContent);
		List<String> afterDeletionFirstRowContent = getFirstRowContent();
		System.out.println(afterDeletionFirstRowContent);
		if (afterDeletionFirstRowContent.equals(beforeDeletionFirstRowContent)) {
			return false;
		}
		return true;
	}

	public String closeButtonDeleteConfirmationPopup = "Close";

	public ManufacturingHandoverRecordPage closeDeleteConfirmationPopup() {
		page.locator("#connx_delete_confirm").getByLabel(closeButtonDeleteConfirmationPopup).click();
		return this;
	}

	public ManufacturingHandoverRecordPage selectFirstRecord() {
		page.locator(firstRowFirstColumn).click();
		return this;
	}



	public ManufacturingHandoverRecordPage selectARecordWithInUseStatus() {
		waitForTableToLoad(firstRowThirdColumn,"Manufacturing Handover Record");
		List<Locator> cells = page.locator(statusColumn).all();
		boolean recordFound = false;
		for (int i = 0; i < cells.size(); i++) {
			String cellText = cells.get(i).innerText().trim();
			System.out.println();
			if (cellText.equals("In-use")) {
				String checkBoxXpath = "//table/tbody/tr[" + (i + 1) + "]/td[1]";
				page.locator(checkBoxXpath).click();
				recordFound = true;
				break;
			}
		}
		if (!recordFound) {
			System.out.println("No record with 'In-use' status found.");
		}
		return this;
	}
	
	public void waitForTableToLoad(String cellLocator, String tableName) {
		assertThat(page.locator(cellLocator)).isVisible();
		if (!page.locator(cellLocator).isVisible()) {
			System.out.println("There are no rows in the " + tableName + " table.");
		}

	}
	
	
	
	 public Map<String, String> captureCurrentShiftDetailsFromManufacturingHandoverPageTable() {
		 waitForTableToLoad(firstRowThirdColumn,"Manufacturing Handover Record");
	    	        Map<String, String> currentShiftDetailsFromManufacturingHandoverPageTable = new HashMap<String, String>();

	        
	    	        currentShiftDetailsFromManufacturingHandoverPageTable.put("Date", page.locator("//table[@id='datatable_ajax']/tbody/tr[1]/td[2]").innerText().trim());
	    	        currentShiftDetailsFromManufacturingHandoverPageTable.put("ShiftType", page.locator("//table[@id='datatable_ajax']/tbody/tr[1]/td[3]").innerText().trim());
	    	        currentShiftDetailsFromManufacturingHandoverPageTable.put("Operation", page.locator("//table[@id='datatable_ajax']/tbody/tr[1]/td[4]").innerText().trim());
	    	        currentShiftDetailsFromManufacturingHandoverPageTable.put("ShiftLeader", page.locator("//table[@id='datatable_ajax']/tbody/tr[1]/td[5]").innerText().trim());
	    	        currentShiftDetailsFromManufacturingHandoverPageTable.put("Resources", page.locator("//table[@id='datatable_ajax']/tbody/tr[1]/td[6]").innerText().trim());

	        return currentShiftDetailsFromManufacturingHandoverPageTable;
	    }
	 
	 public Map<String, String> capturePreviousShiftDetailsFromManufacturingHandoverPageTable() {
		 waitForTableToLoad(firstRowThirdColumn,"Manufacturing Handover Record");
	        Map<String, String> PreviousShiftDetailsFromManufacturingHandoverPageTable = new HashMap<String, String>();

 
	        PreviousShiftDetailsFromManufacturingHandoverPageTable.put("Date", page.locator("//table[@id='datatable_ajax']/tbody/tr[2]/td[2]").innerText().trim());
	        PreviousShiftDetailsFromManufacturingHandoverPageTable.put("ShiftType", page.locator("//table[@id='datatable_ajax']/tbody/tr[2]/td[3]").innerText().trim());
	        PreviousShiftDetailsFromManufacturingHandoverPageTable.put("Operation", page.locator("//table[@id='datatable_ajax']/tbody/tr[2]/td[4]").innerText().trim());
	        PreviousShiftDetailsFromManufacturingHandoverPageTable.put("ShiftLeader", page.locator("//table[@id='datatable_ajax']/tbody/tr[2]/td[5]").innerText().trim());
	        PreviousShiftDetailsFromManufacturingHandoverPageTable.put("Resources", page.locator("//table[@id='datatable_ajax']/tbody/tr[2]/td[6]").innerText().trim());

 return PreviousShiftDetailsFromManufacturingHandoverPageTable;
}

//	// Method to select a record and generate the PDF
//	 public String generateAndDownloadPDF() {
//	
//		  Download download = page.waitForDownload(() -> {
//		        Page page2 = page.waitForPopup(() -> {
//		          page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("PDF")).click();
//		        });
//		      });
//		 
//		    	     System.out.println(download.url());
//		    	return download.url();
//		    }

}

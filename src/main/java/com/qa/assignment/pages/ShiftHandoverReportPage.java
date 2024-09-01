package com.qa.assignment.pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ShiftHandoverReportPage {
	Page page;

	private String priorityColumn = "(//table)[14]/thead/tr/th[1]";
	private String actionTypeColumn = "(//table)[14]/thead/tr/th[2]";
	private String responsibleColumn = "(//table)[14]/thead/tr/th[3]";
	private String descriptionColumn = "(//table)[14]/thead/tr/th[4]";
	private String actionColumn = "(//table)[14]/thead/tr/th[5]";
	private String loggedByColumn = "(//table)[14]/thead/tr/th[6]";
	private String loggedDateColumn = "(//table)[14]/thead/tr/th[7]";
	private String fileColumn = "(//table)[14]/thead/tr/th[8]";
	private String parentColumn = "(//table)[14]/thead/tr/th[9]";
	private String dueDateColumn = "(//table)[14]/thead/tr/th[10]";
	private String statusColumn = "(//table)[14]/thead/tr/th[11]";
	private String mmColumn = "(//table)[14]/thead/tr/th[12]";

	private String parentColumnCellsLocator = "(//table)[14]/tbody/tr/td[9]";
	private String parentColumnCellsLocatorHavingAlink = "(//table)[14]/tbody/tr/td[9]/a";
	private String rows = "(//table)[14]/tbody/tr";
	private String firstRowThirdColumn = "(//table)[14]/tbody/tr[1]/td[3]";

	private String currentSHRbutton = "Current SHR";
	private String previousSHRbutton = "Previous SHR";
	private String pdfButton = "PDF";

	public ShiftHandoverReportPage(Page page) {
		this.page = page;
	}

	public ShiftHandoverReportPage clickCurrentSHRbutton() {
	page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(currentSHRbutton)).click();;
		return this;
	}

	public ShiftHandoverReportPage clickPreviousSHRbutton() {
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(previousSHRbutton)).click();
		return this;
	}

	public boolean areButtonsVisible() {
		assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(currentSHRbutton))).isVisible();
		assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(previousSHRbutton))).isVisible();
		assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(pdfButton))).isVisible();
		return true;
	}

	public boolean isAllColumnsOfActionTableVisible() {
		assertThat(page.locator(actionTypeColumn)).isVisible();
		assertThat(page.locator(responsibleColumn)).isVisible();
		assertThat(page.locator(descriptionColumn)).isVisible();
		assertThat(page.locator(actionColumn)).isVisible();
		assertThat(page.locator(priorityColumn)).isVisible();
		assertThat(page.locator(loggedByColumn)).isVisible();
		assertThat(page.locator(loggedDateColumn)).isVisible();
		assertThat(page.locator(fileColumn)).isVisible();
		assertThat(page.locator(parentColumn)).isVisible();
		assertThat(page.locator(dueDateColumn)).isVisible();
		assertThat(page.locator(statusColumn)).isVisible();
		assertThat(page.locator(mmColumn)).isVisible();
		return true;
	}

	public EditManufacturingHandoverRecordPage clickOnParentCellOfArecordWithInApprovalStatus() {
		page.pause();
		waitForTableToLoad(firstRowThirdColumn, "Actions");
		Locator rowElements = page.locator(rows);
		int totalRows = rowElements.count();

		for (int i = 0; i < totalRows; i++) {

			String parentCellXPath = "(" + parentColumnCellsLocator + ")[" + (i + 1) + "]";
			Locator parentCell = page.locator(parentCellXPath);

			// Check if the parent cell has an `i` tag (arrow button)
			if (parentCell.locator("i").count() > 0) {

				String statusCellXPath = rows + "[" + (i + 1) + "]/td[11]";
				String statusText = page.locator(statusCellXPath).innerText().trim();

				// Click the arrow button if the status is "In Approval"
				if (statusText.equalsIgnoreCase("In Approval")) {

					Page page1 = page.waitForPopup(() -> {
						parentCell.locator("i").click();
					});
					return new EditManufacturingHandoverRecordPage(page1); // because a in a new tab
																			// EditManufacturingHandoverRecord page
																			// opens
				}
			}
		}

		throw new NoSuchElementException("No records with arrow button and 'In Approval' status found.");
	}

	public String getLoggedByOfParentCellOfArecordWithInApprovalStatus() {

		waitForTableToLoad(firstRowThirdColumn, "Actions");
		Locator rowElements = page.locator(rows);
		int totalRows = rowElements.count();

		for (int i = 0; i < totalRows; i++) {

			String parentCellXPath = "(" + parentColumnCellsLocator + ")[" + (i + 1) + "]";
			Locator parentCell = page.locator(parentCellXPath);

			// Check if the parent cell has an `i` tag (arrow button)
			if (parentCell.locator("i").count() > 0) {

				String statusCellXPath = rows + "[" + (i + 1) + "]/td[11]";
				String statusText = page.locator(statusCellXPath).innerText().trim();

				// Click the arrow button if the status is "In Approval"
				if (statusText.equalsIgnoreCase("In Approval")) {

					String loggedBycellXpath = rows + "[" + (i + 1) + "]" + "/td[6]";
					return page.locator(loggedBycellXpath).innerText().trim();

				}
			}
		}

		throw new NoSuchElementException("No records with arrow button and 'In Approval' status found.");
	}

	public void waitForTableToLoad(String cellLocator, String tableName) {
		assertThat(page.locator(cellLocator)).isVisible();
		if (!page.locator(cellLocator).isVisible()) {
			System.out.println("There are no rows in the " + tableName + " table.");
		}

	}

	public Map<String, String> captureCurrentShiftHandoverDetailsFromCurrentSHR() {
		waitForTableToLoad(firstRowThirdColumn, "Actions");
		page.waitForLoadState();
		
		Map<String, String> currentShiftDetailsFromCurrentSHR = new HashMap<String, String>();
		String combinedDateAndShift = page.locator("(//h3)[1]").innerText().trim();
		String[] result = extractDateAndShift(combinedDateAndShift);
		String date = result[0];
		String shift = result[1];
		currentShiftDetailsFromCurrentSHR.put("Date", date);
		currentShiftDetailsFromCurrentSHR.put("ShiftType", shift);
		currentShiftDetailsFromCurrentSHR.put("Operation", page.locator("//label[text()='Operation']/parent::div//div/input").inputValue().trim());
		currentShiftDetailsFromCurrentSHR.put("ShiftLeader",
				page.locator("(//b/parent::a//following-sibling::div/textarea)[1]").inputValue().trim());
		currentShiftDetailsFromCurrentSHR.put("Resources",
				page.locator("(//b/parent::a//following-sibling::div/textarea)[2]").inputValue().trim());

		return currentShiftDetailsFromCurrentSHR;
	}

	public Map<String, String> capturePreviousShiftHandoverDetailsFromCurrentSHR() {
		
		page.waitForLoadState();
		
		Map<String, String> previousShiftDetailsFromCurrentSHR = new HashMap<String, String>();
		String combinedDateAndShift = page.locator("(//h3)[1]").innerText().trim();
		String[] result = extractDateAndShift(combinedDateAndShift);
		String date = result[0];
		String shift = result[1];
		previousShiftDetailsFromCurrentSHR.put("Date", date);
		previousShiftDetailsFromCurrentSHR.put("ShiftType", shift);
		previousShiftDetailsFromCurrentSHR.put("Operation", page.locator("//label[text()='Operation']/parent::div//div/input").inputValue().trim());
		previousShiftDetailsFromCurrentSHR.put("ShiftLeader",
				page.locator("(//textarea)[3]").inputValue().trim());
		previousShiftDetailsFromCurrentSHR.put("Resources",
				page.locator("(//textarea)[4]").inputValue().trim());

		return previousShiftDetailsFromCurrentSHR;
	}
	public static String[] extractDateAndShift(String combinedText) {

		int hyphenIndex = combinedText.lastIndexOf("-");
		String shift = combinedText.substring(hyphenIndex + 1); // "Day"
		String date = combinedText.substring(0, hyphenIndex); // "28 Aug 2024"

		return new String[] { date.trim(), shift.trim() };
	}

	
}
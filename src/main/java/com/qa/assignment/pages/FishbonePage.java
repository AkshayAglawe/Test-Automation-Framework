package com.qa.assignment.pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class FishbonePage {
	Page page;

	public FishbonePage(Page page) {
		this.page = page;
	}

	private String downtimeCategoriesLocator = "Category";
	private String rows = "//table/tbody/tr";
	private String popupTable = "//table";
	private String durationRowXpath = "//table/tbody/tr/td[1]";
	private String firstRowSecondColumn = "//table/tbody/tr[1]/td[2]";
	private String closeButton = "(//div[@class='btn-close'])[1]";

	private String operationPlaceholder = "(//span[@role='combobox'])[1]";
	private String selectCategoryPlaceholder = "(//span[@role='combobox'])[2]";
	private String selectRootCausePlaceholder = "(//span[@role='combobox'])[3]";
	private String selectReasonL1Placeholder = "(//span[@role='combobox'])[4]";
	private String selectReasonL2Placeholder = "(//span[@role='combobox'])[5]";
	private String selectShiftTypePlaceholder = "(//span[@role='combobox'])[6]";
	private String serachButtonLocator = "Search";
	private String showHideFilterLocator = "Show/Hide Filter";
	private String exportButtonLocator = "Export";

	public boolean doesTheSumShownOnScreenMatchesWithSumOfDurationInTable() {
		assertThat(page.locator("#myViewer").getByText(downtimeCategoriesLocator).nth(0)).isVisible();
		page.locator("#myViewer").getByText(downtimeCategoriesLocator).nth(0).click();
		assertThat(page.locator(firstRowSecondColumn)).isVisible();
		List<String> durations = page.locator(durationRowXpath).allInnerTexts();
		int totalHours = 0;
		int totalMinutes = 0;
		int durationCount = 0;
		for (String duration : durations) {
			System.out.println(duration);
			if (duration.contains(":")) {
				durationCount++;
				String[] parts = duration.split(":");
				int hours = Integer.parseInt(parts[0]);
				int minutes = Integer.parseInt(parts[1]);

				totalHours += hours;
				totalMinutes += minutes;
			}
		}

		// Handle minute overflow
		totalHours += totalMinutes / 60;
		totalMinutes = totalMinutes % 60;
		String actualDuration = String.format("%d:%02d", totalHours, totalMinutes);
		clickCloseButton();
		String rowCountString = String.valueOf(durationCount);
		page.reload();
		String expectedSumToBeShown = actualDuration + "/" + rowCountString;
		System.out.println(expectedSumToBeShown);
		assertThat(page.locator("#myViewer")).containsText(actualDuration + "/" + rowCountString);
		return true;
	}

	public void clickCloseButton() {
		page.locator(closeButton).click();
	}

	public FishbonePage clickOnShowHideFilterButton() {
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(showHideFilterLocator)).click();
		return this;
	}

	public FishbonePage selectOperations(String operation) {
		page.locator(operationPlaceholder).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(operation)).click();
		return this;
	}

	public FishbonePage selectDowntimeCategory(String category) {
		page.locator(selectCategoryPlaceholder).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(category)).click();
		return this;
	}

	public FishbonePage selectRootCause(String rootCause) {
		page.locator(selectRootCausePlaceholder).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(rootCause)).click();
		return this;
	}

	public FishbonePage selectReasonL1(String reasonL1) {
		page.locator(selectReasonL1Placeholder).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(reasonL1)).click();
		return this;
	}

	public FishbonePage selectReasonL2(String reasonL2) {
		page.locator(selectReasonL2Placeholder).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(reasonL2)).click();
		return this;
	}

	public FishbonePage selectShiftType(String shiftType) {
		page.locator(selectShiftTypePlaceholder).click();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(shiftType)).click();
		return this;
	}

	public FishbonePage clickOnSearchButton() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(serachButtonLocator)).click();
		return this;
	}

	Download download;

	public FishbonePage clickOnExportButton() {
		download = page.waitForDownload(() -> {
			// Perform the action that initiates download
			page.pause();
			page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(exportButtonLocator)).click();
			page.pause();
		});
		return this;

	}

	public boolean validateTheExportedExcelFile(List<String> expectedOperations, List<String> expectedCategories,
			List<String> expectedRootCauses, List<String> expectedDowntimeReasonL2, List<String> expectedShiftTypes)
			throws IOException {
		Path filePath = download.path();
		FileInputStream fis = new FileInputStream(filePath.toFile());
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = workbook.getSheet("Sheet1");
		readSheet(sheet);
		int rows = sheet.getLastRowNum();
		System.out.println(rows + "--");
		if (rows <= 1) {
			System.out.println("No records matching your filters");
			return true;
		}
		for (int i = 1; i < rows; i++) {
			Row row = sheet.getRow(i);

			Cell operationCell = row.getCell(getColumnIndex(sheet, "Operation"));
			String operation = operationCell.getStringCellValue();
			if (!expectedOperations.contains(operation)) {
				System.out.println("Mismatch found in  operation at row " + (i + 1) + ": expected one of "
						+ expectedOperations + " but found [" + operation + "]");
				return false;
			}

			Cell downtimeCategoryCell = row.getCell(getColumnIndex(sheet, "Downtime Category"));
			String downtimeCategory = downtimeCategoryCell.getStringCellValue();
			if (!expectedCategories.contains(downtimeCategory)) {
				System.out.println("Mismatch found in  downtime category at row " + (i + 1) + ": expected one of "
						+ expectedCategories + " but found [" + downtimeCategory + "]");
				return false;
			}

			Cell rootCauseCell = row.getCell(getColumnIndex(sheet, "Downtime Root Cause"));
			String rootCause = rootCauseCell.getStringCellValue();
			if (!expectedRootCauses.contains(rootCause)) {
				System.out.println("Mismatch found in Root Cause at row " + (i + 1) + ": expected one of "
						+ expectedRootCauses + " but found [" + rootCause + "]");
				return false;
			}

			Cell downtimeReasonL2Cell = row.getCell(getColumnIndex(sheet, "Downtime Reason L2"));
			String downtimeReasonL2 = downtimeReasonL2Cell.getStringCellValue();
			if (!expectedDowntimeReasonL2.contains(downtimeReasonL2)) {
				System.out.println("Mismatch found in Downtime Reason L2 at row " + (i + 1) + ": expected one of "
						+ expectedDowntimeReasonL2 + " but found [" + downtimeReasonL2 + "]");
				return false;
			}

			Cell shiftTypeCell = row.getCell(getColumnIndex(sheet, "Shift Type"));
			String shiftType = shiftTypeCell.getStringCellValue();
			if (!expectedShiftTypes.contains(shiftType)) {
				System.out.println("Mismatch found in Shift Type at row " + (i + 1) + ": expected one of "
						+ expectedShiftTypes + " but found [" + shiftType + "]");
				return false;
			}

		}
		System.out.println("All records in downloaded excel match the selected filters.");
		return true;
	}

	public int getColumnIndex(Sheet sheet, String ColumnHeaderName) {
		Row columnHeaderRow = sheet.getRow(0);
		int cellCount = columnHeaderRow.getLastCellNum();
		for (int i = 0; i < cellCount; i++) {
			Cell cell = columnHeaderRow.getCell(i);

			if (cell.getStringCellValue().equalsIgnoreCase(ColumnHeaderName)) {
				return i;
			}
		}
		System.out.println("Column Name not found");
		return -1;
	}

	public void readSheet(Sheet sheet) {
		Iterator<Row> rowItr = sheet.iterator();

		while (rowItr.hasNext()) {
			Row row = rowItr.next();

			Iterator<Cell> cellItr = row.iterator();
			while (cellItr.hasNext()) {

				Cell cell = cellItr.next();
				CellType cellType = cell.getCellType();
				switch (cellType) {
				case STRING:
					System.out.print(cell.getStringCellValue() + " ");
					break;
				case NUMERIC:
					System.out.print(cell.getNumericCellValue() + " ");
					break;

				}

			}
			System.out.println();
		}

	}
	
	 public boolean validateExportedFileName() {
	        // Get the path of the downloaded file
	        Path filePath = download.path();
            String fileName = filePath.getFileName().toString();
 
	        String expectedFileNamePattern = generateExpectedFileNamePattern();

	        // Check if the file name matches the pattern
	        boolean isMatch = Pattern.matches(expectedFileNamePattern, fileName);

	        if (isMatch) {
	            System.out.println("File name is as expected: " + fileName);
	        } else {
	            System.out.println("File name mismatch. Expected pattern: " + expectedFileNamePattern + ", but found: " + fileName);
	        }

	        return isMatch;
	    }
	 
	

	 public String generateExpectedFileNamePattern() {
	     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmm");
	     String currentDateAndTime = dateFormat.format(new Date());
	     return "EviView_Fishbone_" + currentDateAndTime;
	 }

}

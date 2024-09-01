package com.qa.assignment.pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class EditManufacturingHandoverRecordPage {
	Page page;

	public EditManufacturingHandoverRecordPage(Page page) {
		this.page = page;
	}

	private String downTimeTable = "//table[@id='DataTables_Table_1']";
	private String downTimeTableRows = "//table[@id='DataTables_Table_1']/tbody/tr";
	private String downTimeTableFirstRowThirdColumn = "//table[@id='DataTables_Table_1']/tbody/tr[1]/td[3]";
	private String downTimeTableFirstRowNinthColumn = "//table[@id='DataTables_Table_1']/tbody/tr[1]/td[9]";
	private String downTimeTableFirstRowSixthColumn = "//table[@id='DataTables_Table_1']/tbody/tr[1]/td[6]";
	private String downTimeTableFirstRow = "//table[@id='DataTables_Table_1']/tbody/tr[1]";
    private String columnHeaderRow = "//table[@id='DataTables_Table_1']/thead/tr";
    private String firstRowCheckboxDowntimeTable="//table[@id='DataTables_Table_1']//tbody//tr[1]/td[1]";
	
	// Define locators for column headers
	private String downtimeCategoryColumnHeaderLocator = "Downtime Category: activate";
	private String rootCauseColumnHeaderLocator = "Root Cause: activate to sort";
	private String reasonL1ColumnHeaderLocator = "Reason L1: activate to sort";
	private String reasonL2ColumnHeaderLocator = "Reason L2: activate to sort";
	private String startColumnHeaderLocator = "Start: activate to sort";
	private String endColumnHeaderLocator = "End: activate to sort column";
	private String durationColumnHeaderLocator = "Duration: activate to sort";
	private String commentColumnHeaderLocator = "Comment: activate to sort";
	private String loggedByColumnHeaderLocator = "Logged By: activate to sort";
	private String loggedDateColumnHeaderLocator = "Logged Date: activate to sort";
	private String statusColumnHeaderLocator = "Status: activate to sort";
	private String logColumnHeaderLocator = "Log: activate to sort column ascending";
	private String mmColumnHeaderLocator = "MM";
	private String mmColumnDowntimeTable = "//input[@data-table-name='manufacturing_handover_record_downtime']";
    private String backToRecordListButton="Back to Record List";
    
    private String editButtonFromDowntimeTable="(//span[text()='Edit']/parent::button)[2]";
    private String downTimeCatorgoryFromEditDowntimePopup="(//span[@role='combobox'])[4]";
    private String rootCauseFromEditDowntimePopup="(//span[@role='combobox'])[5]";
    private String reasonL1FromEditDowntimePopup="(//span[@role='combobox'])[6]";
    private String reasonL2FromEditDowntimePopup="(//span[@role='combobox'])[7]";
    private String startDatePickerFromEditDowntimePopup="Start";
    private String endDatePickerFromEditDowntimePopup="End";
    private String commentFromEditDowntimePopup="Comment";
    private String updateFromEditDowntimePopup="Update";
 private String nonInteractableAreaFromEditDowntimePopup="//label[text()='Root Cause']";
   
	public List<String> getAllColumnHeadersOfDowntimeTable() {
		waitForTableToLoad(downTimeTableFirstRowThirdColumn, "DownTime");

		List<String> columnHeaders = page.locator(columnHeaderRow).allInnerTexts();
		return columnHeaders;
	}

	public boolean areAllExpectedColumnsVisibleInDownTimeTable() {

		assertThat(
				page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(downtimeCategoryColumnHeaderLocator)))
						.isVisible();
		assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(rootCauseColumnHeaderLocator)))
				.isVisible();
		assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(reasonL1ColumnHeaderLocator)))
				.isVisible();
		assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(reasonL2ColumnHeaderLocator)))
				.isVisible();
		assertThat(
				page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(downtimeCategoryColumnHeaderLocator))
						.getByLabel(startColumnHeaderLocator)).isVisible();
		assertThat(
				page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(downtimeCategoryColumnHeaderLocator))
						.getByLabel(endColumnHeaderLocator)).isVisible();
		assertThat(page.getByLabel(durationColumnHeaderLocator)).isVisible();
		assertThat(
				page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(downtimeCategoryColumnHeaderLocator))
						.getByLabel(commentColumnHeaderLocator)).isVisible();
		assertThat(page.getByLabel(loggedByColumnHeaderLocator)).isVisible();
		assertThat(page.getByLabel(loggedDateColumnHeaderLocator)).isVisible();
		assertThat(
				page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(downtimeCategoryColumnHeaderLocator))
						.getByLabel(statusColumnHeaderLocator)).isVisible();
		assertThat(page.getByLabel(logColumnHeaderLocator, new Page.GetByLabelOptions().setExact(true))).isVisible();
		assertThat(
				page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName(downtimeCategoryColumnHeaderLocator))
						.getByLabel(mmColumnHeaderLocator, new Locator.GetByLabelOptions().setExact(true))).isVisible();

		return true;
	}

	public void uncheckAllMMcheckBoxesFromAllRecords() {
		waitForTableToLoad(downTimeTableFirstRowThirdColumn, "DownTime");
		page.pause();

		for (Locator mmCheckbox : page.locator(mmColumnDowntimeTable).all()) {
			mmCheckbox.uncheck();
		}
	}

	public void waitForTableToLoad(String cellLocator, String tableName) {
		assertThat(page.locator(cellLocator)).isVisible();
		if (!page.locator(cellLocator).isVisible()) {
			System.out.println("There are no rows in the " + tableName + " table.");
		}

	}

	public EditManufacturingHandoverRecordPage selectMMCheckboxOfDownTimeRecord() {
		waitForTableToLoad(downTimeTableFirstRowThirdColumn, "DownTime");
		page.locator(mmColumnDowntimeTable).nth(0).check();
		return this;
	}
	
	public EditManufacturingHandoverRecordPage selectACheckboxOfFirstDownTimeRecordForEditOrDelete() {
		unselectSelectedCheckbox();
		page.locator(firstRowCheckboxDowntimeTable).click();
		return this;
	}
	
	public void unselectSelectedCheckbox() {

		waitForTableToLoad(downTimeTableFirstRowThirdColumn, "DownTime");
		int count = page.locator(downTimeTableRows).count();

		for (int i = 0; i < count; i++) {
			String classAttr = page.locator(downTimeTableRows).nth(i).getAttribute("class");
			if (classAttr.equals("odd selected")) {
				page.locator(firstRowCheckboxDowntimeTable).click();
			}
		}
	}

	public List<String> getDowntimeRecordWhoseMMisChecked() {
	    List<Locator> mmCheckboxes = page.locator(mmColumnDowntimeTable).all();
	    int count = mmCheckboxes.size();
	    List<String> selectedRowRecords = new ArrayList<>();

	    for (int i = 0; i < count; i++) {
	        Locator checkbox = mmCheckboxes.get(i);
	        if (checkbox.isChecked()) {
	            String selectedRowCellsXpath = "//table[@id='DataTables_Table_1']/tbody/tr[" + (i + 1) + "]/td";
	            List<Locator> cells = page.locator(selectedRowCellsXpath).all();

	            for (Locator cell : cells) {
	                String cellText = cell.innerText().trim();
	                selectedRowRecords.add(cellText);
	            }

	             break;
	        }
	    }
	    System.out.println(selectedRowRecords);
	    return selectedRowRecords;
	   
	}

	public ManufacturingHandoverRecordPage clickGoBackToRecordsList() {
	page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(backToRecordListButton)).click();
	return new ManufacturingHandoverRecordPage(page);

}
	
	public EditManufacturingHandoverRecordPage clickOnEditbuttonFromDowntimeTable() {
		page.locator(editButtonFromDowntimeTable).click();
		return this;
	}

	public void selectDowntimeCategoryFromEditDowntimePopup() {
    	page.locator(downTimeCatorgoryFromEditDowntimePopup).click();
    }
    
	public void clearDowntimeCategoryFromEditDowntimePopup() {
    	page.locator(downTimeCatorgoryFromEditDowntimePopup).click();
    page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Select")).click();
    }
	
    public void selectRootCauseFromEditDowntimePopup() {
    	page.locator(rootCauseFromEditDowntimePopup).click();
    }
    
    public void clearRootCauseFromEditDowntimePopup() {
    	page.locator(rootCauseFromEditDowntimePopup).click();
    	page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Select")).click();
    }
    public void selectReasonL1FromEditDowntimePopup() {
    	page.locator(reasonL1FromEditDowntimePopup).click();
    }
    
    public void clearReasonL1FromEditDowntimePopup() {
    	page.locator(reasonL1FromEditDowntimePopup).click();
    	 page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Select")).click();
    }
    public void selectReasonL2FromEditDowntimePopup() {
    	page.locator(reasonL2FromEditDowntimePopup).click();
    }
    
    public void clearReasonL2FromEditDowntimePopup() {
    	page.locator(reasonL2FromEditDowntimePopup).click();
    	 page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Select")).click();
    }
    public void clickOnStartDatePicker() {
    	 page.getByLabel(startDatePickerFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true)).click();
    }
    public void clearStartDatePicker() {
   	 page.getByLabel(startDatePickerFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true)).click();
   	 page.getByLabel(startDatePickerFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true)).clear();
   }
    
    public void clickOnEndDatePicker() {
	    page.getByLabel(endDatePickerFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true)).click();
	    
    }
    
 public void clearEndDatePicker() {
	    page.getByLabel(endDatePickerFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true)).click();
	    page.getByLabel(endDatePickerFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true)).clear();
    }

  public void enterComment(String comment) {
	  page.getByLabel(commentFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true)).click();
	  page.getByLabel(commentFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true)).fill(comment);
  }
  
  public void clearComment() {
	  page.getByLabel(commentFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true)).click();
	  page.getByLabel(commentFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true)).clear();
  }
   
   
  public void clickUpdate() {
	  
	  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(updateFromEditDowntimePopup)).click();
  }
  
  public boolean mandatoryValidationErrorMessagesAreVisible() {
	  assertThat(page.getByText("This field is required.").first()).isVisible();
      assertThat(page.getByText("This field is required.").nth(1)).isVisible();
      assertThat(page.getByText("This field is required.").nth(2)).isVisible();
      assertThat(page.getByText("This field is required.").nth(3)).isVisible();
      assertThat(page.getByText("This field is required.").nth(4)).isVisible();
      return true;
  }
  

  public boolean allTheExpectedFieldsFromEditDowntimePopupAreVisible() {
	  assertThat(page.locator(downTimeCatorgoryFromEditDowntimePopup)).isVisible();
	  assertThat(page.locator(rootCauseFromEditDowntimePopup)).isVisible();
	  assertThat(page.locator(reasonL1FromEditDowntimePopup)).isVisible();
	  assertThat(page.locator(reasonL2FromEditDowntimePopup)).isVisible();
	  assertThat(page.getByLabel(startDatePickerFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true))).isVisible();
	  assertThat( page.getByLabel(endDatePickerFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true))).isVisible();
	  assertThat(page.getByLabel(commentFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true))).isVisible();
	  assertThat( page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(updateFromEditDowntimePopup))).isVisible();
	  return true;
  }
  
  
  public boolean clearAllFieldInputFromDowntimeEditPopup() {
	  clearDowntimeCategoryFromEditDowntimePopup();
	  clearRootCauseFromEditDowntimePopup();
	  clearReasonL1FromEditDowntimePopup();
	  clearReasonL2FromEditDowntimePopup();
	  clearStartDatePicker();
	  clearEndDatePicker();
	  clearComment();
	  clickUpdate();
	 return allTheExpectedFieldsFromEditDowntimePopupAreVisible();
	  
	  
  }
  

  
  public boolean isDropdownSingleSelect(String dropdownLocator, String nonInteractableAreaLocator) {
	
	  page.locator(nonInteractableAreaLocator).click();
	  page.waitForTimeout(5000);
	    page.locator(dropdownLocator).click();
	  assertThat(page.getByRole(AriaRole.OPTION).nth(1)).isVisible();
	    List<Locator> options = page.getByRole(AriaRole.OPTION).all();
	   // options.size() <= 2 because first option is select
	    if (options.size() <= 2) {
	    	System.out.println("Not enough options to test the dropdown is single select or multiselect");
	        return false; 
	    }
        options.get(1).click();// as first option is select
	    String firstSelectedText = page.locator(dropdownLocator).innerText().trim();
	   
	    page.locator(nonInteractableAreaLocator).click();
	    page.locator(dropdownLocator).click();
	 
	    options.get(2).click();
	    String secondSelectedText = page.locator(dropdownLocator).innerText().trim();
//	     System.out.println("1st text "+firstSelectedText+ " 2nd "+secondSelectedText);
	    // Compare the inner texts
	    if (secondSelectedText.contains(firstSelectedText)) {
	        return false; // Multi-select since the second text includes the first
	    } else {
	        return true; // Single-select since the second text does not include the first
	    }
	}
  
  public boolean isDownTimeCatorgoryDropdownSingleselect() {
	 return isDropdownSingleSelect(downTimeCatorgoryFromEditDowntimePopup,nonInteractableAreaFromEditDowntimePopup);
  }

  public boolean isrootCauseDropdownSingleselect() {
		 return isDropdownSingleSelect(rootCauseFromEditDowntimePopup,nonInteractableAreaFromEditDowntimePopup);
	  }
  
  public boolean isReasonL1DropdownSingleselect() {
		 return isDropdownSingleSelect(reasonL1FromEditDowntimePopup,nonInteractableAreaFromEditDowntimePopup);
	  }
  
  public boolean isReasonL2DropdownSingleselect() {
		 return isDropdownSingleSelect(reasonL2FromEditDowntimePopup,nonInteractableAreaFromEditDowntimePopup);
	  }
  
  public boolean validateDateInput(String startDateLocator, String commentTextBoxLocator, String inputDate) {
	  
	  page.waitForLoadState();
	  page.getByLabel(startDateLocator, new Page.GetByLabelOptions().setExact(true)).click();
	  page.getByLabel(startDateLocator, new Page.GetByLabelOptions().setExact(true)).clear();
	  page.getByLabel(startDateLocator, new Page.GetByLabelOptions().setExact(true)).fill(inputDate);
	  page.getByLabel(commentFromEditDowntimePopup, new Page.GetByLabelOptions().setExact(true)).click();
	  page.waitForTimeout(6000);
	  clickUpdate();
	  waitForTableToLoad(downTimeTableFirstRowSixthColumn, "DownTime"); 
	  String selectedDate=page.locator(downTimeTableFirstRowSixthColumn).innerText().trim();
	
	  if (inputDate.equals(selectedDate)) {
			 return true;
		 }
		 return false;
	  

	}

  public boolean isEnterdStartDateInValidFormat() {
	 return validateDateInput(startDatePickerFromEditDowntimePopup,commentFromEditDowntimePopup,"21/11/2023 12:45");
  }
  
  public boolean editingCommentIsPossibleWithLongInput() {
	 
	    page.waitForLoadState();
         clearComment();
       String commentText = generateDynamicTextWithTimestamp();
       enterComment(commentText);
       page.waitForTimeout(6000);
	    clickUpdate();
	    
	    waitForTableToLoad(downTimeTableFirstRowNinthColumn, "DownTime");  
	    String actualUpdatedCellText = page.locator(downTimeTableFirstRowNinthColumn).innerText().trim();

	    return commentText.equals(actualUpdatedCellText);
	}

	
	private String generateDynamicTextWithTimestamp() {
	    String baseText = "This is the comment text - ";
      String timestamp = String.valueOf(System.currentTimeMillis());

	    String commentText = baseText + timestamp;
	    
	    return commentText;
	}

public boolean rowInDowntimeColumnIsHighlightedWithBlueColourOrNot(String loggedBy) {
	 waitForTableToLoad(downTimeTableFirstRowNinthColumn, "DownTime");  
	List<Locator> loggedByCells = page.locator("//table[@id='DataTables_Table_1']/tbody/tr/td[10]").all();
	int rowCount=page.locator(downTimeTableRows).count();
	
	for (int i=0;i<rowCount;i++) {
		
		if(loggedByCells.get(i).innerText().trim().equals(loggedBy)) {
			
			String rowXpath=downTimeTableRows+"["+(i+1)+"]";
			assertThat(page.locator(rowXpath)).hasCSS("background-color","rgb(131, 226, 255)");
			return true;
		}
	}
	return false;
}
	

  }
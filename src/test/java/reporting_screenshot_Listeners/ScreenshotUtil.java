package reporting_screenshot_Listeners;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

import com.microsoft.playwright.Page;

public class ScreenshotUtil {
	 public static void captureScreenshot(Page page, String testName) {
	       
	            
	            Date date = new Date();
	            String currentDate = date.toString();
                String timestamp=String.valueOf(currentDate).replace(" ", "-").replace(":", "-");
	            String screenshotFileName = "./screenshots/"+testName+timestamp+".png";
	          
	                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotFileName)));
	            
	      
	    }
}

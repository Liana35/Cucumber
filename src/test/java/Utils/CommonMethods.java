package Utils;

import StepDefinitions.PageInitializer;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class CommonMethods extends PageInitializer {

  public static WebDriver driver;
    public static void openBrowserAndLaunchApplication(){
        ConfigReader.readProperties();
        String browserType = ConfigReader.getPropertyValue("browserType");

        switch (browserType) {
            case "Chrome":
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--no-sandbox");
                ops.addArguments("--remote-allow-origins=*");
                if(ConfigReader.getPropertyValue("Headless").equals("true")){
                    ops.addArguments("--headless=new");
                }
                driver = new ChromeDriver(ops);
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "IE":
                driver = new InternetExplorerDriver();
                break;
            default:
                driver = new EdgeDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Constants.WAIT_TIME));
        initializePageObjects();  // this will init. all the pages we have in our PageInit class along
        // with the launching of the application

        // To configure the File and pattern it has
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("This is the beginning of my Test Case");
        Log.info("My test case is executing right now");
        Log.warning("My test case might have some trivial issues");



    }


    public static void closeBrowser(){

        Log.info("This test case is about to get completed");
        Log.endTestCase("This test case is finished");
        driver.close();
    }


    public static void doClick(WebElement element){
        element.click();
    }

    public static void sendText(WebElement element, String text)
    {
        element.clear();
        element.sendKeys(text);
    }

    public static Select clickOnDropDown(WebElement element){
        Select select= new Select(element);
        return select;
    }

    public static void selectByValue(WebElement element,String value){
        clickOnDropDown(element).selectByValue(value);
    }

    public static void selectByVisibleText(WebElement element,String text){
        clickOnDropDown(element).selectByVisibleText(text);
    }
    public void selectByIndex(WebElement element, int index){
        clickOnDropDown(element).selectByIndex(index);
    }

    public static void selectByOptions(WebElement element,String text){

       List<WebElement> options = clickOnDropDown(element).getOptions();
       for(WebElement option:options){

           String ddlOptionTest= option.getText();
           if(ddlOptionTest.equals(text)){
               option.click();
           }
       }
    }

    //          ================== SCREENSHOT =============================

    public static byte[] takeScreenshot(String imageName){
        //This casts the webDriver instance 'driver' to TakeScreenShot interface
        // since it's an interface we cant create an object of it, so we cast it
        TakesScreenshot ts=(TakesScreenshot) driver;
        //this captures a screenshot and stores it in a byte array
       byte[] picBytes= ts.getScreenshotAs(OutputType.BYTES); // screenshots are  stored in an array of BYTES

        //This captures the screenshots and stores them as a file in the sourceFile variable
       File sourcePath= ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourcePath, new File(Constants.SCREENSHOT_FILEPATH+imageName+getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return picBytes;
    }


    //method for a time stamp
    // to make time stamp to make screenshots have different names, so they can be saved without substituting each other
    public static String getTimeStamp(String pattern){
        Date date= new Date();
        SimpleDateFormat sdf= new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


}

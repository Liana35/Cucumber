package StepDefinitions;

import Pages.LoginPage;
import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class Login extends CommonMethods {


    @Given("open the browser and launch HRMS application")
    public void open_the_browser_and_launch_hrms_application() {

        openBrowserAndLaunchApplication();

    }


    @When("user enters valid email and valid password")
    public void user_enters_valid_email_and_valid_password() {
       // System.out.println(10/0); for a failrunner
       // LoginPage login=new LoginPage();
        sendText(login.usernameTextBox,ConfigReader.getPropertyValue("username"));
        sendText(login.passwordTextBox,ConfigReader.getPropertyValue("password"));
    }

    @When("click on login button")
    public void click_on_login_button() {
       // LoginPage login=new LoginPage();

    //   WebElement loginBtn= driver.findElement(By.id("btnLogin"));
       doClick(login.loginBtn);
    }

    @Then("user is logged in successfully")
    public void user_is_logged_in_successfully() {
        boolean userloggedIn = driver.findElement(By.xpath("//a[contains(text(), 'Welcome')]")).isDisplayed();
        if (userloggedIn) {
            System.out.println("User is logged in Successfully");
        }
    }

   /* @Then("close the browser")
    public void close_the_browser() {
       closeBrowser();
    }
*/

    @When("user enters valid email {string} and valid password {string}")
    public void user_enters_valid_email_and_valid_password(String username, String password) {
      //  LoginPage login=new LoginPage();
      //  WebElement usernameTextBox=driver.findElement(By.id("txtUsername"));
        sendText(login.usernameTextBox,username);
      //  WebElement passwordTextBox= driver.findElement(By.id("txtPassword"));
        sendText(login.passwordTextBox,password);
    }



    @When("user enters username and password and verifies login")
    public void user_enters_username_and_password_and_verifies_login(DataTable dataTable) {
       // LoginPage login=new LoginPage();
        List<Map<String, String>> userCredentials=dataTable.asMaps();
        for(Map<String, String> userCreds:userCredentials )
        {
            String username=userCreds.get("username");
            String password= userCreds.get("password");
           // WebElement usernameTextBox=driver.findElement(By.id("txtUsername"));
            sendText(login.usernameTextBox,username);
          //  WebElement passwordTextBox= driver.findElement(By.id("txtPassword"));
            sendText(login.passwordTextBox,password);

           // WebElement loginBtn= driver.findElement(By.id("btnLogin"));
            doClick(login.loginBtn);


          // WebElement welcomeIcon = driver.findElement(By.id("welcome"));
           doClick(login.welcomeIcon);

           // WebElement logoutLink=driver.findElement(By.xpath("//a[text()='Logout']"));
            doClick(login.logoutLink);

        }
    }

}

package StepDefinitions;

import Utils.CommonMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends CommonMethods {
    @Before
    public void preConditions(){
        openBrowserAndLaunchApplication();
    }


    //Scenario class holds complete info of your test execution in Cucumber Framework
    @After
    public void postConditions(Scenario scenario){
        byte[] pic;
        if(scenario.isFailed()){
           pic = takeScreenshot("failed/"+scenario.getName());
        }else{
            pic =takeScreenshot("passed/"+scenario.getName());//passed - is where it will be stored
        }

        //attach the screenshot in my report
        scenario.attach(pic,"image/png",scenario.getName());
        closeBrowser();
    }
}

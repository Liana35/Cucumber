package StepDefinitions;

import Pages.AddEmployeePage;
import Pages.LoginPage;

public class PageInitializer {


    // this class will manage the object creation of different Objects in our Framework
    // Instead of calling the page object again and again, this class will take good care of that step
    public  static LoginPage login;
    public  static AddEmployeePage addEmployeePage;
    public  static void initializePageObjects()
    {
        login = new LoginPage();
        addEmployeePage= new AddEmployeePage();
    }
}

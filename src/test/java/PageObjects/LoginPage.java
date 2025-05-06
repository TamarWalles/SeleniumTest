package PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BaseClass{
    @FindBy(how= How.ID,using = "userName")
    private WebElement userName;
    @FindBy(how=How.ID,using = "password")
    private WebElement password;
    @FindBy(how=How.ID,using = "login")
    private WebElement login;
    @FindBy(how=How.ID,using = "newUser")
    private WebElement newUser;
    public void loginAction(String sUserName, String sPassword)
    {   try {
        userName.sendKeys(sUserName);
        password.sendKeys(sPassword);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", login);
        login.click();
    }catch(Exception e){
        System.out.println("send keys failed "+e.getMessage());
    }
    }
}

package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderPage extends BaseClass {
    @FindBy(how= How.ID,using = "gotoStore")
    private WebElement bookStoreApp;

    public void enterToBookStore() {
        try{
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].scrollIntoView();", bookStoreApp);
            bookStoreApp.click();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}

package PageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class MainPage extends BaseClass {
    LoginPage loginPage;
    HeaderPage headerPage;
    BookStorePage bookStorePage;


    @BeforeClass
    public void startSession() {
        initDriver();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        headerPage = PageFactory.initElements(driver, HeaderPage.class);
        bookStorePage = PageFactory.initElements(driver, BookStorePage.class);
    }

    @Test(priority = 1)
    public void login() {
        try {
            loginPage.loginAction(getData("userName"), getData("password"));
        } catch (Exception e) {
            System.out.println("login failed " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void navigateToBookStore() {
        try {
            headerPage.enterToBookStore();
        } catch (Exception e) {
            System.out.println("navigateToBookStore failed " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void checkResSearch() {
        try {
            bookStorePage.searchBook(getData("firstSearch"));
            int size = bookStorePage.countRes();
            assertEquals(Integer.parseInt(getData("firstSearchExpected")), size);
        } catch (Exception e) {
            System.out.println("checkResSearch failed " + e.getMessage());
        } catch (AssertionError e) {
            System.out.println("Assertion checkResSearch failed " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void checkZeroResSearch() {
        try {
            bookStorePage.clearSearch();
            bookStorePage.searchBook(getData("SecondSearch"));
            int size = bookStorePage.countRes();
            assertEquals(Integer.parseInt(getData("SecondSearchExpected")), size);
            assertTrue(bookStorePage.ExistNotFound());
        } catch (Exception e) {
            System.out.println("checkZeroResSearch failed " + e.getMessage());
        } catch (AssertionError e) {
            System.out.println("Assertion checkZeroResSearch failed " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void printAllBooks() {
        try {
            bookStorePage.clearSearch();
            Book[] arrayBooks = bookStorePage.GetArrBooks();
            bookStorePage.print(arrayBooks);
        } catch (Exception e) {
            System.out.println("test6 failed " + e.getMessage());
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}

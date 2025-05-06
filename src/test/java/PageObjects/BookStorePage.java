package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class BookStorePage extends BaseClass {
    @FindBy(how = How.ID, using = "searchBox")
    private WebElement searchBox;
    @FindBy(how= How.CLASS_NAME, using = "mr-2")
    private List<WebElement> bookListSearch;
    @FindBy(how = How.CLASS_NAME,using = "rt-noData")
    private WebElement notFound;
    public void searchBook(String BookName)
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchBox")));
        searchBox.sendKeys(BookName);
    }
    public int countRes(){

        return bookListSearch.size();
    }
    public boolean ExistNotFound(){

        return notFound.isDisplayed();
    }
    public void clearSearch(){
        searchBox.click();
        String search=searchBox.getAttribute("value");
        for(int i=0;i<search.length();i++){
            searchBox.sendKeys(Keys.BACK_SPACE);
        }
    }

    public List<WebElement> getBookListSearch() {
        return bookListSearch;
    }
    public Book [] GetArrBooks(List<WebElement> books) {
        Book [] arrayBooks=new Book[books.size()];
        try{

        for (int i = 0; i < books.size(); i++) {
            String title = driver.findElements(By.className("mr-2")).get(i).getText();
            String author = driver.findElements(By.className("rt-td")).get(2 * i + 1).getText();
            String publisher = driver.findElements(By.className("rt-td")).get(2 * i + 2).getText();
            Book b = new Book(title, author, title);
            arrayBooks[i] = b;
        }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
            }
        return arrayBooks;
    }
    public void print(Book [] books) {
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].toString());
        }
    }
}

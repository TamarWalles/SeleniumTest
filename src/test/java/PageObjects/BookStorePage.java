package PageObjects;

import com.google.gson.internal.bind.util.ISO8601Utils;
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
    @FindBy(how= How.CLASS_NAME, using = "rt-tr-group")
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
        return driver.findElements(By.xpath("//div[@class=\"rt-td\"]/img")).size();
    }
    public boolean ExistNotFound(){

        return notFound.isDisplayed();
    }
    public WebElement getBook(int row) {
        return bookListSearch.get(row);
    }
    public void clearSearch(){
        searchBox.click();
        String search=searchBox.getAttribute("value");
        for(int i=0;i<search.length();i++){
            searchBox.sendKeys(Keys.BACK_SPACE);
        }
    }

    public Book [] GetArrBooks() {
        Book [] arrayBooks=new Book[countRes()];
        try{
            for (int i = 0; i < arrayBooks.length; i++) {
                List<WebElement> temp =getBook(i).findElements(By.className("rt-td"));
                String title=temp.get(1).getText();
                String author=temp.get(2).getText();
                String publisher=temp.get(3).getText();
                arrayBooks[i]=new Book(title,author,publisher);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            }
        return arrayBooks;
    }
    public void print(Book [] books) {
        for(int i = 0; i < books.length; i++) {
            System.out.println(books[i].toString());
        }
    }
}

package demo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.its.ITSPublicEncryptionKey.symmAlgorithm;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWrapper {
    static WebDriverWait wait;
    static boolean status;
    
    public static boolean sendkeys(WebElement textboxElement, String stringToSend,WebDriver driver)
    {
        textboxElement.clear();
        textboxElement.click();
        textboxElement.sendKeys(stringToSend);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String textInTextField = textboxElement.getAttribute("data-initial-value");
        if(stringToSend.equalsIgnoreCase(textInTextField))
            return true;

        return false;

    }

    public static boolean isSelectedElement(List<WebElement> list,String selectedOption,WebDriver driver) throws InterruptedException
    {
        
        String options="";
        for(WebElement ele : list)
        {
            
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            options = ele.getText();
            if(options.equals(selectedOption))
            {    
                ele.click();
                break;
            }

        }
        // Thread.sleep(2000);
        List<WebElement> radioList = driver.findElements(By.xpath("//div[@role='radio']"));
        for(WebElement element : radioList)
        {
            String value = element.getAttribute("aria-checked");
            // System.out.println(value);
            if(value.equals("true"))
            {
                String dataValue = element.getAttribute("data-value");
                // System.out.println(options);
                if(dataValue.equals(options))
                    return true;
            }
        }

        return false;
    }

    public static boolean checkBoxSelection(List<WebElement> checkboxList,WebDriver driver,String[] options) throws InterruptedException
    {
        List<WebElement> radioList = driver.findElements(By.xpath("//div[@role='checkbox']"));
        for(String option : options)
        {
            for(WebElement element : radioList)
            {
                String value = element.getAttribute("data-answer-value");
                if(value.equals(option))
                {
                    // Thread.sleep(2000);
                    element.click();
                }
            }
        }
        // boolean status = false;
        ArrayList<String> list = new ArrayList<>();
        for(WebElement element : radioList)
        {
            String value = element.getAttribute("data-answer-value");
            // System.out.println(value);
            for(String val : options)
            {
                if(value.equals(val))
                {
                    String dataValue = element.getAttribute("aria-checked");
                    // System.out.println(dataValue);
                    if(dataValue.equals("true"))
                    {
                        list.add((value));
                    }
                    
                }
            }
            
        }
        if(list.size() == options.length)
        {
            for(String text : options)
            {
                if(list.contains(text))
                    status = true;
            }
        }
        return status;
    }

    public static boolean dropDownSelection(List<WebElement> list, String textSelectedInDropDown,WebDriver driver) throws InterruptedException
    {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        for(WebElement ele : list)
        {
            // wait.until(ExpectedConditions.visibilityOf(text));
            Thread.sleep(1000);
            String val = ele.getText();
            System.out.println(val);
            if(val.equals(textSelectedInDropDown))
            {
                ele.click();
                // driver.findElement(By.xpath("//div[@role='option']/div/following-sibling::span")).click();
                // String xpathExpression = "(//div[@class='MocG8c HZ3kWc mhLiyf OIC90c LMgvRb']/div)[" + (i + 1) + "]";
                // System.out.println(xpathExpression);
                // driver.findElement(By.xpath(xpathExpression)).click();
                Thread.sleep(2000);
                break;
            }
        }
        return true;
    }
}

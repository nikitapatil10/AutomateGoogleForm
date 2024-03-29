package demo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    WebDriverWait  wait;
    JavascriptExecutor js;
    boolean status = false;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");
        //navigate to the url https://forms.gle/wjPkzeSEk1CM7KgGA
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        System.out.println("end Test case: testCase01");
    }
    public  void testCase02() throws InterruptedException{
        System.out.println("");
        System.out.println("Start Test case: testCase02");
        //locate the name textbox
        // Thread.sleep(3000);
        WebElement textBox = driver.findElement(By.xpath("//div[@class='AgroKb']/div/div/div/div/input"));
        wait.until(ExpectedConditions.elementToBeClickable(textBox));
        //add the name in the textbox
        status = SeleniumWrapper.sendkeys(textBox, "Nikita", driver);
        if(status)
            System.out.println("Text Sent Successfully.");
        else
            System.out.println("Failed to sent the correct text.");

        System.out.println("end Test case: testCase02");
    }

    
    public  void testCase03() throws InterruptedException{
        System.out.println("");
        System.out.println("Start Test case: testCase03");

        //locate the webelement for the 3rd question
        //sent the text as "I want to be the best QA Engineer! 1710572021"
        long epoch = System.currentTimeMillis()/1000;
        // System.out.println(epoch);
        WebElement textArea = driver.findElement(By.xpath("//textarea[@class='KHxj8b tL9Q4c']"));
        
        js.executeScript("arguments[0].scrollIntoView(true);", textArea);
        status = SeleniumWrapper.sendkeys(textArea, "I want to be the best QA Engineer! "+epoch, driver);
        // Thread.sleep(2000);
        if(status)
            System.out.println("Text Sent Successfully.");
        else
            System.out.println("Failed to sent the correct text.");

        System.out.println("end Test case: testCase03");
    }

    public  void testCase04() throws InterruptedException{
        System.out.println("");
        System.out.println("Start Test case: testCase04");
        //locate the radio buttons for the 4th question
        //select the experience which is 0-2
        List<WebElement> list = driver.findElements(By.xpath("//div[@role='radio']/parent::div/following-sibling::div/div/span"));
        status = SeleniumWrapper.isSelectedElement(list, "0 - 2", driver);
        // Thread.sleep(2000);
        if(status)
            System.out.println("Selected expected text.");
        else
            System.out.println("Failed to select the correct text.");

        System.out.println("end Test case: testCase04");
    }

    public  void testCase05() throws InterruptedException{
        System.out.println("");
        System.out.println("Start Test case: testCase05");

        //locate the webelement for the checkboxes for the 5th question
        List<WebElement> checkboxList = driver.findElements(By.xpath("//span[@class='aDTYNe snByac n5vBHf OIC90c']"));
        String[]  options = {"Java","Selenium","TestNG"};
        status = SeleniumWrapper.checkBoxSelection(checkboxList,driver,options);
        if(status)
            System.out.println("Selected expected text.");
        else
            System.out.println("Failed to select the correct text.");
        //select the checkbxes in which you have learned 
        System.out.println("end Test case: testCase05");
    }

    public  void testCase06() throws InterruptedException{
        System.out.println("");
        System.out.println("Start Test case: testCase06");

        //locate the webelement for dropdown of the 6th question
        //select the option from the dropdown
        WebElement dropdownButton =  driver.findElement(By.xpath("(//div[@class='e2CuFe eU809d' and @role='presentation'])[1]"));
        js.executeScript("arguments[0].scrollIntoView(true);", dropdownButton);
        dropdownButton.click();
        List<WebElement> dropdownList = driver.findElements(By.xpath("//span[@class='vRMGwf oJeWuf']/preceding-sibling::div/parent::div"));
        Thread.sleep(1000);
        status = SeleniumWrapper.dropDownSelection(dropdownList, "Mrs", driver);
        // Thread.sleep(5000);
        System.out.println("end Test case: testCase06");
    }

    public  void testCase07() throws InterruptedException{
        System.out.println("");
        System.out.println("Start Test case: testCase07");
        
        WebElement calendarButton = driver.findElement(By.xpath("//div[@class='aXBtI Wic03c']/div/input[@type='date']"));
        js.executeScript("arguments[0].scrollIntoView(true);", calendarButton);
        Thread.sleep(2000);
        calendarButton.click();
        
        // js.executeScript("arguments[0].scrollIntoView(true);", calendarButton);
        LocalDate currentDate = LocalDate.now();
        LocalDate sevenDaysAgo = currentDate.minusDays(7);
        String pattern = "dd/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        Thread.sleep(2000);
        String date = sevenDaysAgo.format(formatter);
        // calendarButton.sendKeys(date);
        Thread.sleep(5000);
        //locate the webelement for the date field of the 7th question
        //select the date 7 days ago
        
        
        System.out.println(sevenDaysAgo.format(formatter));
        
        System.out.println("end Test case: testCase07");
    }

    public  void testCase08() throws InterruptedException{
        System.out.println("");
        System.out.println("Start Test case: testCase08");
        //locate the time webelement of the 8th question
        // Get the current time
        LocalTime currentTime = LocalTime.now();
        // Get the hour component of the current time
        int hour = currentTime.getHour();
        // Adjust hour to 12-hour format
        hour = (hour > 12) ? hour - 12 : hour;
        if (hour == 0) {
            hour = 12; 
        }
        //add the time in the the webelement
        WebElement hourTextField = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        SeleniumWrapper.sendkeys(hourTextField, String.valueOf(hour), driver);
        WebElement minuteTextField = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        SeleniumWrapper.sendkeys(minuteTextField, String.valueOf(currentTime.getMinute()), driver);
        //locate the locator for the AM/PM dropdown
        //select the option AM/PM as per time
        String period = (currentTime.getHour() < 12) ? "AM" : "PM";
        // System.out.println(period);
        // System.out.println("Current time: " + hour + ":" + currentTime.getMinute() + ":" + currentTime.getSecond() + " " + period);
        driver.findElement(By.xpath("(//div[@class='e2CuFe eU809d' and @role='presentation'])[2]")).click();
        Thread.sleep(2000);
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='MocG8c HZ3kWc mhLiyf LMgvRb']/div/following-sibling::span"));
        for(WebElement ele : list)
        {
            
            if(ele.getText().equals(period))
            {
                // System.out.println(ele.getText());
                ele.click();
                break;
            }
        }
        
        System.out.println("end Test case: testCase08");
    }

    public  void testCase09() throws InterruptedException{
        System.out.println("");
        System.out.println("Start Test case: testCase09");
        //insert new url as amazon.in and check the popup is comes
        driver.get("https://www.amazon.in/");
        //select the cancel option
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
        Thread.sleep(2000);
        System.out.println("end Test case: testCase09");
    }

    public  void testCase10() throws InterruptedException{
        System.out.println("");
        System.out.println("Start Test case: testCase10");
        //locate the submit button webelement
        WebElement submitButton = driver.findElement(By.xpath("//span[text()='Submit']/parent::span/parent::div"));
        //click on the submit button
        submitButton.click();
        System.out.println("end Test case: testCase10");
    }
    public  void testCase11() throws InterruptedException{
        System.out.println("");
        System.out.println("Start Test case: testCase11");
        //check the message is displayed as " Thanks for your response, Automateion Wizard!"
        //print the message
       
        System.out.println("end Test case: testCase11");
    }


}

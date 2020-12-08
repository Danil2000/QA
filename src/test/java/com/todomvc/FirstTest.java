package com.todomvc;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FirstTest {
    private ChromeDriver driver;
    private Elements elements;
    private String s;

    @Before
    public void setConnection() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Даниил/Desktop/chromedriver1.exe");
        driver = new ChromeDriver();
        elements = new Elements(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(" http://todomvc.com/examples/angularjs/");
        if (elements.getCount() > 0) {
            elements.delete(s);
        }
        s = "new todo";
        elements.create(s);

    }

    @After
    public void quit() {
        driver.quit();
    }

    @Test
    public void checkConnection() {
        String title = "AngularJS • TodoMVC";
        String currentTitle = driver.getTitle();
        Assert.assertTrue(currentTitle.equals(title));
    }

    @Test
    public void createTest() {
        elements.create(s);
        Assert.assertEquals(1, elements.getCounter());
    }

    @Test
    public void toggleTest() {
        elements.toggle(s);
        Assert.assertEquals(0, elements.getCounter());
    }

    @Test
    public void toggleAllTest() {
        String s2 = "second todo";
        elements.create(s2);
        elements.toggleAll();
        Assert.assertEquals(0, elements.getCounter());
        Assert.assertEquals(2, elements.getCount());
    }

    @Test
    public void deleteTest() {
        String s2 = "second todo";
        String s3 = "second todo";
        elements.create(s2);
        elements.create(s3);
        elements.delete(s);
        elements.delete(s2);
        Assert.assertEquals(1, elements.getCount());
    }

    @Test
    public void editTest() {
        String newTodo1 = "something";
        elements.create(s);
        elements.edit(s, newTodo1);
        Assert.assertEquals(newTodo1, elements.getCertain(newTodo1).getText());
    }

    @Test
    public void checkActiveTest() {
        String s2 = "second todo";
        elements.create(s);
        elements.create(s2);
        elements.toggle(s);
        elements.showActive();
        Assert.assertEquals(1, elements.getCount());
    }
}

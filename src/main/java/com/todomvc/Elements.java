package com.todomvc;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.security.SecureRandom;

public class Elements {
    public WebDriver driver;

    public Elements(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(className = "new-todo")
    private WebElement inputTodo;

    @FindBy(className = "destroy")
    private WebElement deleteTodo;

    @FindBy(css = "label[for=toggle-all]")
    private WebElement toggleAll;

    @FindBy(className = "todo-list")
    private WebElement todoList;

    @FindBy(className = "toggle")
    private WebElement toggleTodo;

    @FindBy(className = "clear-completed")
    private WebElement clearCompleted;

    @FindBy(xpath = "//*[contains(@class,'todo-count')]//*[contains(@class,'ng-binding')]")
    private WebElement todoCount;

    @FindBy(linkText = "All")
    private WebElement allTodos;

    @FindBy(linkText = "Active")
    private WebElement activeTodos;

    @FindBy(linkText = "Completed")
    private WebElement completedTodos;

    public void create(String name) {
        inputTodo.sendKeys(name);
        inputTodo.sendKeys(Keys.RETURN);
    }

    public void toggleAll() {
        toggleAll.click();
    }

    public WebElement  getCertain(String name) {
        return todoList.findElement(By.xpath("//*[contains(text(),'" + name + "')]"));
    }

    public void delete(String name) {
        getCertain(name).click();
        deleteTodo.click();
    }

    public void edit(String name, String changedName) {
        Actions action = new Actions(driver);
        action.doubleClick(getCertain(name)).sendKeys(Keys.chord(Keys.CONTROL, "a"))
                .sendKeys(Keys.DELETE).sendKeys(changedName)
                .sendKeys(Keys.RETURN).perform();
    }

    public int getCounter() {
        return Integer.parseInt(todoCount.getText());
    }

    public int getCount() {
        return todoList.findElements(By.tagName("li")).size();
    }

    public void toggle(String name) {
        getCertain(name).click();
        toggleTodo.click();
    }

    public void showActive() {
        activeTodos.click();
    }

    public void showCompleted() {
        completedTodos.click();
    }

    public void deleteCompleted() {
        clearCompleted.click();
    }
}


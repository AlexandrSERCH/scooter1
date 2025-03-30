package edu.praktikum.samokat;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

public class OrderStatus {

    Faker faker = new Faker();
    String fakeOrder = faker.number().digits(5);
    String fakeName = faker.name().fullName();

    private WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void notFoundOrder() {


        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Получить текст
        //String test = driver.findElement(By.xpath(".//button[@class='Header_Link__1TAG7']")).getText();

        WebElement buttonStatusOrder = driver.findElement(By.xpath(".//button[@class='Header_Link__1TAG7']"));
        buttonStatusOrder.click();

        WebElement inputNumberOrder = driver.findElement(By.xpath(".//input[contains(@class, 'Input_Input__1iN_Z Header_Input__xIoUq')]"));
        inputNumberOrder.click();
        inputNumberOrder.sendKeys(fakeOrder);

        WebElement buttonGo = driver.findElement(By.xpath(".//button[text()='Go!']"));
        buttonGo.click();

        int imgOrderNotFoundSize = driver.findElements(By.xpath(".//img[@alt='Not found']")).size();

        Assert.assertTrue("Сообщение 'Такого заказа нет' отсутствует", imgOrderNotFoundSize > 0);

    }

    @After
    public void quitDriver(){
        driver.quit();
    }

}

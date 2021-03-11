package main;

import driver.WebDriverWrapper;
import org.junit.jupiter.api.Test;
import pageobjects.*;

import java.util.ArrayList;
import java.util.List;

public class RegardTest {
    protected WebDriverWrapper driver = WebDriverWrapper.getInstance();
    MainPage mainPage = new MainPage();
    PlatesPage platesPage = new PlatesPage();
    BodyPage bodyPage = new BodyPage();
    CorsairBodyPage corsairBodyPage = new CorsairBodyPage();
    CartPage cartPage = new CartPage();

    List<String> listOfBuyings = new ArrayList<>();

    @Test
    void testRegard() {
        // 1
        mainPage.open();
        // 2
        mainPage.choosePlateCategory();
        // 3
            listOfBuyings.add(platesPage.buyingsList(5));
        platesPage.addNElementInCart(5);
        // 4
        platesPage.chooseBody("AEROCOOL");
        // 5
            listOfBuyings.add(bodyPage.buyingsList(4));
        bodyPage.addNElementInCart(4);
        // 6
        bodyPage.clickConcreteBody("CORSAIR");
        // 7
        bodyPage.scrollPage(5);
            listOfBuyings.add(bodyPage.buyingsList(10));
        bodyPage.clickOnLinkName(10);
        // 8
        corsairBodyPage.clickOnBigRedCart();
        // 9
        corsairBodyPage.clickOnBigBlueCart();
        // 10
        cartPage.checkIfBuyindsListsSame(listOfBuyings);
        // Ввод данных корзины
        cartPage.scrollPage(5);
        cartPage.enterFieldsInCart();
        // Попытка входа в учетную запись
        cartPage.scrollPage(-15);
        cartPage.tryToLogin("hrgr", "fefe");
        driver.quit();
    }
}

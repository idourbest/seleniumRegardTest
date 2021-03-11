package pageobjects;

import driver.WebDriverWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BasePage {

    static Logger log = LogManager.getRootLogger();

    protected WebDriverWrapper driver = WebDriverWrapper.getInstance();
    protected String pageName;
    protected String address;

    protected BasePage(String pageName, String address) {
        this.pageName = pageName;
        this.address = address;
    }

    public void choosePlateCategory() {
        clickOnPlates();
        clickOnSocket1200();
    }

    public void clickOnPlates() {
        log.info("Кликаю по кнопке 'Материнские платы'");
        WebElement searchPlateCategory = driver.findElement(By.xpath("//*[@id=\"lmenu\"]/ul[1]/li[9]/a"));
        searchPlateCategory.click();
    }

    public void clickOnSocket1200() {
        log.info("Кликаю по кнопке 'Intel Socket 1200'");
        WebElement searchPlateCategory = driver.findElement(By.xpath("//*[@id=\"lmenu\"]/ul[1]/li[9]/ul/li[9]/a"));
        searchPlateCategory.click();
    }

    public void addNElementInCart(int element) {
        log.info(String.format("Кликаю по кнопке 'Добавить в козрину' на %d элементе", element));
        int elementPos = element + 1;
        WebElement addToCartButton = driver.findElement(By.xpath("//div[@id='hits']//div[" + elementPos + "]//a[@title='Добавить в корзину']"));
        addToCartButton.click();
    }

    public void chooseBody(String body) {
        clickBody();
        clickConcreteBody(body);
    }

    public void clickBody() {
        log.info("Кликаю по кнопке 'Корпуса'");
        WebElement body = driver.findElement(By.xpath("//*[@id=\"lmenu\"]/ul[1]/li[8]/a"));
        body.click();
    }

    public void clickConcreteBody(String body) {
        log.info("Кликаю по кнопке '" + body + "'");
        WebElement concreteBody = driver.findElement(By.xpath("//*[@id=\"lmenu\"]/ul[1]/li[8]//a[contains(text(),'" + body + "')]"));
        concreteBody.click();
    }

    public void clickOnLinkName(int element) {
        log.info(String.format("Кликаю по имени %d элемента", element));
        int elementPos = element + 1;
        WebElement addToCartButton = driver.findElement(By.xpath("//*[@id=\"hits\"]/div[2]/div[" + elementPos + "]/div/div[@class='aheader']/a[@class='header']"));

        addToCartButton.click();
    }

    public void clickOnBigRedCart() {
        log.info("Кликаю по большой кнопке 'Добавить в корзину'");
        WebElement bigRedCart = driver.findElement(By.xpath("//*[@id=\"add_cart\"]"));
        bigRedCart.click();
    }

    public void clickOnBigBlueCart() {
        log.info("Кликаю по большой кнопке 'Перейти в корзину'");
        WebElement bigBlueCart = driver.findElement(By.xpath("//*[@id=\"add_cart\"]"));
        bigBlueCart.click();
    }

    public String buyingsList(int element) {
        log.info(String.format("Записываю %d элемент в хранилище покупок", element));
        int elementPos = element + 1;
        WebElement addToBuyingsList = driver.findElement(By.xpath("//*[@id=\"hits\"]/div[2]/div[" + elementPos + "]/div/div[2]/a"));
        return addToBuyingsList.getText();
    }

    public void checkIfBuyindsListsSame(List buyings) {
        for (int i = 0, y = i + 3, counter = 1; i < buyings.size(); i++, y++, counter++) {
            log.info(String.format("Проверяю, есть ли %d элемент в хранилище покупок", counter));
            WebElement findBuyingName = driver.findElement(By.xpath("//*[@id=\"table-basket\"]/tbody/tr[" + y + "]/td[3]/a"));
            String checkedFromSiteCart = findBuyingName.getText();
            Assertions.assertTrue(buyings.contains(checkedFromSiteCart));
        }
    }

    public void scrollPage(int vertical) {
        if (vertical >= 0) {
            for (int i = 0; i < vertical; i++) {
                driver.findElement(By.cssSelector("body")).sendKeys(Keys.DOWN);
            }
        } else {
            for (int i = 0; i > vertical; i--) {
                driver.findElement(By.cssSelector("body")).sendKeys(Keys.UP);
            }
        }
    }

    public void enterFieldsInCart() {
        chooseCity();
        chooseCustomer();
        chooseDelivery();
        adress();
        buyerVariables();
        enterNum();
    }

    private void enterNum() {
        log.info("Ввожу номер покупателя");
        for (int i = 0; i < 2; i++) {
            String suffix;
            String chars;
            if (i == 0) {
                suffix = "kod";
                chars = "999";
            } else {
                suffix = "nom";
                chars = "1234567";
            }
            WebElement phoneEnter = driver.findElement(By.xpath("//*[@id=\"phone_inner_" + suffix + "\"]"));
            phoneEnter.sendKeys(chars);
        }
    }

    private void buyerVariables() {
        log.info("Ввожу данные покупателя");
        WebElement enterFIO = driver.findElement(By.xpath("//*[@id=\"fam_inner\"]"));
        enterFIO.sendKeys("Новый Покупатель");
        WebElement enterEmail = driver.findElement(By.xpath("//*[@id=\"email_inner\"]"));
        enterEmail.sendKeys("novy.pokupatel@bk.ru");
    }

    private void adress() {
        log.info("Ввожу адрес");
        WebElement enterStreet = driver.findElement(By.xpath("//*[@id=\"ulica\"]"));
        enterStreet.sendKeys("Народная");
        driver.wait(1500);
        WebElement enterHome = driver.findElement(By.xpath("//*[@id=\"dom\"]"));
        enterHome.sendKeys("12");
    }

    private void chooseDelivery() {
        log.info("Выбираю тип доставки");
        for (int i = 5; i >= 4; i--) {
            WebElement deliveryVariant = driver.findElement(By.xpath("//*[@id=\"delivery-variables\"]/div[" + i + "]/label/div"));
            deliveryVariant.click();
        }
    }

    private void chooseCustomer() {
        log.info("Выбираю тип покупателя");
        for (int i = 2; i >= 1; i--) {
            WebElement findButton = driver.findElement(By.xpath("//*[@id=\"customerType" + i + "\"]"));
            findButton.click();
        }
    }

    private void chooseCity() {
        log.info("Ввожу город");
        WebElement findCityName = driver.findElement(By.xpath("//*[@id=\"gorod\"]"));
        findCityName.clear();
        findCityName.sendKeys("Ростов");

        for (int i = 0; i < 3; i++) {
            findCityName.click();
        }

        log.info("Выбираю город из списка");
        WebElement findCityNameInList = driver.findElement(By.xpath("//*[@class=\"autocomplete-w1\"]//td[contains(text(),'Ростов, Ярославская обл.')]"));
        findCityNameInList.click();
    }

    public void open() {
        log.info(String.format("Открываю страницу '%s' по адресу '%s'",
                this.pageName,
                this.address));
        driver.get(this.address);
    }

    public void tryToLogin(String logi, String pass) {
        WebElement cabinetButton = driver.findElement(By.xpath("//*[@id=\"main-menu\"]/li[8]/span/span"));
        cabinetButton.click();
        log.info("Пытаюсь зайти в учетную запись с логином " + logi + " и паролем " + pass);

        WebElement login = driver.findElement(By.xpath("//*[@id=\"new_login\"]"));
        log.info("Ввожу логин " + logi);
        login.sendKeys(logi);

        WebElement password = driver.findElement(By.xpath("//*[@id=\"new_password1\"]"));
        log.info("Ввожу пароль " + pass);
        password.sendKeys(pass);

        WebElement enterButton = driver.findElement(By.xpath("//*[@id=\"persona_loginButton\"]"));
        log.info("Нажимаю 'Войти'");
        enterButton.click();
    }
}


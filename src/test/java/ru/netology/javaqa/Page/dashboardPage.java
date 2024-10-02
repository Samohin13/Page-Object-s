package ru.netology.javaqa.Page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.javaqa.DataHelper.dataHelper;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class dashboardPage {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final ElementsCollection cards = $$(".list__item div");
    private final SelenideElement reloadButton = $("[data-test-id='action-reload']");

    public dashboardPage() {
        heading.shouldBe(visible);
    }

    //поиск по маскированному методу
    public int getCardBalance(String maskedCardNumber) {
        val text = cards.findBy(Condition.text(maskedCardNumber)).getText();
        return extractBalance(text);

    }

    // поиск по индексу
    public int getCardBalance(int index) {
        var text = cards.get(index).getText();
        return extractBalance(text);

    }

    //поиск по тестовой метке
    public transferPage selectCardToTransfer(dataHelper.CardInfo cardInfo) {
        cards.findBy(Condition.attribute("data-test-id", cardInfo.getTestId())).$("button").click();
        return new transferPage();
    }


    public void reloadDashboardPage() {
        reloadButton.click();
        heading.shouldBe(visible);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);

    }


}



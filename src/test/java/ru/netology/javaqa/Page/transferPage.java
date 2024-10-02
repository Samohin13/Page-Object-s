package ru.netology.javaqa.Page;


import com.codeborne.selenide.SelenideElement;

import ru.netology.javaqa.DataHelper.dataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class transferPage {
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement amountInput = $("[data-test-id='amount'] input");
    private final SelenideElement fromInput = $("[data-test-id='from'] input");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $("[data-test-id='error-notification'] .notification__content");

    public transferPage() {
        transferHead.shouldBe(visible);

    }

    public dashboardPage makeValidTransfer(String amountTransfer, dataHelper.CardInfo cardInfo) {
        makeTransfer(amountTransfer, cardInfo);
        return new dashboardPage();
    }

    public void makeTransfer(String amountTransfer, dataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}




package ru.netology.javaqa.Page;


import com.codeborne.selenide.SelenideElement;
import ru.netology.javaqa.DataHelper.dataHelper;


import static com.codeborne.selenide.Selenide.$;

public class loginPage {
    // присваиваем переменным тестовые метки через селектор
    private final SelenideElement loginField = $("[data-test-id =login] input");
    private final SelenideElement PasswordField = $("[data-test-id = password] input");
    private final SelenideElement ButtonLogin = $("[data-test-id =action-login]");


    public verificationPage validLogin(dataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        PasswordField.setValue(info.getPassword());
        ButtonLogin.click();
        return new verificationPage();

    }
}


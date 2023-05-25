import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AlphaDebitCardTest {
    @Test
    void shouldSumbitRequest() {
        open("http://localhost:9999");
        SelenideElement request = $(".form");
        request.$("[data-test-id=name] input").setValue("Сильнов Дима");
        request.$("[data-test-id=phone] input").setValue("+79999999999");
        request.$("[data-test-id=agreement]").click();
        request.$(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void notValidDataInName() {
        open("http://localhost:9999");
        SelenideElement request = $(".form");
        request.$("[data-test-id=name] input").setValue("Silnov Dima");
        request.$("[data-test-id=phone] input").setValue("+79999999999");
        request.$("[data-test-id=agreement]").click();
        request.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void notValidDataInPhone() {
        open("http://localhost:9999");
        SelenideElement request = $(".form");
        request.$("[data-test-id=name] input").setValue("Сильнов Дима");
        request.$("[data-test-id=phone] input").setValue("QA");
        request.$("[data-test-id=agreement]").click();
        request.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void notCheckbox() {
        open("http://localhost:9999");
        SelenideElement request = $(".form");
        request.$("[data-test-id=name] input").setValue("Сильнов Дима");
        request.$("[data-test-id=phone] input").setValue("+79999999999");
        //не установили чекбокс, но пытаемся создать заявку
        request.$(".button").click();
        $(".input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
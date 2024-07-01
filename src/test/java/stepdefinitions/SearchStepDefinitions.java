package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import ui.CartForm;
import ui.LogInForm;
import utils.action.Click;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static ui.CartForm.ORDER_CONFIRMATION;

public class SearchStepDefinitions {
    @Managed
    WebDriver driver;


    @Given("{actor} tries to launch the Swag Labs website and logs in")
    public void tryToLaunchTheSwagLabsWebsiteAndLogIn(Actor actor) {
        actor.can(BrowseTheWeb.with(driver));
        driver.manage().window().maximize();
        actor.attemptsTo(
                Open.url("https://www.saucedemo.com/"),
                Enter.theValue("standard_user").into(LogInForm.USER_NAME),
                Enter.theValue("secret_sauce").into(LogInForm.PASS),
                Click.on(LogInForm.LOGIN),
                Click.on(LogInForm.ADD_TO_CART)
        );
    }

    @When("{string} adds product to cart")
    public void addsProductToCart(String actorName) {
        Actor actor = Actor.named(actorName);
        actor.can(BrowseTheWeb.with(driver));
        actor.attemptsTo(
                Click.on(LogInForm.CART),
                Click.on(LogInForm.CHECK_OUT)
        );
    }

    @And("{string} adds address details")
    public void addsAddressDetails(String actorName) {
        Actor actor = Actor.named(actorName);
        actor.can(BrowseTheWeb.with(driver));
        actor.attemptsTo(
                Enter.theValue("raja").into(CartForm.F_NAME),
                Enter.theValue("ram").into(CartForm.L_NAME),
                Enter.theValue("post").into(CartForm.POST),
                Click.on(CartForm.CONTINUE),
                WaitUntil.the(CartForm.FINISH, isVisible()).then(Click.on(CartForm.FINISH))

        );
    }

    @Then("{string} should able to see the message as {string}")
    public void shouldAbleToSeeTheMessageAs(String actorName, String term) {
        Actor actor = Actor.named(actorName);
        actor.can(BrowseTheWeb.with(driver));
        actor.should(
                seeThat(Text.of(ORDER_CONFIRMATION), Matchers.containsString(term))
        );
    }

}

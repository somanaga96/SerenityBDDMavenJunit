package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ui.CricbuzzForm;
import utils.action.Click;

import java.util.List;

public class CricbuzzDefinations {

    private Actor actor;
    @Managed
    WebDriver driver;

    @Before
    public void setUp() {
        actor = Actor.named("DefaultActor");
        actor.can(BrowseTheWeb.with(driver));
    }

    @Given("{string} launched the cricbuzz")
    public void launchedTheCricbuzz(String actorName) {
        actor = Actor.named(actorName);
        actor.can(BrowseTheWeb.with(driver));
        driver.manage().window().maximize();
        driver.get("https://www.cricbuzz.com/");
    }

    @When("{string} clicks on india teams")
    public void clicks_on_india_teams(String actorName) {
        WebElement teams = driver.findElement(By.id("teamDropDown"));
        Actions actions = new Actions(driver);
        actions.moveToElement(teams).perform();
        actor.attemptsTo(
                Click.on(CricbuzzForm.INDIA),
                Click.on(CricbuzzForm.STATS)
        );
    }

    @And("{string} collects all the users")
    public void collects_all_the_users(String actorName) {
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"teamStatsTable\"]//tr"));
        for (int i = 1; i < rows.size(); i++) {
            String text = rows.get(i).findElement(By.xpath("./td[1]")).getText();
            actor.sawAsThe(text);
        }
    }

}

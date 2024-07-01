package utils.action;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;

@Slf4j
public class ClickAndWait implements Performable {
    private final Target locator;

    ClickAndWait(Target target) {
        this.locator = target;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            WebElementFacade webElementFacade = locator.resolveFor(actor);
            webElementFacade.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Element CLick intercepted");
            retryClickAction(actor, "Element CLick intercepted");
        } catch (StaleElementReferenceException e) {
            System.out.println("Stale element");
            retryClickAction(actor, "Stale element");
        }
    }

    private void retryClickAction(Actor actor, String execptionType) {
        log.debug(execptionType + "exception " + locator.getName());
        performAs(actor);
    }
}

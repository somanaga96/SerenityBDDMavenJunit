package utils.action;

import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.targets.Target;
import utils.exception.DoNotThrowException;

public class Click {
    private Click() {
        throw new DoNotThrowException();
    }

    public static ClickAndWait on(Target locator) {
        return Tasks.instrumented(ClickAndWait.class, locator);
    }
}

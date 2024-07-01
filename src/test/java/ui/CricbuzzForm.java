package ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CricbuzzForm {

    public static final Target INDIA = Target.the("India").located(By.xpath("//*[@title=\"India Cricket Team\"]"));
    public static final Target STATS = Target.the("Stats").located(By.xpath("//*[@title=\"India Cricket Team Stats\"]"));

}

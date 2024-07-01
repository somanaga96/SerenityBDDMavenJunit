package ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartForm {
    public static final Target F_NAME = Target.the("FirstName").located(By.xpath("//*[@id=\"first-name\"]"));
    public static final Target L_NAME = Target.the("LastName").located(By.xpath("//*[@id=\"last-name\"]"));
    public static final Target POST = Target.the("Post").located(By.xpath("//*[@id=\"postal-code\"]"));
    public static final Target CONTINUE = Target.the("Continue").located(By.xpath("//input[@name=\"continue\"]"));
    public static final Target FINISH = Target.the("Finis").located(By.xpath("//*[@id=\"finish\"]"));
    public static final Target ORDER_CONFIRMATION = Target.the("Order Confirmation").located(By.xpath("//*[@id=\"checkout_complete_container\"]/h2"));

}

package ui;


import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LogInForm {
    public static final Target USER_NAME = Target.the("Username").located(By.xpath("//*[@id=\"user-name\"]"));
    public static final Target PASS = Target.the("Password").located(By.xpath("//*[@id=\"password\"]"));
    public static final Target LOGIN = Target.the("Login").located(By.xpath("//*[@id=\"login-button\"]"));
    public static final Target ADD_TO_CART = Target.the("Cart").located(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
    public static final Target CART = Target.the("Cart").located(By.xpath("//*[@id=\"shopping_cart_container\"]"));
    public static final Target CHECK_OUT = Target.the("Cart").located(By.xpath("//*[@id=\"checkout\"]"));

    public final static Target addToCartButtonFor(String id) {
        return Target.the("Login").located(By.xpath("//*[@id=" + id + "]"));
    }
}

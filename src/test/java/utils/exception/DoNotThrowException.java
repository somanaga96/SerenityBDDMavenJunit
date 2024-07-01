package utils.exception;

public class DoNotThrowException extends IllegalStateException{
    public DoNotThrowException() {
        super("Class contanning static method");
    }
}

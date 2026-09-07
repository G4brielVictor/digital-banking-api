package exceptions;

public class DepositLimitExceededException extends RuntimeException {
    public DepositLimitExceededException(String message) {
        super(message);
    }
}

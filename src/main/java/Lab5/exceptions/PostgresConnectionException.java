package Lab5.exceptions;

public class PostgresConnectionException extends Exception {
    public PostgresConnectionException(String message) {
        super(message);
    }
}

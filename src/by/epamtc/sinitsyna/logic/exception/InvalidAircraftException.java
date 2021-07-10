package by.epamtc.sinitsyna.logic.exception;

public class InvalidAircraftException extends InvalidFlyingMachineException {
	private static final long serialVersionUID = 1L;

	public InvalidAircraftException() {
		super();
	}

	public InvalidAircraftException(String message) {
		super(message);
	}

	public InvalidAircraftException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAircraftException(Throwable cause) {
		super(cause);
	}
}
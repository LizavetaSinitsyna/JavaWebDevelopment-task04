package by.epamtc.sinitsyna.logic;

public class InvalidHelicopterException extends InvalidAircraftException {
	private static final long serialVersionUID = 1L;

	public InvalidHelicopterException() {
		super();
	}

	public InvalidHelicopterException(String message) {
		super(message);
	}

	public InvalidHelicopterException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidHelicopterException(Throwable cause) {
		super(cause);
	}
}
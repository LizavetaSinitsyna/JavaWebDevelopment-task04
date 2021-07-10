package by.epamtc.sinitsyna.logic.exception;

public class InvalidFlyingMachineException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidFlyingMachineException() {
		super();
	}

	public InvalidFlyingMachineException(String message) {
		super(message);
	}

	public InvalidFlyingMachineException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidFlyingMachineException(Throwable cause) {
		super(cause);
	}
}

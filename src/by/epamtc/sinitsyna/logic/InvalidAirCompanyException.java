package by.epamtc.sinitsyna.logic;

public class InvalidAirCompanyException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidAirCompanyException() {
		super();
	}

	public InvalidAirCompanyException(String message) {
		super(message);
	}

	public InvalidAirCompanyException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAirCompanyException(Throwable cause) {
		super(cause);
	}
}

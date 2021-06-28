package by.epamtc.sinitsyna.exception;

public class IndexOutOfBoundsException extends Exception {

	private static final long serialVersionUID = 1L;

	public IndexOutOfBoundsException() {
		super();
	}

	public IndexOutOfBoundsException(String message) {
		super(message);
	}

	public IndexOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public IndexOutOfBoundsException(Throwable cause) {
		super(cause);
	}

}

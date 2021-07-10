package by.epamtc.sinitsyna.dao;

public class InvalidFileDataException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidFileDataException() {
		super();
	}

	public InvalidFileDataException(String message) {
		super(message);
	}

	public InvalidFileDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidFileDataException(Throwable cause) {
		super(cause);
	}

}

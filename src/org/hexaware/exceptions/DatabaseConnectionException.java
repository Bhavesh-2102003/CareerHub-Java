package org.hexaware.exceptions;

public class DatabaseConnectionException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseConnectionException(String message) {
        super(message);
    }
}
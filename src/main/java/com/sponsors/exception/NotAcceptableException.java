package com.sponsors.exception;

public class NotAcceptableException extends BaseException {

	private static final long serialVersionUID = 1L;

	public NotAcceptableException(NotAcceptable notFound) {
		super(4090101, notFound.getStatusCode(), notFound.getErrorMessage(), notFound.getDeveloperMessage());
	}

	public enum NotAcceptable {
		PASSWORD_NOT_MATCHES("4090101", "Username password do not matches", "Username password do not matches");

		private String statusCode;
		private String errorMessage;
		private String developerMessage;

		private NotAcceptable(String statusCode, String errorMessage, String developerMessage) {
			this.statusCode = statusCode;
			this.errorMessage = errorMessage;
			this.developerMessage = developerMessage;
		}

		public String getStatusCode() {
			return statusCode;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public String getDeveloperMessage() {
			return developerMessage;
		}
	}

}

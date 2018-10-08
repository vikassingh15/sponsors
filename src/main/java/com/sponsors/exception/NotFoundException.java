package com.sponsors.exception;

public class NotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(NotFound notFound) {
		super(404, notFound.getStatusCode(), notFound.getErrorMessage(), notFound.getDeveloperMessage());
	}

	public enum NotFound {
		SPONSOR_NOT_FOUND("4040001", "No matching sponsors found.", "No matching sponsors found with given details."),
		CANDIDATE_NOT_FOUND("4040002", "No matching candidate found.", "No matching candidate found with given details."),
		USER_NOT_FOUND("4040003", "No matching user found.", "No matching user found with given details.");

		private String statusCode;
		private String errorMessage;
		private String developerMessage;

		private NotFound(String statusCode, String errorMessage, String developerMessage) {
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

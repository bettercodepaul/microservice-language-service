package de.exxcellent.microservices.showcase.common.errorhandling;

/**
 * Interface to be implemented by all error codes.
 *
 * @author Felix Riess
 * @since 21.01.20
 */
public interface IErrorCode {
    /**
     * Get the {@link ErrorCategory} of this specific error.
     * @return the {@link ErrorCategory}.
     */
    ErrorCategory getErrorCategory();

    /**
     * Get a human readable description of this specific error.
     * @return the description as {@link String}.
     */
    String getDescription();
}

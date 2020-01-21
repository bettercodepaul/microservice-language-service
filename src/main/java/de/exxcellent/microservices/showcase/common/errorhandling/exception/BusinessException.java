package de.exxcellent.microservices.showcase.common.errorhandling.exception;

import de.exxcellent.microservices.showcase.common.errorhandling.IErrorCode;

/**
 * Exception to use for all business errors.
 *
 * @author Felix Riess
 * @since 21.01.20
 */
public class BusinessException extends BaseException {
    /**
     * generated serialVersionUID
     */
    private static final long serialVersionUID = -7029192443249154314L;

    /**
     * Generates a new {@link BusinessException}.
     *
     * @param errorCode the {@link IErrorCode} of this exception.
     * @param message the error message.
     */
    public BusinessException(final IErrorCode errorCode, final String message) {
        super(errorCode, message);
    }

    /**
     * Generates a new {@link BusinessException}.
     *
     * @param errorCode the {@link IErrorCode} of this exception.
     * @param cause the {@link Throwable} of this exception.
     */
    public BusinessException(final IErrorCode errorCode, final Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * Generates a new {@link BusinessException}.
     *
     * @param errorCode the {@link IErrorCode} of this exception.
     * @param message the error message.
     * @param cause the {@link Throwable} of this exception.
     */
    public BusinessException(final IErrorCode errorCode, final String message, final Throwable cause) {
        super(errorCode, message, cause);
    }
}

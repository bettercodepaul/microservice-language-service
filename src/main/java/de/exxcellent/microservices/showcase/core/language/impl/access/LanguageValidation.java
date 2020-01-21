package de.exxcellent.microservices.showcase.core.language.impl.access;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.TechnicalException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.language.api.types.LanguageTO;
import de.exxcellent.microservices.showcase.core.language.impl.persistence.model.LanguageET;

/**
 * A simple helper class to validate {@link LanguageET}s and {@link LanguageTO}s.
 *
 * @author Felix Riess
 * @since 21.01.20
 */
public final class LanguageValidation {
    public static final String LANGUAGE_NOT_NULL = "Language must not be null";
    public static final String LANGUAGE_SHORT_NAME_NOT_NULL = "Language short name must not be null";
    public static final String LANGUAGE_SHORT_NAME_LENGTH = "Language short name must have 3 charaters";
    public static final String LANGUAGE_NAME_NOT_NULL = "Language name must not be null";

    /**
     * private constructor to hide implicit public one.
     * @exception TechnicalException when class is tried to be instantiated.
     */
    private LanguageValidation() {
        throw new TechnicalException(ErrorCode.ILLEGAL_ACCESS_ERROR, "LanguageValidation is a utility class with static methods and must not be instantiated");
    }

    /**
     * Validate the provided {@link LanguageTO}.
     * The following is checked:
     * <ol>
     *     <li>{@link LanguageTO} must not be {@code null}</li>
     *     <li>{@link LanguageTO#getName()} must not be {@code null}</li>
     *     <li>{@link LanguageTO#getShortName()} must not be {@code null}</li>
     *     <li>{@link LanguageTO#getShortName()} must have length 3</li>
     * </ol>
     *
     * @param language the {@link LanguageTO} to be validated.
     */
    public static void validateLanguageTO(final LanguageTO language) {
        Preconditions.checkNotNull(language, LANGUAGE_NOT_NULL);
        Preconditions.checkNotNull(language.getName(), LANGUAGE_NAME_NOT_NULL);
        Preconditions.checkNotNull(language.getShortName(), LANGUAGE_SHORT_NAME_NOT_NULL);
        Preconditions.checkStringLength(language.getShortName(), 3, LANGUAGE_SHORT_NAME_LENGTH);
    }

    /**
     * Validate the provided {@link LanguageET}.
     * The following is checked:
     * <ol>
     *     <li>{@link LanguageET} must not be {@code null}</li>
     *     <li>{@link LanguageET#getName()} must not be {@code null}</li>
     *     <li>{@link LanguageET#getShortName()} must not be {@code null}</li>
     *     <li>{@link LanguageET#getShortName()} must have length 3</li>
     * </ol>
     *
     * @param language the {@link LanguageET} to be validated.
     */
    public static void validateLanguageET(final LanguageET language) {
        Preconditions.checkNotNull(language, LANGUAGE_NOT_NULL);
        Preconditions.checkNotNull(language.getName(), LANGUAGE_NAME_NOT_NULL);
        Preconditions.checkNotNull(language.getShortName(), LANGUAGE_SHORT_NAME_NOT_NULL);
        Preconditions.checkStringLength(language.getShortName(), 3, LANGUAGE_SHORT_NAME_LENGTH);
    }
}

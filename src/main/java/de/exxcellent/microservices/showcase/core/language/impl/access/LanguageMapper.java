package de.exxcellent.microservices.showcase.core.language.impl.access;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.TechnicalException;
import de.exxcellent.microservices.showcase.core.language.api.types.LanguageTO;
import de.exxcellent.microservices.showcase.core.language.impl.persistence.model.LanguageET;

/**
 * Maps a {@link LanguageET} to a {@link LanguageTO} and vice versa.
 *
 * @author Felix Riess
 * @since 21.01.20
 */
public final class LanguageMapper {

    /**
     * private constructor to hide implicit public one.
     * @exception TechnicalException when class is tried to be instantiated.
     */
    private LanguageMapper() {
        throw new TechnicalException(ErrorCode.ILLEGAL_ACCESS_ERROR, "LanguageMapper is a utility class with static methods and must not be instantiated");
    }

    /**
     * Maps the given {@link LanguageTO} to a {@link LanguageET}.
     *
     * @param language the {@link LanguageTO} to be mapped (must be valid, see {@link LanguageValidation#validateLanguageTO(LanguageTO)}).
     * @return a {@link LanguageET} holding the information from the {@link LanguageTO}.
     */
    public static LanguageET fromTO(final LanguageTO language) {
        LanguageValidation.validateLanguageTO(language);
        return new LanguageET(language.getShortName(), language.getName());
    }

    /**
     * Maps the given {@link LanguageET} to a {@link LanguageTO}.
     *
     * @param language the {@link LanguageET} to be mapped (must be valid, see {@link LanguageValidation#validateLanguageET(LanguageET)}).
     * @return a {@link LanguageTO} holding the information from the {@link LanguageET}.
     */
    public static LanguageTO toTO(final LanguageET language) {
        LanguageValidation.validateLanguageET(language);
        return new LanguageTO(language.getShortName(), language.getName());
    }
}

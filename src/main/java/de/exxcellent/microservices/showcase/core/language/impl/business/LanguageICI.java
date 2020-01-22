package de.exxcellent.microservices.showcase.core.language.impl.business;

import de.exxcellent.microservices.showcase.core.language.impl.persistence.model.LanguageET;

import java.util.Map;
import java.util.Set;

/**
 * The inner-component interface (ICI) of the language component.
 *
 * @author Felix Riess
 * @since 21.01.20
 */
public interface LanguageICI {
    /**
     * Get all languages.
     *
     * @return all languages as {@link Set} of {@link LanguageET}s.
     */
    Set<LanguageET> getLanguages();

    /**
     * Get a language by its short name (ISO Code).
     *
     * @param shortName the short name of the language to be returned (3 characters, not {@code null}).
     * @return the language with the provided short name as {@link LanguageET}.
     * @exception de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException with {@link de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode#NOT_FOUND_ERROR} if no language with the provided short name is existing.
     */
    LanguageET getLanguage(final String shortName);

    /**
     * Add a language.
     * No duplicates will be created.
     *
     * @param language the language to be added as {@link LanguageET}. (must be valid, see {@link de.exxcellent.microservices.showcase.core.language.impl.access.LanguageValidation#validateLanguageET(LanguageET)}).
     * @return all available languages including the newly added as {@link Set} of {@link LanguageET}s.
     */
    Set<LanguageET> addLanguage(final LanguageET language);

    /**
     * Get all countries with their language.
     *
     * @return a {@link Map} containing the country short name as key and its language as value.
     */
    Map<String, LanguageET> getCountriesWithLanguage();

    /**
     * Get the language of a country by its short name.
     *
     * @param countryShortName the short name of the country (3 characters, not {@code null}).
     * @return the language of the country with the provided short name as {@link LanguageET}.
     * @exception de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException with {@link de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode#NOT_FOUND_ERROR} if no language with the provided short name is existing.
     */
    LanguageET getLanguageOfCountry(final String countryShortName);

    /**
     * Add a country with its language.
     *
     * @param countryShortName the short name of the country to be added (3 characters, not {@code null}).
     * @param language the language of the country to be added as {@link LanguageET} (must be valid, see {@link de.exxcellent.microservices.showcase.core.language.impl.access.LanguageValidation#validateLanguageET(LanguageET)}).
     * @return all available countries with their language including the newly added as {@link Map} with the country short name as key and its language as value.
     */
    Map<String, LanguageET> addCountryWithLanguage(final String countryShortName, final LanguageET language);
}

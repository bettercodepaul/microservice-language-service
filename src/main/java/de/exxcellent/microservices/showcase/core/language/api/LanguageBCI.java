package de.exxcellent.microservices.showcase.core.language.api;

import de.exxcellent.microservices.showcase.core.language.api.types.CountryWithLanguageCTO;
import de.exxcellent.microservices.showcase.core.language.api.types.LanguageTO;

import java.util.Set;

/**
 * The business component interface (BCI) of the language component.
 *
 * @author Felix Riess
 * @since 21.01.20
 */
public interface LanguageBCI {
    /**
     * Get all available languages.
     *
     * @return a {@link Set} with all languages as {@link LanguageTO}s.
     */
    Set<LanguageTO> getLanguages();

    /**
     * Get a language by its short name (ISO code).
     *
     * @param shortName the short name of the language to be returned (3 characters, not {@code null}).
     * @return the language with the provided short name as {@link LanguageTO}.
     * @exception de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException with {@link de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode#NOT_FOUND_ERROR} if no language with the provided short name is existing.
     */
    LanguageTO getLanguage(final String shortName);

    /**
     * Add a language.
     * Does not generate duplicates.
     *
     * @param language the language to be added as {@link LanguageTO} (must be valid, see {@link de.exxcellent.microservices.showcase.core.language.impl.access.LanguageValidation#validateLanguageTO(LanguageTO)}).
     * @return all languages including the newly added as {@link Set} of {@link LanguageTO}s.
     */
    Set<LanguageTO> addLanguage(final LanguageTO language);

    /**
     * Get all available countries with their language.
     *
     * @return a {@link Set} containing all countries with their language as {@link CountryWithLanguageCTO}.
     */
    Set<CountryWithLanguageCTO> getCountriesWithLanguage();

    /**
     * Get a country with its language by its short name.
     *
     * @param countryShortName the short name of the country to be returned (3 characters, not {@code null}).
     * @return the country with the provided short name and its language as {@link CountryWithLanguageCTO}.
     * @exception de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException with {@link de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode#NOT_FOUND_ERROR} if no country with the provided short name is existing.
     */
    CountryWithLanguageCTO getCountryWithLanguage(final String countryShortName);

    /**
     * Add a country and its language.
     * Does not generate duplicates.
     *
     * @param countryWithLanguage the country and its language to be added as {@link CountryWithLanguageCTO}.
     * @return all countries with their language as {@link Set} of {@link CountryWithLanguageCTO}s.
     */
    Set<CountryWithLanguageCTO> addCountryWithLanguage(final CountryWithLanguageCTO countryWithLanguage);
}

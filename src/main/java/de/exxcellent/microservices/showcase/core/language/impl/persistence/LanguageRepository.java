package de.exxcellent.microservices.showcase.core.language.impl.persistence;

import de.exxcellent.microservices.showcase.core.language.impl.persistence.model.LanguageET;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * A repository interface for storage operations.
 *
 * @author Felix Riess
 * @since 21.01.20
 */
public interface LanguageRepository {
    /**
     * Get all available languages.
     *
     * @return all languages as {@link Set} of {@link LanguageET}s.
     */
    Set<LanguageET> findAll();

    /**
     * Gut a language by its short name (ISO code).
     *
     * @param shortName the short name (ISO code) of the language to be returned (3 characters, not {@code null}).
     * @return an {@link Optional} containing the language with the provided short name as {@link LanguageET} or {@code null} if language is not existing.
     */
    Optional<LanguageET> findByShortName(final String shortName);

    /**
     * Adds the given language to the known languages.
     * Does not add duplicates.
     *
     * @param language the language to add as {@link LanguageET}. (must be valid, see {@link de.exxcellent.microservices.showcase.core.language.impl.access.LanguageValidation#validateLanguageET(LanguageET)}).
     * @return all available languages including the new one as {@link Set} of {@link LanguageET}.
     */
    Set<LanguageET> addLanguage(final LanguageET language);

    /**
     * Get all available countries with their language.
     *
     * @return a {@link Map} containing the country short name as {@link String} and its language as {@link LanguageET}.
     */
    Map<String, LanguageET> findAllCountriesWithLanguage();

    /**
     * Adds a country with its language to the known countries with languages.
     *
     * @param countryShortName the short name of the country as {@link String} (3 characters, not {@code null}).
     * @param language the language of the country as {@link LanguageET} (must be valid, see {@link de.exxcellent.microservices.showcase.core.language.impl.access.LanguageValidation#validateLanguageET(LanguageET)}).
     * @return a {@link Map} containing all countries with their currencies including the new one as {@link Map} with the country short name as key and its language as value.
     */
    Map<String, LanguageET> addCountryWithLanguage(final String countryShortName, final LanguageET language);

    /**
     * Get the language of a country.
     *
     * @param countryShortName the short name of the country of which the language should be returned (3 characters, not {@code null}).
     * @return an {@link Optional} containing the language of the given country as {@link LanguageET}
     */
    Optional<LanguageET> findLanguageByCountry(final String countryShortName);
}

package de.exxcellent.microservices.showcase.core.language.impl.persistence;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.language.impl.access.LanguageValidation;
import de.exxcellent.microservices.showcase.core.language.impl.persistence.model.LanguageET;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * The implementation of {@link LanguageRepository} for a runtime storage for languages.
 *
 * @author Felix Riess
 * @since 21.01.20
 */
@ApplicationScoped
public class LanguageRuntimeStorage implements LanguageRepository {
    /**
     * A {@link Set} containing all known languages as {@link LanguageET}s.
     */
    Set<LanguageET> languages;
    /**
     * A {@link Map} containing all relations between country short names (key) and languages (value).
     */
    Map<String, LanguageET> countriesWithLanguage;

    public LanguageRuntimeStorage() {
        this.languages = new HashSet<>();
        this.countriesWithLanguage = new HashMap<>();
        initData();
    }

    private void initData() {
        final LanguageET german = new LanguageET("deu", "German");
        final LanguageET french = new LanguageET("fra", "French");
        final LanguageET scottish = new LanguageET("eng", "English");
        this.languages.add(german);
        this.languages.add(french);
        this.languages.add(scottish);
        this.countriesWithLanguage.putIfAbsent("GER", german);
        this.countriesWithLanguage.putIfAbsent("FRA", french);
        this.countriesWithLanguage.putIfAbsent("SCO", scottish);
    }

    @Override
    public Set<LanguageET> findAll() {
        return this.languages;
    }

    @Override
    public Optional<LanguageET> findByShortName(final String shortName) {
        Preconditions.checkNotNull(shortName, LanguageValidation.LANGUAGE_SHORT_NAME_NOT_NULL);
        Preconditions.checkStringLength(shortName, 3, LanguageValidation.LANGUAGE_SHORT_NAME_LENGTH);
        return this.languages.stream().filter(l -> shortName.equalsIgnoreCase(l.getShortName())).findFirst();
    }

    @Override
    public Set<LanguageET> addLanguage(final LanguageET language) {
        LanguageValidation.validateLanguageET(language);
        this.languages.add(language);
        return this.languages;
    }

    @Override
    public Map<String, LanguageET> findAllCountriesWithLanguage() {
        return this.countriesWithLanguage;
    }

    @Override
    public Map<String, LanguageET> addCountryWithLanguage(final String countryShortName, final LanguageET language) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        LanguageValidation.validateLanguageET(language);
        this.languages.add(language);
        this.countriesWithLanguage.putIfAbsent(countryShortName, language);
        return this.countriesWithLanguage;
    }

    @Override
    public Optional<LanguageET> findLanguageByCountry(final String countryShortName) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        return Optional.ofNullable(this.countriesWithLanguage.get(countryShortName));
    }
}

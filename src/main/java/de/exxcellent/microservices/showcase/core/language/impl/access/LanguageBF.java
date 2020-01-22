package de.exxcellent.microservices.showcase.core.language.impl.access;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.language.api.LanguageBCI;
import de.exxcellent.microservices.showcase.core.language.api.types.CountryWithLanguageCTO;
import de.exxcellent.microservices.showcase.core.language.api.types.LanguageTO;
import de.exxcellent.microservices.showcase.core.language.impl.business.LanguageICI;
import de.exxcellent.microservices.showcase.core.language.impl.persistence.model.LanguageET;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The business facade (BF) of the language component. Implements the {@link LanguageBCI}.
 *
 * @author Felix Riess
 * @since 21.01.20
 */
@ApplicationScoped
public class LanguageBF implements LanguageBCI {

    private final LanguageICI languageManager;

    @Inject
    LanguageBF(final LanguageICI languageManager) {
        this.languageManager = languageManager;
    }

    @Override
    public Set<LanguageTO> getLanguages() {
        return this.languageManager.getLanguages()
                                   .stream()
                                   .map(LanguageMapper::toTO)
                                   .collect(Collectors.toSet());
    }

    @Override
    public LanguageTO getLanguage(final String shortName) {
        Preconditions.checkNotNull(shortName, LanguageValidation.LANGUAGE_SHORT_NAME_NOT_NULL);
        Preconditions.checkStringLength(shortName, 3, LanguageValidation.LANGUAGE_SHORT_NAME_LENGTH);
        return LanguageMapper.toTO(this.languageManager.getLanguage(shortName));
    }

    @Override
    public Set<LanguageTO> addLanguage(final LanguageTO language) {
        LanguageValidation.validateLanguageTO(language);
        final LanguageET languageET = LanguageMapper.fromTO(language);
        return this.languageManager.addLanguage(languageET)
                                   .stream()
                                   .map(LanguageMapper::toTO)
                                   .collect(Collectors.toSet());
    }

    @Override
    public Set<CountryWithLanguageCTO> getCountriesWithLanguage() {
        return this.languageManager.getCountriesWithLanguage()
                                   .entrySet()
                                   .stream()
                                   .map(c -> new CountryWithLanguageCTO(c.getKey(), LanguageMapper.toTO(c.getValue())))
                                   .collect(Collectors.toSet());
    }

    @Override
    public CountryWithLanguageCTO getCountryWithLanguage(final String countryShortName) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        final LanguageTO language = LanguageMapper.toTO(this.languageManager.getLanguageOfCountry(countryShortName));
        return new CountryWithLanguageCTO(countryShortName, language);
    }

    @Override
    public Set<CountryWithLanguageCTO> addCountryWithLanguage(final CountryWithLanguageCTO countryWithLanguage) {
        Preconditions.checkNotNull(countryWithLanguage, "Country with language must not be null");
        Preconditions.checkNotNull(countryWithLanguage.getCountryShortName(), "Country short name must not be null");
        Preconditions.checkStringLength(countryWithLanguage.getCountryShortName(), 3, "Country short name must have 3 characters");
        LanguageValidation.validateLanguageTO(countryWithLanguage.getLanguage());
        final LanguageET language = LanguageMapper.fromTO(countryWithLanguage.getLanguage());
        return this.languageManager.addCountryWithLanguage(countryWithLanguage.getCountryShortName(), language)
                                   .entrySet()
                                   .stream()
                                   .map(c -> new CountryWithLanguageCTO(c.getKey(), LanguageMapper.toTO(c.getValue())))
                                   .collect(Collectors.toSet());
    }
}

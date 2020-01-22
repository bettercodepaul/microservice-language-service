package de.exxcellent.microservices.showcase.core.language.api.types;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.language.impl.access.LanguageValidation;

import java.io.Serializable;

/**
 * Combined transport object (CTO) representing a country with its language.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 22.01.2020
 */
public class CountryWithLanguageCTO implements Serializable {
    /**
     * generated serialVersionUID
     */
    private static final long serialVersionUID = 1878582494357056473L;
    /**
     * The country short name (3 characters).
     */
    private String countryShortName;
    /**
     * the language of the country as {@link LanguageTO}.
     */
    private LanguageTO language;

    /**
     * empty constructor for JSON mapping.
     */
    public CountryWithLanguageCTO() {

    }

    /**
     * Constructor.
     *
     * @param countryShortName the short name of the country (3 characters, not {@code null}).
     * @param language the language of the country as {@link LanguageTO} (must be valid, see {@link LanguageValidation#validateLanguageTO(LanguageTO)}).
     */
    public CountryWithLanguageCTO(final String countryShortName, final LanguageTO language) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        LanguageValidation.validateLanguageTO(language);
        this.countryShortName = countryShortName;
        this.language =  language;
    }

    public String getCountryShortName() {
        return this.countryShortName;
    }

    public void setCountryShortName(final String countryShortName) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        this.countryShortName = countryShortName;
    }

    public LanguageTO getLanguage() {
        return this.language;
    }

    public void setLanguage(final LanguageTO language) {
        LanguageValidation.validateLanguageTO(language);
        this.language = language;
    }

    @Override
    public String toString() {
        return "CountryWithLanguageCTO{" +
                        "countryShortName='" + this.countryShortName + '\'' +
                        ", language=" + this.language +
                        '}';
    }
}

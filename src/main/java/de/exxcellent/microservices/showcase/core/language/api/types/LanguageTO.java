package de.exxcellent.microservices.showcase.core.language.api.types;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.language.impl.access.LanguageValidation;

import java.io.Serializable;

/**
 * The transport object (TO) holding the information of a language.
 *
 * @author Felix Riess
 * @since 21.01.20
 */
public class LanguageTO implements Serializable {
    /**
     * generated serialVersionUID
     */
    private static final long serialVersionUID = -741113964815463571L;
    /**
     * the short name (ID) of this {@link LanguageTO}.
     */
    private String shortName;
    /**
     * the name of this {@link LanguageTO}.
     */
    private String name;

    /**
     * empty constructor for JSON mapping.
     */
    public LanguageTO() {

    }

    /**
     * Constructor.
     *
     * @param shortName the short name (ID) of the language (3 characters, not {@code null}).
     * @param name the name of the language (not {@code null}).
     */
    public LanguageTO(final String shortName, final String name) {
        Preconditions.checkNotNull(shortName, LanguageValidation.LANGUAGE_SHORT_NAME_NOT_NULL);
        Preconditions.checkNotNull(name, LanguageValidation.LANGUAGE_NAME_NOT_NULL);
        Preconditions.checkStringLength(shortName, 3, LanguageValidation.LANGUAGE_SHORT_NAME_LENGTH);
        this.shortName = shortName;
        this.name = name;
    }

    /**
     * Get shortName
     *
     * @return value of shortName
     */
    public String getShortName() {
        return this.shortName;
    }

    /**
     * Set the shortName
     *
     * @param shortName the shortName to set
     */
    public void setShortName(final String shortName) {
        Preconditions.checkNotNull(shortName, LanguageValidation.LANGUAGE_SHORT_NAME_NOT_NULL);
        Preconditions.checkStringLength(shortName, 3, LanguageValidation.LANGUAGE_SHORT_NAME_LENGTH);
        this.shortName = shortName;
    }

    /**
     * Get name
     *
     * @return value of name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name
     *
     * @param name the name to set
     */
    public void setName(final String name) {
        Preconditions.checkNotNull(name, LanguageValidation.LANGUAGE_NAME_NOT_NULL);
        this.name = name;
    }

    @Override
    public String toString() {
        return "LanguageTO{" +
                "shortName='" + this.shortName + '\'' +
                ", name='" + this.name + '\'' +
                '}';
    }
}

package de.exxcellent.microservices.showcase.core.language.impl.persistence.model;

import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.language.impl.access.LanguageValidation;

import java.io.Serializable;
import java.util.Objects;

/**
 * The entity type (ET) for a language.
 *
 * @author Felix Riess
 * @since 21.01.20
 */
public class LanguageET implements Serializable {
    /**
     * generated serialVersionUID
     */
    private static final long serialVersionUID = 3693030349672861639L;
    /**
     * the short name (ID) of this {@link LanguageET}.
     */
    private final String shortName;
    /**
     * the name of this {@link LanguageET}.
     */
    private final String name;

    /**
     * Constructor.
     *
     * @param shortName the short name (ID) of the language (3 characters, not {@code null}).
     * @param name the name of the language (not {@code null}).
     */
    public LanguageET(final String shortName, final String name) {
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
     * Get name
     *
     * @return value of name
     */
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LanguageET that = (LanguageET) o;
        return Objects.equals(this.shortName, that.shortName) &&
                Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.shortName, this.name);
    }

    @Override
    public String toString() {
        return "LanguageET{" +
                "shortName='" + this.shortName + '\'' +
                ", name='" + this.name + '\'' +
                '}';
    }
}

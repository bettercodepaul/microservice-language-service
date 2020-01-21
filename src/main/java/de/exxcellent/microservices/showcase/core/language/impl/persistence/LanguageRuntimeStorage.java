package de.exxcellent.microservices.showcase.core.language.impl.persistence;

import de.exxcellent.microservices.showcase.core.language.impl.persistence.model.LanguageET;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    LanguageRuntimeStorage() {
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
}

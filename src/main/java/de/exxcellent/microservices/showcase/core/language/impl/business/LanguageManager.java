package de.exxcellent.microservices.showcase.core.language.impl.business;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.language.impl.access.LanguageValidation;
import de.exxcellent.microservices.showcase.core.language.impl.persistence.LanguageRepository;
import de.exxcellent.microservices.showcase.core.language.impl.persistence.model.LanguageET;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Manages the languages. Implements {@link LanguageICI}.
 *
 * @author Felix Riess
 * @since 21.01.20
 */
@ApplicationScoped
public class LanguageManager implements LanguageICI {
    /**
     * The {@link Logger} of this {@link LanguageManager}.
     */
    private static final Logger LOG = LoggerFactory.getLogger(LanguageManager.class);
    /**
     * The {@link LanguageRepository} to be used for storage interactions.
     */
    private final LanguageRepository languageRepository;

    @Inject
    LanguageManager(final LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public Set<LanguageET> getLanguages() {
        LOG.info("Query storage to get all languages");
        return this.languageRepository.findAll();
    }

    @Override
    public LanguageET getLanguage(final String shortName) {
        Preconditions.checkNotNull(shortName, LanguageValidation.LANGUAGE_SHORT_NAME_NOT_NULL);
        Preconditions.checkStringLength(shortName, 3, LanguageValidation.LANGUAGE_SHORT_NAME_LENGTH);
        LOG.info("Query storage for language with short name {}", shortName);
        final Optional<LanguageET> optionalLanguage = this.languageRepository.findByShortName(shortName);
        if(optionalLanguage.isPresent()) {
            final LanguageET language = optionalLanguage.get();
            LOG.info("Returning language {} for short name {} from storage", language.getName(), language.getShortName());
            return language;
        } else {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "Language with short name " + shortName + " is not existing");
        }
    }

    @Override
    public Set<LanguageET> addLanguage(final LanguageET language) {
        addLanguageIfNotExisting(language);
        return this.languageRepository.findAll();
    }

    @Override
    public Map<String, LanguageET> getCountriesWithLanguage() {
        LOG.info("Query storage to get all countries with their language");
        return this.languageRepository.findAllCountriesWithLanguage();
    }

    @Override
    public LanguageET getLanguageOfCountry(final String countryShortName) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        LOG.info("Query storage for language of country with short name {}", countryShortName);
        final Optional<LanguageET> optionalExistingLanguage = this.languageRepository.findLanguageByCountry(countryShortName);
        if(optionalExistingLanguage.isPresent()) {
            final LanguageET existingLanguage = optionalExistingLanguage.get();
            LOG.info("Returning language {} for country with short name {}", existingLanguage.getName(), countryShortName);
            return existingLanguage;
        } else {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "No language is existing for country with short name " + countryShortName);
        }
    }

    @Override
    public Map<String, LanguageET> addCountryWithLanguage(final String countryShortName, final LanguageET language) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        addLanguageIfNotExisting(language);
        LOG.info("Adding language {} to country with short name {}", language.getName(), countryShortName);
        return this.languageRepository.addCountryWithLanguage(countryShortName, language);
    }

    private void addLanguageIfNotExisting(final LanguageET language) {
        LanguageValidation.validateLanguageET(language);
        LOG.info("Query storage for language with short name {} to avoid generating duplicates", language.getShortName());
        final Optional<LanguageET> optionalExistingLanguage = this.languageRepository.findByShortName(language.getShortName());
        if(optionalExistingLanguage.isPresent()) {
            final LanguageET existingLanguage = optionalExistingLanguage.get();
            LOG.info("Language with short name {} is already existing in storage: {}", existingLanguage.getShortName(), existingLanguage.getName());
            if(!existingLanguage.getName().equalsIgnoreCase(language.getName())) {
                // another language with this short name is already existing. No other language can be created with the same short name!
                throw new BusinessException(ErrorCode.INVALID_ARGUMENT_ERROR, "A language with the short name " + existingLanguage.getShortName()
                                + " is already existing: " + existingLanguage.getName() + ". Cannot create two languages with the same short name");
            } // else: language with this name is already existing and must not be added again.
        } else {
            // no language with the provided short name is present. Create a new one.
            LOG.info("Adding new language {} with short name {} to storage", language.getName(), language.getShortName());
            this.languageRepository.addLanguage(language);
        }
    }
}

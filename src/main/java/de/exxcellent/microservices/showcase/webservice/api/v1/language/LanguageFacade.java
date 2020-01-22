package de.exxcellent.microservices.showcase.webservice.api.v1.language;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.language.api.LanguageBCI;
import de.exxcellent.microservices.showcase.core.language.api.types.LanguageTO;
import de.exxcellent.microservices.showcase.core.language.impl.access.LanguageValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Provides the REST API for the language component.
 *
 * @author Felix Riess
 * @since 21.01.20
 */
@Path("/api/v1/languages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LanguageFacade {
    /**
     * The {@link Logger} of this {@link LanguageFacade}.
     */
    private static final Logger LOG = LoggerFactory.getLogger(LanguageFacade.class);
    private final LanguageBCI languageService;

    @Inject
    LanguageFacade(final LanguageBCI languageService) {
        this.languageService = languageService;
    }

    /**
     * Get all available languages.
     *
     * @return a {@link Set} containing all languages as {@link LanguageTO}.
     * @exception BusinessException with {@link ErrorCode#EMPTY_LIST_ERROR} if no languages are available to produce HTTP 204.
     */
    @GET
    public Set<LanguageTO> getLanguages() {
        LOG.info("Resource to get all languages triggered");
        final Set<LanguageTO> languages = this.languageService.getLanguages();
        if(languages.isEmpty()) {
            throw new BusinessException(ErrorCode.EMPTY_LIST_ERROR, "No languages are existing");
        } else {
            return languages;
        }
    }

    /**
     * Get the language with the provided short name (ISO code).
     *
     * @param shortName the short name (ISO code) of the language to be returned (3 characters, not {@code null}).
     * @return the language with the provided short name as {@link LanguageTO}.
     */
    @GET
    @Path("{shortName}")
    public LanguageTO getLanguage(@PathParam("shortName") final String shortName) {
        Preconditions.checkNotNull(shortName, LanguageValidation.LANGUAGE_SHORT_NAME_NOT_NULL);
        Preconditions.checkStringLength(shortName, 3, LanguageValidation.LANGUAGE_SHORT_NAME_LENGTH);
        LOG.info("Resource to get language with short name {} triggered", shortName);
        return this.languageService.getLanguage(shortName);
    }

    /**
     * Create a new language from the provided information.
     *
     * @param language the information about the language to be created as {@link LanguageTO}. (must be valid, see {@link LanguageValidation#validateLanguageTO(LanguageTO)}).
     * @return all available languages including the new one as {@link Set} of {@link LanguageTO}s.
     */
    @POST
    public Set<LanguageTO> createLanguage(final LanguageTO language) {
        LanguageValidation.validateLanguageTO(language);
        LOG.info("Resource to create new language {} with short name {} triggered", language.getName(), language.getShortName());
        return this.languageService.addLanguage(language);
    }
}

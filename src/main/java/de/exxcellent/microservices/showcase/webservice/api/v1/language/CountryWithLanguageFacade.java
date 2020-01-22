package de.exxcellent.microservices.showcase.webservice.api.v1.language;

import de.exxcellent.microservices.showcase.common.errorhandling.ErrorCode;
import de.exxcellent.microservices.showcase.common.errorhandling.exception.BusinessException;
import de.exxcellent.microservices.showcase.common.validation.Preconditions;
import de.exxcellent.microservices.showcase.core.language.api.LanguageBCI;
import de.exxcellent.microservices.showcase.core.language.api.types.CountryWithLanguageCTO;
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
 * Provides a REST API to manage countries with their languages.
 *
 * @author Felix Riess, eXXcellent solutions consulting & software gmbh
 * @since 22.01.2020
 */
@Path("/api/v1/countries-with-language")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CountryWithLanguageFacade {
    /**
     * The {@link Logger} of this {@link CountryWithLanguageFacade}.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CountryWithLanguageFacade.class);
    private final LanguageBCI languageService;

    @Inject
    CountryWithLanguageFacade(final LanguageBCI languageService) {
        this.languageService = languageService;
    }

    /**
     * Get all available countries with their language.
     *
     * @return a {@link Set} of countries with their language as {@link CountryWithLanguageCTO}.
     * @exception BusinessException with {@link ErrorCode#EMPTY_LIST_ERROR} if no countries with their language are defined to produce HTTP 204.
     */
    @GET
    public Set<CountryWithLanguageCTO> getCountriesWithLanguage() {
        LOG.info("Resource to get countries with their language triggered");
        final Set<CountryWithLanguageCTO> countriesWithLanguage = this.languageService.getCountriesWithLanguage();
        if(countriesWithLanguage.isEmpty()) {
            throw new BusinessException(ErrorCode.EMPTY_LIST_ERROR, "No countries with languages are available");
        } else {
            return countriesWithLanguage;
        }
    }

    /**
     * Get the language of the country with the provided short name.
     *
     * @param countryShortName the short name of the country where the language should be returned (3 characters, not {@code null}).
     * @return the language of the country with the provided short name as {@link CountryWithLanguageCTO}.
     */
    @GET
    @Path("{countryShortName}")
    public CountryWithLanguageCTO getCountryWithLanguage(@PathParam("countryShortName") final String countryShortName) {
        Preconditions.checkNotNull(countryShortName, "Country short name must not be null");
        Preconditions.checkStringLength(countryShortName, 3, "Country short name must have 3 characters");
        LOG.info("Resource to get language of country with short name {} triggered", countryShortName);
        return this.languageService.getCountryWithLanguage(countryShortName);
    }

    /**
     * Add a language to a country.
     *
     * @param countryWithLanguage the information about the country and the language as {@link CountryWithLanguageCTO}.
     * @return all available countries with their language as {@link Set} of {@link CountryWithLanguageCTO} including the new one.
     */
    @POST
    public Set<CountryWithLanguageCTO> createCountryWithLanguage(final CountryWithLanguageCTO countryWithLanguage) {
        Preconditions.checkNotNull(countryWithLanguage, "Country with language must not be null");
        Preconditions.checkNotNull(countryWithLanguage.getCountryShortName(), "Country short name must not be null");
        Preconditions.checkStringLength(countryWithLanguage.getCountryShortName(), 3, "Country short name must have 3 characters");
        LanguageValidation.validateLanguageTO(countryWithLanguage.getLanguage());
        LOG.info("Resource to add language {} to country with short name {} triggered", countryWithLanguage.getLanguage().getName(), countryWithLanguage.getCountryShortName());
        return this.languageService.addCountryWithLanguage(countryWithLanguage);
    }
}

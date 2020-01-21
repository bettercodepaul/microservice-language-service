package de.exxcellent.microservices.showcase.webservice.api.v1.language;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

    // FIXME [FR] (21.01.2020): implement me
}

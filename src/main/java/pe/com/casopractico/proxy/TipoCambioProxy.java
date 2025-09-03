package pe.com.casopractico.proxy;

import pe.com.casopractico.dto.TipoCambioResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@RegisterRestClient(configKey = "tipo-cambio-api")
public interface TipoCambioProxy {

    @GET
    @Path("/tipo-cambio/today.json")
    @Produces(MediaType.APPLICATION_JSON)
    TipoCambioResponse getToday();
}

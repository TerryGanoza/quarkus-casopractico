package pe.com.casopractico.resource;

import pe.com.casopractico.dto.ErrorResponse;
import pe.com.casopractico.dto.TipoCambioResponse;
import pe.com.casopractico.service.TipoCambioService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tipo-cambio")
@Produces(MediaType.APPLICATION_JSON)
public class TipoCambioResource {

    @Inject
    TipoCambioService tipoCambioService;

    @GET
    public Response getTipoCambio(@QueryParam("dni") String dni) {
        if (dni == null || dni.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("El campo dni es obligatorio")).build();
        }

        try {
            TipoCambioResponse response = tipoCambioService.consultarTipoCambio(dni);
            return Response.ok(response).build();
        } catch (RuntimeException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(ex.getMessage())).build();
        }
    }
}
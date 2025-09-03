package pe.com.casopractico.service;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import pe.com.casopractico.proxy.TipoCambioProxy;
import pe.com.casopractico.dto.TipoCambioResponse;
import pe.com.casopractico.repository.ConsultaTipoCambio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ApplicationScoped
public class TipoCambioService {

    @Inject
    @RestClient
    TipoCambioProxy tipoCambioProxy;

    private static final int LIMITE_CONSULTAS = 10;

    public long contarConsultasHoy(String dni) {
        LocalDate hoy = LocalDate.now();
        return ConsultaTipoCambio.find("dni = ?1 and fechaConsulta between ?2 and ?3",
                dni, hoy.atStartOfDay(), hoy.atTime(23, 59, 59)).count();
    }

    @Transactional
    public TipoCambioResponse consultarTipoCambio(String dni) {
        long consultasHoy = contarConsultasHoy(dni);

        if (consultasHoy >= LIMITE_CONSULTAS) {
            throw new RuntimeException("Se superó el límite de consultas diarias (10).");
        }

        TipoCambioResponse externo = tipoCambioProxy.getToday();

        ConsultaTipoCambio consulta = new ConsultaTipoCambio();
        consulta.dni = dni;
        consulta.fechaConsulta = LocalDateTime.now();
        consulta.tipoCambioCompra = externo.compra;
        consulta.tipoCambioVenta = externo.venta;
        consulta.persist();

        return externo;
    }
}
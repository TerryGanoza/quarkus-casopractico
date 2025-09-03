package pe.com.casopractico.repository;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consulta_tipo_cambio")
public class ConsultaTipoCambio extends PanacheEntity {

    @Column(nullable = false)
    public String dni;

    @Column(nullable = false)
    public LocalDateTime fechaConsulta;

    @Column(nullable = false)
    public Double tipoCambioCompra;

    @Column(nullable = false)
    public Double tipoCambioVenta;
}

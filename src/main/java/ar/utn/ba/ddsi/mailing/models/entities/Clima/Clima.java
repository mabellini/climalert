package ar.utn.ba.ddsi.mailing.models.entities.Clima;

import ar.utn.ba.ddsi.mailing.models.entities.Ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class Clima {
    private Long id;
    private Ubicacion ubicacion;
    private Temperatura temperatura;
    private String condicion;
    private VelocidadViento velocidadViento;
    private Integer humedad;
    private LocalDateTime fechaActualizacion;
    private boolean procesado;

    public Clima() {
        this.fechaActualizacion = LocalDateTime.now();
        this.procesado = false;
    }

    public String getCiudad(){
        return this.ubicacion.getNombreCiudad();
    }

    public double getTemperaturaCelsius(){
        return this.getTemperaturaCelsius();
    }

    public double getVelocidadVientoKmh(){
        return this.getVelocidadVientoKmh();
    }
} 
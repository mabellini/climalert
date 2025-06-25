package ar.utn.ba.ddsi.mailing.models.entities;

import ar.utn.ba.ddsi.mailing.models.entities.Clima.Clima;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Alerta {
    public Long id;
    public Clima clima;
    public boolean comunicada;
    public LocalDateTime horaDeGeneracion;

    public Alerta(Clima clima){
        this.clima = clima;
        comunicada = false;
        horaDeGeneracion = LocalDateTime.now();
    }
}

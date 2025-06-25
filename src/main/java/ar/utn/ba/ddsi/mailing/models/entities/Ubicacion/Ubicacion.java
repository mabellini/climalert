package ar.utn.ba.ddsi.mailing.models.entities.Ubicacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ubicacion {
    private Long id;
    private Ciudad ciudad;
    private Region region;
    private Pais pais;

    public String getNombreCiudad(){
       return this.ciudad.getNombre();
    }
}



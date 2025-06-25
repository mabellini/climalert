package ar.utn.ba.ddsi.mailing.services.ServiciosDeDominio;


import ar.utn.ba.ddsi.mailing.models.entities.Clima.Clima;

public class CondicionadorDeAlertas {
    private static final double TEMPERATURA_ALERTA = 35.0;
    private static final int HUMEDAD_ALERTA = 60;

    public boolean debeGenerarAlerta(Clima clima) {
        return esTemperaturaCritica(clima) && esHumedadCritica(clima);
    }

    private boolean esTemperaturaCritica(Clima clima) {
        return clima.getTemperaturaCelsius() > TEMPERATURA_ALERTA;
    }

    private boolean esHumedadCritica(Clima clima) {
        return clima.getHumedad() > HUMEDAD_ALERTA;
    }
}

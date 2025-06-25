package ar.utn.ba.ddsi.mailing.services.impl;

import ar.utn.ba.ddsi.mailing.models.entities.Clima.Clima;
import ar.utn.ba.ddsi.mailing.models.entities.Email;
import ar.utn.ba.ddsi.mailing.models.repositories.IAlertaRepository;
import ar.utn.ba.ddsi.mailing.services.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class NotificacionService {
    private static final Logger logger = LoggerFactory.getLogger(NotificacionService.class);
    private final IAlertaRepository alertaRepository;

    private final String remitente;
    private final List<String> destinatarios;

    private final IEmailService emailService;

    public NotificacionService(IAlertaRepository alertaRepository,
                               EmailService emailService,
                               @Value("${email.alertas.remitente}") String remitente,
                               @Value("${email.alertas.destinatarios}") String destinatarios) {
        this.alertaRepository = alertaRepository;
        this.remitente = remitente;
        this.destinatarios = Arrays.asList(destinatarios.split(","));
        this.emailService = emailService;
    }

    public Mono<Void> ProcesarAlertarPendientes(){ // Separo el envio de emails de la generacion, podria generar otro tipo de notificaciones tambien.
        return Mono.fromCallable(() -> alertaRepository.findByComunicado(false))
                .doOnNext(alertas -> {
                    alertas.forEach(alerta -> generarEmail(alerta.getClima()));
                })
                .then();

    }

    private void generarEmail(Clima clima) {

        String asunto = "Alerta de Clima - Condiciones Extremas";
        String mensaje = String.format(
                "ALERTA: Condiciones climáticas extremas detectadas en %s\n\n" +
                        "Temperatura: %.1f°C\n" +
                        "Humedad: %d%%\n" +
                        "Condición: %s\n" +
                        "Velocidad del viento: %.1f km/h\n\n" +
                        "Se recomienda tomar precauciones.",
                clima.getCiudad(),
                clima.getTemperaturaCelsius(),
                clima.getHumedad(),
                clima.getCondicion(),
                clima.getVelocidadVientoKmh()
        );

        for (String destinatario : destinatarios) {
            Email email = new Email(destinatario, remitente, asunto, mensaje);
            emailService.crearEmail(email);
        }

        logger.info("Email de alerta generado para {} - Enviado a {} destinatarios",
                clima.getCiudad(), destinatarios.size());
    }

}

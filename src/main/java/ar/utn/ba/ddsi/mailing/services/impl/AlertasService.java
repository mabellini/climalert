package ar.utn.ba.ddsi.mailing.services.impl;

import ar.utn.ba.ddsi.mailing.models.entities.Alerta;
import ar.utn.ba.ddsi.mailing.models.entities.Clima.Clima;
import ar.utn.ba.ddsi.mailing.models.entities.Email;
import ar.utn.ba.ddsi.mailing.models.repositories.IAlertaRepository;
import ar.utn.ba.ddsi.mailing.models.repositories.IClimaRepository;
import ar.utn.ba.ddsi.mailing.models.repositories.impl.AlertaRepository;
import ar.utn.ba.ddsi.mailing.services.IAlertasService;
import ar.utn.ba.ddsi.mailing.services.ServiciosDeDominio.CondicionadorDeAlertas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.List;

@Service
public class AlertasService implements IAlertasService {
    private static final Logger logger = LoggerFactory.getLogger(AlertasService.class);
    private final CondicionadorDeAlertas condicionador = new CondicionadorDeAlertas();

    private final IClimaRepository climaRepository;
    private final IAlertaRepository alertaRepository;

    public AlertasService(
            IAlertaRepository alertaRepository,
            IClimaRepository climaRepository, 
            EmailService emailService,
            @Value("${email.alertas.remitente}") String remitente,
            @Value("${email.alertas.destinatarios}") String destinatarios) {
        this.climaRepository = climaRepository;
        this.alertaRepository = alertaRepository;
    }

    @Override
    public Mono<Void> generarAlertas() {
        return Mono.fromCallable(() -> climaRepository.findByProcesado(false))
            .flatMap(climas -> {
                logger.info("Procesando {} registros de clima no procesados", climas.size());
                return Mono.just(climas);
            })
            .flatMap(climas -> {
                climas.stream()
                    .filter(this::cumpleCondicionesAlerta)
                        .map(Alerta::new) // Crea una alerta por cada clima
                        .forEach(alertaRepository::save);
                
                // Marcar todos como procesados
                climas.forEach(clima -> {
                    clima.setProcesado(true);
                    climaRepository.save(clima);
                });
                
                return Mono.empty();
            })
            .onErrorResume(e -> {
                logger.error("Error al procesar alertas: {}", e.getMessage());
                return Mono.empty();
            })
            .then();
    }

    private boolean cumpleCondicionesAlerta(Clima clima) {
         return condicionador.debeGenerarAlerta(clima);
    }


}
package ar.utn.ba.ddsi.mailing.schedulers;

import ar.utn.ba.ddsi.mailing.services.INotificacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class NotificacionesScheduler {
    private static final Logger logger = LoggerFactory.getLogger(NotificacionesScheduler.class);
    private final INotificacionService notificacionService;

    public NotificacionesScheduler(INotificacionService NotificacionesService) {
        this.notificacionService = NotificacionesService;
    }

    @Scheduled(fixedRate = 60000) // Cada 1 minuto
    public void procesarNotificaciones() {
        notificacionService.ProcesarAlertarPendientes()
                .doOnSuccess(v -> logger.info("Procesamiento de Notificaciones completado"))
                .doOnError(e -> logger.error("Error en el procesamiento de Notificaciones: {}", e.getMessage()))
                .subscribe();
    }
}


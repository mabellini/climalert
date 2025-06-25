package ar.utn.ba.ddsi.mailing.services;

import ar.utn.ba.ddsi.mailing.services.impl.AlertasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public interface INotificacionService {
    Mono<Void> ProcesarAlertarPendientes();
}

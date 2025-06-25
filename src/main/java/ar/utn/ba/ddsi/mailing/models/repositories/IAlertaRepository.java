package ar.utn.ba.ddsi.mailing.models.repositories;

import ar.utn.ba.ddsi.mailing.models.entities.Alerta;

import java.util.List;
import java.util.Optional;

public interface IAlertaRepository {
    Alerta save(Alerta Alerta);
    List<Alerta> findAll();
    Optional<Alerta> findById(Long id);
    List<Alerta> findByComunicado(boolean comunicado);
    void delete(Alerta Alerta);
} 



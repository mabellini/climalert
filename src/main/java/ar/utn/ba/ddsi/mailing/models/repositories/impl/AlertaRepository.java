package ar.utn.ba.ddsi.mailing.models.repositories.impl;

import ar.utn.ba.ddsi.mailing.models.entities.Alerta;
import ar.utn.ba.ddsi.mailing.models.repositories.IAlertaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AlertaRepository implements IAlertaRepository {
    private final Map<Long, Alerta> Alertas = new HashMap<>();
    private final Map<String, Long> ciudadToId = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Alerta save(Alerta Alerta) {
        if (Alerta.getId() == null) {
            Long id = idGenerator.getAndIncrement();
            Alerta.setId(id);
            Alertas.put(id, Alerta);
        } else {
            Alertas.put(Alerta.getId(), Alerta);
        }
        return Alerta;
    }

    @Override
    public List<Alerta> findAll() {
        return new ArrayList<>(Alertas.values());
    }

    @Override
    public Optional<Alerta> findById(Long id) {
        return Optional.ofNullable(Alertas.get(id));
    }

    @Override
    public List<Alerta> findByComunicado(boolean comunicado) {
        return Alertas.values().stream()
                .filter(c -> c.comunicada == comunicado)
                .toList();
    }

    @Override
    public void delete(Alerta Alerta) {
        if (Alerta.getId() != null) {
            Alertas.remove(Alerta.getId());
        }
    }
} 

package ch.zli.m223.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Buchung;

@ApplicationScoped
public class BuchungService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Buchung createBuchung(Buchung buchung) {
        return entityManager.merge(buchung);
    }

    @Transactional
    public List<Buchung> getall() {
        var query = entityManager.createQuery("FROM Buchung", Buchung.class);
        return query.getResultList();
    }

    @Transactional
    public Buchung updateBuchung(Long id, Buchung buchung) {
        buchung.setId(id);
        return entityManager.merge(buchung);
    }

    @Transactional
    public Buchung acceptBuchung(Long id) {
        Optional<Buchung> buchung = findbyId(id);
        buchung.get().setStatus("accepted");
        return entityManager.merge(buchung.get());
    }

    @Transactional
    public Buchung declineBuchung(Long id) {
        Optional<Buchung> buchung = findbyId(id);
        buchung.get().setStatus("declined");
        return entityManager.merge(buchung.get());
    }

    @Transactional
    public Buchung deleteBuchung(Long buchungId) {
        Buchung stornierteBuchung = entityManager.find(Buchung.class, buchungId);
        entityManager.remove(stornierteBuchung);
        return stornierteBuchung;
    }

    public Optional<Buchung> findbyId(Long id) {
        return entityManager
                .createNamedQuery("Buchung.findById", Buchung.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }
}
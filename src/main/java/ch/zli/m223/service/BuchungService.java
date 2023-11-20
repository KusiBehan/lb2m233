package ch.zli.m223.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import ch.zli.m223.model.Buchung;

@ApplicationScoped
public class BuchungService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public Buchung createBuchung(Buchung buchung) {
        try {
            return entityManager.merge(buchung);
        } catch (Exception e) {
            throw new RuntimeException("Error creating Buchung", e);
        }
    }

    @Transactional
    public List<Buchung> getall() {
        try {
            var query = entityManager.createQuery("FROM Buchung", Buchung.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error getting all Buchungen", e);
        }
    }

    @Transactional
    public Buchung updateBuchung(Long id, Buchung buchung) {
        try {
            buchung.setId(id);
            return entityManager.merge(buchung);
        } catch (Exception e) {
            throw new RuntimeException("Error updating Buchung", e);
        }
    }

    @Transactional
    public Buchung acceptBuchung(Long id) {
        try {
            Optional<Buchung> buchung = findbyId(id);
            buchung.orElseThrow(() -> new EntityNotFoundException("Buchung not found"));
            buchung.get().setStatus("accepted");
            return entityManager.merge(buchung.get());
        } catch (Exception e) {
            throw new RuntimeException("Error accepting Buchung", e);
        }
    }

    @Transactional
    public Buchung declineBuchung(Long id) {
        try {
            Optional<Buchung> buchung = findbyId(id);
            buchung.orElseThrow(() -> new EntityNotFoundException("Buchung not found"));
            buchung.get().setStatus("declined");
            return entityManager.merge(buchung.get());
        } catch (Exception e) {
            throw new RuntimeException("Error declining Buchung", e);
        }
    }

    @Transactional
    public Buchung deleteBuchung(Long buchungId) {
        try {
            Buchung stornierteBuchung = entityManager.find(Buchung.class, buchungId);
            if (stornierteBuchung == null) {
                throw new EntityNotFoundException("Buchung not found");
            }
            entityManager.remove(stornierteBuchung);
            return stornierteBuchung;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting Buchung", e);
        }
    }

    public Optional<Buchung> findbyId(Long id) {
        try {
            return entityManager
                    .createNamedQuery("Buchung.findById", Buchung.class)
                    .setParameter("id", id)
                    .getResultStream()
                    .findFirst();
        } catch (Exception e) {
            throw new RuntimeException("Error finding Buchung by ID", e);
        }
    }
}

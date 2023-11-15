package ch.zli.m223.service;

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
}
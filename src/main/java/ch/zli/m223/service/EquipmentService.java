package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Equipment;

@ApplicationScoped
public class EquipmentService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Equipment createEquipment(Equipment equipment) {
        return entityManager.merge(equipment);
    }

    @Transactional
    public List<Equipment> getall() {
        var query = entityManager.createQuery("FROM Equipment", Equipment.class);
        return query.getResultList();
    }

    // @Transactional
    // public Buchung deleteBuchung(Long buchungId) {
    // Buchung stornierteBuchung = entityManager.find(Buchung.class, buchungId);
    // entityManager.remove(stornierteBuchung);
    // return stornierteBuchung;
    // }
}
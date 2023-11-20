package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import ch.zli.m223.model.Room;

@ApplicationScoped
public class RoomService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Room createRoom(Room room) {
        return entityManager.merge(room);
    }

    @Transactional
    public List<Room> getall() {
        var query = entityManager.createQuery("FROM Room", Room.class);
        return query.getResultList();
    }

    // @Transactional
    // public Buchung deleteBuchung(Long buchungId) {
    // Buchung stornierteBuchung = entityManager.find(Buchung.class, buchungId);
    // entityManager.remove(stornierteBuchung);
    // return stornierteBuchung;
    // }
}
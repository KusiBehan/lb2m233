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

    @Transactional
    public Room deleteRoom(Long roomId) {
        Room stornierteBuchung = entityManager.find(Room.class, roomId);
        entityManager.remove(stornierteBuchung);
        return stornierteBuchung;
    }

    @Transactional
    public Room updateRoom(Long id, Room room) {
        room.setId(id);
        return entityManager.merge(room);
    }
}
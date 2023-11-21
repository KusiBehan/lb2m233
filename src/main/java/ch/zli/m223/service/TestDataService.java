package ch.zli.m223.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Buchung;
import ch.zli.m223.model.Equipment;
import ch.zli.m223.model.Room;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;

@IfBuildProfile("dev")
@ApplicationScoped
public class TestDataService {

    @Inject
    EntityManager entityManager;

    @Transactional
    void generateTestData(@Observes StartupEvent event) {
        // Equipments
        var projectAEquipment = new Equipment();
        projectAEquipment.setName("Kaffemaschine");
        projectAEquipment.setBeschreibung("Eine Kaffemaschine um Kaffe zu trinken");
        projectAEquipment.setPreis(22.05);
        entityManager.persist(projectAEquipment);

        var projectBEquipment = new Equipment();
        projectBEquipment.setName("Schreibtisch");
        projectBEquipment.setBeschreibung("Einen Schreibtisch um zu schreiben");
        projectBEquipment.setPreis(400.00);
        entityManager.persist(projectBEquipment);

        // Rooms
        var newRoom1 = new Room();
        newRoom1.setPlace("newPlace1");
        newRoom1.setEquipments(new HashSet<>(Arrays.asList(projectAEquipment, projectBEquipment)));
        newRoom1.setgroesse("32cm2");
        entityManager.persist(newRoom1);

        // Users
        var user1 = new ApplicationUser();
        user1.setEmail("Nasko@icloud.com");
        user1.setFirstName("Nasko");
        user1.setLastName("Feratovic");
        user1.setRole("Admin");
        user1.setPassword("12345");
        entityManager.persist(user1);

        // Buchungen
        var firstEntry = new Buchung();
        firstEntry.setBookedRoom(newRoom1);
        firstEntry.setStatus("Pending");
        firstEntry.setDatum(LocalDateTime.parse("2023-11-15T08:00:12"));
        firstEntry.setDauer("Halber Tag");
        firstEntry.setUser(user1);
        entityManager.persist(firstEntry);
    }
}

package ch.zli.m223.model;

import java.util.Set;

import javax.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String beschreibung;

    @Column(nullable = false)
    private Float preis;

    // @ManyToMany(mappedBy = "equipments")
    @ManyToMany
    @JoinTable(name = "room_equipment", joinColumns = @JoinColumn(name = "equipment_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
    @JsonIgnoreProperties("equipments")
    @Fetch(FetchMode.JOIN)
    private Set<Room> rooms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Float getPreis() {
        return preis;
    }

    public void setPreis(Float preis) {
        this.preis = preis;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

}
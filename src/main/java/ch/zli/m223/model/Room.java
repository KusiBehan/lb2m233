package ch.zli.m223.model;

import java.util.Set;

import javax.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String groesse;

    @OneToMany(mappedBy = "bookedRoom")
    @JsonIgnoreProperties("bookedRoom")
    @Fetch(FetchMode.JOIN)
    private Set<Buchung> buchungen;

    @ManyToMany
    @JoinTable(name = "room_equipment", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    @JsonIgnoreProperties("rooms")
    @Fetch(FetchMode.JOIN)
    private Set<Equipment> equipments;

    @JsonIgnore
    public Set<Buchung> getBuchungen() {
        return buchungen;
    }

    public Set<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
    }

    public void setBuchungen(Set<Buchung> buchungen) {
        this.buchungen = buchungen;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getgroesse() {
        return groesse;
    }

    public void setgroesse(String groesseparam) {
        groesse = groesseparam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
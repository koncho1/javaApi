package com.example.demo.manager;

import com.example.demo.club.Club;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.ZonedDateTime;


@Entity
@Table(name="manager")
public class Manager {

    @Id
    @Column(name="id")
    private Long id;

    private Long age;

    private String name;

    private String surname;

    @Column(name="club_id",unique = true)
    private Long clubId;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name="club",insertable = false,updatable = false)
    private Club club;

    private ZonedDateTime modificationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public ZonedDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(ZonedDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Manager(Long id, Long age, String name, String surname, Long clubId) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.surname = surname;
        this.clubId = clubId;
    }

    public Manager() {
    }
}

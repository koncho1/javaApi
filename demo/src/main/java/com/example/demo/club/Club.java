package com.example.demo.club;


import com.example.demo.stadium.Stadium;
import jakarta.persistence.*;
import org.joda.time.DateTime;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;

@Entity
@Table(name="club")
public class Club {
    @Id
    @Column(name="id")
    private Long id;
    private String name;
    private String city;

    private Long year;

    private ZonedDateTime modificationDate;

    public ZonedDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(ZonedDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Club() {
    }

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


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Club(Long id, String name, String city,Long year) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.year=year;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", year=" + year +
                '}';
    }
}

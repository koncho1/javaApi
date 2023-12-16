package com.example.demo.club;


import jakarta.persistence.*;
import org.springframework.lang.Nullable;

@Entity
@Table
public class Club {
@Id
@SequenceGenerator(
        name="club_sequence",sequenceName = "club_sequence",allocationSize = 1
)

@GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator="club_sequence"
)
    private Long id;
    private String name;
    private String city;

    private Long year;

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

package de.neusta.ldagostino.codingchallengetdd.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Room {

    private String roomNumber;

    private List<Person> persons = new ArrayList<>();

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room() {
    }
}

package de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
public class RoomEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @NaturalId
    private String roomNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private List<PersonEntity> persons = new ArrayList<>();
}

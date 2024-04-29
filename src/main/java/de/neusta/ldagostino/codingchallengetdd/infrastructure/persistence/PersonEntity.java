package de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
public class PersonEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String ldapuser;

    private String title;

    private String nameAddition;

//    @ManyToOne
//    private RoomEntity roomEntity;
}

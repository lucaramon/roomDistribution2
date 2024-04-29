package de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomJpaRepository extends JpaRepository<RoomEntity, UUID> {

    RoomEntity findRoomByRoomNumber(String roomNumber);
}

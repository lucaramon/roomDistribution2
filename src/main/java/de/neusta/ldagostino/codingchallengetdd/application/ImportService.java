package de.neusta.ldagostino.codingchallengetdd.application;

import de.neusta.ldagostino.codingchallengetdd.domain.Room;

import java.util.List;

public interface ImportService {

    List<Room> importData(byte[] content);
}

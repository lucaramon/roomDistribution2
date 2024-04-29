package de.neusta.ldagostino.codingchallengetdd.application;

import de.neusta.ldagostino.codingchallengetdd.domain.Person;

public interface CreatePersonService {

    Person createPerson(String personAsString);
}

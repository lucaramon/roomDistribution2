package de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence;

import de.neusta.ldagostino.codingchallengetdd.domain.Person;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Collection<Person> mapPersonEntities(Collection<PersonEntity> personEntities);

    Collection<PersonEntity> mapPersons(Collection<Person> persons);
}

package de.neusta.ldagostino.codingchallengetdd.infrastructure.persistence;

import de.neusta.ldagostino.codingchallengetdd.domain.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;

@ExtendWith(MockitoExtension.class)
class PersonMapperImplTest {

    @InjectMocks
    PersonMapperImpl personMapper;

    @Test
    void mapEmptyPersonCollectionEntityToPersonCollectionTest() {

        Collection<Person> persons = personMapper.mapPersonEntities(null);

        Assertions.assertThat(persons).isEqualTo(null);
    }

    @Test
    void mapPersonCollectionEntityToPersonCollectionTest() {

        Collection<PersonEntity> personEntities = new ArrayList<>();
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstname("Susanne");
        personEntity.setLastname("Moog");
        personEntity.setLdapuser("smoog");
        personEntities.add(personEntity);

        Collection<Person> persons = personMapper.mapPersonEntities(personEntities);

        Assertions.assertThat(persons).isNotEmpty();
        Assertions.assertThat(persons.size()).isEqualTo(personEntities.size());
        Assertions.assertThat(persons.stream().toList().get(0).getLdapuser()).isEqualTo(personEntity.getLdapuser());
    }

    @Test
    void mapEmptyPersonCollectionToPersonEntityCollectionTest() {

        Collection<PersonEntity> personEntities = personMapper.mapPersons(null);

        Assertions.assertThat(personEntities).isEqualTo(null);
    }

    @Test
    void mapPersonCollectionToPersonEntityCollectionTest() {

        Collection<Person> persons = new ArrayList<>();
        Person person = new Person("Susanne", "Moog", "smoog");
        persons.add(person);

        Collection<PersonEntity> personEntities = personMapper.mapPersons(persons);

        Assertions.assertThat(personEntities).isNotEmpty();
        Assertions.assertThat(personEntities.size()).isEqualTo(persons.size());
        Assertions.assertThat(personEntities.stream().toList().get(0).getLdapuser()).isEqualTo(person.getLdapuser());
    }

    @Test
    void mapEmptyPersonEntityToPersonTest() {

        Person person = personMapper.personEntityToPerson(null);

        Assertions.assertThat(person).isEqualTo(null);
    }

    @Test
    void mapPersonEntityToPersonTest() {

        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstname("Susanne");
        personEntity.setLastname("Moog");
        personEntity.setLdapuser("smoog");

        Person person = personMapper.personEntityToPerson(personEntity);

        Assertions.assertThat(person.getLdapuser()).isEqualTo(personEntity.getLdapuser());
    }

    @Test
    void mapEmptyPersonToPersonEntityTest() {

        PersonEntity personEntity = personMapper.personToPersonEntity(null);

        Assertions.assertThat(personEntity).isEqualTo(null);
    }

    @Test
    void mapPersonToPersonEntityTest() {

        Person person = new Person("Susanne", "Moog", "smoog");

        PersonEntity personEntity = personMapper.personToPersonEntity(person);

        Assertions.assertThat(personEntity.getLdapuser()).isEqualTo(person.getLdapuser());
    }
}
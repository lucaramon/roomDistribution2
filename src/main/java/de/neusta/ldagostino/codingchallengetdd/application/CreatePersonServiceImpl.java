package de.neusta.ldagostino.codingchallengetdd.application;

import de.neusta.ldagostino.codingchallengetdd.domain.Person;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class CreatePersonServiceImpl implements CreatePersonService {

    @Override
    public Person createPerson(String personAsString) {

        List<String> personSplitByValues = Arrays.asList(personAsString.split(" "));
        List<String> valuesOfPerson = new LinkedList<>(personSplitByValues);

        String ldapuser = getLdapuser(valuesOfPerson);
        String lastname = getLastname(valuesOfPerson);
        String nameAddition = getNameAddition(valuesOfPerson);
        String title = getTitle(valuesOfPerson);
        String firstname = getFirstname(valuesOfPerson);

        Person person = new Person(firstname, lastname, ldapuser);
        person.setTitle(title);
        person.setNameAddition(nameAddition);

        return person;
    }

    private String getLdapuser(List<String> valuesOfPerson) {
        String ldapuser = valuesOfPerson.get(valuesOfPerson.size() - 1).replaceAll("[()]", "");
        valuesOfPerson.remove(valuesOfPerson.size() - 1);
        return ldapuser;
    }

    private String getLastname(List<String> valuesOfPerson) {
        String lastname = valuesOfPerson.get(valuesOfPerson.size() - 1);
        valuesOfPerson.remove(valuesOfPerson.size() - 1);
        return lastname;
    }

    private String getNameAddition(List<String> valuesOfPerson) {
        String nameAddition = null;
        if (valuesOfPerson.contains("van") || valuesOfPerson.contains("von") || valuesOfPerson.contains("de")) {
            nameAddition = valuesOfPerson.get(valuesOfPerson.size() - 1);
            valuesOfPerson.remove(valuesOfPerson.size() - 1);
        }
        return nameAddition;
    }

    private String getTitle(List<String> valuesOfPerson) {
        String title = null;
        if (Objects.equals(valuesOfPerson.get(0), "Dr.")) {
            title = valuesOfPerson.get(0);
            valuesOfPerson.remove(0);
        }
        return title;
    }

    private String getFirstname(List<String> valuesOfPerson) {

        return String.join(" ", valuesOfPerson);
    }
}
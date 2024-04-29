package de.neusta.ldagostino.codingchallengetdd.domain;

import lombok.Data;

@Data
public class Person {

    private String firstname;
    private String lastname;
    private String ldapuser;
    private String nameAddition;
    private String title;

    public Person(String firstname, String lastname, String ldapuser){
        this.firstname = firstname;
        this.lastname = lastname;
        this.ldapuser = ldapuser;
    }

    public Person(){}
}

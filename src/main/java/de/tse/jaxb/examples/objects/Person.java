package de.tse.jaxb.examples.objects;


import com.migesok.jaxb.adapter.javatime.LocalDateXmlAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Created by Tino on 17.03.2016.
 */
@XmlType(name = "PersonType",
        namespace = "http://xmldefs.mydefinitions/common",
        propOrder = { "firstname", "lastname", "dayOfBirth", "privateAddress", "workAddress" })
@XmlRootElement(name = "Person", namespace = "http://xmldefs.mydefinitions/common")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlElement(name = "FirstName", required = true)
    private String firstname;

    @XmlElement(name = "LastName", required = true)
    private String lastname;

    @XmlElement(name = "DayOfBirth")
    @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
    private LocalDate dayOfBirth;

    @XmlElement(name = "PrivateAddress", namespace = "http://xmldefs.mydefinitions/common")
    private Address privateAddress;

    @XmlElement(name = "WorkAddress", namespace = "http://xmldefs.mydefinitions/common")
    private Address workAddress;

    public Person(final String firstname, final String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Person() {

    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }
    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public Address getPrivateAddress() {
        return privateAddress;
    }
    public void setPrivateAddress(Address privateAddress) {
        this.privateAddress = privateAddress;
    }

    public Address getWorkAddress() {
        return workAddress;
    }
    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dayOfBirth=" + dayOfBirth +
                ", privateAddress=" + privateAddress +
                ", workAddress=" + workAddress +
                '}';
    }
}

package de.tse.jaxb.examples.objects;


import javax.xml.bind.annotation.*;

/**
 * Created by Tino on 17.03.2016.
 */
@XmlType(name = "SimplePersonType",
        propOrder = { "firstname", "lastname" })
@XmlRootElement(name = "SimplePerson")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimplePerson {

    @XmlElement(name = "FirstName")
    private String firstname;

    @XmlElement(name = "LastName")
    private String lastname;

    public SimplePerson(final String firstname, final String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public SimplePerson() {

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

    @Override
    public String toString() {
        return "SimplePerson{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}

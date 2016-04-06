package de.tse.jaxb.examples.objects;

import javax.xml.bind.annotation.*;

/**
 * Created by Tino on 17.03.2016.
 */
@XmlType(name = "StreetType",
        namespace = "http://xmldefs.mydefinitions/common",
        propOrder = { "number" })
@XmlAccessorType(XmlAccessType.FIELD)
public class Street {

    @XmlValue
    private String street;

    @XmlAttribute(name = "number")
    private String number;

    public Street(final String street, final String number) {
        this.street = street;
        this.number = number;
    }

    public Street() {

    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Street{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}

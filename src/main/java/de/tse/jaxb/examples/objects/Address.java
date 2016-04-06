package de.tse.jaxb.examples.objects;

import javax.xml.bind.annotation.*;

/**
 * Created by Tino on 17.03.2016.
 */
@XmlType(name = "AddressType",
        namespace = "http://xmldefs.mydefinitions/common",
        propOrder = { "street", "city" })
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

    @XmlElement(name = "Street", namespace = "http://xmldefs.mydefinitions/common")
    private Street street;

    @XmlElement(name = "City", namespace = "http://xmldefs.mydefinitions/common")
    private City city;

    public Address(final Street street, final City city) {
        this.street = street;
        this.city = city;
    }

    public Address() {

    }

    public Street getStreet() {
        return street;
    }
    public void setStreet(Street street) {
        this.street = street;
    }

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street=" + street +
                ", city=" + city +
                '}';
    }
}

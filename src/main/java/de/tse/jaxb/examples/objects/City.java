package de.tse.jaxb.examples.objects;

import javax.xml.bind.annotation.*;

/**
 * Created by Tino on 17.03.2016.
 */
@XmlType(name = "CityType",
        namespace = "http://xmldefs.mydefinitions/common",
        propOrder = { "city" })
@XmlAccessorType(XmlAccessType.FIELD)
public class City {

    @XmlValue
    private String city;

    @XmlAttribute(name = "zipCode")
    private String zipCode;

    public City(final String zipCode, final String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public City() {

    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "City{" +
                "zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

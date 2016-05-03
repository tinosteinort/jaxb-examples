package de.tse.jaxb.examples;

import de.tse.jaxb.examples.objects.Address;
import de.tse.jaxb.examples.objects.City;
import de.tse.jaxb.examples.objects.Person;
import de.tse.jaxb.examples.objects.Street;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * Created by Tino on 17.03.2016.
 */
public class _03_ObjectToFile_Complex {

    public static void main(String[] args) throws JAXBException, IOException, SAXException {

        // Create Context
        final JAXBContext context = JAXBContext.newInstance(Person.class);

        // Java to XML -> Marshaller needed
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);



        // Prepare Data
        final Person person = new Person("Dagobert", "Duck");
        // LocalDate etc. is not supported out of the box
        person.setDayOfBirth(LocalDate.of(1900, 01, 01));
        person.setPrivateAddress(new Address(
                new Street("Zum Ententeich", "17a"),
                new City("38313", "Entenhausen")
        ));
        person.setWorkAddress(new Address(
                new Street("Am Geldspeicher", "1"),
                new City("38313", "Entenhausen")
        ));



        // Marshalling
        final Path target = Paths.get("Person.xml");
        try (final OutputStream out = Files.newOutputStream(target)) {

            marshaller.marshal(person, out);
        }
    }
}

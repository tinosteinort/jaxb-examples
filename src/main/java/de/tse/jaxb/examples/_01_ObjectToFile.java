package de.tse.jaxb.examples;

import de.tse.jaxb.examples.objects.SimplePerson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Tino on 17.03.2016.
 */
public class _01_ObjectToFile {

    public static void main(String[] args) throws JAXBException, IOException {

        // Create Context
        final JAXBContext context = JAXBContext.newInstance(SimplePerson.class);

        // Java to XML -> Marshaller needed
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);



        // Prepare Data
        final SimplePerson person = new SimplePerson("Dagobert", "Duck");



        // Marshalling
        final Path target = Paths.get("SimplePerson.xml");
        try (final OutputStream out = Files.newOutputStream(target)) {

            marshaller.marshal(person, out);
        }

    }
}

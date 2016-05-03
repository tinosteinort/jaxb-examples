package de.tse.jaxb.examples;

import de.tse.jaxb.examples.objects.SimplePerson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Tino on 17.03.2016.
 */
public class _02_FileToObject {

    public static void main(String[] args) throws JAXBException, IOException {

        // Create Context
        final JAXBContext context = JAXBContext.newInstance(SimplePerson.class);

        // XML to Java -> Unmarshaller needed
        final Unmarshaller unmarshaller = context.createUnmarshaller();



        // Unmarshalling
        final Path source = Paths.get("SimplePerson.xml");
        try (final InputStream in = Files.newInputStream(source)) {

            final SimplePerson person = (SimplePerson) unmarshaller.unmarshal(in);
            System.out.println(person);
        }
    }
}

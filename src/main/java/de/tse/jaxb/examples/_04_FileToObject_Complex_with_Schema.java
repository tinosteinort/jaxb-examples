package de.tse.jaxb.examples;

import de.tse.jaxb.examples.objects.Person;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Tino on 17.03.2016.
 */
public class _04_FileToObject_Complex_with_Schema {

    public static void main(String[] args) throws JAXBException, IOException, SAXException {

        // Create Context
        final JAXBContext context = JAXBContext.newInstance(Person.class);

        // Java to XML -> Marshaller needed
        final Unmarshaller unmarshaller = context.createUnmarshaller();



        // Load Schema
        final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        final Schema commonSchema = schemaFactory.newSchema(new StreamSource(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("schemagen/Common.xsd")));

        // Configure Marshaller with Schema
        unmarshaller.setSchema(commonSchema);



        // Marshalling
        final Path source = Paths.get("Person.xml");
        try (final InputStream in = Files.newInputStream(source)) {

            final Person person = (Person) unmarshaller.unmarshal(in);
            System.out.println(person);
        }

    }
}

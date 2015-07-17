
import java.io.FileOutputStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLWriter {
    private String configFile;
    private XMLEventWriter eventWriter;
    private XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    private XMLEvent end = eventFactory.createDTD("\n");
    private XMLEvent tab = eventFactory.createDTD("\t");

    public void setFile(String configFile) {
        this.configFile = configFile;
    }

    public void saveConfig() throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

        eventWriter = outputFactory
                .createXMLEventWriter(new FileOutputStream(configFile));

        StartDocument startDocument = eventFactory.createStartDocument();
        eventWriter.add(startDocument);

        StartElement configStartElement = eventFactory.createStartElement("",
                "", "course");
        eventWriter.add(configStartElement);
        eventWriter.add(end);

        createNode("id", "1");
        createNode("name", "TDD");
        createNode("description", "Crash course");

        eventWriter.add(eventFactory.createEndElement("", "", "course"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();
    }

    private void createNode(String name,
                            String value) throws XMLStreamException {
        createStartNode(name);
        createContent(value);
        createEndNode(name);
    }

    private void createStartNode(String name) throws XMLStreamException {
        StartElement startElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(startElement);
    }

    private void createContent(String value) throws XMLStreamException {
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
    }

    private void createEndNode(String name) throws XMLStreamException {
        EndElement endElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(endElement);
        eventWriter.add(end);
    }
} 
package katok.Core.XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLDataMiner extends DefaultHandler {
    static List<eMailsModel> eMails;
    private eMailsModel eMail;
    boolean bId = false;
    boolean bRecepient = false;
    boolean bSubject = false;
    boolean bText = false;
    private static final String EMPLOYEES_XML = "Emails.xml";

    public List<eMailsModel> getEmails() throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        XMLDataMiner xmlDataMiner = new XMLDataMiner();
        saxParser.parse(new File(EMPLOYEES_XML), xmlDataMiner);
        return eMails;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("Email")) {
            String id = attributes.getValue("id");
            eMail = new eMailsModel();
            eMail.setId(Integer.parseInt(id));
            if (eMails == null) {
                eMails = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("recepient")) {
            bRecepient = true;
        } else if (qName.equalsIgnoreCase("subject")) {
            bSubject = true;
        } else if (qName.equalsIgnoreCase("text")) {
            bText = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Email")) {
            eMails.add(eMail);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bRecepient) {
            eMail.setRecepient(new String(ch, start, length));
            bRecepient = false;
        } else if (bSubject) {
            eMail.setSubject(new String(ch, start, length));
            bSubject = false;
        } else if (bText) {
            eMail.setText(new String(ch, start, length));
            bText = false;
        }
    }
}

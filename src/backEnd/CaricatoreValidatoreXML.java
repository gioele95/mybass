package backEnd;

import com.thoughtworks.xstream.XStream;
import java.io.*;
import java.nio.file.*;
import javax.swing.text.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;

/*
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import org.xml.sax.*;
import org.w3c.dom.Document;
/**
 *
 * @author Gioele
 */
public class CaricatoreValidatoreXML {
    private String pathFileXML;
    private String pathFileXSD;
    private XStream streamXML;
    private String xml;
    public CaricatoreValidatoreXML(String xsd,String xml){
        pathFileXSD=xsd;
        pathFileXML=xml;
        streamXML=new XStream();
    }
    public boolean validaXML(){
	try{
            xml = new String(Files.readAllBytes(Paths.get(pathFileXML)));
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document documentoXML =  db.parse(new InputSource(new StringReader(xml))); 
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new StreamSource(new File(pathFileXSD)));
            schema.newValidator().validate(new DOMSource(documentoXML));
            return true;
	}catch(Exception ex){
            System.out.println(ex);
	    return false;
        }
    }
    public Object prelevaDaXML(){
        try{
            if(!validaXML()){
                System.out.println("non validato");
                return null;
            }
            return streamXML.fromXML(xml);
        } catch(Exception e) {
            System.out.println("errore caricamento xml "+ e);
            return null;
        }     
    }
}

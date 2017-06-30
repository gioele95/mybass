package backEnd;

import com.thoughtworks.xstream.XStream;
import java.nio.file.*;
import javax.xml.*;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

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
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(pathFileXSD));
	    Validator validator = schema.newValidator();
	    validator.validate(new StreamSource(xml));
	    return true;
	}catch(Exception ex){
	    return false;
        }
    }
    public Object prelevaDaXML(){
        try{
            if(!validaXML())
                return null;
            return streamXML.fromXML(xml);
        } catch(Exception e) {
            System.out.println("errore caricamento xml "+e.getLocalizedMessage());
            return null;
        }     
    }
}

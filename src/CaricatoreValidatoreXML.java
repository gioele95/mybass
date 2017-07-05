

import com.thoughtworks.xstream.XStream;
import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.w3c.dom.Document;

public class CaricatoreValidatoreXML {
    private String pathFileXML;
    private String pathFileXSD;
    public CaricatoreValidatoreXML(String xsd,String xml){
        pathFileXSD=xsd;
        pathFileXML=xml;
    }
    private boolean validaXML(){
	try{
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            
            Document d =  db.parse(new File(pathFileXML)); 
            
            Schema s= sf.newSchema(new StreamSource(new File(pathFileXSD)));
            
            s.newValidator().validate(new DOMSource(d));
            System.out.println("pathxsd "+pathFileXSD+ " pathXML "+pathFileXML);
            return true;
	}catch(Exception ex){
                        System.out.println("exc"+ex);
	    return false;
        }
    }
    public Object prelevaDaXML(){
        XStream xs = new XStream();
        if(!validaXML()){
            System.out.println("non validato");
            return null;
        }
        System.out.println("valido");
        return  xs.fromXML(new File(pathFileXML));
    }
}

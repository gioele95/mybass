

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.StringReader;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class CaricatoreValidatoreXML {
    private String pathFileXML;
    private String pathFileXSD;
    public CaricatoreValidatoreXML(String xsd,String xml){
        pathFileXSD=xsd;
        pathFileXML=xml;
    }
    public void setPathXML(String s){
        pathFileXML=s;        
    }
    public boolean validaXML(String xml){
        Document d;
	try{
          //  System.out.println("validazione : "+ pathFileXSD);
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            if(xml.equals(""))                                                   //1)
                d =  db.parse(new File(pathFileXML)); 
            else
                d=  db.parse(new InputSource(new StringReader(xml))); 
            Schema s= sf.newSchema(new StreamSource(new File(pathFileXSD)));
            
            s.newValidator().validate(new DOMSource(d));
            return true;
	}catch(Exception ex){
                        System.out.println("exc"+ex);
	    return false;
        }
    }
    public Object prelevaDaXML(String s){
        XStream xs = new XStream();
        if(!validaXML(s)){
            System.out.println("non validato");
            return null;
        }
       // System.out.println("valido");
        return  xs.fromXML(new File(pathFileXML));
    }
}
//1) se è "" riguarda la validazione dei parametri di configurazione.

public class ParametriConfigurazioneXML {
    public int numeroMassimoPesci;
    public  String coloreSfondo;
    public int portaServerLog;
    public String IPServerLog;
    public String hostnameDatabase;
    public String utenteDatabase;
    public String passwordDatabase;
    public int portaDatabase;
    public String IPClient;
    public String pathImmagine;

    public ParametriConfigurazioneXML(){
        ottieniParametriConfigurazioneXML();
        ParametriConfigurazioneXML par=ParametriConfigurazioneXML.ottieniParametriConfigurazioneXML();
        IPServerLog=par.IPServerLog;
        portaServerLog=par.portaServerLog;
        IPClient=par.IPClient;
        numeroMassimoPesci=par.numeroMassimoPesci;
        coloreSfondo=par.coloreSfondo;
        hostnameDatabase=par.hostnameDatabase;
        utenteDatabase=par.utenteDatabase;
        passwordDatabase=par.passwordDatabase;
        portaDatabase=par.portaDatabase;
        pathImmagine=par.pathImmagine;
    }
    public static ParametriConfigurazioneXML ottieniParametriConfigurazioneXML(){
        CaricatoreValidatoreXML caricatore= new CaricatoreValidatoreXML("configurazione/conf.xsd","configurazione/conf.xml");
        ParametriConfigurazioneXML par=  (ParametriConfigurazioneXML)caricatore.prelevaDaXML("");
        return par;
    }
    
    
    /* public static  boolean validaXML(){
	try{
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Document d =  db.parse(new File("conf.xml")); 
            Schema s= sf.newSchema(new StreamSource(new File("conf.xsd")));
            s.newValidator().validate(new DOMSource(d));
            return true;
	}catch(Exception ex){
                        System.out.println("exc"+ex);
	    return false;
        }
    }*/
}

package middleWare;

import backEnd.CaricatoreValidatoreXML;
import java.io.Serializable;

/**
 *
 * @author Gioele
 */
public class ParametriConfigurazioneXML implements Serializable {
    public int numeroMassimoPesci;
    public  String coloreSfondo;
    public int portaServerDiLog;
    public String IPServerDiLog;
    public String hostnameDatabase;
    public String utenteDatabase;
    public String passwordDatabase;
    public int portaDatabase;
    public String IPClient;

    
   // public CaricatoreValidatoreXML caricatore;
    public ParametriConfigurazioneXML(){
        ottieniParametriConfigurazioneXML();
        System.out.println("entro in parametri ");
    }
    private void ottieniParametriConfigurazioneXML(){
        CaricatoreValidatoreXML caricatore= new CaricatoreValidatoreXML("./configurazione/conf.xsd","./configurazione/conf.xml");
        ParametriConfigurazioneXML par=(ParametriConfigurazioneXML)caricatore.prelevaDaXML();
     /*   IPServerDiLog=par.IPServerDiLog;
        System.out.println("server di log"+IPServerDiLog);
        portaServerDiLog=par.portaServerDiLog;
        IPClient=par.IPClient;
        numeroMassimoPesci=par.numeroMassimoPesci;
        dimensioneCaratteri=par.dimensioneCaratteri;
        font=par.font;
        coloreSfondo=par.coloreSfondo;*/
    }
    
}

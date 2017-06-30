package middleWare;

import backEnd.CaricatoreValidatoreXML;
import java.io.Serializable;

/**
 *
 * @author Gioele
 */
public class ParametriConfigurazioneXML implements Serializable {
    public String IPServerDiLog;
    public int portaServerDiLog;
    public String IPClient;
    public int numeroMassimoPesci;
    public int dimensioneCaratteri;
    public String font;
    public  String coloreSfondo;
   // public CaricatoreValidatoreXML caricatore;
    public void  ParametriConfigurazioneXML(){
        ottieniParametriConfigurazioneXML();
    }
    private void ottieniParametriConfigurazioneXML(){
        CaricatoreValidatoreXML caricatore= new CaricatoreValidatoreXML("./configurazione/conf.xsd","./configurazione/conf.xml");
        ParametriConfigurazioneXML par=(ParametriConfigurazioneXML)caricatore.prelevaDaXML();
        IPServerDiLog=par.IPServerDiLog;
        portaServerDiLog=par.portaServerDiLog;
        IPClient=par.IPClient;
        numeroMassimoPesci=par.numeroMassimoPesci;
        dimensioneCaratteri=par.dimensioneCaratteri;
        font=par.font;
        coloreSfondo=par.coloreSfondo;
    }
    
}

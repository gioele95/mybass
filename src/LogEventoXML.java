import java.text.*;
import java.util.*;
public class LogEventoXML { 
    public final String nomeEvento; 
    public String IPClient;
    public final String data;
    public final String ora;    
    public LogEventoXML(String evento, String IPClient) {
        nomeEvento = evento;
        this.IPClient = IPClient;
        data = new SimpleDateFormat("yyyy-MM-dd").format((new Date()));
        ora =new SimpleDateFormat("HH:mm:ss").format((new Date()));
    }

}   
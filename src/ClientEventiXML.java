import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.converters.basic.*;
import java.io.*;
import java.net.*;

public class ClientEventiXML { 
    public static void inviaLog(String nomeEvento, String ipServer, int portaServer, String ipClient) { 
        XStream xs = new XStream();
        
        xs.useAttributeFor(EventoXML.class, "nomeEvento");//00)
        xs.alias("Evento", EventoXML.class);
        
        String x = xs.toXML(new EventoXML(nomeEvento, ipClient));
        
        xs.registerConverter(new DateConverter("yyyy-MM-dd", null));
        xs.registerConverter(new DateConverter("HH:mm:ss", null));
        
        try(Socket socket = new Socket(ipServer, portaServer); 
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream()); 
        ) {
            dout.writeUTF(x); 
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

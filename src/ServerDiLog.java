
import com.thoughtworks.xstream.XStream;
import java.io.DataInputStream;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ServerDiLog {
    private final static int PORTA_SERVER = 8080;
    private static int portaAscolto;
    public ServerDiLog(int p, String s){
        portaAscolto=p;
        //indirizzoIP=s;
    }
    public static void aggiungiAlFile(String xml){
                        System.out.println("2");

        CaricatoreValidatoreXML valid = new CaricatoreValidatoreXML("log/EventoLog.xsd",null);
                        System.out.println("3");

        if(!valid.validaXML(xml)) { 
            System.out.println("non valido");
            return;
        } System.out.println("3e");

        String ls = System.lineSeparator();
        xml+=ls+ls;
        //xml=xml+"/n";
        try {
            if(!Files.exists(Paths.get("log/log.xml")))
                Files.createFile(Paths.get("log/log.xml"));
            Files.write(Paths.get("log/log.xml"),xml.getBytes(),StandardOpenOption.APPEND); 
        } catch(Exception e) {
            System.out.println("Impossibile scrivere su file di log: "+e.getMessage());
        }
        System.out.println("43");

    }
  public static void main(String[] args) {
         ServerDiLog sdl = new ServerDiLog(PORTA_SERVER,"localhost");
        while(true) {
            System.out.println("In attesa di connessioni...");
                            System.out.println("4");

            try(
                     
                
                ServerSocket socketServerDiLog = new ServerSocket(portaAscolto);
                Socket s = socketServerDiLog.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
            ) {
                System.out.println("1");
               
                String attivitaXML= (String)dis.readUTF(); 
                
                System.out.println("readutf");

                System.out.println("Ricevutog: "+attivitaXML);
                
                aggiungiAlFile(attivitaXML);                
            } catch(Exception e) {
                System.out.println("Errore server: "+e);//.getMessage());
                return;
            }
        }
    }   
}
     /*  
    public static void main(String[] args) {
        EventoXML logXML = null;
        XStream xs = new XStream();
/*        xs.useAttributeFor(EventoXML.class, "nomeEvento");
        xs.alias("Evento", EventoXML.class);
   
            System.out.println("In attesa di connessioni...42");
            while(true) { 
                try(ServerSocket serverSocket = new ServerSocket(PORTA_SERVER);
                    Socket socket = serverSocket.accept(); 
                    DataInputStream din = new DataInputStream(socket.getInputStream()); 
                ) {
                    System.out.println("1");
                            
                       //ale     logXML = (EventoXML) xs.fromXML(logString);
                    String attivitaXML=(String)din.readUTF(); 
                    System.out.println("readutf");

                    System.out.println("Ricevutog: "+attivitaXML);

                    aggiungiAlFile(attivitaXML);                
                } catch(Exception e) {
                    System.out.println("Errore server: "+e);//.getMessage());
                    return;
                }
            }
    }
}
public static void main(String[] args){
        
        try(
                ServerSocket sv = new ServerSocket(PORTA_SERVER);

                ){
                 System.out.println("In attesa di connessioni...4fe2");
                while(true){
                
                 Socket so = sv.accept();
                 DataInputStream dis  = new DataInputStream(so.getInputStream());
                 
                 String risultato = dis.readUTF();
                 System.out.println(risultato);  
                 
///                 validaRecordLogXML(risultato);
                  aggiungiAlFile(risultato);  
                /*FileWriter fw = new FileWriter("serverLog.txt",true);
                fw.write(risultato + "\n\n");
                fw.close();
                
                 dis.close();
                 so.close();
            }
            
            
        }catch(Exception e){
            
          e.printStackTrace();
        }
}}
*/
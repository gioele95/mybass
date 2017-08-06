
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ServerDiLog {
    private static int portaAscolto;
    public ServerDiLog(int p, String s){
        portaAscolto=p;
        //indirizzoIP=s;
    }
    public static void aggiungiAlFile(String xml){
                        System.out.println("2");

        CaricatoreValidatoreXML valid = new CaricatoreValidatoreXML("log/EventoLog.xsd",null);
                        System.out.println("3");

        if(valid.prelevaDaXML(xml)==null) { 
            System.out.println("non valido");
            return;
        }
       // String ls = System.lineSeparator();
        //xml+=ls+ls;
        xml=xml+"/n";
        try {
            if(!Files.exists(Paths.get("log/log.xml")))
                Files.createFile(Paths.get("log/log.xml"));
            Files.write(Paths.get("log/log.xml"),xml.getBytes(),StandardOpenOption.APPEND); //(4)
        } catch(Exception e) {
            System.out.println("Impossibile scrivere su file di log: "+e.getMessage());
        }
    }
    public static void main(String[] args) {
         ServerDiLog sdl = new ServerDiLog(8080,"localhost");
        while(true) {
            System.out.println("In attesa di connessioni...");
                            System.out.println("4");

            try(
                     
                
                ServerSocket socketServerDiLog = new ServerSocket(portaAscolto);
                Socket s = socketServerDiLog.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
            ) {
                System.out.println("1");
               
                String attivitaXML = (String)dis.readUTF(); 

                System.out.println("Ricevutog: "+attivitaXML);
                
                aggiungiAlFile(attivitaXML);                
            } catch(Exception e) {
                System.out.println("Errore server: "+e.getMessage());
                return;
            }
        }
    }   
}

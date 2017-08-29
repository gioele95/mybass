import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ServerDiLog {
    private final static int PORTA_SERVER=8080; 
    public static void aggiungiAlFile(String xml){
        CaricatoreValidatoreXML valid = new CaricatoreValidatoreXML("C:/prg/myapps/MyBass/log/EventoLog.xsd",null);
        if(!valid.validaXML(xml)) { 
            System.out.println("non valido");
            return;
        }
        String ls = System.lineSeparator();
        xml+=ls+ls;
        try {
            if(!Files.exists(Paths.get("C:/prg/myapps/MyBass/log/log.xml")))
                Files.createFile(Paths.get("C:/prg/myapps/MyBass/log/log.xml"));
            Files.write(Paths.get("C:/prg/myapps/MyBass/log/log.xml"),xml.getBytes(),StandardOpenOption.APPEND); 
        } catch(IOException e) {
            System.out.println("Impossibile scrivere su file di log: "+e.getMessage());
        }
    }
  public static void main(String[] args) {
        while(true) {
            System.out.println("In attesa di connessioni...");
            try(
                ServerSocket socketServerDiLog = new ServerSocket(PORTA_SERVER);
                Socket s = socketServerDiLog.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
            ) {
                String attivitaXML= (String)dis.readUTF();                
                aggiungiAlFile(attivitaXML);                
            } catch(Exception e) {
                System.out.println("Errore server: "+e);
                return;
            }
        }
    }   
}

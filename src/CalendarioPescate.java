import java.time.LocalDate;
import javafx.geometry.HPos;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
public class CalendarioPescate {
        private DatePicker calendario;   //1)
        private GridPane gridPane;
        private Label titolo;
        private static LocalDate dataSelezionata;
        private TabellaCatture tabella;
        private GestoreMappa gestore;
        public GridPane creaCalendario(GestoreMappa g){
            gestore=g;
            calendario = new DatePicker(LocalDate.now());
            dataSelezionata=LocalDate.now();
            calendario.setOnAction((e) -> {System.out.println("CalendarioPescate.creaCalendario()");cambioData(calendario.getValue(),true);});
            gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            titolo = new Label("Data selezionata");
            titolo.setStyle("-fx-font-size: 20px;");
            gridPane.add(titolo, 0, 0);
            GridPane.setHalignment(titolo, HPos.LEFT);
            gridPane.add(calendario, 0, 1);
            return gridPane;
        }
        public void assegnaTabella(TabellaCatture t){
            tabella=t;
        }
        public static String getData(){
            return String.valueOf(dataSelezionata);
        }
        public void setData(String d){
            cambioData(LocalDate.parse(d),false);
            calendario.setValue(LocalDate.parse(d));
        }
        private void cambioData(LocalDate value, boolean mandaLog) {
            if(value.isAfter(LocalDate.now())){                            //2)
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ATTENZIONE");
                alert.setContentText("non puoi scegliere una data futura");
                alert.showAndWait();
                setData(String.valueOf(dataSelezionata));
                return;
            }
            dataSelezionata= value;
            DepositoInformazioniCatture.getIstanza().listaCatture.clear();
            DepositoInformazioniCatture.getIstanza().caricaCatture(String.valueOf(dataSelezionata));
            if(mandaLog){
                ParametriConfigurazioneXML xml = new ParametriConfigurazioneXML();    
                ClientEventiXML.inviaLog("Cambio Data", xml.IPServerLog, xml.portaServerLog, xml.IPClient);
            }
            
            gestore.caricaPosizioni(String.valueOf(dataSelezionata));
        
        }
}

/*
1) https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/DatePicker.html,
    https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/date-picker.htm
2) risulta insensato scegliere una data futura

*/
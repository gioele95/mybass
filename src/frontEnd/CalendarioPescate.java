package frontEnd;

import backEnd.DepositoInformazioniCatture;
import java.time.LocalDate;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

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
            calendario.setOnAction((e) -> {cambioData(calendario.getValue());});
            gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            titolo= new Label("Data selezionata");
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
            cambioData(LocalDate.parse(d));
            calendario.setValue(LocalDate.parse(d));
        }
        private void cambioData(LocalDate value) {
            if(value.isAfter(LocalDate.now())){
                JOptionPane.showMessageDialog(null, "Non puoi selezionare una "
                    + "data futura", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                calendario.setValue(dataSelezionata);
                return;
            }
            dataSelezionata= value;
            DepositoInformazioniCatture.getIstanza().caricaCatture
                                    (String.valueOf(dataSelezionata));
            gestore.caricaPosizioni(String.valueOf(dataSelezionata));
        }
}

/*
1) https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/DatePicker.html,
    https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/date-picker.htm
*/
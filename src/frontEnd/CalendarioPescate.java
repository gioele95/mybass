package frontEnd;


import java.time.LocalDate;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CalendarioPescate {
        private DatePicker calendario;   //1)
        private GridPane gridPane;
        private Label titolo;
        public LocalDate dataSelezionata;
        public GridPane creaCalendario(){
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
        
        private void cambioData(LocalDate value) {
          System.out.println(calendario.getValue());
          dataSelezionata= calendario.getValue();
        }
}

/*
1) https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/DatePicker.html,
    https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/date-picker.htm
*/

import java.time.LocalDate;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class Calendario {
        private DatePicker calendario;   //1)
        private GridPane gridPane;
        private Label dataSelezionata;
        Calendario(){
            calendario.setOnAction((e) -> {commitEdit(calendario.getValue());});
            calendario = new DatePicker(LocalDate.now());
            return calendario;
        }
        public GridPane creaCalendario(){
            //VBox vbox = new VBox(20);
         //   vbox.setStyle("-fx-padding: 10;");            
            gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            dataSelezionata= new Label("Data selezionata");
            gridPane.add(dataSelezionata, 0, 0);
            GridPane.setHalignment(dataSelezionata, HPos.LEFT);
            gridPane.add(calendario, 0, 1);
           // vbox.getChildren().add(gridPane);
            return gridPane;
        }
        
        private void commitEdit(LocalDate value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
}

/*
1) https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/DatePicker.html,
    https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/date-picker.html
*/
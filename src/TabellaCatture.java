
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

/**
 *
 * @author Gioele
 */
public class TabellaCatture extends TableView<DatiCattura>{    
        private final TableColumn<DatiCattura,Integer> colonnaCattura = new TableColumn("Cattura");
        private final TableColumn<DatiCattura,Double> colonnaPeso = new TableColumn("Peso kg");
        private final TableColumn<DatiCattura,String> colonnaTecnica = new TableColumn("Tecnica");
        private final TableColumn<DatiCattura,String> colonnaEsca = new TableColumn("Esca");
        private ObservableList<String> tecnichePossibili;
        public TabellaCatture() {
            //setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //(1)
            colonnaCattura.setCellValueFactory(new PropertyValueFactory<>("cattura"));
            colonnaPeso.setCellValueFactory(new PropertyValueFactory<>("Peso"));
            colonnaTecnica.setCellValueFactory(new PropertyValueFactory<>("Tecnica"));
            setEditable(true);
            getColumns().addAll(colonnaCattura,colonnaPeso,colonnaTecnica,colonnaEsca);
        }
        private void assegnaTecnichePossibili() {
            tecnichePossibili = FXCollections.observableArrayList();
            tecnichePossibili.add("Finesse");
            tecnichePossibili.add("Reazione");
            tecnichePossibili.add("Gomma");
        }
        
}

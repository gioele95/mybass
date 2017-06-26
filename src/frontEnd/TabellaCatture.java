package frontEnd;

import middleWare.*;
import backEnd.DepositoInformazioniCatture;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
/**
 *
 * @author Gioele
 */
public class TabellaCatture extends TableView<DatiCattura>{    
        private final TableColumn<DatiCattura,Integer> colonnaCattura = new TableColumn<>("Cattura");
        private final TableColumn<DatiCattura,String> colonnaPeso = new TableColumn("Peso kg");
        private final TableColumn<DatiCattura,String> colonnaTecnica = new TableColumn("Tecnica");
        private final TableColumn<DatiCattura,String> colonnaEsca = new TableColumn("Esca");
        private final TableColumn<DatiCattura,Double> colonnaX = new TableColumn("CoordinataY");
        private final TableColumn<DatiCattura,Double> colonnaY = new TableColumn("CoordinataX");
        private final TableColumn<DatiCattura,String> colonnaData = new TableColumn("Data");
        private ObservableList<String> tecnichePossibili;
        public DepositoInformazioniCatture deposito;
        public TabellaCatture(String d) {
            setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
            assegnaTecnichePossibili();
            colonnaCattura.setCellValueFactory(new PropertyValueFactory<>("Numero"));
            colonnaPeso.setCellValueFactory(new PropertyValueFactory<>("Peso"));
            colonnaTecnica.setCellValueFactory(new PropertyValueFactory<>("Tecnica"));
            colonnaEsca.setCellValueFactory(new PropertyValueFactory<>("Esca"));
            colonnaX.setCellValueFactory(new PropertyValueFactory<>("CoordinataY"));
            colonnaY.setCellValueFactory(new PropertyValueFactory<>("CoordinataX"));
            colonnaData.setCellValueFactory(new PropertyValueFactory<>("Data"));            
            deposito=DepositoInformazioniCatture.getIstanza();
            colonnaTecnica.setCellFactory(ComboBoxTableCell.forTableColumn(tecnichePossibili));
            setEditable(true);
            getColumns().addAll(colonnaCattura,colonnaPeso,colonnaTecnica,colonnaEsca);
            colonnaEsca.setCellFactory(TextFieldTableCell.<DatiCattura>forTableColumn());
            colonnaPeso.setCellFactory(TextFieldTableCell.<DatiCattura>forTableColumn());//(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            deposito.caricaCatture(d);
            setItems(deposito.listaCatture);
            inizializzaEventi();
            colonnaCattura.setPrefWidth(60);
            colonnaEsca.setPrefWidth(150);
            colonnaPeso.setPrefWidth(60);
            colonnaTecnica.setPrefWidth(100);
        }
        private void assegnaTecnichePossibili() {
            tecnichePossibili = FXCollections.observableArrayList();
            tecnichePossibili.add("Finesse");
            tecnichePossibili.add("Reazione");
            tecnichePossibili.add("Gomma");
        }
        public void inizializzaEventi(){
            colonnaEsca.setOnEditCommit((CellEditEvent<DatiCattura, String> t) -> {
                DatiCattura d= t.getTableView().getItems().get(t.getTablePosition().getRow());
                if(d.getCodiceCattura()!=-1)
                    deposito.modificaCattura(d);
                else 
                    deposito.inserisciCattura(d);
            });
            colonnaTecnica.setOnEditCommit((CellEditEvent<DatiCattura, String> t) -> {
            deposito.modificaCattura((DatiCattura) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
            });
            colonnaPeso.setOnEditCommit((CellEditEvent<DatiCattura, String> t) -> {
            deposito.modificaCattura((DatiCattura) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        );
            });
        }

}

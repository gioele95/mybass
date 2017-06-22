
import java.sql.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.cell.TextFieldTableCell;
/**
 *
 * @author Gioele
 */
public class TabellaCatture extends TableView<DatiCattura>{    
        private final TableColumn<DatiCattura,String> colonnaCattura = new TableColumn<>("Cattura");
        private final TableColumn<DatiCattura,String> colonnaPeso = new TableColumn("Peso kg");
        private final TableColumn<DatiCattura,String> colonnaTecnica = new TableColumn("Tecnica");
        private final TableColumn<DatiCattura,String> colonnaEsca = new TableColumn("Esca");
        private final TableColumn<DatiCattura,String> colonnaX = new TableColumn("CoordinataY");
        private final TableColumn<DatiCattura,String> colonnaY = new TableColumn("CoordinataX");
        private final TableColumn<DatiCattura,String> colonnaData = new TableColumn("Data");
        private ObservableList<String> tecnichePossibili;
        private ObservableList<DatiCattura> listaCatture;
        public TabellaCatture() {
            setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //(1)
            assegnaTecnichePossibili();
            colonnaCattura.setCellValueFactory(new PropertyValueFactory<>("Numero"));
            colonnaPeso.setCellValueFactory(new PropertyValueFactory<>("Peso"));
            colonnaTecnica.setCellValueFactory(new PropertyValueFactory<>("Tecnica"));
            colonnaEsca.setCellValueFactory(new PropertyValueFactory<>("Esca"));
            colonnaX.setCellValueFactory(new PropertyValueFactory<>("CoordinataY"));
            colonnaY.setCellValueFactory(new PropertyValueFactory<>("CoordinataX"));
            colonnaData.setCellValueFactory(new PropertyValueFactory<>("Data"));
            
            
            colonnaTecnica.setCellFactory(ComboBoxTableCell.forTableColumn(tecnichePossibili));
            setEditable(true);
            getColumns().addAll(colonnaCattura,colonnaPeso,colonnaTecnica,colonnaEsca);
            colonnaEsca.setCellFactory(TextFieldTableCell.<DatiCattura>forTableColumn());
            colonnaPeso.setCellFactory(TextFieldTableCell.<DatiCattura>forTableColumn());
            caricaCatture();
            setItems(listaCatture);
        }
        private void assegnaTecnichePossibili() {
            tecnichePossibili = FXCollections.observableArrayList();
            tecnichePossibili.add("Finesse");
            tecnichePossibili.add("Reazione");
            tecnichePossibili.add("Gomma");
        }
        private void caricaCatture() {
            listaCatture = FXCollections.observableArrayList();
            try ( Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybass", "root","");   //9
                Statement st = co.createStatement(); 
            ) { 
                ResultSet rs = st.executeQuery("SELECT *  FROM tabellacatture"); 
                while (rs.next()) 
                  listaCatture.add(new DatiCattura( rs.getString("cattura"),rs.getString("esca"), 
                          rs.getString("data"),rs.getString("tecnica"),rs.getString("peso"),
                          rs.getString("coordinataX"),rs.getString("coordinataY")));
            } catch (SQLException e) {System.err.println(e.getMessage());}     
        }
}

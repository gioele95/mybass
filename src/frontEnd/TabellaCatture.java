package frontEnd;

import middleWare.*;
import backEnd.DepositoInformazioniCatture;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javax.swing.JOptionPane;
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
                modificaCelle(t,0);//1
            });
            colonnaTecnica.setOnEditCommit((CellEditEvent<DatiCattura, String> t) -> {
                modificaCelle(t,1);
            });
            colonnaPeso.setOnEditCommit((CellEditEvent<DatiCattura, String> t) -> {
                modificaCelle(t,2);
            });
        }

        private void modificaCelle(CellEditEvent<DatiCattura, String> t,int sw){
            DatiCattura d= t.getRowValue();           
            int i;
            if(d.getNumero()!=1 && !deposito.primaRigaDisponibile(CalendarioPescate.getData(),d.getNumero())){
                      JOptionPane.showMessageDialog(null, "Aggiungi le catture in ordine",
                              "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                    t.getTableView().getSelectionModel().clearSelection();
                    d.setEsca("");
                    d.setPeso("");
                    d.setTecnica("");
                    t.getTableView().getItems().set(d.getNumero(), d);
                    return;
            }
            switch(sw){
                case 0:
                    d.setEsca(t.getNewValue());
                    break;
                case 1:
                    d.setTecnica(t.getNewValue());
                    break;
                case 2:
                    d.setPeso(t.getNewValue());
                    break;   
            }     
            if(d.getCodiceCattura()!=-1){
                deposito.modificaCattura(d);
                if(sw==1){
                    System.out.println("dopo la inserisci voglio aggiornare il grafico");
                    GraficoTecnicheCatturanti.aggiornaGrafico();
                } 
            }
            else {
                d.setData(CalendarioPescate.getData());
                i=deposito.inserisciCattura(d);
                if(sw==1){
                    System.out.println("dopo la inserisci voglio aggiornare il grafico");
                    GraficoTecnicheCatturanti.aggiornaGrafico();
                }  
                if(i==-1)
                    System.out.println("attenzione inserimento cattura fallito la inserisci ha ritornato-1");
                else
                    d.setCodiceCattura(i);
            }
            deposito.aggiornaDati(d.getData());
        }
}
//1)    mando un int per sapere nel metodo modificaCelle quale sia il valore su cui fare d.set
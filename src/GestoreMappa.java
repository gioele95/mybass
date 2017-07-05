

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Gioele
 */
public class GestoreMappa {
    public ImageView mappaLago;
    public VBox vb;
    public Label[] cattura;
    public ComboBox numeroSelezionato;
    private Label selezioneCattura;
    private HBox hboxSelezione;
    GestoreMappa() {
           vb =new VBox();
           mappaLago=new ImageView("file:Immagini/lago.png");     
           mappaLago.setFitHeight(300);
           mappaLago.setFitWidth(300);
           cattura = new Label[5];
         
           hbox();
           vb.getChildren().addAll(mappaLago,hboxSelezione);
           for (int i=1;i<6;i++){
              cattura[i-1]=new Label (String.valueOf(i));
              cattura[i-1].setVisible(false);
              vb.getChildren().add(cattura[i-1]);
           }
           vb.setSpacing(10);
           hboxSelezione.setPadding(new Insets(10, 30, 10, 30));
    }
    private void hbox(){
        
        selezioneCattura= new Label ("Seleziona Cattura");
        ObservableList<Integer> opzioni;
        selezioneCattura.setStyle("-fx-font-size: 12px;");
        opzioni = FXCollections.observableArrayList(1,2,3,4,5);
        numeroSelezionato= new ComboBox(opzioni);
        numeroSelezionato.setValue(1);
        hboxSelezione = new HBox(20);
        hboxSelezione.setSpacing(10);
        hboxSelezione.setPadding(new Insets(10, 30, 10, 30));
        hboxSelezione.getChildren().addAll(selezioneCattura,numeroSelezionato);
    }
    public void clickMappa(int i,double x, double y,boolean caricamento,String d){
        if(caricamento || DepositoInformazioniCatture.getIstanza().catturaEsistente(d,i+1)){
            cattura[i].setTranslateX(x);
            cattura[i].setTranslateY(y);
            if(x!=0)
                cattura[i].setVisible(true);
            if(!caricamento){
                DepositoInformazioniCatture.getIstanza().impostaCoordinate(x,y,d,i+1);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Non puoi posizionare una  "
                    + "cattura non esistente", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
        }
    }
    public void caricaPosizioni(String d){

        DatiCattura dc;
        numeroSelezionato.setValue(1);
        for(int i=0;i<5;i++){ 
            ObservableList<DatiCattura> ol =DepositoInformazioniCatture.getIstanza().listaCatture;
            if(ol.isEmpty()){
                System.out.println("attenzione observable list vuota");
                return;
            }
            dc=ol.get(i);
            clickMappa(dc.getNumero()-1,dc.getCoordinataX(),dc.getCoordinataY(),true,d);
        }
    }

}

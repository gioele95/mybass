package frontEnd;

import static java.lang.Integer.parseInt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
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
           for (int i=1;i<6;i++){
              cattura[i-1]=new Label (String.valueOf(i));
              cattura[i-1].setVisible(false);
           }
           hbox();
           vb.getChildren().addAll(mappaLago,hboxSelezione,cattura[0],cattura[1],cattura[2],cattura[3],cattura[4]/*,hboxSelezione/*,menu*/);
           vb.setSpacing(10);
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
    public void clickMappa(double x, double y){
        Integer i=(Integer)numeroSelezionato.getValue()-1;
        System.out.println(i);
        cattura[i].setTranslateX(x);
        cattura[i].setTranslateY(y);
        cattura[i].setVisible(true);
    }
}

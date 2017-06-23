
import javafx.event.*;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 *
 * @author Gioele
 */
public class GestoreMappa {
    public ImageView mappaLago;
    public StackPane pane;
    public Label[] cattura;
    GestoreMappa() {
           pane =new StackPane();
          mappaLago=new ImageView("file:Immagini/lago.png");
         
           mappaLago.setFitHeight(300);
           mappaLago.setFitWidth(300);
           cattura = new Label[5];
           for (int i=1;i<6;i++){
               cattura[i-1]=new Label (String.valueOf(i));
              cattura[i-1].setVisible(false);
           }
           pane.getChildren().addAll(mappaLago,cattura[0],cattura[1],cattura[2],cattura[3],cattura[4]);
    }
    public void clickMappa(double x, double y){
        cattura[0].setTranslateX(x);
        cattura[0].setTranslateY(y);
        cattura[0].setVisible(true);

    }
}

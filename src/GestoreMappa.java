
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
    public Label cattura[];
    public StackPane pane;
    GestoreMappa() {
           pane =new StackPane();
          mappaLago=new ImageView("file:Immagini/lago.png");
         
           mappaLago.setFitHeight(300);
           mappaLago.setFitWidth(300);
          /* mappaLago.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
               System.out.println("Tile pressed ");
               clickMappa(e.getSceneX(),e.getSceneY());
           });*/
           pane.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
               System.out.println("Tile pressed ");
               clickMappa(e.getSceneX(),e.getSceneY());
           });
           cattura = new Label[5];
           for (int i=1;i<6;i++){
               cattura[i-1]=new Label (String.valueOf(i));
              cattura[i-1].setVisible(false);
           }
           pane.getChildren().addAll(mappaLago, cattura[0]);//cattura[0],cattura[1],cattura[2],cattura[3],cattura[4]);
    }
    public void clickMappa(double x, double y){
       // cattura[0].setTranslateX(x);
       // cattura[0].setTranslateY(y);
       pane.getChildren().remove(1);
       cattura[0].setLayoutX(x);
       cattura[0].setLayoutY(y);
       //pane.getChildren().get(1).getLayoutX();//cattura[0].set
          // mappaLago.setVisible(false);
       cattura[0].setVisible(true);
       pane.getChildren().add(cattura[0]);
       //cattura[0].setText("1");
        System.out.println(x+" " +y+"\n" + cattura[0].getLayoutX() + "\n" +  pane.getChildren().get(1).getLayoutX());
        
    }
}


import javafx.scene.image.*;

/**
 *
 * @author Gioele
 */
public class GestoreMappa {
    public ImageView mappaLago;
    GestoreMappa() {
           mappaLago=new ImageView("file:../Immagini/lago.png");
           mappaLago.setFitHeight(300);
           mappaLago.setFitWidth(300);
    }

}

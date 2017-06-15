
import java.time.LocalDate;
import javafx.beans.property.*;



/**
 *
 * @author Gioele
 */
public class DatiCattura {
    int codiceCattura;
    private SimpleStringProperty data;
    private SimpleIntegerProperty numero;
    private SimpleStringProperty esca;
    private SimpleStringProperty tecnica;
    private SimpleDoubleProperty peso;
    private SimpleIntegerProperty coordinataX;
    private SimpleIntegerProperty coordinataY;
    public DatiCattura(int i,Integer n,LocalDate d,String e,String t,Double p) { //(1)
        codiceCattura = i;
        numero = new SimpleIntegerProperty(n);
        esca = new SimpleStringProperty(e);
        data = new SimpleStringProperty(d.toString());
        tecnica = new SimpleStringProperty(t);
        peso= new SimpleDoubleProperty(p);
        coordinataX = new SimpleIntegerProperty(-1);
        coordinataY = new SimpleIntegerProperty(-1);
    }    
    public DatiCattura() {
         this(-1,-1,LocalDate.now(),"","",0.0);
    }
    public Double getPeso(){
        return peso.get();
    }
    public void setPeso(Integer p){
        peso.set(p);
    }
    public Integer getNumero(){
        return numero.get();
    }
    public void setNumero(Integer n){
        peso.set(n);
    }
}

import java.time.LocalDate;
import javafx.beans.property.*;



/**
 *
 * @author Gioele
 */
public class DatiCattura {
    int codiceCattura;
    //public Cattura cattura;
    private SimpleStringProperty numero;
    private SimpleStringProperty peso;
    private SimpleStringProperty tecnica;
    private SimpleStringProperty esca;
    private SimpleStringProperty data;
    private SimpleStringProperty coordinataX;
    private SimpleStringProperty coordinataY;
    public DatiCattura(String n,String e,String d,String t,String p,String x,String y) { //(1)
        //cattura=new Cattura(n,p,t,e);
        numero = new SimpleStringProperty(n); //
        esca = new SimpleStringProperty(e); //
        tecnica = new SimpleStringProperty(t);//
        peso= new SimpleStringProperty(p);//
        data = new SimpleStringProperty(d.toString());//
        coordinataX = new SimpleStringProperty(x);//
        coordinataY = new SimpleStringProperty(y);//
        System.out.println("costr");
    }    
    public DatiCattura() {
         this("","","","","","","");
    }
    public String getPeso(){
        return peso.get();
    }
    public void setPeso(String p){
        peso.set(p);
    }
    public String getNumero(){
        return numero.get();
    }
    public void setNumero(String n){
        numero.set(n);
    }
    public String getCoordinataX(){
        return coordinataX.get();
    }
    public void setCoordinataX(String p){
        coordinataX.set(p);
    }
     public String getCoordinataY(){
        return coordinataY.get();
    }
    public void setCoordinataY(String p){
        coordinataY.set(p);
    }
    public String getTecnica(){
        return tecnica.get();
    }
    public void setTecnica(String p){
        tecnica.set(p);
    }
    public String getData(){
        return data.get();
    }
    public void setData(String p){
        data.set(p);
    }
    public String getEsca(){
        return esca.get();
    }
    public void setEsca(String p){
        esca.set(p);
    }
}
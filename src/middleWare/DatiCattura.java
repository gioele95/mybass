package middleWare;


import java.time.LocalDate;
import javafx.beans.property.*;



/**
 *
 * @author Gioele
 */
public class DatiCattura {
   // int codiceCattura;
    //public Cattura cattura;
    public SimpleIntegerProperty codiceCattura;
    private SimpleIntegerProperty numero;
    private SimpleStringProperty peso;
    private SimpleStringProperty tecnica;
    private SimpleStringProperty esca;
    private SimpleStringProperty data;
    private SimpleDoubleProperty coordinataX;
    private SimpleDoubleProperty coordinataY;
    public DatiCattura(Integer cc,Integer n,String e,String d,String t,String p,Double x,Double y) { //(1)
        //cattura=new Cattura(n,p,t,e);
        numero = new SimpleIntegerProperty(n); //
        codiceCattura = new SimpleIntegerProperty(cc);
        esca = new SimpleStringProperty(e); //
        tecnica = new SimpleStringProperty(t);//
        peso= new SimpleStringProperty(p);//
        data = new SimpleStringProperty(d.toString());//
        coordinataX = new SimpleDoubleProperty(x);//
        coordinataY = new SimpleDoubleProperty(y);//
        System.out.println("costr");
    }    
    public DatiCattura() {
         this(-1,-1,"","","","",-1.0,-1.0);
    }
    public String getPeso(){
        return peso.get();
    }
    public void setPeso(String p){
        peso.set(p);
    }
    public Integer getNumero(){
        return numero.get();
    }
    public void setNumero(Integer n){
        numero.set(n);
    }
    public Double getCoordinataX(){
        return coordinataX.get();
    }
    public void setCoordinataX(Double p){
        coordinataX.set(p);
    }
     public Double getCoordinataY(){
        return coordinataY.get();
    }
    public void setCoordinataY(Double p){
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
    public Integer getCodiceCattura(){
        return codiceCattura.get();
    }
    public void setCodciceCattura(Integer n){
        codiceCattura.set(n);
    }
}
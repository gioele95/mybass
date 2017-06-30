
package middleWare;

import backEnd.DepositoInformazioniCatture;
import frontEnd.CalendarioPescate;
import frontEnd.TabellaCatture;
import java.io.*;

/**
 *
 * @author Gioele
 */
public class CacheDatiCatture implements Serializable{  //1)
    String cacheData;
    int cacheNumero;
    String cacheEsca;
    String cachePeso;
    String cacheTecnica;
   /// boolean cacheValida;

    
    public CacheDatiCatture(CalendarioPescate calendario){
        System.out.println("preleva");
        prelevaDatiCache(calendario);
    }
    public CacheDatiCatture(TabellaCatture tabella) {
        DatiCattura dc=tabella.getItems().get(tabella.getItems().size()-1);
        cacheData=CalendarioPescate.getData();
        cacheNumero=dc.getNumero();
        cacheEsca=dc.getEsca();
        cachePeso=dc.getPeso();
        System.out.println("peso 1 :"+cachePeso);
        cacheTecnica=dc.getTecnica();
      //  cacheValida=true;
        salvaDatiInCache();
    }
    private void salvaDatiInCache(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./cache/cache.bin"))) {
            oos.writeObject(this);
            System.out.println("peso: " + this.cachePeso);
        } catch(Exception e) {
            System.out.println("Erore "+e.getMessage());
        } 
    }
    private void prelevaDatiCache(CalendarioPescate calendario){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./cache/cache.bin"))) {
            CacheDatiCatture obj = (CacheDatiCatture)ois.readObject();
            cacheData = obj.cacheData;
            cacheNumero = obj.cacheNumero;
            cacheEsca = obj.cacheEsca;
            cachePeso = obj.cachePeso;
            cacheTecnica = obj.cacheTecnica;
           // cacheValida = true;
            calendario.setData(cacheData);
            caricaDatiInTabella();
        } catch(ClassNotFoundException | IOException  e) {
            System.out.println("Erore prelievo "+e.getMessage());
      //      cacheValida = false;
        }
    }
    public void caricaDatiInTabella(){
       // if(cacheValida){
            DepositoInformazioniCatture.getIstanza().getCattura(cacheData, cacheNumero);
        //}   
    }
}
//1) per permettere la serializzazione della classe e l'inserimento su un file di tipo binario

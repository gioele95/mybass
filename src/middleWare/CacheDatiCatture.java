
package middleWare;

import frontEnd.TabellaCatture;
import java.io.*;

/**
 *
 * @author Gioele
 */
public class CacheDatiCatture {
    String cacheData;
    int cacheNumero;
    String cacheEsca;
    String cachePeso;
    String cacheTecnica;
    boolean cacheValida;
    public void cacheDatiCatture(TabellaCatture t){
        DatiCattura dc=t.getItems().get(t.getItems().size()-1);
        cacheData=dc.getData();
        cacheNumero=dc.getNumero();
        cacheEsca=dc.getEsca();
        cachePeso=dc.getPeso();
        cacheTecnica=dc.getTecnica();
        cacheValida=true;
        salvaDatiInCache();
    }
    private void salvaDatiInCache(){
    try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file:Cache/cache.bin"))) {
                oos.writeObject(this);
            } catch(Exception e) {
                System.out.println("Erore nel salvare i dati inseriti: "+e.getMessage());
        } 
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author Gioele
 */
public class CacheDatiCatture implements Serializable{  //1)
    private String cacheData;
    private List<DatiCatturaSemplici> cacheListaCatture;
    private class DatiCatturaSemplici implements Serializable{ //1)
            private final int codiceCattura;
            private final int numero;
            private String peso;
            private final String tecnica;
            private final String esca;
            private final String data;
            private final Double coordinataX;
            private final Double coordinataY;
            public DatiCatturaSemplici(DatiCattura d) { 
                numero =d.getNumero(); 
                codiceCattura = d.getCodiceCattura();
                esca = d.getEsca();
                tecnica = d.getTecnica();
                peso= d.getPeso();
                data = CalendarioPescate.getData();
                coordinataX = d.getCoordinataX();
                coordinataY = d.getCoordinataY();
            }  
    }
    public CacheDatiCatture(CalendarioPescate calendario, GestoreMappa mappa){
        prelevaDatiCache(calendario,mappa);
    }
    public CacheDatiCatture(TabellaCatture tabella) {
        cacheData=CalendarioPescate.getData();
        cacheListaCatture= new ArrayList<>();
        ObservableList<DatiCattura> listaCatture = DepositoInformazioniCatture.getIstanza().listaCatture;
        for (DatiCattura dc : listaCatture) {
            DatiCatturaSemplici ds = new DatiCatturaSemplici(dc);
            cacheListaCatture.add(ds);
        }
        salvaDatiInCache();
    }
    private void salvaDatiInCache(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./cache/cache.bin"))) {
            oos.writeObject(this);
        } catch(Exception e) {
            System.out.println("Erore "+e.getMessage());
        } 
    }
    private void prelevaDatiCache(CalendarioPescate calendario,GestoreMappa mappa){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./cache/cache.bin"))) {
            CacheDatiCatture obj = (CacheDatiCatture)ois.readObject();
            cacheData = obj.cacheData;
            calendario.setData(cacheData);
            cacheListaCatture=obj.cacheListaCatture;
            if(!cacheListaCatture.isEmpty()){
                DepositoInformazioniCatture.getIstanza().listaCatture.clear();
                for (DatiCatturaSemplici dc : cacheListaCatture) {
                    if(dc.peso.equals("0"))
                        dc.peso="";
                    DepositoInformazioniCatture.getIstanza().listaCatture.add(new DatiCattura(dc.codiceCattura,dc.numero,
                        dc.esca,dc.data,dc.tecnica,dc.peso,dc.coordinataX,dc.coordinataY));
                    if(dc.coordinataX!=0)                                   //2)
                        mappa.clickMappa(dc.numero-1,dc.coordinataX,dc.coordinataY,true,cacheData);
                }
            }
        } catch(ClassNotFoundException | IOException  e) {
            System.out.println("Erore prelievo "+e.getMessage());
        }
    }
}
//1) per permettere la serializzazione della classe e l'inserimento su un file di tipo binario
//2) significa che vi è una posizione valida per quella cattura
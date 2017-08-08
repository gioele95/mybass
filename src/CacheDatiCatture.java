import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Gioele
 */
public class CacheDatiCatture implements Serializable{  
    private String cacheData;
    private List<DatiCatturaSemplici> cacheListaCatture;
    private class DatiCatturaSemplici implements Serializable{ //1)
            private int codiceCattura;
            private int numero;
            private String peso;
            private String tecnica;
            private String esca;
            private String data;
            private Double coordinataX;
            private Double coordinataY;
            public DatiCatturaSemplici(DatiCattura d) { 
                System.out.println("CacheDatiCatture.DatiCatturaSemplici.<init>()");
                numero =d.getNumero(); //
                

                codiceCattura = d.getCodiceCattura();
                esca = d.getEsca(); //
                tecnica = d.getTecnica();//
                peso= d.getPeso();//
                data = CalendarioPescate.getData();//
               
                coordinataX = d.getCoordinataX();//
                coordinataY = d.getCoordinataY();//
                
               System.out.println("numero "+ numero + " cc "+codiceCattura +" e "+esca+ " t " + tecnica + "peso "+peso);

            }  
        }
   /// boolean cacheValida;

    
    public CacheDatiCatture(CalendarioPescate calendario, GestoreMappa mappa){
        System.out.println("preleva");
        prelevaDatiCache(calendario,mappa);
    }
    public CacheDatiCatture(TabellaCatture tabella) {
        cacheData=CalendarioPescate.getData();
        cacheListaCatture= new ArrayList<>();
        ObservableList<DatiCattura> listaCatture = DepositoInformazioniCatture.getIstanza().listaCatture;
        for (DatiCattura dc : listaCatture) {
            System.out.println("entro");
            DatiCatturaSemplici ds = new DatiCatturaSemplici(dc);
            System.out.println("coordinata XXX.<init>() numero "+ds.coordinataX);
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
                    if(dc.coordinataX!=0)
                        mappa.clickMappa(dc.numero-1,dc.coordinataX,dc.coordinataY,true,cacheData);
                    System.out.println("CacheDatiCatture.prelevaDatiCache() peso  "+ dc.peso);
                }
            }
            System.out.println("peso "+  DepositoInformazioniCatture.getIstanza().listaCatture.get(0).getPeso());
            System.out.println("esco da preleva cache");
        } catch(ClassNotFoundException | IOException  e) {
            System.out.println("Erore prelievo "+e.getMessage());
        }
    }
}
//1) per permettere la serializzazione della classe e l'inserimento su un file di tipo binario

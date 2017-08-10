import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
/**
 *
 * @author Gioele
 */
public class DepositoInformazioniCatture {
    private final String utenteDB;
    private final String passDB;
    private final String URICon;

    public ObservableList<DatiCattura> listaCatture;
    private final String queryInserimentoCattura = "INSERT INTO tabellacatture(data,cattura,peso,tecnica,esca,"
            + "                                 coordinataX,coordinataY) VALUES(?,?,?,?,?,?,?) ";
    private final String queryCaricamentoCatture = "SELECT * FROM tabellacatture WHERE data= ?";
    private final String queryModificaCattura = "UPDATE tabellacatture SET peso=?, "
                                        + "tecnica=?, esca=?,coordinataX=?,coordinataY=? where codicecattura=?";
    private final String queryPersonalBest = "SELECT max(peso) as personal FROM tabellacatture";
    private final String queryBestBag= "SELECT max(bag) as bestbag FROM bagpergiorno";
    private final String queryCurrentBag= "SELECT bag FROM bagpergiorno WHERE data=?";
    private final String queryPercentuale= "SELECT * FROM percentuali";
    private final String queryCoordinate=" UPDATE tabellacatture SET coordinataX=?,coordinataY=? WHERE data=? and cattura=?";
    private final String queryCatturaEsiste="SELECT * FROM tabellacatture WHERE data=? and cattura=? ";
    private final String queryPrecedenti="SELECT codicecattura FROM tabellacatture WHERE data=? and cattura<?";
    
    private static DepositoInformazioniCatture istanza;
    public DatiCattura getCattura(String d, int c){
        try ( Connection co = DriverManager.getConnection(URICon,utenteDB,passDB);
            PreparedStatement st = co.prepareStatement(queryCatturaEsiste);
        ) {
            st.setString(1, d);
            st.setInt(2, c);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return (new DatiCattura( rs.getInt("codicecattura"),rs.getInt("cattura"),rs.getString("esca"), 
                 rs.getString("data"),rs.getString("tecnica"),rs.getString("peso"),
                 rs.getDouble("coordinataX"),rs.getDouble("coordinataY")));
            }
        } catch (SQLException ex) {
            
            System.out.println("Errore di inserimento di una cattura "+ex);
        }  
        return new DatiCattura();
    }
    public boolean catturaEsistente(String d,int c){
        System.out.println("cattura esistente"+c);
        try ( Connection co = DriverManager.getConnection(URICon,utenteDB,passDB);
            PreparedStatement st = co.prepareStatement(queryCatturaEsiste);
        ) {
            st.setString(1, d);
            st.setInt(2, c);
            
            ResultSet rs = st.executeQuery();
            if(rs.next())
                return true;
            return false;
        } catch (SQLException ex) {
            
            System.out.println("Errore di inserimento di una cattura "+ex);
        }      
        return false;
    }
    public boolean primaRigaDisponibile(String d,int c){
        System.out.println("prima riga");
        try ( Connection co = DriverManager.getConnection(URICon,utenteDB,passDB);
            PreparedStatement st = co.prepareStatement(queryPrecedenti);
        ) {
            st.setString(1, d);
            st.setInt(2, c);
            
            ResultSet rs = st.executeQuery();
            if(rs.isBeforeFirst()){
                System.out.println("c'è il dato");
                return true;
            }
            return false;
        } catch (SQLException ex) {
            
            System.out.println("Errore prima riga"+ex);
        }      
        return false;
    }
    public DepositoInformazioniCatture(){
        ParametriConfigurazioneXML p=new ParametriConfigurazioneXML();
        utenteDB=p.utenteDatabase;
        passDB=p.passwordDatabase;
        URICon= "jdbc:mysql://"+p.hostnameDatabase+":"+p.portaDatabase+"/mybass";
        listaCatture= FXCollections.observableArrayList();
    }
    public static DepositoInformazioniCatture getIstanza(){
        if (istanza==null){
            istanza=new DepositoInformazioniCatture();
        }
        return istanza;
    }
    public int inserisciCattura(DatiCattura dc) { 
        try ( Connection co = DriverManager.getConnection(URICon,utenteDB,passDB);
            PreparedStatement st = co.prepareStatement(queryInserimentoCattura,Statement.RETURN_GENERATED_KEYS);
          
        ) {
            System.out.println("inserisci cattura data"+dc.getData());
            System.out.println("tecnica: "+dc.getTecnica());
            System.out.println("esca "+dc.getEsca());
            System.out.println("numero: "+dc.getNumero());
            System.out.println("coordinatax "+dc.getCoordinataX() );
            st.setDate(1,Date.valueOf(dc.getData()));
            st.setInt(2, dc.getNumero());
            if(dc.getPeso().equals(""))
                st.setDouble(3,0.0);
            else
                st.setDouble(3,Double.parseDouble(dc.getPeso()));
            st.setString(4, dc.getTecnica());
            st.setString(5, dc.getEsca());
            st.setDouble(6,dc.getCoordinataX());
            st.setDouble(7,dc.getCoordinataY());
            st.executeUpdate();
            ResultSet rs=st.getGeneratedKeys();
            if(rs.next())
                return rs.getInt(1);
            return -1;
        } catch (SQLException ex) {
            System.out.println("Errore di inserimento di una cattura "+ex);
        }        
        return -1;
    }
    public void caricaCatture(String d) {
        try ( Connection co = DriverManager.getConnection(URICon,utenteDB,passDB);
            PreparedStatement st = co.prepareStatement(queryCaricamentoCatture);
        ) { 
            if(listaCatture.isEmpty()){
                st.setString(1,d);
                ResultSet rs = st.executeQuery();
                Integer i;
                i=0;
                System.out.println("pulisco caricaCatture()");
                listaCatture.clear();
                while (rs.next()) {
                   String s= new String("");
                   if(rs.getDouble("peso")!=0.0)
                        s=String.valueOf(rs.getDouble("peso"));
                   listaCatture.add(new DatiCattura( rs.getInt("codicecattura"),rs.getInt("cattura"),rs.getString("esca"), 
                   rs.getString("data"),rs.getString("tecnica"),s/*rs.getString("peso")*/,
                   rs.getDouble("coordinataX"),rs.getDouble("coordinataY")));
                   i++;
                }
                int nmax=ParametriConfigurazioneXML.ottieniParametriConfigurazioneXML().numeroMassimoPesci;
                if(i!=nmax)
                    while(i<nmax){
                        listaCatture.add(new DatiCattura(-1,i+1,"","","","",0.0,0.0));
                        System.out.println("carica " +i);
                        i++;
                    }
            }
            aggiornaDati(d);
        } catch (SQLException e) {System.err.println(e.getMessage());}     

    }
    public void aggiornaDati(String d){
        try ( Connection co = DriverManager.getConnection(URICon,utenteDB,passDB); ///9)
            PreparedStatement stBestBag = co.prepareStatement(queryBestBag);
            PreparedStatement stPersonal = co.prepareStatement(queryPersonalBest);
            PreparedStatement stCurrent = co.prepareStatement(queryCurrentBag);
            ResultSet rsBest = stBestBag.executeQuery();
            ResultSet rsPersonal = stPersonal.executeQuery();
        ) { 
            stCurrent.setDate(1,Date.valueOf(d));
            ResultSet rsCurrent = stCurrent.executeQuery();
            if(rsPersonal.next()){
                InterfacciaApplicazionePesca.personal.setText(String.valueOf(rsPersonal.getDouble("personal")));
            }
            else{
                InterfacciaApplicazionePesca.personal.setText("0");
            }
            if(rsBest.next())
                InterfacciaApplicazionePesca.best.setText(String.valueOf(rsBest.getDouble("bestbag")));
            else 
                InterfacciaApplicazionePesca.best.setText("0");
            if(rsCurrent.next())
                InterfacciaApplicazionePesca.current.setText(String.valueOf(rsCurrent.getDouble("bag")));
            else 
                InterfacciaApplicazionePesca.current.setText("0");
        } catch (SQLException e) {System.err.println(e.getMessage());}     
    }
    public int modificaCattura(DatiCattura dc){
        System.out.println("modificaCattura");
        try ( Connection co = DriverManager.getConnection(URICon,utenteDB,passDB);
            PreparedStatement st = co.prepareStatement(queryModificaCattura);
        ) {
            System.out.println("MODUIFICAAAAAAAAA numero: "+dc.getNumero());
            System.out.println("coordinatax "+dc.getCoordinataX() );
            st.setInt(6,dc.getCodiceCattura());
            st.setString(1,dc.getPeso());
            st.setString(2, dc.getTecnica());
            st.setString(3, dc.getEsca());
            st.setDouble(4,dc.getCoordinataX());
            st.setDouble(5,dc.getCoordinataY());
            st.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            
            System.out.println("Errore di inserimento di una cattura "+ex);
            return -1;
        }        
    }
     public void impostaCoordinate(double x, double y,String d,int c){
        System.out.println("imposta coordinate");
        try ( Connection co = DriverManager.getConnection(URICon,utenteDB,passDB);
            PreparedStatement st = co.prepareStatement(queryCoordinate);
        ) {
            st.setDouble(1,x);
            st.setDouble(2,y);
            st.setString(3, d);
            st.setInt(4, c);       
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Errore di inserimento di una cattura "+ex);
        }        
    }
    public ObservableList<PieChart.Data> percentuale(){
        ObservableList<PieChart.Data> l;
        l = FXCollections.observableArrayList();
        try ( Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybass","root","");   
            PreparedStatement st = co.prepareStatement(queryPercentuale);
            ResultSet rs = st.executeQuery();
        ) { 
            while(rs.next())
                l.add(new PieChart.Data(rs.getString("tecnica"),rs.getDouble("totale")));
        } catch (SQLException e) {System.err.println(e.getMessage());} 
        return l;
    }
}

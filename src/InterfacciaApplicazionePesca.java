import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.*;
/**
 * @author Gioele
 */
public class InterfacciaApplicazionePesca extends Application {
    private Button bottoneConfermaDati;
    private GraficoTecnicheCatturanti graficoTecnicheCatturanti;
    private Label personalBest;
    private Label currentBag;
    private Label bestBag;
    private Label titolo;
    private VBox vboxCentrale;      //1)
    private VBox vboxSinistra;
    private VBox vboxDestra;
    private HBox hbox;  
    public static  Text personal;
    public static Text current;
    public static Text best;
    
    
    private String colore;
    private CalendarioPescate calendario;
    private GestoreMappa gestoreMappaLago;
    private TabellaCatture tabella; 
    private CacheDatiCatture cache;
    public static ParametriConfigurazioneXML xml;
    @Override
    public void start(Stage stage) {
        xml = new ParametriConfigurazioneXML();
        ClientEventiXML.inviaLog("Avvio Applicazione", xml.IPServerLog, xml.portaServerLog, xml.IPClient);
        colore="-fx-background-color: " + xml.coloreSfondo + ";";
        graficoTecnicheCatturanti= new GraficoTecnicheCatturanti();
        hbox = new HBox();
        hbox.setStyle(colore);
        gestoreMappaLago=new GestoreMappa();
        vboxSinistra= VBoxSinistra();
        vboxDestra= VBoxDestra();
        vboxCentrale = VBoxCentrale();
        hbox.getChildren().addAll(vboxSinistra,vboxCentrale,vboxDestra);
        hbox.setSpacing(40);
        stage.setOnCloseRequest((WindowEvent we)-> {cache=new CacheDatiCatture(tabella);
            ClientEventiXML.inviaLog("Chiusura Applicazione", xml.IPServerLog, xml.portaServerLog, xml.IPClient);});
        Group root = new Group(hbox);
        Scene scene = new Scene(root,1043,590); // x y
        scene.getStylesheets().add("style.css");
        for (int i=0;i<xml.numeroMassimoPesci;i++){
            ((Group) scene.getRoot()).getChildren().add(gestoreMappaLago.cattura[i]);
        }
        clickMouseMappa();
        stage.setTitle("MyBass");

        stage.setScene(scene);
        stage.show();
        cache=new CacheDatiCatture(calendario,gestoreMappaLago);
    }
    private VBox VBoxSinistra(){
        calendario=new CalendarioPescate();
        VBox vb= new VBox(30);
        vb.setPadding(new Insets(30, 15, 30, 30));
        personalBest = new Label("Personal Best[kg]");
        bestBag = new Label("Best Bag[kg]");
        currentBag = new Label("Current Bag[kg]");
        bestBag.setStyle("-fx-font-size: 15px;");
        currentBag.setStyle("-fx-font-size: 15px;");
        personalBest.setStyle("-fx-font-size: 15px;");

        best=new Text("");
        personal=new Text("");
        current=new Text("");  
        
        best.setStyle("-fx-font-size: 15px;");
        current.setStyle("-fx-font-size: 15px;");
        personal.setStyle("-fx-font-size: 15px;");
        
        vb.setAlignment(Pos.TOP_LEFT);
        HBox h1= new HBox(20);
        HBox h2= new HBox(50);
        HBox h3= new HBox(25);
        h1.setPadding(new Insets(40, 30, 10, 30));
        h2.setPadding(new Insets(40, 10, 10, 30));
        h3.setPadding(new Insets(40, 10, 30, 30));
        vb.getChildren().addAll(calendario.creaCalendario(gestoreMappaLago));
        tabella = new TabellaCatture(calendario.getData());
               
        
        h1.getChildren().addAll(personalBest,personal);
        h2.getChildren().addAll(bestBag,best);
        h3.getChildren().addAll(currentBag,current);
        vb.getChildren().addAll(h1,h2,h3);
        return vb;
    }
    private VBox VBoxCentrale(){
        VBox v = new VBox();
        v.setPadding(new Insets(30, 0, 100,10));
        titolo = new Label("MYBASS");
        bottoneConfermaDati = new Button("Conferma Dati");
        bottoneConfermaDati.setOnAction((ActionEvent ae)->{tabella.confermaDati();});
        bottoneConfermaDati.setMinHeight(40);
        bottoneConfermaDati.setFont(Font.font("arial", FontWeight.BOLD, 22));
        bottoneConfermaDati.setStyle("-fx-background-color: linear-gradient(#008CFF, #66B2FF), radial-gradient(center 50% -40%, radius 200%, #008CFF 45%, #66B2FF 50%); -fx-background-radius: 6, 5; "
                + "-fx-background-insets: 0, 1; -fx-text-fill: white;-fx-font-weigth: bold");
        tabella.setFixedCellSize(40);
        tabella.setStyle("-fx-font-size: 15px;");
        titolo.setStyle("-fx-font-size: 30px;");
        v.getChildren().addAll(titolo,tabella,bottoneConfermaDati);
        tabella.setMaxSize(700,232);
        v.setAlignment(Pos.TOP_CENTER);
        v.setSpacing(70);
        gestoreMappaLago.caricaPosizioni(calendario.getData());
        return v; 
    }
    private VBox VBoxDestra(){
        graficoTecnicheCatturanti=new GraficoTecnicheCatturanti();
        VBox vb=new VBox();
        vb.setPadding(new Insets(10, 0, 10, 10));
        vb.setPrefWidth(80);
        vb.setPrefHeight(80);
        vb.getChildren().addAll(gestoreMappaLago.vb,GraficoTecnicheCatturanti.getGrafico());
        return vb;
    }
    private void clickMouseMappa(){
        gestoreMappaLago.mappaLago.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
            public void handle(MouseEvent e) {
                gestoreMappaLago.clickMappa((Integer)gestoreMappaLago.numeroSelezionato.getValue()-1,e.getSceneX()-3,e.getSceneY()-10,false,calendario.getData());
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
//1)http://docs.oracle.com/javafx/2/layout/builtin_layouts.htm

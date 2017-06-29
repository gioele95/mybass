package frontEnd;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
/**
 * @author Gioele
 */
public class InterfacciaApplicazionePesca extends Application {
    private Button bottoneSvuotaMappa;
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
/*    private Text personal;
    private Text current;
    private Text best;*/
    private CalendarioPescate calendario;
    private GestoreMappa gestoreMappaLago;
    private TabellaCatture tabella;     
    @Override
    public void start(Stage stage) {
        
        bottoneConfermaDati = new Button("Conferma Dati");
        bottoneSvuotaMappa = new Button("Svuota Mappa");
        personalBest= new Label("Personal Best");
        bestBag= new Label("Best Bag");
        currentBag= new Label("Current Bag");
        graficoTecnicheCatturanti= new GraficoTecnicheCatturanti();
        //percentuale=new Label("");
        hbox = new HBox();
        gestoreMappaLago=new GestoreMappa();
        vboxSinistra= VBoxSinistra();
        //tabella=new TabellaCatture(calendario.dataSelezionata.toString());
        vboxDestra= VBoxDestra();
        vboxCentrale = VBoxCentrale();
        hbox.getChildren().addAll(vboxSinistra,vboxCentrale,vboxDestra);
        hbox.setSpacing(40);
        Group root = new Group(hbox);
        Scene scene = new Scene(root,1220,650); // x y
  //      ((Group) scene.getRoot()).getChildren().add(graficoTecnicheCatturanti.getPercentuale());
        scene.getStylesheets().add("file:Styles/style.css");
        for (int i=0;i<5;i++){
            ((Group) scene.getRoot()).getChildren().add(gestoreMappaLago.cattura[i]);
        }
  //      clickMouseGrafico();
        clickMouseMappa();
        stage.setTitle("MyBass");

        stage.setScene(scene);
        
        
        stage.show();
    }
    private VBox VBoxSinistra(){
        calendario=new CalendarioPescate();
        VBox vb= new VBox(30);
        vb.setPadding(new Insets(30, 30, 30, 30));
        personalBest = new Label("Personal Best[kg]");
        bestBag = new Label("Best Bag[kg]");
        currentBag = new Label("Current Bag[kg]");
   /*     best=new Text("d");
        personal=new Text("a");
        current=new Text("sdsd");*/
        vb.setAlignment(Pos.TOP_LEFT);
        HBox h1= new HBox(20);
        HBox h2= new HBox(20);
        HBox h3= new HBox(20);
        h1.setPadding(new Insets(0, 30, 10, 30));
        h2.setPadding(new Insets(0, 10, 10, 30));
        h3.setPadding(new Insets(0, 10, 30, 30));
        
        
        vb.getChildren().addAll(calendario.creaCalendario(gestoreMappaLago));
        tabella = new TabellaCatture(calendario.getData());
        System.out.println(calendario.getData());
        h1.getChildren().addAll(personalBest,tabella.deposito.personal);
        h2.getChildren().addAll(bestBag,tabella.deposito.best);
        h3.getChildren().addAll(currentBag,tabella.deposito.current);

        vb.getChildren().addAll(h1,h2,h3);
        return vb;
    }
    private VBox VBoxCentrale(){
        VBox v = new VBox();
        v.setPadding(new Insets(20, 100, 100, 50));
        titolo = new Label("MYBASS");
        System.out.println(calendario.getData());

        //tabella.setFixedCellSize(5);
        titolo.setStyle("-fx-font-size: 30px;");
        v.getChildren().addAll(titolo,tabella);
    //   tabella.setMaxSize(700,176);
        v.setAlignment(Pos.TOP_CENTER);
        v.setSpacing(100);
        gestoreMappaLago.caricaPosizioni(calendario.getData());
        return v; 
    }
    private VBox VBoxDestra(){
        graficoTecnicheCatturanti=new GraficoTecnicheCatturanti();
        VBox vb=new VBox();
        vb.setPadding(new Insets(10, 10, 10, 10));
        vb.setPrefWidth(80);
        vb.setPrefHeight(80);
        vb.getChildren().addAll(gestoreMappaLago.vb,GraficoTecnicheCatturanti.getGrafico());
        return vb;
    }
   /* private void clickMouseGrafico(){
            for (final PieChart.Data data : GraficoTecnicheCatturanti.getGrafico().getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        graficoTecnicheCatturanti.clickGrafico(e.getSceneX(), e.getSceneY(),String.valueOf(data.getPieValue()));
                    }
                });
            }
    }*/
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
                /* vb.setStyle("-fx-padding: 10;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-insets: 5;" + 
                      "-fx-border-radius: 5;" + 
                      "-fx-border-color: blue;");/*/
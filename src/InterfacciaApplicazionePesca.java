import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 *
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
    private VBox vboxCentrale;
    private VBox vboxSinistra;
    private VBox vboxDestra;
    private BorderPane pane;
    private Text personal;
    private Text current;
    private Text best;
    private Calendario calendario;
    private Label percentuale;
    @Override
    public void start(Stage stage) {
        bottoneConfermaDati = new Button("Conferma Dati");
        bottoneSvuotaMappa = new Button("Svuota Mappa");
        personalBest= new Label("Personal Best");
        bestBag= new Label("Best Bag");
        currentBag= new Label("Current Bag");
        graficoTecnicheCatturanti= new GraficoTecnicheCatturanti();
        percentuale=new Label("");
        pane = new BorderPane();
        
        vboxSinistra= VBoxSinistra();
        vboxDestra= VBoxDestra();
        vboxCentrale = VBoxCentrale();

        pane.setRight(vboxDestra);
        pane.setCenter(vboxCentrale);
        pane.setLeft(vboxSinistra);
        Group root = new Group(pane);
        Scene scene = new Scene(root);
        ((Group) scene.getRoot()).getChildren().add(percentuale);
        clickMouseGrafico();
        stage.setTitle("MyBass");
        stage.setScene(scene);
        stage.show();
    }
    private VBox VBoxSinistra(){
        calendario=new Calendario();
        VBox vb= new VBox(30);
        vb.setPadding(new Insets(30, 30, 30, 30));
        personalBest = new Label("Personal Best[kg]");
        bestBag = new Label("Best Bag[kg]");
        currentBag = new Label("Current Bag[kg]");
        best=new Text();
        personal=new Text();
        current=new Text("sdsd");
        vb.setAlignment(Pos.TOP_LEFT);
        HBox h1= new HBox(20);
        HBox h2= new HBox(20);
        HBox h3= new HBox(20);
        h1.setPadding(new Insets(0, 30, 10, 30));
        h2.setPadding(new Insets(0, 10, 10, 30));
        h3.setPadding(new Insets(0, 10, 30, 30));
        h1.getChildren().addAll(personalBest,personal);
        h2.getChildren().addAll(bestBag,best);
        h3.getChildren().addAll(currentBag,current);
        vb.getChildren().addAll(calendario.creaCalendario());
        vb.getChildren().addAll(h1,h2,h3);
        /* vb.setStyle("-fx-padding: 10;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-insets: 5;" + 
                      "-fx-border-radius: 5;" + 
                      "-fx-border-color: blue;");/*/
        return vb;
    }
    private VBox VBoxCentrale(){
        VBox v = new VBox();
        v.setPadding(new Insets(0, 100, 300, 100));
       
        titolo = new Label("MYBASS");

        titolo.setStyle("-fx-font-size: 30px;");
        v.getChildren().addAll(titolo);
        v.setAlignment(Pos.TOP_CENTER);
              /*/v.setStyle("-fx-padding: 10;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-insets: 5;" + 
                      "-fx-border-radius: 5;" + 
                      "-fx-border-color: blue;");*/
        return v; 
    }
    private VBox VBoxDestra(){
        graficoTecnicheCatturanti=new GraficoTecnicheCatturanti();
        VBox vb=new VBox(20);
        vb.setPadding(new Insets(100, 30, 30, 100));
        vb.setPrefWidth(40);
        vb.setPrefHeight(40);
        vb.getChildren().addAll(graficoTecnicheCatturanti.grafico,percentuale);
                /* vb.setStyle("-fx-padding: 10;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-insets: 5;" + 
                      "-fx-border-radius: 5;" + 
                      "-fx-border-color: blue;");/*/
        return vb;
    }
    private void clickMouseGrafico(){
         
            percentuale.setTextFill(Color.BLACK);
            percentuale.setStyle("-fx-font: 12 arial;");

            for (final PieChart.Data data : graficoTecnicheCatturanti.grafico.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        percentuale.setTranslateX(e.getSceneX());
                        percentuale.setTranslateY(e.getSceneY());
                        percentuale.setText(String.valueOf(data.getPieValue()) + "%");

                    }
                });
            }
    }
    private void clickMouseCalendario(){
         for (final PieChart.Data data : graficoTecnicheCatturanti.grafico.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        percentuale.setTranslateX(e.getSceneX());
                        percentuale.setTranslateY(e.getSceneY());
                        percentuale.setText(String.valueOf(data.getPieValue()) + "%");
                    }
                });
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}

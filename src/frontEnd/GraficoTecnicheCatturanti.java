package frontEnd;


import backEnd.DepositoInformazioniCatture;
import static java.awt.Color.BLACK;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GraficoTecnicheCatturanti {
    public PieChart grafico;    //1)
    public Label percentuale = new Label("");
    private ObservableList<PieChart.Data> datiGrafico;
    GraficoTecnicheCatturanti(){
        /*ObservableList<PieChart.Data> datiGrafico=
        FXCollections.observableArrayList(
            new PieChart.Data("Gomma",13),
            new PieChart.Data("Reazione",47),
            new PieChart.Data("Finesse",40));*/
            aggiornaGrafico();
            grafico = new PieChart(datiGrafico);
            grafico.setTitle("Tecniche Catturanti");
            grafico.setLabelsVisible(true);
           
    }
    public void clickGrafico(double x,double y,String s){
        percentuale.setTextFill(Color.BLACK);
        percentuale.setStyle("-fx-font: 12 arial;");
        percentuale.setTranslateX(x-3);
        percentuale.setTranslateY(y-11);
        percentuale.setText(s + "%");
    }
    public void aggiornaGrafico(){
       datiGrafico = DepositoInformazioniCatture.getIstanza().percentuale();
    }
}
/*
01) Classe PieChart: https://docs.oracle.com/javafx/2/api/javafx/scene/chart/PieChart.html
    http://docs.oracle.com/javafx/2/charts/pie-chart.html
                                  
*/
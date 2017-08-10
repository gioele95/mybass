import javafx.collections.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
public class GraficoTecnicheCatturanti {
    public static PieChart grafico;    //1)
    private static ObservableList<PieChart.Data> datiGrafico;
    GraficoTecnicheCatturanti(){
            grafico=new PieChart();
            aggiornaGrafico();
            grafico.setTitle("Tecniche Catturanti");
            grafico.setLabelsVisible(true);           
    }
    public static void aggiornaGrafico(){
       datiGrafico = DepositoInformazioniCatture.getIstanza().percentuale();
       grafico.setData(datiGrafico);  
    }
    public static PieChart getGrafico(){  
        return grafico;
    }
}
/*
01) Classe PieChart: https://docs.oracle.com/javafx/2/api/javafx/scene/chart/PieChart.html
    http://docs.oracle.com/javafx/2/charts/pie-chart.html
                                  
*/
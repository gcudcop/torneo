/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo.presentacion.ctrl;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import torneo.logica.servicios.ServiciosEquipo;

@ManagedBean
@ViewScoped
public class CtrlEstadisticas {

    private CartesianChartModel lineModel;
    private PieChartModel pieModel;

    public CtrlEstadisticas() {
        this.graficar();
    }

    @PostConstruct
    private void graficar() {
        this.lineModel = grafica();
        createPieModel();
    }

    private CartesianChartModel grafica() {
        CartesianChartModel model = new CartesianChartModel();
        try {

            ChartSeries ladoA = new ChartSeries();
            //ladoA.setLabel("Lado A:");
            for (int i = 0; i < ServiciosEquipo.obtenerEquiposPorGrupos().size(); i++) {
                ladoA.set(ServiciosEquipo.obtenerEquiposPorGrupos().get(i).getIdGrupo().getNombreGrupo(),
                        ServiciosEquipo.obtenerEquiposDadoGrupo(ServiciosEquipo.obtenerEquiposPorGrupos().get(i).getIdGrupo().getIdGrupo()).size());
            }

            model.addSeries(ladoA);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return model;
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        try {
            for (int i = 0; i < ServiciosEquipo.obtenerEquiposPorGrupos().size(); i++) {
                pieModel.set(ServiciosEquipo.obtenerEquiposPorGrupos().get(i).getIdGrupo().getNombreGrupo(),
                        ServiciosEquipo.obtenerEquiposDadoGrupo(ServiciosEquipo.obtenerEquiposPorGrupos().get(i).getIdGrupo().getIdGrupo()).size());
            }
        } catch (Exception e) {
        }

    }

    /*
     get y set
     */
    public CartesianChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(CartesianChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

}

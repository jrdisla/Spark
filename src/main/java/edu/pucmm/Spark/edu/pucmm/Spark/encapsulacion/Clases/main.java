package edu.pucmm.Spark.edu.pucmm.Spark.encapsulacion.Clases;
import edu.pucmm.Spark.edu.pucmm.Spark.encapsulacion.Manejadores.ManejadorPlantilla;
import spark.Spark;

import static spark.Spark.*;
/**
 * Created by jrdis on 30/5/2017.
 */
public class main {
    public static void main(String[] args) {


        Spark.staticFileLocation("/public");
        new ManejadorPlantilla().startApp();

    }
}

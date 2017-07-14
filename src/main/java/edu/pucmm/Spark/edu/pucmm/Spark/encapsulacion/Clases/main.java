package edu.pucmm.Spark.edu.pucmm.Spark.encapsulacion.Clases;
import edu.pucmm.Spark.edu.pucmm.Spark.encapsulacion.Manejadores.ManejadorPlantilla;
import spark.Spark;

import static spark.Spark.*;
/**
 * Created by jrdis on 30/5/2017.
 */
public class main {
    public static void main(String[] args) {

        Spark.port(getHerokuAssignedPort());

        Spark.staticFileLocation("/public");


        get("/", (request, response) -> {
            return "Hola Mundo Heroku";
       });
      // new ManejadorPlantilla().startApp();

    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}

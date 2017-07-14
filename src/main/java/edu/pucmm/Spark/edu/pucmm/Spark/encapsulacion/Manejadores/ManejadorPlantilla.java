package edu.pucmm.Spark.edu.pucmm.Spark.encapsulacion.Manejadores;

import edu.pucmm.Spark.edu.pucmm.Spark.encapsulacion.Clases.Estudiante;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by jrdis on 31/5/2017.
 */
public class ManejadorPlantilla {
List<Estudiante> listEstudent = new ArrayList<>();
    public void startApp() {
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(ManejadorPlantilla.class, "/templates");
        FreeMarkerEngine FreeMarkerengine = new FreeMarkerEngine(configuration);

        addStudent(FreeMarkerengine);
        listStudents(FreeMarkerengine);
        IndividualShow(FreeMarkerengine);
        updateStudent(FreeMarkerengine);
        deleteStudent();
    }


    /***
     * http://localhost:4567/addStudent/
     * @param FreeMarkerengine
     */
    public void addStudent(FreeMarkerEngine FreeMarkerengine) {
        get("/addStudent/", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Agregar Nuevo Estudiante");
            return new ModelAndView(attributes, "addEstudents.ftl");
        }, FreeMarkerengine);
    }

    /***
     * http://localhost:4567/listStudents/
     * @param FreeMarkerengine
*/
    public void listStudents(FreeMarkerEngine FreeMarkerengine) {

        get("/listStudents/", (request, response) -> {
            String htmlCode = automaticHtmlCode(listEstudent);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Studens List");
            attributes.put("table", htmlCode );
            return new ModelAndView(attributes, "listEstudent.ftl");
        }, FreeMarkerengine);


        post("/listStudents/", (request, response) -> {
            int matricula = Integer.parseInt(request.queryParams("matricula"));
            String name = request.queryParams("nombre");
            String lastname = request.queryParams("apellido");
            String tel = request.queryParams("telefono");

            Estudiante student = new Estudiante(matricula, name, lastname, tel);
            listEstudent.add(student);
            String htmlCode = automaticHtmlCode(listEstudent);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Studens List");
            attributes.put("table", htmlCode);

            return new ModelAndView(attributes, "listEstudent.ftl");
        }, FreeMarkerengine);

        delete("/listStudents/", (request, response) -> {

            listEstudent.remove(find(Integer.parseInt(request.queryParams("eliminar"))));

            String htmlString = automaticHtmlCode(listEstudent);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Studens List");
            attributes.put("table", htmlString);

            return new ModelAndView(attributes, "listEstudent.ftl");
        }, FreeMarkerengine);
     }
    /***
     * http://localhost:4567/individualInfo/
     * @param FreeMarkerengine
     */
    public void IndividualShow(FreeMarkerEngine FreeMarkerengine) {
        get("/individualInfo/:matricula/", (request, response) -> {
            Estudiante estudiante = listEstudent.get(find(Integer.parseInt(request.params(":matricula"))));
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Información de Estudiante");
            attributes.put("Student", estudiante);
            return new ModelAndView(attributes, "infoEstudents.ftl");
        }, FreeMarkerengine);

        post("/individualInfo/:matricula/", (request, response) -> {
            int matricula = Integer.parseInt(request.params(":matricula"));
            String name = request.queryParams("nombre");
            String lastName = request.queryParams("apellido");
            String phone = request.queryParams("telefono");
            Estudiante student = new Estudiante(matricula, name, lastName, phone);

                 int index = find(matricula);
            listEstudent.get(index).setLastname(lastName);
            listEstudent.get(index).setName(name);
            listEstudent.get(index).setTel(phone);
            listEstudent.get(index).setMatricula(matricula);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Información de Estudiante");
            attributes.put("Student", student);
            return new ModelAndView(attributes, "infoEstudents.ftl");
        }, FreeMarkerengine);
    }
    /***
     *
     * http://localhost:4567/actStudent/
     * @param FreeMarkerengine
     */
    public void updateStudent(FreeMarkerEngine FreeMarkerengine) {
        get("/actStudent/:matricula/", (request, response) -> {
            Estudiante student = listEstudent.get(find(Integer.parseInt(request.params(":matricula"))));
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Actualizando Estudiante");
            attributes.put("Student", student);
            return new ModelAndView(attributes, "updateEstudent.ftl");
        }, FreeMarkerengine);
    }


    /***
     * http://localhost:4567/deleteStudent/:matricula/
     */
    public void deleteStudent() {
        get("/deleteStudent/:matricula/", (request, response) -> {
            int studentID = Integer.parseInt(request.params(":matricula"));
            int index = find(studentID);
            listEstudent.remove(index);

            response.redirect("/listStudents/");
            return "";
        });
    }


    private String automaticHtmlCode(List<Estudiante> estudents) {
        String htmlCode = "";
        for (Estudiante item : estudents) {
            htmlCode += "<tr onclick=\"document.location = '/individualInfo/" + item .getMatricula() + "/';\">" + "\n\t\t" +
                    "<td>" + item .getMatricula() + "</td>" + "\n\t\t" +
                    "<td>" + item .getName() + "</td>" + "\n\t\t" +
                    "<td>" + item .getLastname() + "</td>" + "\n\t\t" +
                    "<td>" + item .getTel() + "</td>" + "\n\t\t" +
                    "<td>" + "\n\t\t\t" +
                    "<a href=\"/actStudent/" + item .getMatricula() + "/\" class=\"btn btn-warning\"  role=\"button\">Actualizar</a>" + "\n\t\t\t" +
                    "<a href=\"/deleteStudent/" + item .getMatricula() + "/\"class=\"btn btn-danger\"  role=\"button\">Eliminar</a>" + "\n\t\t\t" +
                    "</td>" + "\n\t    " +
                    "</tr>\n\t    ";
        }

        return htmlCode;
    }
public int find (int matricula)
{
    int count =0;
    for (Estudiante item : listEstudent)
    {
        if (item.getMatricula() == matricula)
        {
            return count;
        }
        else
        {
            count++;
        }
    }
    return -1;
}
    }

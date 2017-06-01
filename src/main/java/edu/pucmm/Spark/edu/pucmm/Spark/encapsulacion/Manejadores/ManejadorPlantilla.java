package edu.pucmm.Spark.edu.pucmm.Spark.encapsulacion.Manejadores;

import edu.pucmm.Spark.edu.pucmm.Spark.encapsulacion.Estudiante;
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
     * @param engine
     */
    public void addStudent(FreeMarkerEngine engine) {
        get("/addStudent/", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Agregar Nuevo Estudiante");
            return new ModelAndView(attributes, "addEstudents.ftl");
        }, engine);
    }

    /***
     * http://localhost:4567/studentList/
     * @param engine
*/
    public void listStudents(FreeMarkerEngine engine) {

        get("/studentList/", (request, response) -> {
            String htmlCode = automaticHtmlCode(listEstudent);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Studens List");
            attributes.put("tableRows", htmlCode );
            return new ModelAndView(attributes, "listEstudent.ftl");
        }, engine);

        post("/studentList/", (request, response) -> {
            int matricula = Integer.parseInt(request.queryParams("matricula"));
            String name = request.queryParams("nombre");
            String lastname = request.queryParams("apellido");
            String tel = request.queryParams("telefono");
            Estudiante student = new Estudiante(matricula, name, lastname, tel);
            listEstudent.add(student);
            String htmlCode = automaticHtmlCode(listEstudent);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Studens List");
            attributes.put("tableRows", htmlCode);

            return new ModelAndView(attributes, "listEstudent.ftl");
        }, engine);

        delete("/studentList/", (request, response) -> {

            listEstudent.remove(find(Integer.parseInt(request.queryParams("eliminar"))));

            String htmlString = automaticHtmlCode(listEstudent);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Studens List");
            attributes.put("tableRows", htmlString);

            return new ModelAndView(attributes, "listEstudent.ftl");
        }, engine);
     }
    /***
     * http://localhost:4567/showStudentInfo/
     * @param engine
     */
    public void IndividualShow(FreeMarkerEngine engine) {
        get("/showStudentInfo/:matricula/", (request, response) -> {
            Estudiante estudiante = listEstudent.get(find(Integer.parseInt(request.params(":matricula"))));
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Información de Estudiante");
            attributes.put("Student", estudiante);
            return new ModelAndView(attributes, "infoEstudents.ftl");
        }, engine);

        post("/showStudentInfo/:matricula/", (request, response) -> {
            int studentID = Integer.parseInt(request.params(":matricula"));
            String name = request.queryParams("nombre");
            String lastName = request.queryParams("apellido");
            String phone = request.queryParams("telefono");
            Estudiante student = new Estudiante(studentID, name, lastName, phone);

                 int index = find(studentID);
            listEstudent.get(index).setLastname(lastName);
            listEstudent.get(index).setName(name);
            listEstudent.get(index).setTel(phone);
            listEstudent.get(index).setMatricula(studentID);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Información de Estudiante");
            attributes.put("Student", student);
            return new ModelAndView(attributes, "infoEstudents.ftl");
        }, engine);
    }
    /***
     * http://localhost:4567/updateStudent/
     * @param engine
     */
    public void updateStudent(FreeMarkerEngine engine) {
        get("/updateStudent/:matricula/", (request, response) -> {
            Estudiante student = listEstudent.get(find(Integer.parseInt(request.params(":matricula"))));
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Actualizando Estudiante");
            attributes.put("Student", student);
            return new ModelAndView(attributes, "updateEstudent.ftl");
        }, engine);
    }


    /***
     * http://localhost:4567/deleteStudent/:matricula/
     */
    public void deleteStudent() {
        get("/deleteStudent/:matricula/", (request, response) -> {
            int studentID = Integer.parseInt(request.params(":matricula"));
            int index = find(studentID);
            listEstudent.remove(index);

            response.redirect("/studentList/");
            return "";
        });
    }


    private String automaticHtmlCode(List<Estudiante> estudents) {
        String htmlCode = "";
        for (Estudiante item : estudents) {
            htmlCode += "<tr onclick=\"document.location = '/showStudentInfo/" + item .getMatricula() + "/';\">" + "\n\t\t" +
                    "<td>" + item .getMatricula() + "</td>" + "\n\t\t" +
                    "<td>" + item .getName() + "</td>" + "\n\t\t" +
                    "<td>" + item .getLastname() + "</td>" + "\n\t\t" +
                    "<td>" + item .getTel() + "</td>" + "\n\t\t" +
                    "<td>" + "\n\t\t\t" +
                    "<a href=\"/updateStudent/" + item .getMatricula() + "/\" class=\"btn btn-warning\"  role=\"button\">Actualizar</a>" + "\n\t\t\t" +
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

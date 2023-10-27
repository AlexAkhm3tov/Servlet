package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/get")

public class ServletList extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        //request
        //endpoint IdeaProjects_war_exploded/get
        //parameter ?name=sdf&surname=sdf&salary=123124
        //url http://localhost:8083/

        PrintWriter pw = response.getWriter();
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        int id = Integer.parseInt(request.getParameter("id"));

        if (id == 0) {
            String json = gson.toJson(model.getFromList());
            pw.print(json);

        } else if (id > 0) {
            if (model.getFromList().containsKey(id)) {
                User user = model.getFromList().get(id);
                String json = gson.toJson(user);
                pw.print(json);
            } else {
                String errorMessage = gson.toJson("Пользователь не найден");
                pw.print(errorMessage);
            }
        } else {
            String errorMessage = gson.toJson("ID должен быть больше 0");
            pw.print(errorMessage);
        }
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html; charset=UTF-8");
//        PrintWriter pw = response.getWriter();
//
//        int id = Integer.parseInt(request.getParameter("id"));
//
//        if (id == 0) {
//            pw.print ("<html>" +
//                      "<h3>Доступные пользователи:</h3><br/>" +
//                      "ID пользователя: " +
//                      "<ul>");
//
//            for (Map.Entry<Integer, User> entry : model.getFromList().entrySet()) {
//                pw.print("<li>" + entry.getKey() + "</li>" +
//                        "<ul>" +
//                        "<li>Имя: " + entry.getValue().getName() + "</li>" +
//                        "<li>Фамилия: " + entry.getValue().getSurname() + "</li>" +
//                        "<li>Зарплата: " + entry.getValue().getSalary() + "</li>" +
//                        "</ul>");
//            }
//            pw.print("</ul>" +
//                    "<a href =\"index.jsp\">Домой</a>" +
//                    "</html>");
//        } else if (id > 0) {
//            if (id > model.getFromList().size()) {
//                pw.print("<html>" +
//                        "<h3>Такого пользователя нет</h3>" +
//                        "<a href =\"index.jsp\">Домой</a>" +
//                        "</html>");
//            } else {
//                pw.print("<html>" +
//                        "<h3>Запрошенный пользователь</h3>" +
//                        "<br/>" +
//                        "Имя: " + model.getFromList().get(id).getName() + "<br/>" +
//                        "Фамилия: " + model.getFromList().get(id).getSurname() + "<br/>" +
//                        "Зарплата: " + model.getFromList().get(id).getSalary() + "<br/>" +
//                        "<a href =\"index.jsp\">Домой</a>" +
//                        "</html>");
//            }
//        } else {
//            pw.print("<html>" +
//                    "<h3>ID должен быть больше 0</h3>" +
//                    "<a href =\"index.jsp\">Домой</a>" +
//                    "</html>");
//        }
//    }
}

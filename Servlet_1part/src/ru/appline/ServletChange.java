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

@WebServlet(urlPatterns = "/change")
public class ServletChange extends HttpServlet {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer sb = new StringBuffer();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        String jsonString = sb.toString();

        JsonObject jobj = gson.fromJson(jsonString, JsonObject.class);

        int id = jobj.get("id").getAsInt();
        String name = jobj.get("name").getAsString();
        String surname = jobj.get("surname").getAsString();
        double salary = jobj.get("salary").getAsDouble();

        Model model = Model.getInstance();
        User user = new User(name, surname, salary);
        model.change(user,id);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }
}

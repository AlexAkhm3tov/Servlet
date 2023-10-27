package ru.appline;

import ru.appline.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    Model model = Model.getInstance();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        if (id == 0) {
            pw.print("Ошибка. Не указан id пользователя.");
            return;
        }

        if (model.getFromList().containsKey(id)) {
            model.deleteFromList(id);
            pw.print("Пользователь с id = " + id + " успешно удален.");
        } else {
            pw.print("Ошибка удаления. Пользователь с id = " + id + " не найден.");
        }
    }
}

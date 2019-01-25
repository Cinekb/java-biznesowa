package wipb.jee.clientdemo.web.controller;

import wipb.jee.clientdemo.ejb.ErrorDao;
import wipb.jee.clientdemo.model.Error;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author m
 */
@WebServlet(name = " RegulaminController", urlPatterns = {"/regulamin", "/regulamin/error_list"})
public class RegulaminController extends HttpServlet {

    private final Logger log = Logger.getLogger(RegulaminController.class.getName());

    @EJB
    ErrorDao dao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/regulamin")) {
            handleRegulamin(request, response);
        } else if (path.equals("/regulamin/error_list"))
            handleregulaminList(request, response);

    }



    private void handleregulaminList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Error> error = dao.findAll();
        request.setAttribute("errorList", error);
        request.getRequestDispatcher("/WEB-INF/views/regulamin/error_list.jsp").forward(request, response);
    }

    private void handleRegulamin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/regulamin/regulamin.jsp").forward(request, response);
    }



    private boolean validate( String author, String tekst) {
        if (author == null || author.trim().isEmpty()) {
            return false;
        }
        if(tekst== null || tekst.trim().isEmpty()) {
            return false;
        }
        return true;
    }}
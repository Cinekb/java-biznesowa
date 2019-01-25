/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wipb.jee.clientdemo.web.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import wipb.jee.clientdemo.ejb.MailDao;
import wipb.jee.clientdemo.ejb.UserDao;
import wipb.jee.clientdemo.model.Customer;
import wipb.jee.clientdemo.model.Mail;

/**
 *
 * @author m
 */
@WebServlet(name = "UserController", urlPatterns = {"/post/Logout", "/login", "/post/list/", "/loginfailed","/logincompleted", "/book/remove/*", "/registration/*", "/registration/completed","/mail/add"})
public class UserController extends HttpServlet {
    
    private final Logger log = Logger.getLogger(UserController.class.getName());
    

    @EJB
    private UserDao userdao;
    @EJB
    private MailDao mdao;
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        String path = request.getServletPath();
        if (path.equals("/registration")) {
            handleRegistration(request, response);
        } else if (path.equals("/login")) {
            handleLogin(request, response);
        } else if (path.equals("/registration/completed")) {
            handleRCompleted(request, response);}
        else if (path.equals("/logincompleted")) {
            handleLoginCompleted(request, response);}
        else if (path.equals("/loginfailed")) {
            handleLoginFailed(request, response);}
            else if (path.equals("/mail/add")) {
            handlepostMailAdd(request, response);}

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/registration")) {
            handleRegistrationPost(request, response);
        }
        else if (path.equals("/login")) {
            handleLoginPost(request, response);
        }
        else if (path.equals("/post/Logout")) {
            handleLogoutPost(request, response);
        }else if (path.equals("/mail/add"))
            handlepostAddMail(request, response);
    }
    
    private void handleRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registration/registration.jsp").forward(request, response);
    }
    
     private void handleRegistrationPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //if (!validate(title, author, price)) {            
        //    request.getRequestDispatcher("/WEB-INF/views/book/book_add.jsp").forward(request, response);
        //    return;
       // }        
       System.out.println(UserController.class.getClassLoader().getResource("logging.properties"));
        Customer u = new Customer();
        u.setLogin(login);
        u.setName(name);
        u.setSurname(surname);
        u.setEmail(email);
        u.setPassword(password);
        if(name.equals("admin")) u.setSuperuser(true);
        else u.setSuperuser(false);
        userdao.save(u);
         log.log(Level.INFO,"user has been registered and added to database");
 
        response.sendRedirect(request.getContextPath() + "/registration/completed");
     }   
     
      private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registration/login.jsp").forward(request, response);
    }
      
    
     private void handleLoginPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        //if (!validate(title, author, price)) {            
        //    request.getRequestDispatcher("/WEB-INF/views/book/book_add.jsp").forward(request, response);
        //    return;
       // }        
       boolean valid = false;
       boolean issuperuser = false;
        List<Customer> user_list = userdao.findAll();
        for (Customer u : user_list)
        {
            if (u.getLogin().equals(login))
            {
                if (u.getPassword().equals(password))
                {
                    valid = true;
                    issuperuser = u.isSuperuser();
                }
            }
        }
        if (valid){ 
            response.sendRedirect(request.getContextPath() + "/logincompleted");
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(300);
            session.setAttribute("username", login);
            session.setAttribute("issuperuser", issuperuser);
            log.log(Level.INFO,"log in user: " + login + " success");
        }
        else 
        {
                    log.log(Level.INFO,"log in user: " + login + " failed" );
            response.sendRedirect(request.getContextPath() + "/loginfailed");
        }
     }
      private void handleLoginCompleted(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registration/logincompleted.jsp").forward(request, response);
    }
      
            private void handleLoginFailed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registration/loginfailed.jsp").forward(request, response);
    }
     
     private void handleRCompleted(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registration/rcompleted.jsp").forward(request, response);
    }
     
     private void handleLogoutPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registration/logout.jsp").forward(request, response);
    }




    private void handlepostMailAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/mail/mail_add.jsp").forward(request, response);

    }

    private void handlepostAddMail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String adres = request.getParameter("tekst");

        if (!validate(adres)) {
            request.getRequestDispatcher("/WEB-INF/views/mail/mail_add.jsp").forward(request, response);
            return;
        }
        Mail b = new Mail();
        b.setAdres(adres);
        mdao.save(b);
        response.sendRedirect(request.getContextPath() + "/post/list");
         log.log(Level.INFO,"Subscription success");
    }

    private Long convId(String s) {
        if (s==null || !s.startsWith("/"))
            return null;
        try {
            return Long.parseLong(s.substring(1));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean validate(String adres) {
        if (adres== null || adres.trim().isEmpty()) {
            return false;
        }

        return true;
    }



}

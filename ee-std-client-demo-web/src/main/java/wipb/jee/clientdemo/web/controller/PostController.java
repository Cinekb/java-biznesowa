/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wipb.jee.clientdemo.web.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.geometry.Pos;
import wipb.jee.clientdemo.ejb.*;
import wipb.jee.clientdemo.model.Answer;
import wipb.jee.clientdemo.model.Error;
import wipb.jee.clientdemo.model.Mail;
import wipb.jee.clientdemo.model.Post;

/**
 *
 * @author marcin
 */
@WebServlet(name = "PostController", urlPatterns = {"/post/myposts", "/post/list", "/post/add","/post/remove/*","/post/edit/*","/post_nr/*","/regulamin/error/add/*","/post_nr/add_answer/*"})
public class PostController extends HttpServlet {

    private final Logger log = Logger.getLogger(PostController.class.getName());

    @EJB
    private PostDao dao;
    @EJB
    private ErrorDao errordao;
    @EJB
    private SendMail smdao;
    @EJB
    private MailDao mdao;
    @EJB
    private AnswerDao adao;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/post/list")) {
            handlepostList(request, response);
        } else if (path.equals("/post/add")) {
            handlepostAdd(request, response);
        } else if (path.equals("/post/remove")) {
            handlepostRemove(request, response);
        }else if (path.equals("/post/edit")) {
            handlepostEdit(request, response);
        }else if (path.equals("/post/myposts")) {
            handlepostMyposts(request, response);
        }else if(path.equals("/post_nr")){
            postpage(request,response);
        }else if (path.equals("/regulamin/error/add")){
            handleerrorAdd(request,response);}
        else if(path.equals("/post_nr/add_answer")){
           handleAnserwAdd(request,response);}

   }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/post/add")) {
            handlepostAddPost(request, response);
        }else if (path.equals("/regulamin/error/add")) {
            handlepostAddError(request, response);
        } else if (path.equals("/post/edit"))
            handlepostEditADD(request, response);
        else if(path.equals("/post_nr/add_answer"))
            handleAnserAdd(request,response);
    }



    private void handlepostEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getPathInfo();
        Long id = convId(s);
        Post post=dao.getpost(id);
        if (id!=null) {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String tekst = request.getParameter("tekst");

            if (!validate(title, author, tekst)) {
                request.setAttribute("PostID",post);
                request.getRequestDispatcher("/WEB-INF/views/post/post_edit.jsp").forward(request, response);
        }

    }}

    private void handlepostEditADD(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String s = request.getPathInfo();
        Long id = convId(s);
        Post post=dao.getpost(id);
        if (id!=null) {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String tekst = request.getParameter("tekst");

            if (!validate(title, author, tekst)) {
                request.setAttribute("PostID",post);
                request.getRequestDispatcher("/WEB-INF/views/post/post_edit.jsp").forward(request, response);

            }

            Post b = new Post();
            b.setId(id);
            b.setTitle(title);
            b.setAuthor(author);
            b.setTekst(tekst);
            //dao.remove(id);
            dao.update(b);

        }
        response.sendRedirect(request.getContextPath() + "/post/list");

    }

    private void postpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postid =request.getParameter("PostID");
        Long id = Long.parseLong(postid);
        Post post=dao.getpost(id);
        request.setAttribute("PostID",post);
List<Answer> answers=adao.getAns(id);
        request.setAttribute("listAnswer",answers);
        RequestDispatcher dispatcher;
        if(post == null)

            dispatcher=  request.getRequestDispatcher("/WEB-INF/views/post/post_list.jsp");
        else {
            dispatcher = request.getRequestDispatcher("/WEB-INF/views/post/post_nr.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void handlepostList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = dao.findAll();
        request.setAttribute("postList", posts);
        request.getRequestDispatcher("/WEB-INF/views/post/post_list.jsp").forward(request, response);
    }

    private void handleerrorAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/regulamin/error_add.jsp").forward(request, response);
    }


    private void handlepostAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/post/post_add.jsp").forward(request, response);
    }

    private void handlepostAddPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String tekst = request.getParameter("tekst");

        if (!validate(title, author, tekst)) {
            request.getRequestDispatcher("/WEB-INF/views/post/post_add.jsp").forward(request, response);
            return;
        }
        meil_sending();
        Post b = new Post();
        b.setTitle(title);
        b.setAuthor(author);
        b.setTekst(tekst);
        dao.save(b);
        response.sendRedirect(request.getContextPath() + "/post/list");
    }
    private void handlepostAddError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String s = request.getPathInfo();
        Long id = convId(s);
        String author = request.getParameter("author");
        String tekst = request.getParameter("tekst");
        Post post =dao.getpost(id);
        if (!validate( author,tekst,post)) {
            request.getRequestDispatcher("/WEB-INF/views/regulamin/error_add.jsp").forward(request, response);
            return;
        }

        Error error =new Error();
        error.setPost_id(post.getId());
        error.setAuthor(author);
        error.setTekst(tekst);
        errordao.save(error);
        response.sendRedirect(request.getContextPath() + "/regulamin/error_list");
    }




    private boolean validate(String title, String author, String tekst) {
        if (title == null || title.trim().isEmpty()) {
            return false;
        }
        if (author == null || author.trim().isEmpty()) {
            return false;
        }
        if(tekst== null || tekst.trim().isEmpty()) {
            return false;
        }
        return true;
    }
    private boolean validate(String author, String tekst,Post post) {
        if (post == null  ) {
            return false;
        }
        if (author == null || author.trim().isEmpty()) {
            return false;
        }
        if(tekst== null || tekst.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    private void handlepostRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String s = request.getPathInfo();
        Long id = convId(s);
        if (id!=null) {
            try {
                dao.remove(id);
            } catch (Throwable t) {
                log.log(Level.SEVERE,"post remove failed",t);
            }
        }
        log.log(Level.INFO,"post remove corectly");
        response.sendRedirect(request.getContextPath() + "/post/list");
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
    
     private void handlepostMyposts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> allposts = dao.findAll();
        request.setAttribute("postList", allposts);
        request.getRequestDispatcher("/WEB-INF/views/post/myposts.jsp").forward(request, response);
    }
    private  void meil_sending()
    {
        List<Mail> mails = mdao.findAll();
       for(Mail mail : mails)
       {
           smdao.sendMail(mail.getAdres());
       }
               log.log(Level.INFO,"mail's has been sent");
    }

    private void handleAnserwAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/post/answer_add.jsp").forward(request, response);

    }
    private void handleAnserAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postid =request.getParameter("PostID");
        Long id = Long.parseLong(postid);

        String tekst = request.getParameter("tekst");
        String author = request.getParameter("author");
        Post post = dao.getpost(id);
        if (!validate(author, tekst,post)) {
            request.getRequestDispatcher("/WEB-INF/views/post/answer_add.jsp").forward(request, response);
            return;
        }


   Answer b= new Answer();
        b.setPost(post);
        b.setAuthor(author);
        b.setTekst(tekst);
        dao.update(post);
                log.log(Level.INFO,"answet has been addded");
        response.sendRedirect(request.getContextPath() + "/post/list");
    }
    }



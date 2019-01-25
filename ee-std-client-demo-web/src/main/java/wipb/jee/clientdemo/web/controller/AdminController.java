/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wipb.jee.clientdemo.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wipb.jee.clientdemo.ejb.ErrorDao;
import wipb.jee.clientdemo.ejb.PostDao;
import wipb.jee.clientdemo.model.Post;


@WebServlet(name = "AdminController", urlPatterns = {"/admin/posts", "/admin/posts/add", "/admin/posts/remove/*","/admin/posts/edit/*", "/admin/regulamin/error_list"})
public class AdminController extends HttpServlet {
    
    private final Logger log = Logger.getLogger(PostController.class.getName());
        @EJB
    ErrorDao edao;
        @EJB
    private PostDao dao;
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
     
     private Long convId(String s) {
        if (s==null || !s.startsWith("/"))
            return null;
        try {
            return Long.parseLong(s.substring(1));
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        String path = request.getServletPath();
        if (path.equals("/admin/posts")) {
            handlePostsAdmin(request, response);
        } else if (path.equals("/admin/posts/add")) {
            handlePostsAdminAdd(request, response);
        } else if (path.equals("/admin/posts/remove")) {
            handlePostsAdminRemove(request, response);}
        else if (path.equals("/admin/posts/edit")) {
            handlePostsAdminEdit(request, response);}
        else if (path.equals("/admin/regulamin/error_list")){
            handlePostsAdminRaportList(request,response);
        }
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
        if (path.equals("/admin/posts/add")) {
            handlePostAddAdminPost(request, response);
        }else if (path.equals("/admin/posts/edit"))
            handlePostsAdminEditPost(request, response);
    }
    
     private void handlePostsAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = dao.findAll();
        request.setAttribute("postList", posts);
        request.getRequestDispatcher("/WEB-INF/views/admin/posts.jsp").forward(request, response);
    }
     
     private void handlePostsAdminAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/post/post_add.jsp").forward(request, response);
    }
     
      private void handlePostAddAdminPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String tekst = request.getParameter("tekst");

        if (!validate(title, author, tekst)) {
            request.getRequestDispatcher("/WEB-INF/views/admin/post_add.jsp").forward(request, response);
            return;
        }
        Post b = new Post();
        b.setTitle(title);
        b.setAuthor(author);
        b.setTekst(tekst);
        dao.save(b);
        response.sendRedirect(request.getContextPath() + "/admin/posts");
    }
      
      private void handlePostsAdminRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String s = request.getPathInfo();
        Long id = convId(s);
        if (id!=null) {
            try {
                dao.remove(id);
            } catch (Throwable t) {
                log.log(Level.SEVERE,"post remove failed",t);
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/posts");
    }
      
      private void handlePostsAdminEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
      
       private void handlePostsAdminEditPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        response.sendRedirect(request.getContextPath() + "/admin/posts");
    
}
       
        private void handlePostsAdminRaportList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<wipb.jee.clientdemo.model.Error> error = edao.findAll();
        request.setAttribute("errorList", error);
        request.getRequestDispatcher("/WEB-INF/views/admin/reportedposts.jsp").forward(request, response);
    }
}

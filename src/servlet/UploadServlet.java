/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.PhotoAlbum;
import dao.DAOFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Photo;

/**
 *
 * @author Rodrigo
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
@MultipartConfig()
public class UploadServlet extends HttpServlet 
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession(true);
        PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(session);
        if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) 
        {
            this.uploadPhoto(request, pa);
        }
    
        RequestDispatcher rd = request.getRequestDispatcher("/album.jsp");      
        rd.forward(request, response);

    }
    
    private void uploadPhoto(HttpServletRequest request,
            PhotoAlbum pa)
            throws IOException, ServletException 
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String filename = null;
        for (Part p : request.getParts()) 
        {
            this.copyBytes(p.getInputStream(), baos);
            filename = p.getSubmittedFileName();
        }
        
        String subtitle = request.getParameter("subtitle");
        String author = request.getParameter("author");
        String local = request.getParameter("local");
        
        Photo photo = new Photo(baos.toByteArray(),subtitle, author, local);
        
        DAOFactory.createFotoDAO().inserir(photo);
        
        pa.addPhoto(photo);
    }

    private void copyBytes(InputStream is, OutputStream os) throws IOException {
        int i;
        while ((i = is.read()) != -1) 
        {
            os.write(i);
        }
        is.close();
        os.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

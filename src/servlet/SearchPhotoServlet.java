/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.PhotoAlbum;
import beans.PhotoResultAlbum;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Photo;

/**
 *
 * @author Rodrigo
 */
@WebServlet(name = "SearchPhotoServlet", urlPatterns = {"/SearchPhotoServlet"})
public class SearchPhotoServlet extends HttpServlet {

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
        String searchString = request.getParameter("search");
        PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(session);
        PhotoResultAlbum pra = PhotoResultAlbum.getPhotoResultAlbum(session);
        
        session.setAttribute("search", true);
        
        addItensResultList(pa, pra, searchString);
        
        RequestDispatcher rd = request.getRequestDispatcher("/results.jsp");      
        rd.forward(request, response);
    }
    
    private void addItensResultList(PhotoAlbum pa, PhotoResultAlbum pra, String searchString)
    {
        List<Photo> resultList = new ArrayList<Photo>();
        
        for(Photo photo : pa.getPhotoDataList())
        {
            if(photo.getAuthor().contains(searchString) || photo.getLocal().contains(searchString))
            {
                resultList.add(photo);
            }
        }
        pra.setResultList(resultList);
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

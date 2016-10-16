package servlet;


import Exceptions.IllegalPhotoIndexException;
import beans.PhotoAlbum;
import beans.PhotoResultAlbum;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Photo;

@WebServlet(name = "RemovePhotoServlet",
        urlPatterns = {"/RemovePhotoServlet"})
public class RemovePhotoServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);
        
        String indexString = request.getParameter("photo");
        int index = (new Integer(indexString.trim())).intValue();
        PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(session);
        PhotoResultAlbum pra = PhotoResultAlbum.getPhotoResultAlbum(session);
        boolean search = (Boolean) session.getAttribute("search");
        
        if(search)
        {
            Photo photo = pra.getPhoto(index);
            try
            {
                index = getPhotoAlbumIndex(pa, photo);
            } 
            catch (IllegalPhotoIndexException ex) 
            {
                RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");      
                rd.forward(request, response);
            }
        }
        
        pa.removePhoto(index);
        
        RequestDispatcher rd = request.getRequestDispatcher("/album.jsp");      
        rd.forward(request, response);
    }
    
    private int getPhotoAlbumIndex(PhotoAlbum pa, Photo photo)
            throws IllegalPhotoIndexException
    {
        int index = 0;
        List<Photo> photos = new ArrayList<Photo>();
        photos = pa.getPhotoDataList();
        for(Photo photoAux : photos)
        {
            if(photoAux.equals(photo))
            {
                return index;
            }
            index++;
        }
        throw new IllegalPhotoIndexException("�?ndice ilegal para remoção");
    }
}
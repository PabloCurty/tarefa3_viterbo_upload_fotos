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
import utils.Pair;

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
        
        Pair<Integer, Long> pair = new Pair<>();
        String indexString = request.getParameter("photo");
        int index = (new Integer(indexString.trim())).intValue();
        PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(session);
        PhotoResultAlbum pra = PhotoResultAlbum.getPhotoResultAlbum(session);
        boolean search = false;
        try {
        	search = (Boolean) session.getAttribute("search");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
        
        if(search)
        {
            Photo photo = pra.getPhoto(index);
            try
            {
                pair = getPhotoAlbumIndex(pa, photo);
            } 
            catch (IllegalPhotoIndexException ex) 
            {
                RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");      
                rd.forward(request, response);
            }
        }else{
        	pair.setFirst(index);
        	pair.setSecond(pa.getPhotoID(index));
        }
        
        if(!pa.removePhotoJDBC(pair)){
        	pa.removePhoto(index);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/album.jsp");      
        rd.forward(request, response);
    }
    
    // F È int index S long id
    private Pair<Integer, Long> getPhotoAlbumIndex(PhotoAlbum pa, Photo photo)
            throws IllegalPhotoIndexException
    {
        int index = 0;
        List<Photo> photos = new ArrayList<Photo>();
        photos = pa.getPhotoDataList();
        for(Photo photoAux : photos)
        {
            if(photoAux.equals(photo))
            {
            	Pair<Integer, Long> pair = new Pair<>();
            	pair.setFirst(index);
            	pair.setSecond(photoAux.getId());
                return pair;
            }
            index++;
        }
        throw new IllegalPhotoIndexException("√?ndice ilegal para remo√ß√£o");
    }
}
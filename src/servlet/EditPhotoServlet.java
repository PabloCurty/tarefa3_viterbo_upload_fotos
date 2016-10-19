package servlet;


import beans.PhotoAlbum;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EditPhotoServlet",
        urlPatterns = {"/EditPhotoServlet"})
public class EditPhotoServlet extends HttpServlet 
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
        
        Long id = pa.getPhotoID(index);
        String subtitle = pa.getPhotoName(index);
        String author = pa.getPhotoAuthor(index);
        String local = pa.getPhotoLocal(index);
        
        session.setAttribute("id", id);
        session.setAttribute("index", index);
        session.setAttribute("subtitle", subtitle);
        session.setAttribute("author", author);
        session.setAttribute("local", local);
        
        RequestDispatcher rd = request.getRequestDispatcher("/edit.jsp");      
        rd.forward(request, response);
    }
}

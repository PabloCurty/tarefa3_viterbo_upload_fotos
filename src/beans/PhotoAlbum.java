/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Rodrigo
 */
import model.Photo;
import utils.Pair;

import java.util.*;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;

public class PhotoAlbum 
{
    public static String ATTRIBUTE_NAME = "Photo_Album";
    private static List<Photo> photoDataList = DAOFactory.createFotoDAO().listar(); 
    
    public PhotoAlbum() 
    {
    }
    
    public List<Photo> getPhotoDataList()
    {
        return photoDataList;
    }
    
    public void setSession(HttpSession session) 
    {
        session.setAttribute(ATTRIBUTE_NAME, this);
    }
    
    public List getPhotoSubtitles() 
    {
        List<String> names = new ArrayList<String>();
        for(Photo photo : photoDataList)
        {
            names.add(photo.getSubtitle());
        }    
        return names;
    }
    
    public List getPhotoAuthors() 
    {
        List<String> names = new ArrayList<String>();
        for(Photo photo : photoDataList)
        {
            names.add(photo.getAuthor());
        }    
        return names;
    }
    
    public List getPhotoLocals() 
    {
        List<String> names = new ArrayList<String>();
        for(Photo photo : photoDataList)
        {
            names.add(photo.getLocal());
        }    
        return names;
    }
    
    public void addPhoto(Photo photo) 
    {
        this.photoDataList.add(photo);
    }

    public Long getPhotoID(int i) 
    {
        return photoDataList.get(i).getId();
    }
    
    public byte[] getPhotoData(int i) 
    {
        return photoDataList.get(i).getData();
    }
    
    //TODO change method name - jsp too
    public String getPhotoName(int i) 
    {
        return photoDataList.get(i).getSubtitle();
    }
    
    public String getPhotoAuthor(int i) 
    {
        return photoDataList.get(i).getAuthor();
    }
    
    public String getPhotoLocal(int i) 
    {
        return photoDataList.get(i).getLocal();
    }
    
    public int getPhotoCount() 
    {
        return photoDataList.size();
    }
    
    public void removePhoto(int i) 
    {
        photoDataList.remove(i);
    }
    
    public Boolean removePhotoJDBC(Pair<Integer, Long> pair) 
    {
    	return DAOFactory.createFotoDAO().remover(pair.getSecond());
    }
    
    
    public static PhotoAlbum getPhotoAlbum(HttpSession session) 
    {
        return (PhotoAlbum) session.getAttribute(ATTRIBUTE_NAME);
    }
    
    public void editPhoto(int index, String subtitle, String author, String local)
    {
        photoDataList.get(index).setAuthor(author);
        photoDataList.get(index).setSubtitle(subtitle);
        photoDataList.get(index).setLocal(local);
    }
    
    public void editPhotoJDBC(int index, Long id, String subtitle, String author, String local)
    {
        Photo foto = new Photo(id, subtitle, author, local);
        if(!DAOFactory.createFotoDAO().editar(foto)){
        	editPhoto(index, subtitle, author, local);
        }
    }
    
}
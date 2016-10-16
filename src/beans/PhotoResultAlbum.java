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
import java.util.*;
import javax.servlet.http.HttpSession;

public class PhotoResultAlbum 
{
    public static String ATTRIBUTE_NAME = "PhotoResult_Album";
    private List<Photo> photoDataResultList = new ArrayList<Photo>();
    private List<Integer> correspondentIndex = new ArrayList<Integer>();
    
    public PhotoResultAlbum() 
    {
    }
    
    public void setSession(HttpSession session) 
    {
        session.setAttribute(ATTRIBUTE_NAME, this);
    }
    
    public byte[] getPhotoData(int i) 
    {
        return photoDataResultList.get(i).getData();
    }
    
    public Photo getPhoto(int i) 
    {
        return photoDataResultList.get(i);
    }
    
    public static PhotoResultAlbum getPhotoResultAlbum(HttpSession session) 
    {
        return (PhotoResultAlbum) session.getAttribute(ATTRIBUTE_NAME);
    }
    
    public void setResultList(List<Photo> resultList)
    {
        photoDataResultList = resultList;
    }
    
    public List<Photo> getResultList()
    {
        return photoDataResultList;
    }
    
    public int getResultListCount() 
    {
        return photoDataResultList.size();
    }
    
    public List getResultListSubtitles() 
    {
        List<String> names = new ArrayList<String>();
        for(Photo photo : photoDataResultList)
        {
            names.add(photo.getSubtitle());
        }    
        return names;
    }
    
    public List getResultListAuthors() 
    {
        List<String> names = new ArrayList<String>();
        for(Photo photo : photoDataResultList)
        {
            names.add(photo.getAuthor());
        }    
        return names;
    }
    
    public List getResultListLocals() 
    {
        List<String> names = new ArrayList<String>();
        for(Photo photo : photoDataResultList)
        {
            names.add(photo.getLocal());
        }    
        return names;
    }
    
//    public getCorrespondentIndex(Photo photo)
//    {
//        for(Photo photo_ : )
//    }
}
<%-- 
    Document   : edit
    Created on : Oct 13, 2016, 7:01:36 PM
    Author     : Rodrigo
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="photo-tags" uri="WEB-INF/mytaglib.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Long id = (Long)request.getSession().getAttribute("id"); %>
<% int index = (Integer)request.getSession().getAttribute("index"); %>
<% String subtitle = (String)request.getSession().getAttribute("subtitle"); %>
<% String author = (String)request.getSession().getAttribute("author"); %>
<% String local = (String)request.getSession().getAttribute("local"); %>
<!DOCTYPE html>
<html>
    <jsp:useBean id="photoAlbum" scope = "session" class="beans.PhotoAlbum"/>
    <jsp:setProperty name="photoAlbum" property="session" value="<%=session%>"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Photo Viewer</title>
    </head>
    <body>
        <h3 align='center'>Photos</h3>
        <table align='center'>
            <tr>
                <td align='center'>
                    <photo-tags:photo width='150' height='120' index='${index}'></photo-tags:photo>
                </td>
                <td bgcolor='#cccccc' width='120' height='120'>
                    <form align='left' action='SavePhotoServlet' method='post'>
                    	<input name='id' value='${id}' type='hidden'>
                        Subtitle: <input name='subtitle' value='${subtitle}' type='text'>
                        Author: <input name='author' value='${author}' type='text'>
                        Local: <input name='local' value='${local}' type='text'>
                        <input value='Save' type='submit'><br>
                    </form>
                </td>
            </tr>
            <tr>
                <td align='center'>
                    <a href='RemovePhotoServlet?photo=${index}'>remove</a>
                </td>
            </tr>
            <tr>
                <td align='center'>
                    <a href='ReturnMenuServlet'>return</a>
                </td>
            </tr>
        </table>
    </body>
</html>

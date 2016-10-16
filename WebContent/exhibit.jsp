<%-- 
    Document   : exhibit
    Created on : Oct 13, 2016, 9:31:58 PM
    Author     : Rodrigo
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="photo-tags" uri="WEB-INF/mytaglib.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% int index = (Integer)request.getSession().getAttribute("index"); %>
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
                    <photo-tags:photo width='800' height='600' index='${index}'></photo-tags:photo>
                </td>
            </tr>
            <tr>
                <td align='center'>
                    <a href='EditPhotoServlet?photo=${index}'>edit</a>
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

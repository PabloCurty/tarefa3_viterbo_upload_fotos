<%-- 
    Document   : album
    Created on : Oct 12, 2016, 9:06:46 PM
    Author     : Rodrigo
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="photo-tags" uri="WEB-INF/mytaglib.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:useBean id="photoAlbum" scope = "session" class="beans.PhotoAlbum"/>
    <jsp:useBean id="photoResultAlbum" scope = "session" class="beans.PhotoResultAlbum"/>
    <jsp:setProperty name="photoAlbum" property="session" value="<%=session%>"/>
    <jsp:setProperty name="photoResultAlbum" property="session" value="<%=session%>"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Photo Viewer</title>
    </head>
    <body>
        <h3 align='center'>Photos</h3>
        <h3 align='center'>
            <%-- TODO jquery para colocar possivel input --%>
            <form align='left' action='SearchPhotoServlet' method='get'>  
                <input name='search' type='text'>
                <input value='Search' type='submit'>
            </form>
        </h3>
        <table align='center'>
            <tr>
                <c:forEach var="i" begin="1" end="${photoAlbum.photoCount}">
                    <td align='center'>
                        <photo-tags:photo width='150' height='120' index='${i-1}'></photo-tags:photo>
                    </td>
                </c:forEach>
                <td bgcolor='#cccccc' width='120' height='120'>
                    <form align='left' action='UploadServlet'
                          method='post' enctype='multipart/form-data'>
                        <input value='Choose' name='myFile'
                               type='file' accept='image/jpeg'><br>
                        Subtitle: <input name='subtitle'
                               type='text'><br>
                        Author: <input name='author'
                               type='text'> <br>
                        Local: <input name='local'
                               type='text'> <br>
                        <input value='Upload' type='submit'><br>
                    </form>
                </td>
            </tr>
            <tr>
                <c:forEach var="subtitle" items="${photoAlbum.photoSubtitles}">
                    <td align='center'>
                        ${subtitle}
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="author" items="${photoAlbum.photoAuthors}">
                    <td align='center'>
                        ${author}
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="local" items="${photoAlbum.photoLocals}">
                    <td align='center'>
                        ${local}
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="i" begin="1" end="${photoAlbum.photoCount}">
                    <td align='center'>
                        <a href='ExhibitPhotoServlet?photo=${i-1}'>view</a>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="i" begin="1" end="${photoAlbum.photoCount}">
                    <td align='center'>
                        <a href='EditPhotoServlet?photo=${i-1}'>edit</a>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="i" begin="1" end="${photoAlbum.photoCount}">
                    <td align='center'>
                        <a href='RemovePhotoServlet?photo=${i-1}'>remove</a>
                    </td>
                </c:forEach>
            </tr>
        </table>
    </body>
</html>

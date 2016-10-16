<%-- 
    Document   : results
    Created on : Oct 13, 2016, 10:34:49 PM
    Author     : Rodrigo
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="photo-tags" uri="WEB-INF/mytaglib.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:useBean id="photoResultAlbum" scope = "session" class="beans.PhotoResultAlbum"/>
    <jsp:setProperty name="photoResultAlbum" property="session" value="<%=session%>"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Photo Viewer</title>
    </head>
    <body>
        <h3 align='center'>Search Result</h3>
        <h3 align='center'>
            <%-- TODO jquery para colocar possivel input --%>
            <form align='left' action='SearchPhotoServlet' method='get'>  
                <input name='search' type='text'>
                <input value='Search' type='submit'>
            </form>
        </h3>
        <table align='center'>
            <tr>
                <c:forEach var="i" begin="1" end="${photoResultAlbum.resultListCount}">
                    <td align='center'>
                        <photo-tags:photo width='150' height='120' index='${i-1}'></photo-tags:photo>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="subtitle" items="${photoResultAlbum.resultListSubtitles}">
                    <td align='center'>
                        ${subtitle}
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="author" items="${photoResultAlbum.resultListAuthors}">
                    <td align='center'>
                        ${author}
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="local" items="${photoResultAlbum.resultListLocals}">
                    <td align='center'>
                        ${local}
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="i" begin="1" end="${photoResultAlbum.resultListCount}">
                    <td align='center'>
                        <a href='ExhibitPhotoServlet?photo=${i-1}'>view</a>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="i" begin="1" end="${photoResultAlbum.resultListCount}">
                    <td align='center'>
                        <a href='EditPhotoServlet?photo=${i-1}'>edit</a>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="i" begin="1" end="${photoResultAlbum.resultListCount}">
                    <td align='center'>
                        <a href='RemovePhotoServlet?photo=${i-1}'>remove</a>
                    </td>
                </c:forEach>
            </tr>
        </table>
        <p align='center'>
            <a href='ReturnMenuServlet'>return</a>
        </p>
    </body>
</html>

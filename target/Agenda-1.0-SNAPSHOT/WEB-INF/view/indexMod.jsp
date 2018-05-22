<%-- 
    Document   : indexMod
    Created on : 17/05/2018, 21:25:56
    Author     : White
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eventos</title>
    </head>
    <body>
        <h1>Veja os eventos</h1>
        <fieldset>
            <legend>Buscar evento </legend>
            <form action="Busca" method="get">
                Nome <input type="text" name="busca" value=""/>
                <input type="submit" value="Enviar"/>
            </form>
        </fieldset>
        
        <br>
        <form action="InsertServlet" method="get">
            <input type="submit" name="inserir" value="Novo Evento">
        </form>
        
        <br>
        <table>
            <tr> 
                <th> Evento</th>
                <th> Data</th>
                <th> Usuario</th>
            </tr>
            <c:forEach items="${listaEventos}" var="evento">
                <tr>
                    <td> ${evento.nome}</td>
                    <td> ${evento.data}</td>
                    <td> ${evento.userId}</td>
                </tr>            
            </c:forEach>
        </table>
    </body>
</html>

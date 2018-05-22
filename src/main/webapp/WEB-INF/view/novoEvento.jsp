<%-- 
    Document   : novoEvento
    Created on : 18/05/2018, 22:15:21
    Author     : White
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Novo Evento</h1>
       <c:choose>
            <c:when  test="${sessionScope.logUser != null}">
                <form action="InsertServlet" method="POST">
                    Titulo <input type="text" name="nomeEvento" value=""/> 
                    <c:if test="${eventoOk == true}">
                        <h4 style="color:blue"> Evento adicionado com sucesso</h4>
                    </c:if>
                    <p>Data <input type="date" name="data" value="data">

                    <p>Autor <input type="text" name="nomeUsuario" 
                                    value="${sessionScope.logUser.userName}" disabled/>
                        
                    <p><input type="hidden" name="IdUsuario"
                                    value="${sessionScope.logUser.id}"/>

                    <p><input type="submit" value="Enviar"></input>
                </form>

                <form action="InsertServlet" method="GET">
                    <p><input type="submit" name="logout" value="Logout"></input>
                </form>

                <form action="InsertServlet" method="GET">
                    <p><input type="submit" name="index" value="Index"></input>
                </form>
            </c:when>
            
            <c:otherwise>
                <h1>Não Logado</h1>
                <c:redirect url="login.jsp"></c:redirect>
            </c:otherwise>
        </c:choose>
    </body>
</html>

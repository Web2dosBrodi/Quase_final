<%-- 
    Document   : indexMod
    Created on : 17/05/2018, 21:25:56
    Author     : White
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.messages"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="site.titulo"/></title>
    </head>
    <body>
        <fmt:message key="index.titulo.saudacao" />
        <c:if test="${sessionScope.loggedUser.userName != null}" >
            <c:out value="${sessionScope.loggedUser.userName}"/>
        </c:if>
        <c:if test="${sessionScope.loggedUser.userName == null}" >
            <fmt:message key="index.titulo.visitante" />
        </c:if> 
        <h4>
            <a href="Busca?lang=en_US" > us</a>
            <a href="Busca?lang=pt_BR" > pt</a>
        </h4>
        
        <h1><fmt:message key="index.titulo.principal" /> </h1>
        <fieldset>
            <legend><fmt:message key="index.titulo.legenda"/></legend>
            <form action="Busca" method="get">
                <fmt:message key="index.campo.nome"/> <input type="text" name="busca" value=""/>
                <input type="submit" value="<fmt:message key="index.botao.buscar"/>"/>
            </form>
        </fieldset>
        
        <br>
        <form action="InsertServlet" method="get">
            <input type="submit" name="inserir" value="<fmt:message key="index.botao.novoEvento"/>">
        </form>
        
        <br>
        <table>
            <tr> 
                <th> <fmt:message key="index.campo.evento"/></th>
                <th> <fmt:message key="index.campo.data"/></th>
                <th> <fmt:message key="index.campo.usuario"/></th>
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

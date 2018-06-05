<%-- 
    Document   : NovoEvento
    Created on : 02/06/2018, 20:46:56
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
        <h1><fmt:message key="evento.titulo.principal"/></h1>

        <form action="InsertServlet" method="POST">
            <fmt:message key="evento.campo.titulo"/> 
            <input type="text" name="nomeEvento" value="" required/> 
            <c:if test="${adicionado == true}">
                <h5 style="color:blue"> Evento adicionado com sucesso</h5>
            </c:if>
            <c:if test="${tituloVazio == true}">
                <h5 style="color:blue"> Preencha o campo TITULO</h5>
            </c:if>
                
            <p><fmt:message key="evento.campo.data"/> 
                <input type="date" name="data" value="data" required></p>
            <c:if test="${dataVazia == true}">
                <h5 style="color:blue"> Preencha o campo DATA</h5>
            </c:if>

            <p><fmt:message key="evento.campo.autor"/> 
                <input type="text" name="nomeUsuario" 
                       value="${sessionScope.loggedUser.userName}" disabled/></p>
            

            <p><input type="hidden" name="IdUsuario"
                      value="${sessionScope.loggedUser.id}"/></p>

            <p><input type="submit" name="Enviar" 
                      value="<fmt:message key="evento.botao.enviar"/>"/></p>
        </form>

        <form action="InsertServlet" method="GET">
            <p><input type="submit" name="logout" 
                      value="<fmt:message key="evento.botao.logout"/>"/></p>
        </form>

        <form action="InsertServlet" method="GET">
            <p><input type="submit" name="index" 
                      value="<fmt:message key="evento.botao.pgInicial"/>"/></p>
        </form>

    </body>
</html>

<%-- 
    Document   : Login
    Created on : 18/05/2018, 19:56:38
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
        <fieldset>
            <legend><fmt:message key="login.titulo.login"/></legend>
            <form action="Logar" method="post">
                <fmt:message key="login.campo.email"/> <input type="email" name="email" value=""/>
                <c:if test="${semEmail == true}">
                    <h5 style="color:red"> <fmt:message key="mensagem.erro.email"/></h5>
                </c:if>

                <p><fmt:message key="login.campo.senha"/><input type="password" name="senha" value=""/>
                <c:if test="${semSenha == true}">
                    <h5 style="color:red"> <fmt:message key="mensagem.erro.senha"/></h5>
                </c:if></p>
                <c:if test="${invalido == true}">
                    <h5 style="color:red"> <fmt:message key="mensagem.erro.usuarioInvalido"/></h5>
                </c:if>
                    <input type="submit" name="Logar" value="<fmt:message key="login.botao.entrar"/>"/>
            </form>
            <form action="Logar" method="GET">
                <p><input type="submit" name="index" value="<fmt:message key="login.botao.voltar"/>"/></p>
            </form>
        </fieldset>
    </body>
</html>

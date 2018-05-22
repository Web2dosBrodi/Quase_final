<%-- 
    Document   : login
    Created on : 18/05/2018, 19:56:38
    Author     : White
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <fieldset>
            <legend>Login</legend>
            <form action="logar" method="post">
                Email <input type="email" name="email" value=""/>
                <c:if test="${semEmail == true}">
                    <h3 style="color:red"> Digite o email</h3>
                </c:if>

                <p>Senha <input type="password" name="senha" value=""/></p>
                <c:if test="${semSenha == true}">
                    <h3 style="color:red"> Digite a senha</h3>
                </c:if>
                <c:if test="${invalido == true}">
                    <h3 style="color:red"> Email ou senha invalidos</h3>
                </c:if>
                <input type="submit" value="Logar"/>
            </form>
            <form action="logar" method="GET">
                <p><input type="submit" name="index" value="Voltar"/></p>
            </form>
        </fieldset>
    </body>
</html>

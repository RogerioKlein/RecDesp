<%-- 

    Document   : newjsp
    Created on : 22 de mar. de 2021, 07:56:38
    Author     : rogerio.klein
--%>

<%@page import="entidade.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Categoria categoria = (Categoria) request.getAttribute("objCategoria");

            if (categoria == null) {
                categoria = new Categoria();

                categoria.setId(0);
                categoria.setDescricao("");
                categoria.setSituacao(' ');
            }
        %>
        <h1>Cadastro de categorias</h1>

        <form name='formCategoria' method='post' action='/acao2?param=salvarCategoria'>
            <input type="hidden" name="id" value="<%= categoria.getId()%>">

            Descrição
            <input type='text' name='descricao' value='<%= categoria.getDescricao()%>'>

            <br>
            <br>

            Situação
            <input type='text' name='situacao' value='<%= categoria.getSituacao()%>'>

            <br>
            <br>
            <input type='submit' value='Salvar'>

        </form>
        <h4>Listagem de categorias</h4>
        <%@include file="/listacategoria.jsp" %>
    </body>
</html>

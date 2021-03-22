<%-- 
    Document   : listacategoria
    Created on : 22 de mar. de 2021, 09:30:16
    Author     : rogerio.klein
--%>

<%@page import="dao.CategoriaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            ArrayList<Categoria> categorias = new CategoriaDAO().consultarTodos();
        %>
        <table border='1'>
            <th>Id</th>
            <th>Descrição</th>
            <th>Situação</th>

            <%
                for (int i = 0; i < categorias.size(); i++) {
                    Categoria categ = categorias.get(i);
            %>
            <tr>
                <td><a href='/acao2?param=edCategoria&id=<%= categ.getId()%>'><%= categ.getId()%></a></td>                
                <td><%= categ.getDescricao()%></td>
                <td><%= categ.getSituacao()%></td>
            </tr>
            <%
                }
            %>

        </table>
        <br>
        <br>
        <a href='index.jsp'>Voltar</a>
    </body>
</html>

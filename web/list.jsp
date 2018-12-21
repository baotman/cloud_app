  
<%@page import="java.util.Iterator"%>
<%@page import="bean.Client"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
        </head>
        <body>
                <% String tableName = (String) session.getAttribute("tableName"); %>
            <a href="table?name=<%= tableName %>" >Nouveau</a>
            <table style="border: 1px solid black">
                <thead>
                <th>id</th>
                <th>Contenu</th>
                <th>Options</th>           
            </thead> 
            <tbody> 
                <% Map<String, String> table = (HashMap<String,String>) session.getAttribute("table"); %>
                <% for (Map.Entry<String, String> entry : table.entrySet()) {
                    String id = entry.getKey();
                    String contenu = entry.getValue();%>
                <tr>

                    <td><%= id %></td>
                    <td><%= contenu %></td>
                    <td>
                        <a href="table?name=<%= tableName %>&id=<%= id %>">Detail</a> 
                    </td>
                </tr>
                <% } %>

            </tbody>
        </table>
           </body>
    </html>
</f:view>

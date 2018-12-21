<%-- 
    Document   : detail
    Created on : Dec 18, 2018, 2:37:58 PM
    Author     : MedEM
--%>
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
            <h1>Hello World!</h1>
            <table>
                <thead>
                <th>Colonne</th>
                <th>Valeur</th>
            </thead>
            <tbody>
                <% Map<String, String> columns = (Map<String, String>) session.getAttribute("columns"); %>
                <% for (Map.Entry<String, String> entry : columns.entrySet()) {
                    String key = entry.getKey();
                    String val = entry.getValue();%>
                <tr>
                    <td>
                        <%= key%>
                    </td>
                    <td>
                        <%= val%>
                    </td>
                </tr>
                <% }%>
            </tbody>
        </table>

    </body>
</html>
</f:view>

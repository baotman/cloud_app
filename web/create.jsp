<%-- 
    Document   : create
    Created on : Dec 18, 2018, 1:25:29 PM
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
            <h1><h:outputText value="Hello World!"/></h1>
            <form method="post" action="table?tableName=<%= session.getAttribute("tableName") %>">
                
                <% Map<String, String> columns = (Map<String, String>) session.getAttribute("columns"); %>
                <% for (Map.Entry<String, String> entry : columns.entrySet()) {
                    String name = entry.getKey();
                    String type = entry.getValue();%>
                <label for="<%= name %>"><%= name %></label>
                <% if(type.toLowerCase().equals("int")) { %>
                <input name="<%= name %>" type="number"  id="<%= name %>" />
                <% }else if(type.toLowerCase().equals("text")) {%>
                <textarea name="<%= name %>" id="<%= name %>"></textarea>
                <% }else {%>
                <input name="<%= name %>" type="text"  id="<%= name %>" />
                <% } %>

                <br>
                <% }%>

                <input type="submit" />
            </form>
        </body>
    </html>
</f:view>

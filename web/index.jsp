<%-- 
    Document   : index
    Created on : Dec 17, 2018, 10:47:13 PM
    Author     : MedEM
--%>


<%@page import="java.util.Iterator"%>
<%@page import="bean.Client"%>
<%@page import="java.util.LinkedList"%>
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
   
            <h1><h:outputText value="List des tables "/></h1>
            <table style="border: 1px solid black">
                <thead>
                <th>Name</th>
                <th>Action</th>           
                </thead>
                <tbody> 
                    <% LinkedList<String> tables = (LinkedList<String>) session.getAttribute("tables"); %>
                    <% for (Iterator<String> iterator = tables.iterator(); iterator.hasNext();) {
                        String tableName = iterator.next(); %>
                    <tr>
               
                        <td><%= tableName %></td>
                        <td><a href="Main?table=<%= tableName %>">Access</a> </td>
                    </tr> 
                    <% } %>

                </tbody>
            </table>
        </body>
    </html>
</f:view>

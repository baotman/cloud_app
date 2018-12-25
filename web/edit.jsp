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
            <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
            <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
        </head>
        <body> 

            <div class="container">
                <!--Header-->
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <a class="navbar-brand" href="#">Framework Demo</a>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="Main">Liste des tables</a>
                            </li>                        
                        </ul>
                    </div>
                </nav>
                <div class="row justify-content-center my-2">
                    <div class="col-8">
                        <!--Content-->
                        <% String tableName = (String) session.getAttribute("tableName"); %>
                        <div class="card ">

                            <div class="card-header"><%= tableName %></div>
                            <div class="card-body">

                                <form method="post" action="table?tableName=<%= tableName %>&rowId=<%= (String) session.getAttribute("rowId") %>">

                                    <% Map<String, String> columns = (Map<String, String>) session.getAttribute("columns"); %>
                                    <% for (Map.Entry<String, String> entry : columns.entrySet()) {
                                        String name = entry.getKey();
                                        String value = entry.getValue();%>
                                    <div class="form-group row">
                                        <label for="<%= name %>" class="col-sm-3 col-form-label"><%= name %></label>
                                        <input name="<%= name %>"  class="form-control col-sm-9" type="text"  id="<%= name %>" value="<%= value %>" />
                                    </div>
                                    <% }%>

                                    <input type="submit" class="btn btn-primary mt-2" value="Sauvegarder">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </body>
    </html>
</f:view>

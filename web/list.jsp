  
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
                        <div class="card ">
                            <div class="card-header">Compte</div>
                            <div class="card-body">
                                <% String tableName = (String) session.getAttribute("tableName"); %>
                                <a class="btn btn-outline-primary mb-2" href="table?name=<%= tableName %>&action=new">Ajouter</a>

                                <table  class="table table-bordered">
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
                                                <div class="btn-group">
                                                    <a class="btn btn-outline-info" href="table?name=<%= tableName %>&action=detail&id=<%= id %>">Detail</a> 
                                                    <a class="btn btn-outline-success" href="table?name=<%= tableName %>&action=edit&id=<%= id %>">Modifier</a> 
                                                    <a class="btn btn-outline-danger" href="table?name=<%= tableName %>&action=delete&id=<%= id %>">Supprimer</a>
                                                </div>
                                            </td>
                                        </tr>
                                        <% } %>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </body>
    </html>
</f:view>

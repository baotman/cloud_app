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
                            <div class="card-header">Liste des tables</div>
                            <div class="card-body">
                                <table class="table table-bordered">
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
                                            <td><a class="btn btn-outline-primary " style="width: 100%" href="Main?table=<%= tableName %>">Access</a> </td>
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

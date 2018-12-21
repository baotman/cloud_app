/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AbstractDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Util;

/**
 *
 * @author MedEM
 */
@WebServlet(name = "Table", urlPatterns = {"/table"})
public class Table extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String table = request.getParameter("name");
        System.err.println(table + " " + id); 
        if (id != null) {
            detail(request, response, table, id);
        } else {
            create(request, response, table);
        }
//        processRequest(request, response);
    }

    public void detail(HttpServletRequest request, HttpServletResponse response, String table, String id) {
        try {
            AbstractDAO cnint = new AbstractDAO();
            Map<String, String> columns = cnint.getValues(table, id);
            HttpSession hs = request.getSession();
            hs.setAttribute("columns", columns);
            hs.setAttribute("tableName", table);

            RequestDispatcher rd = request.getRequestDispatcher("/faces/detail.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(HttpServletRequest request, HttpServletResponse response, String table) {
        try {
            AbstractDAO cnint = new AbstractDAO();
//            Map<String,String> columns = cnint.getTableSchema("admins");
            Map<String, String> columns = cnint.getTableSchema(table);
            HttpSession hs = request.getSession();
            hs.setAttribute("columns", columns);
            hs.setAttribute("tableName", table);
            System.err.println("cols" + columns);
            RequestDispatcher rd = request.getRequestDispatcher("/faces/create.jsp");
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AbstractDAO cnint = new AbstractDAO();
        String table = request.getParameter("tableName");
        cnint.add(table,  request); 
       
//        RequestDispatcher rd = request.getRequestDispatcher("Main?table=" + table);
//        rd.include(request, response);
        response.sendRedirect("Main?table=" + table);
//        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

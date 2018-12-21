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

/**
 *
 * @author MedEM
 */
@WebServlet(name = "Main", urlPatterns = {"/Main"})
public class Main extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AbstractDAO conn = new AbstractDAO();
        String table = request.getParameter("table");
        System.err.println("Table " + table  );
        if (table == null) {
            HttpSession hs = request.getSession();
            hs.setAttribute("tables", conn.getTablesName());
            RequestDispatcher rd = request.getRequestDispatcher("/faces/index.jsp");
            rd.forward(request, response);
        } else {
            HttpSession hs = request.getSession();
            try {
                Map<String,String> list = conn.getAllValues(table);
                System.err.println(" " + list);
                System.err.println("");
                hs.setAttribute("table", list);
                hs.setAttribute("tableName", table);

            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/faces/list.jsp");
            rd.forward(request, response);
        }
    }



    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

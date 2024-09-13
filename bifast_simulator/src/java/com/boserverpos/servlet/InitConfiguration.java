/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boserverpos.servlet;

import com.bo.singleton.InitParameterSingleton;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
@WebServlet(name = "InitConfiguration", urlPatterns = {"/InitConfiguration"})
public class InitConfiguration extends HttpServlet {

    private static Logger log = Logger.getLogger(InitConfiguration.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet InitConfiguration</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet InitConfiguration at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public InitConfiguration() {
        super();
//        try {
//            System.out.println("=-=-=-=-=-=-=-=-=-=-= InitConfiguration started =-=-=-=-=-=-=-=-=-=-=");
//            log.info("=-=-=-=-=-=-=-=-=-=-= InitConfiguration started =-=-=-=-=-=-=-=-=-=-=");
//
//            new BuildSocketConnection();
//            Thread.sleep(200);
//            
//            new CleaningDataScheduler().start();
//            Thread.sleep(200);
//            
//        } catch (InterruptedException ex) {
//            log.error(ex.getMessage());
//        }
    }

    @Override
    public void destroy() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-= InitConfiguration destroy =-=-=-=-=-=-=-=-=-=-=");
        log.info("=-=-=-=-=-=-=-=-=-=-= InitConfiguration destroy =-=-=-=-=-=-=-=-=-=-=");
        InitParameterSingleton.getInstance().setStatusThread(false);
    }
}

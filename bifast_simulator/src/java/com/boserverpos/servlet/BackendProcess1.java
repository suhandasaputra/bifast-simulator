/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boserverpos.servlet;

import com.bifast.db.DatabaseProcess;
import com.bifast.process.AccountEnquiryRequest;
import com.bifast.process.MessageRejectResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

/**
 *
 * author MATAJARI MITRA SOLUSI
 */
public class BackendProcess1 extends HttpServlet {

//    Messagein msgin = new Messagein();
//    private Tempmsg tempmsg = new Tempmsg();
    private static Logger log = Logger.getLogger(BackendProcess1.class);

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
            throws ServletException, IOException, JDOMException {
        DatabaseProcess dp = new DatabaseProcess();
        BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String input = "";
        String inputString = "";
        String line = "";
        String resp = "";
        while ((line = in.readLine()) != null) {
            inputString += line;
        }
        inputString = inputString.replace("%20", " ");
        System.out.println("\n" + "Message Incoming :" + inputString + "\n");

        SAXBuilder builder = new SAXBuilder();
        InputStream stream = new ByteArrayInputStream(inputString.getBytes("UTF-8"));
        Document doc = builder.build(stream);
        Element root1 = doc.getRootElement();

        Namespace ns = Namespace.getNamespace("ns", "urn:iso");
        Namespace ns1 = Namespace.getNamespace("ns1", "urn:iso:std:iso:20022:tech:xsd:head.001.001.01");
        Element AppHeader = root1.getChild("AppHdr", ns);
        Element MsgDefIdr = AppHeader.getChild("MsgDefIdr", ns1);
        Element Document = root1.getChild("Document", ns);

        try {
            switch (MsgDefIdr.getValue()) {
                case "pacs.008.001.08":
                    System.out.println("Account Enquiry Request (pacs.008)");
                    input = new AccountEnquiryRequest().process(inputString, Document, ns, ns1);
                    response.getOutputStream().write(input.getBytes());
                    response.getOutputStream().flush();
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            System.out.println("Message Reject Response (admi.002)");
            input = new MessageRejectResponse().process();
            response.getOutputStream().write(input.getBytes());
            response.getOutputStream().flush();
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
        try {
            processRequest(request, response);
        } catch (JDOMException ex) {
            java.util.logging.Logger.getLogger(BackendProcess1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (JDOMException ex) {
            java.util.logging.Logger.getLogger(BackendProcess1.class.getName()).log(Level.SEVERE, null, ex);
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

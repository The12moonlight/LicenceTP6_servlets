/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;


/**
 *
 * @author pierr
 */
public class ShowInState extends HttpServlet {

    private DAO myDAO; // L'objet à utiliser
    private DataSource myDataSource; // La source de données à utiliser

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
            throws ServletException, IOException, DAOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // Initialisation et connection au DAO
            myDataSource = DataSourceFactory.getDataSource();
            myDAO = new DAO(myDataSource);
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowInState</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Customer from "+ request.getParameter("state") +"</h1>");
            // Lecture des résultat dans un tableau
            List<CustomerEntity> listResult;
            listResult = myDAO.customersInState(request.getParameter("state"));
           
            out.println("<table>");
                out.println("<tr>");
                    // Ligne d'entete du tableau
                    out.println("<th> Id </th>");
                    out.println("<th> Name </th>");
                    out.println("<th> Adress </th>");
                out.println("</tr>");
                // Pour chaque ligne dans la list on ajoute une ligne au tableau
                for (int i = 0; i < listResult.size(); i++){
                    out.println("<tr>");
                        out.println("<td>"+ listResult.get(i).getCustomerId() +"</td>" );
                        out.println("<td>"+ listResult.get(i).getName() +"</td>" );
                        out.println("<td>"+ listResult.get(i).getAddressLine1()+"</td>" );
                    out.println("</tr>");
                }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch(DAOException err) {
            System.err.println("Impossible de se connecter a la BD");
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
        } catch (DAOException ex) {
            Logger.getLogger(ShowInState.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (DAOException ex) {
            Logger.getLogger(ShowInState.class.getName()).log(Level.SEVERE, null, ex);
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

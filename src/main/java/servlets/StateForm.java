/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import codeJava.DAODaugther;
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
import simplejdbc.DAO;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author pierr
 */
public class StateForm extends HttpServlet {
    
    private DAODaugther myDAO; // L'objet à utiliser
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             // Initialisation et connection au DAO
            myDataSource = DataSourceFactory.getDataSource();
            myDAO = new DAODaugther(myDataSource);
            
            // On récupe la liste des etats
            List<String> result;
            // On arrive pas a recuperer le resultat
                result = myDAO.recupStateFromBD();
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StateForm</title>");            
            out.println("</head>");
            out.println("<body>");
            // on créer un formulaire pour les lignes des états présent dans la bd
            out.println("<form action=\"ShowInState\" method=\"get\">");
                out.println("<select name=\"state\">");
                    // On crée une ligne dans le formulaire pour chaque etat des clients
                    for (int i = 0; i < result.size(); i++){
                        out.println("<option value=\""+result.get(i)+"\">"
                                + result.get(i) + "</option>");
                    }
                out.println("</select>");
                out.println("<input type =\"submit\" value=\"Envoyer\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } catch (DAOException ex) {
            Logger.getLogger(StateForm.class.getName()).log(Level.SEVERE, null, ex);
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
    }// </editor-fold>

}

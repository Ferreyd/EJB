/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilisateurs.gestionnaires.GestionnaireUtilisateurs;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author Nicolas
 */
@WebServlet(name = "ServletUsers", urlPatterns = {"/ServletUsers"})
public class ServletUsers extends HttpServlet {        
    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;

      /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods. 
     * @param request servlet request 
     * @param response servlet response 
     * @throws ServletExceptioGestionnaireUtilisateursn if a servlet-specific error occurs 
     * @throws IOException if an I/O error occurs 
     */  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        // Pratique pour décider de l'action à faire  
        String action = request.getParameter("action");
        String forwardTo = "";  
        String message = "";
        int startRow = 0;
        int maxRow = 0;
        String startA;
        String maxA;

        if (action != null) {  
            if (action.equals("listerLesUtilisateurs")) {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers(0, 10);                
                request.setAttribute("listeDesUsers", liste);  
                forwardTo = "index.jsp?action=listerLesUtilisateurs";  
                message = "Liste des utilisateurs";  
            }
            else if(action.equals("listerLesUtilisateursUP"))
            {             
                String start = request.getParameter("start");
                String max = request.getParameter("max");
                startRow = Integer.valueOf(start) + 10;
                maxRow = Integer.valueOf(max);
                System.out.println("START = " + start + " STARROW = " + startRow + " MAX =" + max + " MAXROW = " +maxRow);
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers(startRow, maxRow);                
                request.setAttribute("listeDesUsers", liste);  
                forwardTo = "index.jsp?action=listerLesUtilisateurs";  
                message = "Liste des utilisateurs"; 
            }
            else if(action.equals("listerLesUtilisateursDOWN"))
            {
                String start = request.getParameter("start");
                String max = request.getParameter("max");
                startRow = Integer.valueOf(start) - 10;
                maxRow = Integer.valueOf(max);
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers(startRow, maxRow);                 
                request.setAttribute("listeDesUsers", liste);     
                
                forwardTo = "index.jsp?action=listerLesUtilisateurs";  
                message = "Liste des utilisateurs"; 
            }
            else if (action.equals("creerUtilisateursDeTest")) {  
                  gestionnaireUtilisateurs.creerUtilisateursDeTest();
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();  
                request.setAttribute("listeDesUsers", liste);  
                forwardTo = "index.jsp?action=listerLesUtilisateurs";  
                message = "Liste des utilisateurs";  
            } else if(action.equals("chercherParLogin"))
            {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.chercherParLogin(request.getParameter("login"));
                request.setAttribute("listeDesUsers", liste);  
                forwardTo = "index.jsp?action=listerLesUtilisateurs"; 
                message = "Recherche par login";
            } else if(action.equals("supprimerUtilisateur"))
            {
                gestionnaireUtilisateurs.supprimerUtilisateur(request.getParameter("login"));
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();  
                request.setAttribute("listeDesUsers", liste);  
                forwardTo = "index.jsp?action=listerLesUtilisateurs";  
                message = "Liste des utilisateurs";
            }            
           else if(action.equals("creerUnUtilisateur")){
                gestionnaireUtilisateurs.creeUtilisateur(request.getParameter("prenom"), request.getParameter("nom"), request.getParameter("login"));
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            }
           else if(action.equals("updateUtilisateur"))
           {
                gestionnaireUtilisateurs.modifierUtilisateur(request.getParameter("prenom"), request.getParameter("nom"), request.getParameter("login"));
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);  
                forwardTo = "index.jsp?action=listerLesUtilisateurs";  
                message = "Liste des utilisateurs";
           }
           else {  
                forwardTo = "index.jsp?action=todo";  
                message = "La fonctionnalité pour le paramètre " + action + " est à implémenter !\n";  
            }  
        }  
  
        startA = String.valueOf(startRow);
        maxA = String.valueOf(maxRow);
        
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message + maxA + startA);  
        dp.forward(request, response);  
        // Après un forward, plus rien ne peut être exécuté après !  
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

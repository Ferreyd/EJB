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
import javax.servlet.http.HttpSession;
import utilisateurs.gestionnaires.GestionnaireUtilisateurs;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author Nicolas
 */
@WebServlet(name = "ServletUsers", urlPatterns = {"/ServletUsers"})
public class ServletUsers extends HttpServlet {

    int firstRow = 0;
    int maxRow = 10; // nombre maximal d'utilisateur à afficher

    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletExceptioGestionnaireUtilisateursn if a servlet-specific
     * error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Pratique pour décider de l'action à faire  
        String action = request.getParameter("action");
        String form = request.getParameter("form");
        String forwardTo = "";
        String message = "";

        //Object connecte = null;
        HttpSession session = request.getSession();

        int startRow = 0;
        int maxRow = 0;
        int totalRow;

        if (action != null) {

            if (action.equals("listerLesUtilisateurs")) {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers(0, 10);
                totalRow = gestionnaireUtilisateurs.getAllUsers().size();
                request.setAttribute("totalRow", totalRow);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            } else if (action.equals("listerLesUtilisateursUP")) {
                startRow = Integer.valueOf(request.getParameter("start"));
                maxRow = Integer.valueOf(request.getParameter("max"));

                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers(startRow, maxRow);
                totalRow = gestionnaireUtilisateurs.getAllUsers().size();
                request.setAttribute("totalRow", totalRow);

                startRow += 10;
                request.setAttribute("start", startRow);

                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&form=" + form;
                message = "Liste des utilisateurs";

            } else if (action.equals("listerLesUtilisateursDOWN")) {
                startRow = Integer.valueOf(request.getParameter("start"));
                maxRow = Integer.valueOf(request.getParameter("max"));
                if (startRow <= 0) {
                    startRow = 0;
                }
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers(startRow, maxRow);
                totalRow = gestionnaireUtilisateurs.getAllUsers().size();
                request.setAttribute("totalRow", totalRow);
                request.setAttribute("listeDesUsers", liste);
                startRow -= 10;
                if (startRow <= 0) {
                    startRow = 0;
                }
                request.setAttribute("start", startRow);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&form=" + form;
                message = "Liste des utilisateurs";
            } else if (action.equals("creerUtilisateursDeTest")) {
                gestionnaireUtilisateurs.creerUtilisateursDeTest();
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers(0, 10);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            } else if (action.equals("chercherParLogin")) {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.chercherParLogin(request.getParameter("login"));
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&form=" + form;
                message = "Recherche par login";
            } else if (action.equals("supprimerUtilisateur")) {
                gestionnaireUtilisateurs.supprimerUtilisateur(request.getParameter("login"));
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&form=" + form;
                message = "Liste des utilisateurs";
            } else if (action.equals("creerUnUtilisateur")) {

                gestionnaireUtilisateurs.creeUtilisateur(request.getParameter("prenom"), request.getParameter("nom"), request.getParameter("login"), "pass");
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers(firstRow, 10);

                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Liste des utilisateurs";
            } else if (action.equals("updateUtilisateur")) {
                gestionnaireUtilisateurs.modifierUtilisateur(request.getParameter("prenom"), request.getParameter("nom"), request.getParameter("login"));
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&form=" + form;
                message = "Liste des utilisateurs";
            } else if (action.equals("checkConnexion")) {
                boolean existe = gestionnaireUtilisateurs.userExists(request.getParameter("log"), request.getParameter("pass"));
                //System.out.println("EXISTE : " + existe);
                //boolean existe = true;

                if (existe) {
                    session.setAttribute("login", request.getParameter("log"));
                    session.setAttribute("mdp", request.getParameter("pass"));
                    session.setAttribute("connecte", "OK");
                    //connecte = true;
                    message = "Connexion reussie";

                    forwardTo = "index.jsp?action=ok";
                } else {
                    session.setAttribute("connecte", "KO");
                    message = "Connexion failed";
                    forwardTo = "index.jsp?action=ko";
                }
            } else if (action.equals("deconnexion")) {
                session.setAttribute("connecte", "KO");
                //connecte = null;
                message = "Deconnexion reussie";
                forwardTo = "index.jsp?action=bye";
            } else {
                forwardTo = "index.jsp?action=todo";
                message = "La fonctionnalité pour le paramètre " + action + " est à implémenter !\n";
            }
        }
        if (form != null) {
            if (form.contentEquals("AfficheCreerUtilisateur")) {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers(0, 10);
                totalRow = gestionnaireUtilisateurs.getAllUsers().size();
                request.setAttribute("totalRow", totalRow);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&form=AfficheCreerUtilisateur";
                message = "AfficheCreerUtilisateur";
            } else if (form.contentEquals("AfficheDetailUtilisateur")) {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers(0, 10);
                totalRow = gestionnaireUtilisateurs.getAllUsers().size();
                request.setAttribute("totalRow", totalRow);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&form=AfficheDetailUtilisateur";
                message = "Liste des utilisateurs";
            } else if (form.contentEquals("AfficheModifieUtilisateur")) {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers(0, 10);
                totalRow = gestionnaireUtilisateurs.getAllUsers().size();
                request.setAttribute("totalRow", totalRow);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&form=AfficheModifieUtilisateur";
                message = "Liste des utilisateurs";
            } else if (form.contentEquals("AfficheSupprimerUtilisateur")) {
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers(0, 10);
                totalRow = gestionnaireUtilisateurs.getAllUsers().size();
                request.setAttribute("totalRow", totalRow);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&form=AfficheSupprimerUtilisateur";
                message = "Liste des utilisateurs";
            }
        }

        RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message + " START = " + startRow);

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

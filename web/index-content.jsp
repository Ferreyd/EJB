<%-- 
    Document   : index
    Created on : 13 mars 2014, 14:38:52
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
    "http://www.w3.org/TR/html4/loose.dtd">  

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<html>  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/style.css">
        <title>Gestionnaire d'utilisateurs</title>  
    </head>  
    <body> 
        <div class="col-lg-12">  

            <!-- Message qui s'affiche lorsque la page est appelé avec un paramètre http message -->  
            <c:if test="${!empty param['message']}">  
                <h4>Reçu message : ${param.message}</h4>  
            </c:if>  


            <h2>Menu de gestion des utilisateurs</h2>  
            <ul>  
                <h4><a class="btn btn-default" href="ServletUsers?action=listerLesUtilisateurs">Afficher/raffraichir la liste de tous les utilisateurs</a></h4>  
                <p>  
            </ul>  
            <h2>Liste des fonctionnalités à implémenter dans la Servlet (note : après chaque action cette page sera  
                rappelée par la servlet avec la liste des utilisateurs raffraichie et un message de confirmation</h2> 

            <h3><a class="btn btn-default" href="ServletUsers?action=creerUtilisateursDeTest" role="button">Créer 4 utilisateurs de test</a></h3>  
            <div class="col-lg-12">
                <div class="col-lg-6">
                    <h2>Formulaire</h2>
                    <h3>Créer un utilisateur</h3> 
                    <form action="ServletUsers" method="get"> 
                        <label for="nom" class="col-sm-2 control-label">nom</label>
                        <input type="text" class="form-control"placeholder="Nom" id="inputSuccess1" name="nom"/><br>  
                        <input type="text" class="form-control"placeholder="Prenom" name="prenom"/><br>  
                        <input type="text" class="form-control"placeholder="Login" name="login"/><br>  
                        <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                        <input type="hidden" name="action" value="creerUnUtilisateur"/>  
                        <input type="submit" value="Créer l'utilisateur" class="btn btn-primary" name="submit"/>  
                    </form>  


                    <h3>Afficher les détails d'un utilisateur</h3>  
                    <form action="ServletUsers" method="get">  
                        <input type="text" class="form-control"placeholder="Login" name="login"/><br>  
                        <input type="hidden" name="action" value="chercherParLogin"/>  
                        <input type="submit" value="Chercher" class="btn btn-info" name="submit"/>  
                    </form> 

                    <h3>Modifier les détails d'un utilisateur :</h3>  
                    <form action="ServletUsers" method="get">  
                        <input type="text" class="form-control"placeholder="Login" name="login"/><br>  
                        <input type="text" class="form-control"placeholder="Nom" name="nom"/><br>  
                        <input type="text" class="form-control"placeholder="Prenom" name="prenom"/><br>  
                        <input type="hidden" name="action" value="updateUtilisateur"/>  
                        <input type="submit" value="Mettre à jour" class="btn btn-warning" name="submit"/>  
                    </form> 

                    <h3>Supprimer un utilisateur :</h3>  
                    <form action="ServletUsers" method="get">  
                        <input type="text" class="form-control"placeholder="Login" name="login"/><br>  
                        <input type="hidden" name="action" value="supprimerUtilisateur"/>  
                        <input type="submit" value="Supprimer" class="btn btn-danger" name="submit"/>  
                    </form>



                </div>
                <!-- Fin du menu -->  

                <!-- Zone qui affiche les utilisateurs si le paramètre action vaut listerComptes --> 
                <div class="col-lg-6">
                    <c:if test="${param['action'] == 'listerLesUtilisateurs'}" >  
                        <h2>Liste des utilisateurs</h2>  

                        <table border="10" class="table table-bordered">  
                            <!-- La ligne de titre du tableau des comptes -->  
                            <tr>  
                                <td><b>Login</b></td>  
                                <td><b>Nom</b></td>  
                                <td><b>Prénom</b></td>  
                            </tr>  

                            <!-- Ici on affiche les lignes, une par utilisateur -->  
                            <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                            <c:set var="total" value="0"/>  

                            <c:forEach var="u" items="${requestScope['listeDesUsers']}">  
                                <tr>  
                                    <td>${u.login}</td>  
                                    <td>${u.lastname}</td>  
                                    <td>${u.firstname}</td>  
                                    <!-- On compte le nombre de users -->  
                                    <c:set var="total" value="${total+1}"/>  
                                </tr>  
                            </c:forEach>  

                            <!-- Affichage du solde total dans la dernière ligne du tableau -->  
                            <tr><td><b>TOTAL</b></td><td></td><td><b>${total}</b></td><td></td></tr>  
                        </table>  

                    </c:if> 
                </div>
            </div>    
    </body>  
</html> 
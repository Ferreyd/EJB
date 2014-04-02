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
        <title>Gestionnaire d'utilisateurs</title>  
    </head>  
    <body> 
    </div>
    <div class="col-xs-12">
        <!-- Message qui s'affiche lorsque la page est appelé avec un paramètre http message -->  
        <c:if test="${!empty param['message']}">  
            <h4>Reçu message : ${param.message}</h4>  
        </c:if>  

        <h4>Liste des fonctionnalités à implémenter dans la Servlet (note : après chaque action cette page sera  
            rappelée par la servlet avec la liste des utilisateurs raffraichie et un message de confirmation</h4> 

        <h4 class="position"><a class="btn btn-default" href="ServletUsers?action=creerUtilisateursDeTest" role="button">Créer 4 utilisateurs de test</a></h4>  
        <div class="col-xs-1">
            <div id="menu">
                <h2>Menu</h2>
                <a class="btn btn-primary" href="ServletUsers?action=${action}&form=AfficheCreerUtilisateur">Creer un utilisateur</a>
                <a class="btn btn-primary" href="ServletUsers?action=${action}&form=AfficheDetailUtilisateur">Afficher un utilisateur</a>
                <a class="btn btn-primary" href="ServletUsers?action=${action}&form=AfficheModifieUtilisateur">Modifier un utilisateur</a>
                <a class="btn btn-primary" href="ServletUsers?action=${action}&form=AfficheSupprimerUtilisateur">Supprimer un utilisateur</a>

            </div>
        </div>
        <div class="col-xs-11">
            <div class="col-xs-12">
                <div class="col-xs-6">
                    <h2 class="position">Formulaire</h2>

                    <c:if test="${param['form'] == 'AfficheCreerUtilisateur'}" >
                        <h4>Créer un utilisateur</h4>

                        <form action="ServletUsers" method="get"> 
                            <div class="form-group">
                                <label for="nom">Nom</label>
                                <input type="text" class="form-control"placeholder="Nom" id="inputSuccess1" name="nom"/><br> 
                                <label for="prenom">Prenom</label>
                                <input type="text" class="form-control"placeholder="Prenom" name="prenom"/><br> 
                                <label for="login">Login</label>
                                <input type="text" class="form-control"placeholder="Login" name="login"/><br>  
                                <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                                <input type="hidden" name="action" value="creerUnUtilisateur"/>  
                                <input type="submit" value="Créer l'utilisateur" class="btn btn-primary" name="submit"/>
                            </div>
                        </form> 
                    </c:if>

                    <c:if test="${param['form'] == 'AfficheDetailUtilisateur'}" >    
                        <h4>Afficher les détails d'un utilisateur</h4>  
                        <form action="ServletUsers" method="get"> 
                            <div class="form-group">
                                <label for="login">Login</label>
                                <input type="text" class="form-control"placeholder="Login" name="login"/><br>  
                                <input type="hidden" name="action" value="chercherParLogin"/>  
                                <input type="submit" value="Chercher" class="btn btn-info" name="submit"/> 
                            </div>
                        </form> 


                    </c:if>
                    <c:if test="${param['form'] == 'AfficheModifieUtilisateur'}" >   


                        <h4>Modifier les détails d'un utilisateur :</h4>  
                        <form action="ServletUsers" method="get">
                            <div class="form-group">
                                <label for="login">Login</label>
                                <input type="text" class="form-control"placeholder="Login" name="login"/><br>
                                <label for="nom">Nom</label>
                                <input type="text" class="form-control"placeholder="Nom" name="nom"/><br>  
                                <label for="prenom">Prenom</label>
                                <input type="text" class="form-control"placeholder="Prenom" name="prenom"/><br>  
                                <input type="hidden" name="action" value="updateUtilisateur"/>  
                                <input type="submit" value="Mettre à jour" class="btn btn-warning" name="submit"/>  
                            </div>
                        </form> 
                    </c:if>
                    <c:if test="${param['form'] == 'AfficheSupprimerUtilisateur'}" >   
                        <h4>Supprimer un utilisateur :</h4>  
                        <form action="ServletUsers" method="get"> 
                            <div class="form-group">
                                <label for="login">Login</label>
                                <input type="text" class="form-control"placeholder="Login" name="login"/><br>  
                                <input type="hidden" name="action" value="supprimerUtilisateur"/>  
                                <input type="submit" value="Supprimer" class="btn btn-danger" name="submit"/> 
                            </div>
                        </form>


                    </c:if>

                </div>
                <!-- Fin du menu -->  

                <div class="col-xs-6">
                    <c:if test="${param['action'] == 'listerLesUtilisateurs'}" >  
                        <h2 class="position">Liste des utilisateurs</h2>  

                        <!-- Zone qui affiche les utilisateurs si le paramètre action vaut listerComptes --> 

                        <c:if test="${start == null}">
                            <c:set var="start" value="0"/>

                        </c:if>
                        <c:set var="max" value="10"/>
                        <center>
                            <a class="btn btn-primary" href="ServletUsers?action=listerLesUtilisateursDOWN&start=${start}&max=${max}">Precedent</a>
                            <a class="btn btn-primary" href="ServletUsers?action=listerLesUtilisateursUP&start=${start}&max=${max}">Suivant</a>
                        </center>
                        <c:if test="${param['action'] == 'listerLesUtilisateurs'}" > 


                            <h2 class="position">Liste des utilisateurs</h2>  

                            <table id="table" width="100%">  
                                <!-- La ligne de titre du tableau des comptes -->  
                                <tr>  
                                    <td><b>Login</b></td>  
                                    <td><b>Nom</b></td>  
                                    <td><b>Prénom</b></td>  
                                    <td><b>Ville</b></td>  
                                    <td><b>Code postal</b></td> 
                                    <td><b>Telephones</b></td> 
                                </tr>  

                                <!-- Ici on affiche les lignes, une par utilisateur -->  
                                <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                                <c:set var="total" value="${start}"/>  

                                <c:forEach var="u" items="${requestScope['listeDesUsers']}">  
                                    <tr>  
                                        <td>${u.login}</td>  
                                        <td>${u.firstname}</td>  
                                        <td>${u.lastname}</td>  
                                        <td>
                                            <a href="ServletUsers?action=listerUtilisateursParVille&idville=${u.adresse.id}">
                                                ${u.adresse.ville}
                                            </a>
                                        </td>
                                        <td>${u.adresse.codePostal}</td>  
                                        <td> 
                                            <c:forEach var="tel" items="${u.telephones}">
                                            
                                                
                                                    ${tel.numTel} <br/>
                                                
                                           
                                            </c:forEach>
                                        </td>
                                        <!-- On compte le nombre de users -->   
                                        <c:set var="total" value="${total+1}"/>  
                                    </tr>  
                                </c:forEach>  

                                <!-- Affichage du solde total dans la dernière ligne du tableau -->  
                                <tr><td><b>TOTAL</b></td><td></td><td><b>${total}/${totalRow}</b></td><td></td></tr>  
                            </table>  
                        </c:if> 
                    </c:if>
                    <ul>
                        <h4 class="position"><a class="btn btn-default" href="ServletUsers?action=listerLesUtilisateurs">Afficher/raffraichir la liste de tous les utilisateurs</a></h4>
                        <p>  
                    </ul> 
                </div>
            </div>    
        </div>
    </div>   
</body>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<link rel="stylesheet" href="lib/bootstrap-3.1.1-dist/css/bootstrap-theme.css">
<link rel="stylesheet" href="lib/bootstrap-3.1.1-dist/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="lib/bootstrap-3.1.1-dist/css/bootstrap.css">
<link rel="stylesheet" href="resources/style.css">
</html> 
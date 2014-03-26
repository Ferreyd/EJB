<%-- 
    Document   : connexion
    Created on : 24 mars 2014, 15:23:51
    Author     : Nicolas
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>


        <form role="form" action="ServletUsers" method="get" >
            <div class="form-group col-xs-12">
                <h2>Veuillez entrez vos identifiants de connexions ci dessous :</h2>
                <div class="col-xs-3 text-center">
                     <label for="log">Login</label>
                    <input class="form-control"placeholder="Login" type="text" name="log">
                    <label for="pass">Mot de passe</label>
                    <input class="form-control"placeholder="Mot de passe" type="text" name="pass"><br/>    
                    <input type="hidden" name="action" value="checkConnexion">    
                    <input type="submit" name="submit" value="Connexion">
                </div>
            </div>
        </form>



    </body>
</html>

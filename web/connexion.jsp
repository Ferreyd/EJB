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


        <form action="ServletUsers" method="get" >    
            Connexion : 
            Login<input type="text" name="log"><br/>                
            Mot de passe<input type="text" name="pass"><br/>    
            <input type="hidden" name="action" value="checkConnexion">    
            <input type="submit" name="submit" value="Connexion"> 
            ${connecte}

        </form>


    </body>
</html>

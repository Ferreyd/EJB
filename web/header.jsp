<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<c:if test="${connecte==null}">  
    <form action="ServletUsers" method="get" >    
            Connexion : 
            Login<input type="text" name="log"><br/>                
            Mot de passe<input type="text" name="pass"><br/>    
            <input type="hidden" name="action" value="checkConnexion">    
            <input type="submit" name="submit" value="Connexion"> 
           ${connecte}
           
    </form>
</c:if>
<c:if test="${connecte!=null}">
    <a href="ServletUsers?action=deconnexion">Deconnexion</a>
</c:if>

<div>  
    <a href="${pageContext.request.contextPath}">  
        <img src="${pageContext.request.contextPath}/resources/logo.jpg"/>  
    </a>  
</div> 
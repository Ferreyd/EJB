<c:if test="${!connecte}">  
    <form action="ServletConnexion" method="get" id="formconnexion">     
        Connexion : 
        Login<input type="text" name="log"><br/>                 
        Mot de passe<input type="text" name="pass"><br/>     
        <input type="hidden" name="action" value="checkConnexion">     
        <input type="submit" name="submit" value="Connexion">  
    </form>
</c:if>
<c:if test="${connecte}">  
    <a href="ServletConnexion?action=deconnexion">Déconnexion</a>
</c:if>

<div>  
    <a href="${pageContext.request.contextPath}">  
        <img src="${pageContext.request.contextPath}/resources/logo.jpg"/>  
    </a>  
</div> 
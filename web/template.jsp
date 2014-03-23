<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE HTML>  
<html>  
<head>  
<title>${param.title}</title>  
</head>  
<body> 
   
    <jsp:include page="header.jsp"/> 
  
        <jsp:include page="menu.jsp"/>  

        <h1>${param.title}</h1>  

        <jsp:include page="${param.content}.jsp"/>  
 
    <jsp:include page="footer.jsp"/>  
</body>  
</html>  

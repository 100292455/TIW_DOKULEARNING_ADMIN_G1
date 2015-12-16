<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>


		<!-- Información sobre el documento -->
	
		<title>Practica TIW: Administracion - Login</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="e-learning, cursos">
		<meta name="description" content="Adminsitracion de Web de cursos en linea">
		<meta name="author" content="Miguel Solera Martin">
		<link href="<c:url value="/admin_css/empresa-mis-ofertas.css" />" rel="stylesheet" type="text/css" >
		<style type="text/css">
			.error {color: red;}
		</style>
	</head>
	
	<body>
		
	
			<!-- CABECERA-->
			
			<header>
			
				<div class="AreaPersonal">Administracion DOKULEARNING - LOGIN</div>
			
			</header>
			

			<!--CUERPO DE LA PAGINA-->
	
			<section> 
				
				<div id="admin-list">
				
				<!-- Form que llama a login_servlet para autorizar al usuario en la herramienta de administraicon -->
				
					<form method = "post" action = "LoginServlet">
					
							<c:choose>
							<c:when test="${not empty mensajeLogin }">
								<p class="error">${mensajeLogin }</p>
							</c:when>
							<c:otherwise>
								
							</c:otherwise>
						</c:choose>
							<p>Iniciar sesion</p>
							
							<input type="text" name ="usuariologin" id="UsuarioLogin" placeholder="Usuario"> 
							<br>
							<input type="password" name = "passwordlogin" id="ContraseniaLogin"  placeholder="Contraseña">
							<br>
							<input type="submit" id="AccesoLogin" value="Acceder"/>
						
					</form>
			</div>

			</section>		
		
	</body>

</html>
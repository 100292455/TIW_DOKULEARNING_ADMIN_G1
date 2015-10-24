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
		<link href="<c:url value="/admin_css/base.css" />" rel="stylesheet" type="text/css" >
		
	</head>
	
	<body>
		
	
			<!-- CABECERA-->
			
			<header>
			
				<div class="AreaPersonal">Administracion DOKULEARNING - LOGIN</div>
			
			</header>
			

			<!--CUERPO DE LA PAGINA-->
	
			<section> 
				
				<div style="float: left; margin-top: 50px; margin-left: 300px; border: 1px double">
					<div style="float: left; margin-right: 20px; margin-left: 20px; width: 160px">
						<input type="text" id="UsuarioLogin" style="margin-top: 20px; margin-bottom: 20px; width: 150px"
			placeholder="Usuario"> 
						<input type="password" id="ContraseniaLogin" style="margin-bottom: 20px; width: 150px"
			placeholder="Contraseña">
					</div>
					<div style="float: left; margin-right: 20px; margin-top: 38px;">
						<input type="button" id="AccesoLogin" value="Acceder"">
					</div>
			</div>

			</section>		
		
	</body>

</html>
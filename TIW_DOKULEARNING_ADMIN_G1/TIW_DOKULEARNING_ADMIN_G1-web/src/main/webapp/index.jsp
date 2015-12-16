<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>


		<!-- Información sobre el documento -->
	
		<title>Practica TIW: Administracion - Principal</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="e-learning, cursos">
		<meta name="description" content="Adminsitracion de Web de cursos en linea">
		<meta name="author" content="Miguel Solera Martin">
		<link href="<c:url value="/admin_css/empresa-mis-ofertas.css" />" rel="stylesheet" type="text/css" >
		
	</head>
	
	<body>
		
	
			<!-- CABECERA-->
			
			<header>
			
				<div class="AreaPersonal">Administrar DOKULEARNING</div>
							
			</header>
			

			<!--CUERPO DE LA PAGINA -->
	 
			<section> 
			
			<!-- pasamos al servlet Admin_Administrador por request el filtro para poder controlar a donde redirige cada boton -->
				
			<div id="admin-list">	
				
				<form action="Administracion" method="post">
						<input type="hidden" name="filtro" value="AdministrarCursosPendientes">
						<input type="submit" id="volver-validar" value="Administrar Cursos Pendientes de Validar" />
				</form>
				
				<form action="Administracion" method="post">
						<input type="hidden" name="filtro" value="AdministrarPromociones">
						<input type="submit" id="volver-promocion" value="Administrar Promociones" />
				</form>
				
				<form action="Administracion" method="post">
						<input type="hidden" name="filtro" value="AdministrarCursosDestacados">
						<input type="submit" id="volver-destacado" value="Administrar Cursos Destacados" />
				</form>
				
				<form action="Administracion" method="get">
						<input type="hidden" name="filtro" value="Conciliacion">
						<input type="submit" id="volver-conciliacion" value="Conciliar" />
				</form>
				
				<!-- Form para desonectarse de la herramienta de administracion -->

				<form action="Admin_DesconectaLogin" method="post">
						<a href= "Admin_Login.jsp?accion=salir" id="volver-salir">Salir</a>
				</form>
			</div>	
			
			</section>		
		
	</body>

</html>
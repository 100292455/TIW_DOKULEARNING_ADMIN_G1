<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>


		<!-- Información sobre el documento -->
	
		<title>Practica TIW: Administracion - Administrar Destacados</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="e-learning, cursos">
		<meta name="description" content="Adminsitracion de Web de cursos en linea">
		<meta name="author" content="Miguel Solera Martin">
		<link href="<c:url value="/admin_css/base.css" />" rel="stylesheet" type="text/css" >
		<link href="<c:url value="/admin_css/empresa-mis-ofertas.css" />" rel="stylesheet" type="text/css" >
		<style type="text/css">
			.error {color: red;}
		</style>
	</head>
	
	<body>
		
	
			<!-- CABECERA-->
			
			<header>
			
				<div class="AreaPersonal" style="color:white;">Administrar Cursos Destacados</div>
			
			</header>
			

			<!--CUERPO DE LA PAGINA-->
	
			<section> 
			<c:choose>
				<c:when test="${empty cursosDestacados }">
					<!-- cursosDestacados es un atributo metido en el request por eso no es necesario 
					ponerle el prefijo param -->
					<p class="error">Actualmente no hay cursos que destacar.</p>
				</c:when>
				<c:otherwise>
		 		<c:forEach items="${cursosDestacados }" var="curso"> 
				<!-- recorremos todos los objetos de la coleccion cursosDestacados
					y cada objeto devuelto lo asignamos a la variable curso -->
				<ul style="list-style-type: none;">
					<li id = "oferta-ejemplo${curso.ID_curso}">
						<div class = "ofertas-descripcion">
							<p class="oferta-titulo">ID de curso: ${curso.ID_curso}</p>
							<p class = "ofertas-titulo">${curso.DES_titulo }</p>
							<p class = "ofertas-empresa">Impartido por: <!-- TO-DO cambiar COD_prof por nombre del profesor -->${curso.profesor.nombre }</p>
							<p class = "ofertas-resumen">${curso.DES_descripcion }</p>
							<p class = "ofertas-tipo-contrato">${curso.horas } hrs.</p>
							<p class = "ofertas-jornada">${curso.precio_final } euros.</p>
							<c:choose>
								<c:when test="${curso.TIPO_dificultad == 0 }">
									<p class = "ofertas-salario">Nivel basico.</p>
								</c:when>
								<c:when test="${curso.TIPO_dificultad == 1 }">
									<p class = "ofertas-salario">Nivel intermedio.</p>
								</c:when>
								<c:otherwise>
									<p class = "ofertas-salario">Nivel avanzado.</p>
								</c:otherwise>
							</c:choose>
						</div>
						
						<div class = "ofertas-edicion">
							<a style="color:white; font-weight:bold"href="DestacaCurso?IdCurso=${curso.ID_curso}">DESTACAR</a>
						</div>
								
					</li>
				</ul>
				</c:forEach> 
				</c:otherwise>
			</c:choose>
				<form action="Administracion" method="post">
					<div style="float: left; width: 33.5%; margin-top: 14px; margin-bottom: 14px;">
						<input type="hidden" name="filtro" value="IrMenu">
						<button type="submit">
							<div id="menu" class="BotonBaseDoku" style="float: left; margin-left: 6%; top: 0px; border: 4px double red !important">
							Volver al Menu
							</div>
						</button>
					</div>
				</form>
			
			</section>		
		
	</body>

</html>
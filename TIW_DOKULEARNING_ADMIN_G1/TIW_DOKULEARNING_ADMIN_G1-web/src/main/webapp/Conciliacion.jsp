<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>


		<!-- Información sobre el documento -->
	
		<title>Practica TIW: Administracion - Conciliacion</title>
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
			
				<div class="AreaPersonal" style="color:white;">Mostrar Conciliacion</div>
			
			</header>
			

			<!--CUERPO DE LA PAGINA-->
	
			<section> 
			<c:choose>
				<c:when test="${empty matriculasBeneficio }">
					<!-- cursosDestacados es un atributo metido en el request por eso no es necesario 
					ponerle el prefijo param -->
					<p class="error">.No hay ningun alumno matriculado en toda la web</p>
				</c:when>
				<c:otherwise>
				 <table>
                <caption>Lista Conciliacion</caption>
                <thead>
                        <tr>
                                <th>Nombre Profesor</th>
                                <th>Curso</th>
                                <th>Beneficio obtenido</th>
                        </tr>
                </thead>
                <c:forEach items="${matriculasBeneficio }" var="matricula">
                        <tr>
                                <td><c:out value="${matricula.curso.profesor.nombre }"></c:out></td>
                                <td><c:out value="${matricula.curso.DES_titulo }"></c:out></td>
                                <td><c:out value="${matricula.beneficioProfe }"></c:out></td>
                        </tr>
                </c:forEach>
       			 </table>
       			 <c:out value="El beneficio total de la aplicacion es ${beneficioPortalTotal } en la ultima conciliacion"/>
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
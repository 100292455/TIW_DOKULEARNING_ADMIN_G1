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
			
				<div class="AreaPersonal">Administrar Conciliaciones</div>
				
				<form action="Administracion" method="post">
						<input type="hidden" name="filtro" value="IrMenu">
						<input type="submit" id="volver-menu" value="Volver al menu" />
				</form>
			
			</header>
			

			<!--CUERPO DE LA PAGINA-->
	
			<section> 
			
			<div id="lista-destacado">
				<h4>LISTA DE CONCILIACION</h4>
				
			<c:choose>
				<c:when test="${empty matriculasBeneficio }">
					<!-- cursosDestacados es un atributo metido en el request por eso no es necesario 
					ponerle el prefijo param -->
					<p class="error">.No hay ningun alumno matriculado en toda la web</p>
				</c:when>
				<c:otherwise>
					
					<div id = "lista-conciliacion">
					 <table>
	                <thead>
	                        <tr>
	                                <th class ="conciliacion-tit">Nombre Profesor</th>
	                                <th class ="conciliacion-tit">Curso</th>
	                                <th class ="conciliacion-tit">Beneficio obtenido</th>
	                        </tr>
	                </thead>
	                <c:forEach items="${matriculasBeneficio }" var="matricula">
	                        <tr>
	                                <td class ="conciliacion-des"><c:out value="${matricula.curso.profesor.nombre }"></c:out></td>
	                                <td class ="conciliacion-des"><c:out value="${matricula.curso.DES_titulo }"></c:out></td>
	                                <td class ="conciliacion-des"><c:out value="${matricula.beneficioProfe }"></c:out></td>
	                        </tr>
	                </c:forEach>
	       			 </table>
	       			 <c:out value="El beneficio total de la aplicacion es ${beneficioPortalTotal } en la ultima conciliacion"/>
				</div>
				</c:otherwise>
			</c:choose>
			</div>
			
			</section>		
		
	</body>

</html>
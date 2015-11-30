<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>


		<!-- Informacion sobre el documento -->
	
		<title>Practica TIW: Gestion de Promociones</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="e-learning, cursos">
		<meta name="description" content="Web de cursos en linea">
		<meta name="author" content="Jorge Garcia">
		<link href="<c:url value="/admin_css/base.css" />" rel="stylesheet" type="text/css" >
		<link href="<c:url value="/admin_css/usuario-contenido-curso.css" />" rel="stylesheet" type="text/css" >
		<link href="<c:url value="/admin_css/empresa-mis-ofertas.css" />" rel="stylesheet" type="text/css" >
		<link href="<c:url value="http://fonts.googleapis.com/css?family=Ubuntu" />" rel='stylesheet' type='text/css'>
		<link href="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.css" />" rel="stylesheet">
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/external/jquery/jquery.js" />"></script>
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.js" />"></script>
		<style type="text/css">
			.error {color: red;}
		</style>
	</head>
	
	<body>
		
	
			<!-- CABECERA-->
			
			<header>
			
				<div class="AreaPersonal" style="color:white;">Administrar Promociones</div>
			
			</header>
			

			<!--CUERPO DE LA PAGINA-->
	
			<section> 
				
				<div id = "mi-empresa">
				
					<input type = "button"  value = "Anadir Promocion"  class = "anadir-cupon" id="boton-anadir-cupon">
				
					<h4>MIS PROMOCIONES</h4>
					
					
					
					<div id = "ofertas">
				 		<c:if test="${empty promociones }">
							<!-- promociones es un atributo metido en el request por eso no es necesario 
							ponerle el prefijo param -->
							<p class="error">Actualmente no hay promociones.</p>
						</c:if>
				
						<ul>
					<c:forEach items="${promociones }" var="promocion"> 	
							<li id = "oferta-ejemplo">
								<div class = "ofertas-descripcion">
									<p class = "ofertas-titulo">Nombre: ${promocion.nombrePromo }</p>
									<c:choose>
										<c:when test="${promocion.tipo_promo == 0 }">
											<p class = "ofertas-titulo">Descuento Fijo: ${promocion.descuento }% descuento</p>
											
										</c:when>
										<c:otherwise>
											<p class = "ofertas-titulo">Descuento Porcentajes: ${promocion.descuento }% de descuento</p>
										</c:otherwise>
									</c:choose>
									
									<p class = "ofertas-titulo">${promocion.fecha_fin } fin de la promocion.</p>
								</div>

								
						
								
							</li>
						</c:forEach> 
						</ul>				
						<c:if test="${ mensaje != null }">
							<p class="error">${mensaje }</p>
						</c:if>
						
					<!-- Crear un cupon nuevo -->
									 
						<div id="anadir-cupon">

					        <h2> Crear nueva promocion </h2>  

							<form action="AltaPromociones" method="post" id ="anadir-cupon-form" onsubmit="return validarcrearcupon();">
								
								<div> 
								<!-- Precio promocion -->
	
							       	<div id="formul7">  
							        	<p class="nombre">Precio de descuento<span class=aster>*</span>:</p>  
								        <p  id="mens7">No ha especificado precio de descuento*</p>
								        <input  type="text" name="precio" id="precio" value="0">
							        </div>		
							      	
								 <!-- Formato promocion -->

							        <div id="formul8">  
								        <p class="nombre">Formato promocion<span class=aster>*</span>:</p> 
								        <p  id="mens8">No ha especificado el formato de la promocion*</p>
								        <select name="tipo_promocion" id="tipo_cupon">
								        	<option value="-1" selected>Sin especificar</option>
		  									<option value="0">Fijo</option>
		  									<option value="1">Porcentaje</option>
										</select> 
							        </div>
					        	</div>
					        	
								
								
								<!-- Fecha fin de promocion -->
								
								
								<div id="formul9">
						        	<p class="nombre">Fecha fin de promocion<span class=aster>*</span>.</p>  
							        <p  id="mens9">No ha especificado la fecha final de la promocion*</p>  
									<input type="text" name="datepicker" id="datepicker">
								 </div>
								 
					        <!-- Boton aÃ±adir -->

							<input type="submit" id="crear-cupon-boton" value="Anadir" />
							</form>
					   	</div>
					</div>
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
				</div>
				
			</section>

	
			<!--PIE DE PAGINA-->

		<script src ="<c:url value="script/empresa-mis-ofertas2.js" />" type = "text/javascript" ></script>
		
		
	</body>

</html>
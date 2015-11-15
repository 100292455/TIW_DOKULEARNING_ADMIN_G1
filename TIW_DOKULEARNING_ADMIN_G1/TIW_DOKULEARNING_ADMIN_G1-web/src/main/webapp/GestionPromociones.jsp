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
				
					<input type = "button" name = "actualizar" value = "Añadir promocion" id = "añadir-oferta1" class = "añadir-oferta">
				
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
						
						<input type = "button" name = "actualizar" value = "Añadir promocion" id = "añadir-oferta2" class = "añadir-oferta">
						
						<c:if test="${ mensaje != null }">
							<p class="error">${mensaje }</p>
						</c:if>
						
						<div id="añadir">

					        <h2> Añade una nueva Promocion</h2>  
			
							<form action="AltaPromociones" method="post">
							
					        <!-- Titulo de la promocion -->

					       	<div id="formul1">  
					        	<p class="nombre">Nombre de la promocion<span class=aster>*</span>:</p>
						        <p  id="mens1">No ha especificado el nombre de la Promocion*</p>
						        <input type="text" name="nombrePromo" id="nombrePromo"/>
					      	</div>

					        <!-- Precio del descuento -->

					        <div id="formul2">  
					        	<p class="nombre">Precio de descuento<span class=aster>*</span>:</p>  
						        <p  id="mens2">No ha especificado el precio de descuento*</p>
						        <input  type="text" name="precio" id="precio"/>
					        </div>

					        <!-- Tipo de descuento -->

					        <div id="formul3">  
						        <p class="nombre">Tipo de promocion<span class=aster>*</span>:</p> 
						        <select name="tipo_promocion" id="tipo_promocion">
						        	
  									<option value="0">Precio Fijo</option>
  									<option value="1">Porcentaje</option>
  								</select> 
					        </div>

					        <!-- Fecha fin promo -->

					        <div id="formul4">  
					        	<p class="nombre">Fecha final de Promocion<span class=aster>*</span>:</p>  
						        <p  id="mens4">No ha especificado la fecha final de la promocion*</p>
								<textarea name="fecha_fin" id="fecha_fin"></textarea>
					        </div>

							<div id="formul6">  
					        	<p class="nombre">Recuerde que solo puede haber una promocion activa.<span class=aster>*</span>.</p>  
					        </div>

					        <!-- Boton aÃ±adir -->

							<input type="submit" id="añadirboton" value="Añadir" />
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

		<script src ="<c:url value="script/cupones.js" />" type = "text/javascript" ></script>
		
		
	</body>

</html>
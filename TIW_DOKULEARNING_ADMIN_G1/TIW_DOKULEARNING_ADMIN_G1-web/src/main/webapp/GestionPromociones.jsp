<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>


		<!-- Información sobre el documento -->
	
		<title>Practica TIW: Gestion de cursos</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="e-learning, cursos">
		<meta name="description" content="Web de cursos en linea">
		<meta name="author" content="Miguel Solera Martin">
		<link href="<c:url value="/style/empresa-mis-ofertas.css" />" rel="stylesheet" type="text/css" >
		<link href="<c:url value="http://fonts.googleapis.com/css?family=Ubuntu" />" rel='stylesheet' type='text/css'>
		<link href="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.css" />" rel="stylesheet">
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/external/jquery/jquery.js" />"></script>
		<script src="<c:url value="/script/jquery-ui-1.11.2.custom/jquery-ui.js" />"></script>
		<style type="text/css">
			.error {color: red;}
		</style>
	</head>
	
			<!-- ******************** TO-DO ******************** -->
			<!-- RESCATAR ID DEL PROFESOR CORRESPONDIENTE Y PASARLO EN EL POST A AltaCursosServlet y BajaCursosServlet -->
			<!-- *********************************************** -->
	
	<body>
		
	
			<!-- CABECERA-->
			
			<header>
			
				<div id = "cabecera-logo">
				
					<a href="index-empresa.jsp">	
					
						<img class = "cabecera" src="images/logo.png" alt="Error en la imagen">    
				
						<h1 class = "cabecera">DOKULEARNING</h1>
				
					</a>
			
				</div>
				
				<nav>
					<ul>
						<li id = "menu-empresa"><a href = "mi-empresa.jsp">MI PERFIL</a></li>
						<li id = "menu-ofertas"><a href = "#">MIS CURSOS</a></li>
					</ul>	
				</nav>
			
				<div id = "cabecera-sesion">
			
					<img class = "cabecera-sesion" src="images/index/microsoft.jpg" alt="Error en la imagen"> 
				
					<div id = "cabecera-sesion-men">
						<p class = "cabecera-sesion">Microsoft Inc.</p>
						<a href = "index.jsp">Cerrar sesión</a>
							
					</div>
					
				</div>
			
			</header>
			

			<!--CUERPO DE LA PAGINA-->
	
			<section> 
				
				<div id = "mi-empresa">
				
					<input type = "button" name = "actualizar" value = "Añadir curso" id = "añadir-oferta1" class = "añadir-oferta">
				
					<h4>MIS PROMOCIONES</h4>
					
					
					
					<div id = "ofertas">
				 		<c:if test="${empty promociones }">
							<!-- cursos es un atributo metido en el request por eso no es necesario 
							ponerle el prefijo param -->
							<p class="error">Actualmente no hay promociones.</p>
						</c:if>
				
						<ul>
					<c:forEach items="${promociones }" var="promocion"> 	
							<li id = "oferta-ejemplo">
								<div class = "ofertas-descripcion">
								<!-- TO-DO
										Esto se deja para pruebas, 
										Hay que mostrar solo aquellos cursos cuyo TIPO_estado == 2 -->
									<p class = "ofertas-titulo">Nombre: ${promocion.nombrePromo }</p>
									<c:choose>
										<c:when test="${promocion.tipo_promo == 0 }">
											<p class = "ofertas-titulo">Descuento Fijo: ${promocion.descuento }€ descuento</p>
											
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
						
						<input type = "button" name = "actualizar" value = "Añadir curso" id = "añadir-oferta2" class = "añadir-oferta">
						
						<c:if test="${ mensaje != null }">
							<p class="error">${mensaje }</p>
						</c:if>
						
						<div id="añadir">

					        <h2> Añade un nuevo curso </h2>  
							
							<!-- ******************* TO-DO ******************* -->
					        <!-- ******* AÑADIR FOTOGRAFIA DEL CURSO ********* -->
					        <!-- http://www.tutorialspoint.com/jsp/jsp_file_uploading.htm -->
					        <!-- INCLUIR EN WEB.XML
					        <web-app>
								....
								<context-param> 
   									<description>Location to store uploaded file</description> 
    								<param-name>file-upload</param-name> 
    								<param-value>
         								c:\apache-tomcat-5.5.29\webapps\data\
     								</param-value> 
								</context-param>
								....
								
							</web-app> 
							-->
							
							<!-- ***************************************************************** -->
			
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
					
							<!-- Precio de matricula 

					        <div id="formul5">  
					        	<p class="nombre">Precio de matricula<span class=aster>*</span>:</p>  
						        <p  id="mens5">No ha especificado precio de matricula*</p>
						        <input  type="text" name="precio" id="añadir-precio" placeholder = "Precio de matricula"/>
					        </div>-->

							<div id="formul6">  
					        	<p class="nombre">Recuerde que solo puede haber una promocion activa.<span class=aster>*</span>.</p>  
					        </div>

					        <!-- Boton añadir -->

							<input type="submit" id="añadirboton" value="Añadir" />
							</form>
					   	</div>
					</div>
					
				</div>
				
				<div id = "seguidores">
				
					<h5> seguidores </h5>
					
					<ul>
					
						<li>
							<img class = "seguidores-foto" src = "images/famosos/gates.jpg" alt = "Error en la imagen">
							<p class = "seguidores-info">Bill Gates</p></li>
					
					</ul>
				
				</div>
					

			</section>

	
			<!--PIE DE PAGINA-->

			<footer>  
				<ul id="pie">
					<li> Copyright © jooglecam.com</li>
				 	<li><a href="#">Aviso legal	</a></li>
				 	<li><a href="#">Privacidad 	 </a></li>
				 	<li><a href="#">Política de cookies	</a></li>
				 	<li><a href="#">Accesibilidad  </a></li>
				 	<li><a href="#">Contacto </a></li>
				 	<li><a href="#">Ayuda  </a></li>
				</ul>
			</footer>
		
		<script src ="<c:url value="script/empresa-mis-ofertas.js" />" type = "text/javascript" ></script>
		
		
	</body>

</html>
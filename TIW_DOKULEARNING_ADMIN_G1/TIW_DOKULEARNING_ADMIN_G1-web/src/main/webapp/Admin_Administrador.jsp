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
		<link href="<c:url value="/admin_css/base.css" />" rel="stylesheet" type="text/css" >
		
	</head>
	
	<body>
		
	
			<!-- CABECERA-->
			
			<header>
			
				<div class="AreaPersonal">Administracion DOKULEARNING</div>
			
			</header>
			

			<!--CUERPO DE LA PAGINA -->
	 
			<section> 
			
				<form action="Administracion" method="post">				
					<div class="AceptarNuevo">
						<input type="hidden" name="filtro" value="CursosPendientes">
						<button type="submit">
							<div id="IrAdministrarCursosPendientes" style="height:auto;margin-top:10%;" class="BotonBaseDoku">
							Administrar Cursos Pendientes de Validar
							</div>
						</button>
					</div>
				</form>
				<form action="Administracion" method="post">				
					<div class="AceptarNuevo">
						<input type="hidden" name="filtro" value="GestionCupones">
						<button type="submit">
							<div id="IrGestionarCupones" style="height:auto;margin-top:10%;" class="BotonBaseDoku">
							Gestionar Cupones
							</div>
						</button>
					</div>
				</form>
				<!-- TO-DO -->
				<form action="Admin_DesconectaLogin" method="post">
					<div style="float: left; width: 33.5%; margin-top: 14px; margin-bottom: 14px;">
						<button type="submit">
							<div id="logout" class="BotonBaseDoku" style="float: left; margin-left: 6%; top: 0px; border: 4px double red !important">
							Desconectar
							</div>
						</button>
					</div>
				</form>
				<!-- ******************** -->
			</section>		
		
	</body>

</html>
						
//metodo para ajustar la longitud de los seguidores
			
	var largo = $("#mi-empresa").height();
	largo=largo-200;
	
	$("#seguidores > ul").height(largo);
			
			
//metodo para ejecutar el editor de texto para la descripcion de empresa
			

	$('#informacion-empresa-descripcion').jqte();

	// settings of status
	var jqteStatus = true;
	$(".status").click(function()
	{
		jqteStatus = jqteStatus ? false : true;
		$('.jqte-test').jqte({"status" : jqteStatus})
	});
				
				
//metodo para cambiar la imagen

	document.getElementById('picField').onchange = function (evt) {
	    var tgt = evt.target || window.event.srcElement,
	        files = tgt.files;

	    // FileReader support
	    if (FileReader && files && files.length) {
	        var fr = new FileReader();
	        fr.onload = function () {
	            document.getElementById("imagen-empresa").src = fr.result;
	        }
	        fr.readAsDataURL(files[0]);
	    }

	    // Not supported
	    else {
	        // fallback -- perhaps submit the input to an iframe and temporarily store
	        // them on the server until the user's session ends.
	    }
	}
	
//vista previa

	$(function() {
	    $( "#dialog" ).dialog({
				width: 1000,
				height: 500,
		      autoOpen: false,
		      show: {
		        effect: "blind",
		        duration: 1000
		      },
		      hide: {
		        effect: "clip",
		        duration: 1000,
		      }
		
		    });

		    $( "#opener" ).click(function() {
				$(".eleminar-vista-previa").remove();
		      $( "#dialog" ).dialog( "open" );
			$(function() {
				var nombre = $("#edit-nombre").val();
				var empresa= $("#edit-empresa").val();
				var direccion = $("#edit-direccion").val();
				var ciudad = $("#edit-ciudad").val();
				var telefono = $("#edit-telefono").val();
				var email = $("#edit-email").val();
				var representante = $("#edit-representante").val();
				var descripcion = $(".jqte_editor").clone().addClass("eleminar-vista-previa");
				var img = $("#imagen-empresa").attr("src");

				$("#vista-previa-nombre").text(nombre);
				$("#vista-previa-empresa").text(empresa);
				$("#vista-previa-direccion").text(direccion);
				$("#vista-previa-ciudad").text(ciudad);
				$("#vista-previa-telefono").text(telefono);
				$("#vista-previa-email").text(email);
				$("#vista-previa-representante").text(representante);
				$("#dialog").append(descripcion);
				$("#imagen-vista-previa").attr("src", img);

			});
		    });
	  });
	

	

			
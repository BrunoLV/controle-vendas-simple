function listar() {
	$.ajax({
		url : "/controle-vendas-simple/marca/listar",
		method : "GET",
		success : function(resultado) {
			var table = $("#marcasTable tbody");
			$(".linhaInformacao").remove();
			$.each(resultado, function(index, marca) {
				console.log("index: " + index + ", id: " + marca.idMarca + ", nome: " + marca.nome);
				var botaoDeletar = "<td><button type='button' class='btn btn-danger' onclick='deletar(" + marca.idMarca + ")'>"+"<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></button></td>";
				var botaoEditar = "<td><button type='button' class='btn btn-primary' onclick='editar(" + marca.idMarca + ")'>"+"<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></button></td>";
				var html = "<tr class='linhaInformacao'>"+botaoEditar + botaoDeletar+"<td>" + marca.idMarca + "</td><td>" + marca.nome + "</td></tr>";
				table.append(html);
			});
		}
	});
}

function editar(idEdit) {
	$.ajax({
		url : "/controle-vendas-simple/marca",
		method : "GET",
		data : { id : idEdit },
		success : function(resultado) {
			$("#marcaId").val(resultado.id);
			$("#marcaNome").val(resultado.nome);
		}
	});
}

function deletar(idDelete) {
	$.ajax({
		url : "/controle-vendas-simple/marca?id=" + idDelete,
		method : "DELETE",
		success : function() {
			var dialog = $((document).createElement("div"));
			dialog.html("Marca excluída com sucesso!");
			dialog.dialog({
				modal: true,
				dialogClass : "no-close",
				title : "Inclusão de Marca",
				buttons : [ {
					text : "Ok",
					icons : {
						primary : "ui-icon-check"
					},
					click : function() {
						$(this).dialog("close");
					}
				} ]
			});
			listar();
		}
	});
	return false;
}

$("#botaoListar").on("click", listar);

function enviarPost(marca) {
	$.ajax({
		url : "/controle-vendas-simple/marca",
		method : "POST",
		data : marca,
		success : function() {
			var dialog = $((document).createElement("div"));
			dialog.html("Marca incluída com sucesso!");
			dialog.dialog({
				modal: true,
				dialogClass : "no-close",
				title : "Inclusão de Marca",
				buttons : [ {
					text : "Ok",
					icons : {
						primary : "ui-icon-check"
					},
					click : function() {
						$(this).dialog("close");
					}
				} ]
			});
			listar();
		}
	});
}

$("#botaoGravar").on("click", function(event) {
	event.preventDefault();
	var marca = {};
	if ($("marcaId").val() != "") {
		marca = { id : $("#marcaId").val(), nome : $("#marcaNome").val() };
	} else {
		marca = { nome : $("#marcaNome").val() };
	}
	enviarPost(marca);
});

$(document).ready(listar);

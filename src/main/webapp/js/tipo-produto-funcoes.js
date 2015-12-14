function listar() {
	$.ajax({
		url : "/controle-vendas-simple/tipoProduto/listar",
		method : "GET",
		success : function(resultado) {
			var table = $("#tiposProdutoTable tbody");
			$(".linhaInformacao").remove();
			$.each(resultado, function(index, tipo) {
				console.log("index: " + index + ", id: " + tipo.idTipo + ", nome: " + tipo.nome);
				var botaoDeletar = "<td><button type='button' class='btn btn-danger' onclick='deletar(" + tipo.idTipo + ")'>"+"<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></button></td>";
				var botaoEditar = "<td><button type='button' class='btn btn-primary' onclick='editar(" + tipo.idTipo + ")'>"+"<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></button></td>";
				var html = "<tr class='linhaInformacao'>" + botaoEditar + botaoDeletar + "<td>" + tipo.idTipo + "</td><td>" + tipo.nome + "</td></tr>";
				table.append(html);
			});
		}
	});
}

function editar(idEdit) {
	$.ajax({
		url : "/controle-vendas-simple/tipoProduto",
		method : "GET",
		data : { id : idEdit },
		success : function(resultado) {
			$("#tipoProdutoId").val(resultado.id);
			$("#tipoProdutoNome").val(resultado.nome);
		}
	});
}

function deletar(idDelete) {
	$.ajax({
		url : "/controle-vendas-simple/tipoProduto?id=" + idDelete,
		method : "DELETE",
		success : function() {
			var dialog = $((document).createElement("div"));
			dialog.html("Tipo excluído com sucesso!");
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
		url : "/controle-vendas-simple/tipoProduto",
		method : "POST",
		data : marca,
		success : function() {
			var dialog = $((document).createElement("div"));
			dialog.html("Tipo de Produto incluído com sucesso!");
			dialog.dialog({
				modal: true,
				dialogClass : "no-close",
				title : "Inclusão de Tipo de Produto",
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
	var tipo = {};
	if ($("tipoProdutoId").val() != "") {
		tipo = { id : $("#tipoProdutoId").val(), nome : $("#tipoProdutoNome").val() };
	} else {
		tipo = { nome : $("#tipoProdutoNome").val() };
	}
	enviarPost(tipo);
});

$(document).ready(listar);

function listar() {
	$.ajax({
		url : "/controle-vendas-simple/produto/listar",
		method : "GET",
		success : function(resultado) {
			var table = $("#produtosTable tbody");
			$(".linhaInformacao").remove();
			$.each(resultado, function(index, produto) {
				console.log("index: " + index + ", id: " + produto.id + ", nome: " + produto.nome);
				var botaoDeletar = "<td><button type='button' class='btn btn-danger' onclick='deletar(" + produto.id + ")'>"+"<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></button></td>";
				var botaoEditar = "<td><button type='button' class='btn btn-primary' onclick='editar(" + produto.id + ")'>"+"<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></button></td>";
				var html = "<tr class='linhaInformacao'>" + 
					botaoEditar + 
					botaoDeletar+
					"<td>" + produto.id + "</td>" +
					"<td>" + produto.nome + "</td>" +
					"<td>" + produto.valor + "</td>" +
					"<td>" + produto.quantidadeMinima + "</td>" +
					"<td>" + produto.tipo.nome + "</td>" +
					"<td>" + produto.marca.nome + "</td></tr>";
				table.append(html);
			});
		}
	});
}

function monstarTipos() {
	$.ajax({
		url : "/controle-vendas-simple/tipoProduto/listar",
		method : "GET",
		success : function(resultado) {
			var selectTipo = $("#selectTipoProduto");
			$(".linhaTipo").remove();
			$.each(resultado, function(index, tipo) {
				var option = $("<option></option>", {value: tipo.id, text: tipo.nome});
				selectTipo.append(option);
			});
		}
	});
}

function monstarMarcas() {
	$.ajax({
		url : "/controle-vendas-simple/marca/listar",
		method : "GET",
		success : function(resultado) {
			var selectMarca = $("#selectMarcaProduto");
			$(".linhaMarca").remove();
			$.each(resultado, function(index, marca) {
				var option = $("<option></option>");
				selectMarca.append(option.attr("value", marca.id).text(marca.nome));
			});
		}
	});
}

function deletar(idDelete) {
	$.ajax({
		url : "/controle-vendas-simple/produto?id=" + idDelete,
		method : "DELETE",
		success : function() {
			var dialog = $((document).createElement("div"));
			dialog.html("Produto excluída com sucesso!");
			dialog.dialog({
				modal: true,
				dialogClass : "no-close",
				title : "Remoção de Produto",
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

function editar(idEdit) {
	$.ajax({
		url : "/controle-vendas-simple/produto",
		method : "GET",
		data : { id : idEdit },
		success : function(resultado) {
			$("#produtoId").val(resultado.id);
			$("#produtoNome").val(resultado.nome);
			$("#valorProduto").val(resultado.valor);
			$("#quantidadeMinimaProduto").val(resultado.quantidadeMinima);
			$("#selectTipoProduto option").each(function(){
				if (resultado.tipo.nome != $(this).val()) {
					$(this).attr("selected", false);
				} else {
					$(this).attr("selected", true);
				}
			});
			$("#selectMarcaProduto option").each(function(){
				if (resultado.marca.nome != $(this).val()) {
					$(this).attr("selected", false);
				} else {
					$(this).attr("selected", true);
				}
			});
		}
	});
}

$(document).ready(function(){
	monstarTipos();
	monstarMarcas();
	listar();
});

function enviarPost(produto) {
	$.ajax({
		url : "/controle-vendas-simple/produto",
		method : "POST",
		data : produto,
		success : function() {
			var dialog = $((document).createElement("div"));
			dialog.html("Produto incluído com sucesso!");
			dialog.dialog({
				modal: true,
				dialogClass : "no-close",
				title : "Inclusão de Produto",
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
	var produto = {};
	if ($("produtoId").val() != "") {
		produto = { 
				id : $("#produtoId").val(), 
				nome : $("#produtoNome").val(), 
				valor : $("#valorProduto").val(), 
				quantidadeMinima : $("#quantidadeMinimaProduto").val(),
				idTipoProduto : $("#selectTipoProduto option:selected").val(),
				idMarca : $("#selectMarcaProduto option:selected").val()
				};
	} else {
		produto = { 
				nome : $("#produtoNome").val(), 
				valor : $("#valorProduto").val(), 
				quantidadeMinima : $("#quantidadeMinimaProduto").val(),
				idTipoProduto : $("#selectTipoProduto option:selected").val(),
				idMarca : $("#selectMarcaProduto option:selected").val()
				};
	}
	console.log(produto);
	enviarPost(produto);
});
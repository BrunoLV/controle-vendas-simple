function listar() {
	$.ajax({
		url : "/controle-vendas-simple/produto/listar",
		method : "GET",
		success : function(resultado) {
			var table = $("#produtosTable tbody");
			$(".linhaInformacao").remove();
			$.each(resultado, function(index, produto) {
				var botaoDeletar = "<td>" +
						"<button type='button' class='btn btn-danger' onclick='deletar(" + 
						produto.id + 
						")'>"+
						"<span class='glyphicon glyphicon-remove' aria-hidden='true'></span>" +
						"</button>" +
						"</td>";
				var botaoEditar = "<td>" +
						"<button type='button' class='btn btn-primary' onclick='editar(" + 
						produto.id + 
						")'>"+
						"<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>" +
						"</button>" +
						"</td>";
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
				var option = $("<option></option>");
				selectTipo.append(option.attr("value", tipo.idTipo).text(tipo.nome));
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
				selectMarca.append(option.attr("value", marca.idMarca).text(marca.nome));
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
			$("#selectTipoProduto").find("option").each(function(){
				$(this).attr("selected", resultado.tipo.idTipo == $(this).val());
			});
			$("#selectMarcaProduto").find("option").each(function(){
				$(this).attr("selected", resultado.marca.idMarca == $(this).val());
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
				idTipoProduto : $("#selectTipoProduto").val(),
				idMarca : $("#selectMarcaProduto").val()
				};
	} else {
		produto = { 
				nome : $("#produtoNome").val(), 
				valor : $("#valorProduto").val(), 
				quantidadeMinima : $("#quantidadeMinimaProduto").val(),
				idTipoProduto : $("#selectTipoProduto").val(),
				idMarca : $("#selectMarcaProduto").val()
				};
	}
	enviarPost(produto);
});
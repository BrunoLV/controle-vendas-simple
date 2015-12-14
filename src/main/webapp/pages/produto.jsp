<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Produto</title>

<link rel="stylesheet" href="<c:out value='${pageContext.request.contextPath}/css/normalize.css'/>" />
<link rel="stylesheet" href="<c:out value='${pageContext.request.contextPath}/css/smoothness/jquery-ui.min.css'/>" />
<link rel="stylesheet" href="<c:out value='${pageContext.request.contextPath}/css/smoothness/theme.css'/>" />
<link rel="stylesheet" href="<c:out value='${pageContext.request.contextPath}/css/starter-template.css'/>" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

<style type="text/css">
.no-close .ui-dialog-titlebar-close {
	display: none;
}
</style>
</head>
<body style="background-color: #61A2FF;">

	<jsp:include page="/layout/menu.jsp"></jsp:include>

	<br />

	<div class="container">

		<div id="starter-template">

			<div class="panel panel-primary" id="cadastroProdutoDiv">
				<div class="panel-heading">
					<h3 class="panel-title">Cadastro de Produtos</h3>
				</div>
				<div class="panel-body">
					<form role="form" id="produtoCadastroForm">
						<input id="produtoId" type="hidden" />
						<fieldset>
							<div class="row">
								<div class="col-xs-12">
									<div class="form-group">
										<label for="produtoNome">Nome:</label> 
										<input id="produtoNome" class="form-control" type="text" style="width: 100%;" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-6">
									<div class="form-group">
										<label for="selectTipoProduto">Tipo:</label>
										<select id="selectTipoProduto" class="form-control selectTipoProduto" style="width: 100%;"></select>
									</div>
								</div>
								
								<div class="col-xs-6">
									<div class="form-group">
										<label for="selectMarcaProduto">Marca:</label> 
										<select id="selectMarcaProduto" class="form-control selectMarcaProduto" style="width: 100%;"></select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-3">
									<div class="form-group">
										<label for="valorProduto">Valor:</label>
										<input id="valorProduto" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control" type="number" style="width: 100%;" />
									</div>
								</div>
								<div class="col-xs-3">
									<div class="form-group">
										<label for="quantidadeMinimaProduto">Quantidade minima:</label>
										<input id="quantidadeMinimaProduto" class="form-control" type="number" style="width: 100%;" />
									</div>
								</div>
							</div>
						</fieldset>
						<br />
						<button type="button" class="btn btn-primary" id="botaoGravar">
							<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
							Gravar
						</button>
					</form>
				</div>
			</div>

			<div class="panel panel-primary" id="listagemProdutoDiv">
				<div class="panel-heading">
					<h3 class="panel-title">Lista de Produtos</h3>
				</div>
				<table class="table-condensed table-hover table-striped" id="produtosTable">
					<tr class="linhaCabecalho">
						<th colspan="2" />
						<th>ID</th>
						<th>NOME</th>
						<th>VALOR</th>
						<th>QUANTIDADE MINIMA</th>
						<th>TIPO</th>
						<th>MARCA</th>
					</tr>
				</table>
			</div>

		</div>

	</div>

	<script src="<c:out value='${pageContext.request.contextPath}/js/jquery.min.js'/>"></script>
	<script src="<c:out value='${pageContext.request.contextPath}/js/jquery-ui.min.js'/>"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="<c:out value='${pageContext.request.contextPath}/js/produto-funcoes.js'/>"></script>

</body>
</html>
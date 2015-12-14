package com.valhala.controle.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.valhala.controle.domain.Marca;
import com.valhala.controle.domain.Produto;
import com.valhala.controle.domain.Tipo;
import com.valhala.controle.service.ProdutoService;

@WebServlet("/produto/*")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProdutoService service = new ProdutoService((Connection) request.getAttribute("CONNECTION"));

		boolean encontrouContexto = false;
		boolean executaConsultaListagem = false;

		List<String> pedacosUrl = Arrays.asList(request.getRequestURI().split("/"));
		for (Iterator<String> iterator = pedacosUrl.iterator(); iterator.hasNext();) {
			String pedaco = iterator.next();
			if (pedaco.equals("produto")) {
				encontrouContexto = true;
				continue;
			}
			if (encontrouContexto) {
				if (pedaco.equals("listar")) {
					executaConsultaListagem = true;
					break;
				}
			}
		}
		Object objeto = null;
		if (executaConsultaListagem) {
			objeto = service.findAll();
		} else if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
			objeto = service.findById(Long.valueOf(request.getParameter("id")));
		}
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setStatus(200);
		response.getWriter().write(gson.toJson(objeto));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdutoService service = new ProdutoService((Connection) request.getAttribute("CONNECTION"));
		
		Produto produto = new Produto();
		if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
			produto.setId(Long.valueOf(request.getParameter("id")));
		}
		produto.setNome(request.getParameter("produtoNome"));
		produto.setValor(new BigDecimal(request.getParameter(("produtoValor"))));
		produto.setQuantidadeMinima(Integer.valueOf(request.getParameter("quantidadeMinima")));
		produto.setMarca(new Marca(Long.valueOf(request.getParameter("idMarca"))));
		produto.setTipo(new Tipo(Long.valueOf(request.getParameter("idTipoProduto"))));
		if (produto.getId() != null) {
			service.update(produto);
		} else {
			service.save(produto);
		}
		response.setStatus(200);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		ProdutoService service = new ProdutoService((Connection) request.getAttribute("CONNECTION"));
		service.delete(id);
		response.setStatus(200);
	}

}

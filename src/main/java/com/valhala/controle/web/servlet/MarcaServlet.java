package com.valhala.controle.web.servlet;

import java.io.IOException;
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
import com.valhala.controle.service.MarcaService;

@WebServlet("/marca/*")
public class MarcaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MarcaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MarcaService service = new MarcaService((Connection) request.getAttribute("CONNECTION"));

		boolean encontrouContexto = false;
		boolean executaConsultaListagem = false;

		List<String> pedacosUrl = Arrays.asList(request.getRequestURI().split("/"));
		for (Iterator<String> iterator = pedacosUrl.iterator(); iterator.hasNext();) {
			String pedaco = iterator.next();
			if (pedaco.equals("marca")) {
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
		MarcaService service = new MarcaService((Connection) request.getAttribute("CONNECTION"));
		Marca marca = new Marca();
		if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
			marca.setId(Long.valueOf(request.getParameter("id")));
		}
		marca.setNome(request.getParameter("nome"));
		if (marca.getId() != null) {
			service.update(marca);
		} else {
			service.save(marca);
		}
		response.setStatus(200);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		MarcaService service = new MarcaService((Connection) request.getAttribute("CONNECTION"));
		service.delete(id);
		response.setStatus(200);
	}

}

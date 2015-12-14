package com.valhala.controle.web.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.valhala.controle.db.util.ConnectionFactory;

@WebFilter(urlPatterns = "/*")
public class OpenConnectionFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try (Connection connection = ConnectionFactory.getConnection()) {
			((HttpServletRequest) request).setAttribute("CONNECTION", connection);
			chain.doFilter(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}

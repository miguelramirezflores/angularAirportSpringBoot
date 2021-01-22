package com.angularAirport.jtsecurity;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/*")
public class MyWebFilter  implements Filter{

	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}



	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			String uriPeticionWeb = request.getRequestURI();
			String metodo = request.getMethod();
			int idUsuarioAutenticadoPorJtw= AutenticadorJWT.getIdUsuarioDesdeJwtIncrustadoEnRequest(request);
			
			System.out.println("log- request: " + uriPeticionWeb + " - " + metodo + idUsuarioAutenticadoPorJtw);
			if(metodo.equalsIgnoreCase("OPTIONS") ||
					uriPeticionWeb.startsWith("/webapp") ||
					uriPeticionWeb.equals("/usuario/autentica") ||
					idUsuarioAutenticadoPorJtw != -1) {
				filterChain.doFilter(servletRequest, servletResponse);
			}else {
				HttpServletResponse response = (HttpServletResponse) servletResponse;
				response.sendError(403,"no autorizado");
			}
	}
	@Override
	public void destroy() {
		
	}
}

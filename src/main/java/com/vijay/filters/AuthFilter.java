package com.vijay.filters;

import com.vijay.config.ApplicationProperties;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthFilter extends GenericFilterBean {


    @Autowired
    ApplicationProperties applicationProperties;

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);


    @Override
    protected void initFilterBean() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());

        super.initFilterBean();
    }

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        try {

            String authHeader = request.getHeader(applicationProperties.getJwtTokenName());

            if ("OPTIONS".equals(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
                chain.doFilter(request, response);
            } else {
                logger.info("Header In Filter => {}",authHeader);

                if (authHeader == null || !authHeader.startsWith(applicationProperties.getJwtBearer())) {
                    throw new ServletException("Missing or invalid Authorization header");
                }

                String userId = Jwts.parser()
                        .setSigningKey(applicationProperties.getJwtSecret())
                        .parseClaimsJws(authHeader.replace(applicationProperties.getJwtBearer(), ""))
                        .getBody()
                        .getSubject();
                String token = authHeader.substring(7);
                request.setAttribute("userId",userId);

                chain.doFilter(request, response);
            }

        } catch (ServletException e) {
            e.printStackTrace();
            throw new ServletException("Invalid token");
        } catch (Exception e){
            e.printStackTrace();
            throw new ServletException("Internal Server Error");
        }
    }
}
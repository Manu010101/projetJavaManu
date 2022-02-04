package com.example.projetjavamanu;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebFilter(filterName = "FilterForm")
public class FilterForm implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        Enumeration params = request.getParameterNames();
        boolean erreur = false;

        while (params.hasMoreElements()){
            if (isEmpty(request.getParameter((String) params.nextElement()))){
                erreur = true;
                reject(request, response);
            }
        }

        private boolean isEmpty(String param){
            if(param == null){
                return true;
            }
            return false;
        }

        private void reject(ServletRequest request, ServletResponse response) throws ServletException, IOException{
            request.setAttribute("messageErreur", "Veuillez remplir correctement le formulaire");

            Enumeration params = request.getParameterNames();
            String paramN = null;

            request.setAttribute(paramN, request.getParameter(paramN));

            RequestDispatcher dispatcher = request.getRequestDispatcher("laoutuviens").forward(request, response);


        }

        chain.doFilter(request, response);
    }
}

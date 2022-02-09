package com.example.projetjavamanu;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

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
                reject( request,  response);
            }
        }

        if (!erreur) {
            System.out.println("passage dans doFilter");
            chain.doFilter(request, response);
        }
    }

    public boolean isEmpty(String param){
        return param.length() < 1;
    }

    private void reject(ServletRequest request, ServletResponse response) throws ServletException, IOException{
        request.setAttribute("messageErreur", "Veuillez remplir correctement le formulaire");

        Enumeration params = request.getParameterNames();
        String paramN = null;

        while (params.hasMoreElements()){
            paramN = (String) params.nextElement();
            request.setAttribute(paramN, request.getParameter(paramN));
            System.out.println("pb");
        }
        System.out.println("entree dans reject");
        request.getRequestDispatcher("/viewLayout.jsp").forward(request, response);


    }
}

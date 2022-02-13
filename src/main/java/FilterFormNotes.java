import com.example.projetjavamanu.Groupe;
import com.example.projetjavamanu.GroupeDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebFilter(filterName = "FilterFormNotes")
public class FilterFormNotes implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        ArrayList<String> messageErreur = new ArrayList<>();
        Enumeration params = request.getParameterNames();
        boolean erreur = false;
        String paramV = null;
        String paramN = null;

        //Vérifier si champs pas vides
        while (params.hasMoreElements()){
            paramN = (String) params.nextElement();
            paramV = request.getParameter(paramN);
            if (isEmpty(paramV) || Integer.parseInt(paramV) > 20 ||  Integer.parseInt(paramV) < 0){
                erreur = true;
                messageErreur.add("Les champs ne doivent pas être vides ou > 20 ou < 0");
                break;
            }

        }

        //chainage de la requete ou redirection si erreur
        if (!erreur) {
            System.out.println("passage dans doFilter");
            chain.doFilter(request, response);
        }
        else {
            messageErreur.add("Pour la peine, il faut tout recommencer");
            request.setAttribute("messageErreur", messageErreur);
            reject( request,  response);
        }
    }

    public boolean isEmpty(String param){
        return param.length() < 1;
    }

    private void reject(ServletRequest request, ServletResponse response) throws ServletException, IOException{

        List<Groupe> groupes = GroupeDAO.getAll();
        request.setAttribute("groupes", groupes);
        request.setAttribute("content", "/viewEditNotes.jsp");
        request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);


    }

}

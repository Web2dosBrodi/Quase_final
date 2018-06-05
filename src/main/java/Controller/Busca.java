package Controller;

import Model.Evento;
import Model.EventoDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author White
 */
@WebServlet(name = "Busca", urlPatterns = {"/Busca"})
public class Busca extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
<<<<<<< HEAD
        String nomeBusca = request.getParameter("busca");
=======
        //processRequest(request, response);
        String nomeBusca = request.getParameter("busca"); //pegar a string que o usuário pesquisou
>>>>>>> c726731d0b0e4d16cec152b3dc9aa39e3a4fd84a
        try {
            List<Evento> listaEventos = null; 
            if (nomeBusca != null) { //se houver string
                listaEventos = new EventoDAO().buscaEvento(request.getParameter("busca")); //busca no BD e lista os eventos correspondentes
            } else { //se não houver string
                listaEventos = new EventoDAO().getListaEventos(); //lista todos eventos
            }
<<<<<<< HEAD
            request.setAttribute("listaEventos", listaEventos);
            request.getRequestDispatcher("WEB-INF/view/IndexMod.jsp").forward(request, response);
=======
            request.setAttribute("listaEventos", listaEventos); //define o listaEventos com a lista atual
            request.getRequestDispatcher("WEB-INF/view/indexMod.jsp").forward(request, response); //requisição encaminhada para indexMod
>>>>>>> c726731d0b0e4d16cec152b3dc9aa39e3a4fd84a
            return;
        } catch (Exception e) { //exceção
            System.out.println("Erro ao buscar evento.");
            Logger.getLogger(Busca.class.getName()).log(Level.SEVERE, null, e);
        }
    }

/**
 * Handles the HTTP <code>POST</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

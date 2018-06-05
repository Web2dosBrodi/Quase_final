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
        String nomeBusca = request.getParameter("busca");
        try {
            List<Evento> listaEventos = null;
            if (nomeBusca != null) {
                listaEventos = new EventoDAO().buscaEvento(request.getParameter("busca"));
            } else {
                listaEventos = new EventoDAO().getListaEventos();
            }
            request.setAttribute("listaEventos", listaEventos);
            request.getRequestDispatcher("WEB-INF/view/IndexMod.jsp").forward(request, response);
            return;
        } catch (Exception e) {
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

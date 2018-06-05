package Controller;

import Model.Evento;
import Model.EventoDAO;
import java.io.IOException;
import java.util.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author White
 */
@WebServlet(urlPatterns = {"/InsertServlet"})
public class InsertServlet extends HttpServlet {

    boolean busca = false;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("parameter: "+request.getParameter("logout"));
        if (request.getParameter("index") != null) {
            response.sendRedirect("Busca");
            return;
        } else if (request.getParameter("logout") != null) {
            response.sendRedirect("Logar");
            System.out.println("Indo deslogar");
            return;
        } else {
            System.out.println("abrindo NovoEvento");
            request.getRequestDispatcher("WEB-INF/view/NovoEvento.jsp")
                    .forward(request, response);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String nomeEvento = request.getParameter("nomeEvento");
        int idUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
        String dataForm = request.getParameter("data");
        
        if(nomeEvento == null || nomeEvento.trim().equals("")){
            request.setAttribute("tituloVazio", true);
            request.getRequestDispatcher("WEB-INF/view/NovoEvento.jsp").forward(request, response);
            return;
        }
        if(dataForm == null || dataForm.trim().equals("")){
            request.setAttribute("dataVazia", true);
            request.getRequestDispatcher("WEB-INF/view/NovoEvento.jsp").forward(request, response);
            return;
        }
        
        Date date = castDate(dataForm);
        Evento evento = new Evento(nomeEvento, date, idUsuario);

        try {
            EventoDAO eventoDao = new EventoDAO();
            eventoDao.adicionaEvento(evento);
            request.setAttribute("adicionado", true);
            request.getRequestDispatcher("WEB-INF/view/NovoEvento.jsp").forward(request, response);
            return;
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro ao chamar InserirEvento");
            Logger.getLogger(InsertServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notAdded", true);
        request.getRequestDispatcher("WEB-INF/view/NovoEvento.jsp").forward(request, response);
        return;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Date castDate(String data) {
        int ano = Integer.parseInt(data.substring(0, 4));
        int mes = Integer.parseInt(data.substring(5, 7)) - 1;
        int dia = Integer.parseInt(data.substring(8, 10));
        //int hora = 0;
        //int minuto = 0;
        Calendar c = Calendar.getInstance();
        c.set(ano, mes, dia);
        Date date = c.getTime();
        return date;
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

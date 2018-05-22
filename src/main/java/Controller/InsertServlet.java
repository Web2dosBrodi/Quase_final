package Controller;

import Model.Evento;
import Model.EventoDAO;
import java.io.IOException;
//import java.io.PrintWriter;
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
 *
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

        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("logUser") != null) { //usuario logado
            if (request.getParameter("inserir") != null) {
                request.getRequestDispatcher("WEB-INF/view/novoEvento.jsp")
                                                .forward(request, response);
                return;
            } else if (request.getParameter("index") != null) {
                request.getRequestDispatcher("WEB-INF/view/indexMod.jsp")
                                                .forward(request, response);
                return;
            } else if (request.getParameter("logout") != null) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN); //comandos para logout
                sessao.setAttribute("logUser", null);
                request.getRequestDispatcher("WEB-INF/view/login.jsp")
                                                .forward(request, response);
                return;
            }
        } else { //usuario não logado            
            System.out.println("Usuario NÃO logado");
            request.getRequestDispatcher("WEB-INF/view/login.jsp")
                                            .forward(request, response);
            return;
        }
        /*PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet InserirConteudo</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("   <h1>Inserir Conteudo Web</h1>");

        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("logado") != null) {
            out.println("   <form action=\"InsertServlet\" method=\"post\">");
            out.println("       Titulo <input type=\"text\" name=\"nomeEvento\" value=\"\"></input>");
            out.println("       <p>Data <input type=\"date\" name=\"data\" value=\"data\"></input>");
            out.println("       <p>Autor <input type=\"text\" name=\"nomeUsuario\" value=\""
                    + sessao.getAttribute("nomeUsuario") + "\" disabled ></input>");
            out.println("       <p><input type=\"hidden\" name=\"IdUsuario\" value=\""
                    + sessao.getAttribute("idUsuario") + "\"></input>");
            
            out.println("       <p><input type=\"submit\" value=\"Enviar\"></input>");
            out.println("   </form>");

            out.println("   <form action=\"InsertServlet\" method=\"post\">");
            out.println("       <input type=\"hidden\" name=\"logout\" value=\"logout\" />");
            out.println("       <p><input type=\"submit\" value=\"Logout\"></input>");
            out.println("   </form>");

            out.println("   <form action=\"Index\" method=\"get\">");
            out.println("       <p><input type=\"submit\" name=\"Index\" value=\"Index\"></input>");
            out.println("   </form>");
        } else {
            response.sendRedirect("Login");
        }
        out.println("</body>");
        out.println("</html>");*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*String logout = request.getParameter("logout");
        
        if (logout != null && logout.equals("logout")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            HttpSession sessao = request.getSession();
            sessao.setAttribute("logado", null);
            //System.out.println("Logout");
            response.sendRedirect("InsertServlet");
        } else {*/
        String nomeEvento = request.getParameter("nomeEvento");
        int idUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
        String dataForm = request.getParameter("data");
        Date date = castDate(dataForm);
        Evento evento = new Evento(nomeEvento, date, idUsuario);

        try {
            EventoDAO eventoDao = new EventoDAO();
            eventoDao.adicionaEvento(evento);
                request.setAttribute("eventoOk", true);
            request.getRequestDispatcher("WEB-INF/view/novoEvento.jsp").forward(request, response);
            //response.sendRedirect("InsertServlet");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro ao chamar InserirEvento");
            Logger.getLogger(InsertServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //}
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

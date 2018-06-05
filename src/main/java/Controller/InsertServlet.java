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
public class InsertServlet extends HttpServlet { //servlet

    boolean busca = false;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override //sobrescreveu o metodo service
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { //metodo responsavel por receber requisição via http

<<<<<<< HEAD
        System.out.println("parameter: "+request.getParameter("logout"));
        if (request.getParameter("index") != null) {
            response.sendRedirect("Busca");
            return;
        } else if (request.getParameter("logout") != null) {
            response.sendRedirect("Logar");
            System.out.println("Indo deslogar");
=======
        HttpSession sessao = request.getSession(); //devolve a sessão do usuário atual
        if (sessao.getAttribute("logUser") != null) { //usuário logado
            if (request.getParameter("inserir") != null) { //se o usuario clicar em inserir
                request.getRequestDispatcher("WEB-INF/view/novoEvento.jsp") //requisição encaminhada para novo evento
                                                .forward(request, response);
                return;
            } else if (request.getParameter("index") != null) { //se o usario clicar em index
                request.getRequestDispatcher("WEB-INF/view/indexMod.jsp") //requisição encaminhada para indexMod
                                                .forward(request, response);
                return;
            } else if (request.getParameter("logout") != null) { //se o usuario clicar em logout
                response.setStatus(HttpServletResponse.SC_FORBIDDEN); //comandos para logout
                sessao.setAttribute("logUser", null); //logout
                request.getRequestDispatcher("WEB-INF/view/login.jsp") //requisição encaminhada para login
                                                .forward(request, response);
                return;
            }
        } else { //usuario não logado            
            System.out.println("Usuario NÃO logado"); //mensagem de erro
            request.getRequestDispatcher("WEB-INF/view/login.jsp") //requisição encaminhada para login
                                            .forward(request, response);
>>>>>>> c726731d0b0e4d16cec152b3dc9aa39e3a4fd84a
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
<<<<<<< HEAD

=======
       //recebe requisição via http
        /*String logout = request.getParameter("logout");
        
        if (logout != null && logout.equals("logout")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            HttpSession sessao = request.getSession();
            sessao.setAttribute("logado", null);
            //System.out.println("Logout");
            response.sendRedirect("InsertServlet");
        } else {*/
        
        //pega os dados e cria um novo evento
>>>>>>> c726731d0b0e4d16cec152b3dc9aa39e3a4fd84a
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
<<<<<<< HEAD
            EventoDAO eventoDao = new EventoDAO();
            eventoDao.adicionaEvento(evento);
            request.setAttribute("adicionado", true);
            request.getRequestDispatcher("WEB-INF/view/NovoEvento.jsp").forward(request, response);
            return;
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro ao chamar InserirEvento");
            Logger.getLogger(InsertServlet.class.getName()).log(Level.SEVERE, null, ex);
=======
            EventoDAO eventoDao = new EventoDAO(); //chama a classe DAO que é responsavem pelo acesso ao BD
            eventoDao.adicionaEvento(evento); //DAO para adicionar evento
                request.setAttribute("eventoOk", true); //evento correto
            request.getRequestDispatcher("WEB-INF/view/novoEvento.jsp").forward(request, response);//requisição encaminhada para novo evento
            //response.sendRedirect("InsertServlet");
        } catch (ClassNotFoundException ex) { //se houver erro
            System.err.println("Erro ao chamar InserirEvento"); //mensagem de erro 
            Logger.getLogger(InsertServlet.class.getName()).log(Level.SEVERE, null, ex); //exceção
>>>>>>> c726731d0b0e4d16cec152b3dc9aa39e3a4fd84a
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
    private Date castDate(String data) { //converter string data em date
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

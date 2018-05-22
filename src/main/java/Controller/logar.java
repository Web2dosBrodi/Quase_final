package Controller;

import Model.Usuario;
import Model.UsuarioDAO;
import java.io.IOException;
//import java.io.PrintWriter;
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
@WebServlet(name = "logar", urlPatterns = {"/logar"})
public class logar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

        System.out.println("Passei: " + request.getParameter("loginIncorreto"));
        if (request.getParameter("loginIncorreto") != null) {

            request.setAttribute("invalido", true);
            request.getRequestDispatcher("WEB-INF/view/login.jsp")
                    .forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); //comandos para logout
            request.getSession().setAttribute("logUser", null);
            //request.getRequestDispatcher("WEB-INF/view/indexMod.jsp").forward(request, response);
            response.sendRedirect("Busca");
            return;
        }
        /*
        HttpSession sessao = request.getSession();
        PrintWriter out = response.getWriter();
        System.out.println("Sessao: "+sessao.getAttribute("logado"));
        if (sessao.getAttribute("logado") != null) {
            response.sendRedirect("InsertServlet");
        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet logar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("   <fieldset>");
            out.println("       <legend>logar</legend>");
            out.println("       <form action=\"logar\" method=\"POST\">");
            out.println("           Email <input type=\"email\" name=\"email\" value=\"\"></input>");
            out.println("           <p>Senha <input type=\"text\" name=\"senha\" value=\"\"></input></p>");
            out.println("           <p><input type=\"submit\" value=\"Logar\"></input></p>");
            out.println("       </form>");
            out.println("   </fieldset>");
            out.println("</body>");
            out.println("</html>");
        }*/
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

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (email.trim().equals("")) {
            request.setAttribute("semEmail", true);
            request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
            return;

        } else if (senha.trim().equals("")) {
            request.setAttribute("semSenha", true);
            request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
            return;

        } else if (!email.equals("") && !senha.equals("")) {
            Usuario usu = new Usuario(email, senha);
            try {
                Usuario usuRetorno = new UsuarioDAO().logarUsuario(usu);
                if (usuRetorno == null) {
                    request.setAttribute("loginIncorreto", true);
                    response.sendRedirect("logar?loginIncorreto=" + true);
                    return;
                } else {
                    //System.out.println("Logou");
                    //request.getSession().setAttribute("logged", true);
                    //request.getSession().setAttribute("nomeUsuario", usuRetorno.getUserName());
                    //request.getSession().setAttribute("idUsuario", usuRetorno.getId());
                    request.getSession().setAttribute("logUser", usuRetorno);
                    request.getRequestDispatcher("WEB-INF/view/novoEvento.jsp").forward(request, response);
                    return;
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(logar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //response.getWriter().println("Campo não preenchido");
            System.out.println("Nenhuma das opções de login");
            request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
        }
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

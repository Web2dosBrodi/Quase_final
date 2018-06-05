package Controller;

import Model.Usuario;
import Model.UsuarioDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author White
 */
@WebServlet(name = "Logar", urlPatterns = {"/Logar"})
public class Logar extends HttpServlet {

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

        if (request.getParameter("loginIncorreto") != null) {
            request.setAttribute("invalido", true);
            request.getRequestDispatcher("WEB-INF/view/Login.jsp")
                    .forward(request, response);
        }else if (request.getParameter("naoLogado") != null) {
            request.getRequestDispatcher("WEB-INF/view/Login.jsp")
                    .forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); //comandos para logout
            request.getSession().setAttribute("loggedUser", null);
            response.sendRedirect("Busca");
            return;
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

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (email.trim().equals("")) {
            request.setAttribute("semEmail", true);
            request.getRequestDispatcher("WEB-INF/view/Login.jsp").forward(request, response);
            return;

        } else if (senha.trim().equals("")) {
            request.setAttribute("semSenha", true);
            request.getRequestDispatcher("WEB-INF/view/Login.jsp").forward(request, response);
            return;

        } else if (!email.equals("") && !senha.equals("")) {
            Usuario usu = new Usuario(email, senha);
            try {
                Usuario usuRetorno = new UsuarioDAO().logarUsuario(usu);
                if (usuRetorno == null) {
                    request.setAttribute("loginIncorreto", true);
                    response.sendRedirect("Logar?loginIncorreto=" + true);
                    return;
                } else {
                    request.getSession().setAttribute("loggedUser", usuRetorno);
                    //request.getRequestDispatcher("WEB-INF/view/AddEvento.jsp").forward(request, response);
                    response.sendRedirect("InsertServlet");
                    System.out.println("user logged");
                    return;
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Logar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Nenhuma das opções de login");
            request.getRequestDispatcher("WEB-INF/view/Login.jsp").forward(request, response);
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

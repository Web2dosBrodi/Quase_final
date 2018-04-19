
package com.mycompany.agenda;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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

        HttpSession sessao = request.getSession();
        PrintWriter out = response.getWriter();
        System.out.println("Sessao: "+sessao.getAttribute("logado"));
        if (sessao.getAttribute("logado") != null) {
            response.sendRedirect("InsertServlet");
        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("   <fieldset>");
            out.println("       <legend>Login</legend>");
            out.println("       <form action=\"Login\" method=\"POST\">");
            out.println("           Email <input type=\"email\" name=\"email\" value=\"\"></input>");
            out.println("           <p>Senha <input type=\"text\" name=\"senha\" value=\"\"></input></p>");
            out.println("           <p><input type=\"submit\" value=\"Logar\"></input></p>");
            out.println("       </form>");
            out.println("   </fieldset>");
            out.println("</body>");
            out.println("</html>");
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
        System.out.println("Email: " + email);
        System.out.println("Senha: " + senha);
        if (!email.equals("") && !senha.equals("")) {
            System.out.println("Entrou no IF");
            Usuario usu = new Usuario(email, senha);
            try {
                EventoDAO eventoDao = new EventoDAO();
                Usuario usuRetorno = eventoDao.logarUsuario(usu);
                System.out.println("idUsuario: " + usuRetorno.getId());
                if (usuRetorno.getId() != null) {
                    System.out.println("Logou");
                    request.getSession().setAttribute("logado", true);
                    request.getSession().setAttribute("nomeUsuario", usuRetorno.getUserName());
                    request.getSession().setAttribute("idUsuario", usuRetorno.getId());
                    response.sendRedirect("InsertServlet");
                } else {
                    System.out.println("Login recusado");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.sendRedirect("Login");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //response.getWriter().println("Campo não preenchido");
            response.sendRedirect("Login");
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

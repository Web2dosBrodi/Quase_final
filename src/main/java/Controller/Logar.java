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

<<<<<<< HEAD:src/main/java/Controller/Logar.java
        if (request.getParameter("loginIncorreto") != null) {
            request.setAttribute("invalido", true);
            request.getRequestDispatcher("WEB-INF/view/Login.jsp")
                    .forward(request, response);
        }else if (request.getParameter("naoLogado") != null) {
            request.getRequestDispatcher("WEB-INF/view/Login.jsp")
=======
        System.out.println("Passei: " + request.getParameter("loginIncorreto"));
        if (request.getParameter("loginIncorreto") != null) { //se o login estiver incorreto

            request.setAttribute("invalido", true);
            request.getRequestDispatcher("WEB-INF/view/login.jsp") //requisição encaminhada para login
>>>>>>> c726731d0b0e4d16cec152b3dc9aa39e3a4fd84a:src/main/java/Controller/logar.java
                    .forward(request, response);
        } else { //se não
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); //comandos para logout
<<<<<<< HEAD:src/main/java/Controller/Logar.java
            request.getSession().setAttribute("loggedUser", null);
            response.sendRedirect("Busca");
=======
            request.getSession().setAttribute("logUser", null); //define user como null
            //request.getRequestDispatcher("WEB-INF/view/indexMod.jsp").forward(request, response);
            response.sendRedirect("Busca"); //redireciona para busca
>>>>>>> c726731d0b0e4d16cec152b3dc9aa39e3a4fd84a:src/main/java/Controller/logar.java
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
        //recebe e-mail e senha do usuário
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (email.trim().equals("")) { //se o e-mail estiver vazio
            request.setAttribute("semEmail", true);
<<<<<<< HEAD:src/main/java/Controller/Logar.java
            request.getRequestDispatcher("WEB-INF/view/Login.jsp").forward(request, response);
=======
            request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);//requisição encaminhada para login
>>>>>>> c726731d0b0e4d16cec152b3dc9aa39e3a4fd84a:src/main/java/Controller/logar.java
            return;

        } else if (senha.trim().equals("")) { //se a senha estiver vazia
            request.setAttribute("semSenha", true);
<<<<<<< HEAD:src/main/java/Controller/Logar.java
            request.getRequestDispatcher("WEB-INF/view/Login.jsp").forward(request, response);
=======
            request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response); //requisição encaminhada para lgin
>>>>>>> c726731d0b0e4d16cec152b3dc9aa39e3a4fd84a:src/main/java/Controller/logar.java
            return;

        } else if (!email.equals("") && !senha.equals("")) { // se e-mail e senha forem válidos
            Usuario usu = new Usuario(email, senha); //cria usuário
            try {
                Usuario usuRetorno = new UsuarioDAO().logarUsuario(usu); //busca usuário no BD
                if (usuRetorno == null) { //se o retorno do BD for nulo
                    request.setAttribute("loginIncorreto", true);
<<<<<<< HEAD:src/main/java/Controller/Logar.java
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
=======
                    response.sendRedirect("logar?loginIncorreto=" + true); //login incorreto, redireciona para login
                    return;
                } else { //se o usuário for válido
                    //System.out.println("Logou");
                    //request.getSession().setAttribute("logged", true);
                    //request.getSession().setAttribute("nomeUsuario", usuRetorno.getUserName());
                    //request.getSession().setAttribute("idUsuario", usuRetorno.getId());
                    request.getSession().setAttribute("logUser", usuRetorno); //retorna sessão atual e define logUser com o retorno do BD
                    request.getRequestDispatcher("WEB-INF/view/novoEvento.jsp").forward(request, response);//requisição encaminhada para novoEvento
                    return;
                }
            } catch (ClassNotFoundException ex) { //exceção
                Logger.getLogger(logar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { //se não estiver em denhuma das opções
            //response.getWriter().println("Campo não preenchido");
            System.out.println("Nenhuma das opções de login"); //mensagem de erro
            request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response); //requisição encaminhada para login
>>>>>>> c726731d0b0e4d16cec152b3dc9aa39e3a4fd84a:src/main/java/Controller/logar.java
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        //processRequest(request, response);
        String nomeBusca = request.getParameter("busca");
        try {
            List<Evento> listaEventos = null;
            if (nomeBusca != null) {
                listaEventos = new EventoDAO().buscaEvento(request.getParameter("busca"));
            } else {
                listaEventos = new EventoDAO().getListaEventos();
            }
            request.setAttribute("listaEventos", listaEventos);
            request.getRequestDispatcher("WEB-INF/view/indexMod.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            System.out.println("Erro ao buscar evento.");
            Logger.getLogger(Busca.class.getName()).log(Level.SEVERE, null, e);
        }

    }


//            if (nomeBusca.trim().equals("")) {
//                try {
//                    listaEventos = new EventoDAO().getListaEventos();
//                    //request.setAttribute("listaEventos", listaEventos);
//                    //request.getRequestDispatcher("WEB-INF/view/indexMod.jsp").forward(request, response);
//                } catch (ClassNotFoundException ex) {
//                    System.out.println("Erro ao buscar evento. DoGet SEM parametro");
//                    Logger.getLogger(Busca.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//                try {
//                    listaEventos = new EventoDAO().buscaEvento(request.getParameter("busca"));
//                } catch (ClassNotFoundException ex) {
//                    System.out.println("Erro ao buscar evento. DoGet com parametro");
//                    Logger.getLogger(Busca.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            request.setAttribute("listaEventos", listaEventos);
//            request.getRequestDispatcher("WEB-INF/view/indexMod.jsp").forward(request, response);
//    }  else {
//            response.sendRedirect("WEB-INF/view/indexMod.jsp");
//    }


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

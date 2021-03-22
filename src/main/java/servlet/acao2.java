/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.CategoriaDAO;
import entidade.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rogerio.klein
 */
public class acao2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet acao2</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet acao2 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

        System.out.println("ESTOU no GET");

        String param = request.getParameter("param");
        // ================= PESSOA =========================================
        if (param.equals("edPessoa")) {
            String id = request.getParameter("id");
            System.out.println("ID para edição: " + id);
        } else if (param.equals("exPessoa")) {

        }

        // ================= CATEGORIA ======================================        
        if (param.equals("edCategoria")) {
            String id = request.getParameter("id");

            Categoria categ = new CategoriaDAO().consultarId(Integer.parseInt(id));

            if (categ != null) {

                request.setAttribute("objCategoria", categ);

                encaminharPagina("categoria.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }
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
        System.out.println("ESTOU no POST");

        String param = request.getParameter("param");
        // ================= PESSOA =========================================
        if (param.equals("salvarPessoa")) {
            String nome = request.getParameter("nomePessoa");
            System.out.println("Nome pessoa: " + nome);

            // inacabado
        }

        // ================= CATEGORIA ======================================
        if (param.equals("salvarCategoria")) {
            // capturar dados que vieram do REQUEST
            int id = Integer.parseInt(request.getParameter("id"));
            String descricao = request.getParameter("descricao");
            String situacao = request.getParameter("situacao");

            // validacoes dos campos - não farei
            // criar OBJ do tipo que será salvo
            Categoria c = new Categoria();
            c.setId(id);
            c.setDescricao(descricao);
            c.setSituacao(situacao.charAt(0));

            // chamar o salvar e aguardar o retorno
            String retorno = null;
            if (id == 0) {
                retorno = new CategoriaDAO().salvar(c);
            } else {
                retorno = new CategoriaDAO().atualizar(c);
            }

            if (retorno == null) {
                // deu certo
                request.setAttribute("tipoCadastro", "Categoria");
                request.setAttribute("paginaRetorno", "categoria.jsp");

                encaminharPagina("sucesso.jsp", request, response);
            } else {
                // deu errado
                encaminharPagina("erro.jsp", request, response);
            }

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

    private void encaminharPagina(String pagina, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("Erro ao encaminhar: " + e);
        }
    }
}

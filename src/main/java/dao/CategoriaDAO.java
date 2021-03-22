/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidade.Categoria;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author pretto
 */
public class CategoriaDAO implements IDAO<Categoria> {

    ResultSet resultadoQ;

    public boolean salvarInicial(Categoria categoria) {
        try {
//            Statement stm = ferrados.Ferrados.conexao.createStatement();
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "Insert into categoria values "
                    + "(default,"
                    + " '" + categoria.getDescricao() + "',"
                    + " '" + categoria.getSituacao() + "')";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar categoria: " + e);
            return false;
        }
    }

    @Override
    public String salvar(Categoria o) {
        try {
//            Statement stm = ferrados.Ferrados.conexao.createStatement();
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "Insert into categoria values "
                    + "(default,"
                    + " '" + o.getDescricao() + "',"
                    + " '" + o.getSituacao() + "')";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar categoria: " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Categoria o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Categoria> consultarTodos() {

        ArrayList<Categoria> categorias = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from "
                    + "categoria ";

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Categoria c = new Categoria();

                c.setId(resultado.getInt("id"));
                c.setDescricao(resultado.getString("descricao"));
                c.setSituacao(resultado.getString("situacao").charAt(0));

                categorias.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Categorias: " + e);
        }

        return categorias;
    }

    @Override
    public ArrayList<Categoria> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria consultarId(int id) {
        Categoria c = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from "
                    + "categoria "
                    + "where "
                    + "id = " + id;

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                c = new Categoria();

                c.setId(resultado.getInt("id"));
                c.setDescricao(resultado.getString("descricao"));
                c.setSituacao(resultado.getString("situacao").charAt(0));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Categoria por ID: " + e);
        }
        
        return c;
    }

    @Override
    public boolean registroUnico(Categoria o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean consultar(Categoria o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

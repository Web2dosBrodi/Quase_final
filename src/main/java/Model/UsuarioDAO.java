package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luiz-
 */
public class UsuarioDAO {
    private Connection connection;
    //conexão com BD
    public UsuarioDAO() throws ClassNotFoundException{
        this.connection = ConnectionFactory.getConnection();
    }
    
    public Usuario logarUsuario(Usuario user){ //logar usuário
        String sql = "SELECT * FROM usuario WHERE name_user=? AND password=?"; //prepara instrução sql
        try {
            PreparedStatement ps = connection.prepareStatement(sql); //executa instrução sql
            ps.setString(1, user.getUserName()); //pega o user
            ps.setString(2, user.getPassword()); //pega a senha
            ResultSet rs = ps.executeQuery(); //busca
            Usuario usuario = null; //cria usuário
            while(rs.next()){ //verifica os dados
                long id = rs.getInt("id_user");
                String nome = rs.getString("name_user");
                usuario = new Usuario(id, nome, "", true);
            }
            return usuario; //retorna usuário
        } catch (Exception e) { //se os dados estiverem incorretos
            System.out.println("Erro ao LOGAR"); //mensagem de erro
            e.getStackTrace();
        }
        return null;
    }
    
    public void adicionaUsuario(Usuario usuario){
        String sql = "INSERT INTO usuario (name_user, password)" 
                + " VALUES (?, ?)"; //prepara instrução sql
        
        try {
            // prepared statement para inserção
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql); //executa instrução sql

            // seta os valores
            stmt.setString(1,usuario.getUserName());
            stmt.setString(2,usuario.getPassword());

            // executa a query
            stmt.execute();
            stmt.close();
        } catch (SQLException e) { //exceção
            throw new RuntimeException(e);
        }
    }
    
     public List<Usuario> getListaUsuarios(){
        try {
            List<Usuario> contatos = new ArrayList<Usuario>();
            // prepared statement para listar
            PreparedStatement ps = (PreparedStatement) this.connection.prepareStatement("select * from usuario");
            
            ResultSet rs = ps.executeQuery(); //executa a query
            
            while (rs.next()){ //lista usuários
                Usuario user = new Usuario();                
                user.setUserName(rs.getString("name_user"));                
                contatos.add(user);
            }
            rs.close();
            ps.close();
            return contatos;
        } catch (SQLException e) { //exceção
            throw new RuntimeException(e);
        }
    }
}

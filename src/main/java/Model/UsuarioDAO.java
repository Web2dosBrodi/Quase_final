package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luiz-
 */
public class UsuarioDAO {
    private final Connection connection;
    
    public UsuarioDAO() throws ClassNotFoundException{
        this.connection = ConnectionFactory.getConnection();
    }
    
    public Usuario logarUsuario(Usuario user){
        String sql = "SELECT * FROM usuario WHERE name_user=? AND password=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            Usuario usuario = null;
            while(rs.next()){
                long id = rs.getInt("id_user");
                String nome = rs.getString("name_user");
                usuario = new Usuario(id, nome, "", true);
            }
            return usuario;
        } catch (Exception e) {
            System.out.println("Erro ao LOGAR");
            e.getStackTrace();
        }
        return null;
    }
    
    public void adicionaUsuario(Usuario usuario){
        String sql = "INSERT INTO usuario (name_user, password)" 
                + " VALUES (?, ?)";
        
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores
            stmt.setString(1,usuario.getUserName());
            stmt.setString(2,usuario.getPassword());

            // executa a query
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
     public List<Usuario> getListaUsuarios(){
        try {
            List<Usuario> contatos = new ArrayList<Usuario>();
            PreparedStatement ps = this.connection.prepareStatement("select * from usuario");
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Usuario user = new Usuario();                
                user.setUserName(rs.getString("name_user"));                
                contatos.add(user);
            }
            rs.close();
            ps.close();
            return contatos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

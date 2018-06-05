package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author White
 */
public class EventoDAO {
<<<<<<< HEAD
    private final Connection connection;
    
=======
    private Connection connection;
    
    //conexão com BD
>>>>>>> c726731d0b0e4d16cec152b3dc9aa39e3a4fd84a
    public EventoDAO() throws ClassNotFoundException{
        this.connection = ConnectionFactory.getConnection();
    }
    
    
    public void adicionaEvento(Evento evento){ 
        String sql = "insert into evento (name_event, date, id_user)" 
                + " VALUES (?, ?, ?)"; //prepara a instrução sql
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql); //executa a instrução sql
            
            ps.setString(1, evento.getNome()); //nome do evento
            ps.setTimestamp(2, new java.sql.Timestamp (evento.getData().getTime())); //data e tempo
            ps.setLong(3, evento.getUserId()); //idntificação do usário
            
            ps.executeUpdate(); //atualiza os dados
            ps.close(); 
        } catch (SQLException e){ //exceção
            throw new RuntimeException (e);
        }
    }
    
    public List<Evento> getListaEventos(){ //listar eventos
        try {
            List<Evento> eventos = new ArrayList<Evento>();
            
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM evento"); //instrução sql
            ResultSet rs = ps.executeQuery(); //pesquisa no BD
            
            while (rs.next()){ //lista os eventos
                Evento event = new Evento();                
                event.setNome(rs.getString("name_event"));
                event.setUserId(rs.getInt("id_user"));
                event.setData(rs.getDate("date"));
                eventos.add(event);
            }
            ps.close();
            return eventos; //retorna os eventos
        } catch (SQLException e) { //exceção
            throw new RuntimeException(e);
        }      
    }
    public List<Evento> buscaEvento(String nomeEvento){ //pesquisa eventos
        String sql = "SELECT * FROM evento WHERE name_event LIKE ?"; //prepara instrução sql
        try {
            List<Evento> eventos = new ArrayList(); 
            PreparedStatement ps = this.connection.prepareStatement(sql); //executa a instrução sql
            ps.setString(1, "%" + nomeEvento + "%");  //parametros de pesquisa
            ResultSet rs = ps.executeQuery(); //pesquisa
            
            while (rs.next()){ //compara os eventos existentes com a string de pesquisa
                Evento event = new Evento();                
                event.setNome(rs.getString("name_event"));
                event.setData(rs.getDate("date"));
                event.setUserId(rs.getInt("id_user"));
                eventos.add(event);
            }
            ps.close();
            return eventos;
        } catch (SQLException e) { //exceção
            throw new RuntimeException(e);
        }      
    }

}

package br.edu.ifms.cadastroanimais.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gustavo
 */
public class Conexao {

    private Connection conn;
    private static Conexao conexao;
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("animais");

    private Conexao() throws SQLException {
        String url = "jdbc:postgresql://127.0.0.1/animais?user=postgres&password=postgres";
        this.conn = DriverManager.getConnection(url);
    }

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public static Conexao getInstance() throws SQLException {
        if (conexao == null) {
            conexao = new Conexao();
        }
        return conexao;
    }

    public void close() throws SQLException {
        if (!isClosed()) {
            conn.close();
        }
        conn = null;
    }

    public Connection getConn() {
        return conn;
    }

    public Boolean isClosed() throws SQLException {
        return conn.isClosed();
    }

    public static void main(String[] args) {
        try {
            Conexao c = Conexao.getInstance();
            if (!c.isClosed()) {
                System.out.println("Conexão realizada com sucesso");
            }
            c.close();
            System.out.println("Fechamento realizado com sucesso");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

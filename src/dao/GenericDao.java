package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.Conexao;

/**
 *
 * @author Miquéias
 */
public class GenericDao {
     private Connection connection;

    protected GenericDao() {
        this.connection = Conexao.getConnection();
    }

    protected Connection getConnection() {
        return connection;
    }

    protected void save(String insertSql, Object... parametros) {
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(insertSql);

            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i + 1, parametros[i]);
            }

            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void update(String updateSql, Object... parametros) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateSql);

            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i + 1, parametros[i]);
            }

            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void delete(String deleteSql, Object... parametros) {
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(deleteSql);

            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i + 1, parametros[i]);
            }

            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

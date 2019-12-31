package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miquéias
 */
public class Conexao {

    private static String db_driver = "";
    private static String db_username = "";
    private static String db_password = "";
    public static String db_host = "";
    private static String db_porta = "";
    private static String db_url = "";
    private static Connection connection = null;

    public Conexao() {
        Conexao.getConnection();
    }

    private static void validateHost() {
        if (db_host.equals("OX_SERVIDOR")) {
            db_username = "USRHGINTEGRA";
            db_password = "qepealezememikalumini";
            db_host = "OX_SERVIDOR";
            db_porta = "49159";
        } else if (db_host.equals("OX_MIQUEIAS")) {
            db_username = "sa";
            db_password = "Sqls3rv3r";
            db_host = "OX_MIQUEIAS";
            db_porta = "1433";
        }
    }

    public static Connection getConnection() {
        validateHost();
        db_url = "jdbc:sqlserver://" + db_host + ":" + db_porta + ";databaseName=Oxetil_Oficial";

        try {
            Class.forName(db_driver);
            connection = DriverManager.getConnection(db_url, db_username, db_password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connection;
    }

    public static Connection getSettingConnection() {

        String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
        String URL = "jdbc:derby:myDerby;create=true;user=derby;password=derby";
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }   
    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getHost() {
        return db_host;
    }

}

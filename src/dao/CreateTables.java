package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.Conexao;

/**
 *
 * @author Miquéias
 */
public class CreateTables {

    private Connection connection;

    public CreateTables() {
        this.connection = Conexao.getConnection();
    }

    public void createTableConexoes() {
        String sql = null;
        try {
            //derby
            sql = "CREATE TABLE Conexoes( "
                    + "id integer not null GENERATED ALWAYS AS "
                    + "IDENTITY (START WITH 1, INCREMENT BY 1) "
                    + "CONSTRAINT PK_Conexoes PRIMARY KEY, "
                    + "name varchar(50) not null, "
                    + "driver varchar(100) not null, "
                    + "host varchar(50) not null, "
                    + "username varchar(100) not null, "
                    + "password varchar(100) not null, "
                    + "port integer not null, "
                    + "url varchar(100) not null"
                    + ")";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("CreateTables.createTableConexoes Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTablePessoas() {
        String sql = null;
        try {
            //derby
            sql = "CREATE TABLE PESSOAS( "
                    + "id_pessoa integer not null GENERATED ALWAYS AS "
                    + "IDENTITY (START WITH 1, INCREMENT BY 1) "
                    + "CONSTRAINT PK_PESSOAS PRIMARY KEY, "
                    + "nome varchar(20) not null, "
                    + "idade integer not null"
                    + ")";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("CreateTables.createTablePessoas Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableTelefones() {
        String sql = null;
        try {
            //derby
            sql = "CREATE TABLE TELEFONES( "
                    + "id_fone integer not null GENERATED ALWAYS AS "
                    + "IDENTITY (START WITH 1, INCREMENT BY 1) "
                    + "CONSTRAINT PK_TEFEFONES PRIMARY KEY, "
                    + "numero varchar(12), "
                    + "tipo varchar(11), "
                    + "pessoa integer not null, "
                    + "CONSTRAINT FK_PESSOAS FOREIGN KEY (pessoa) "
                    + "REFERENCES PESSOAS (id_pessoa) "
                    + ")";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            System.out.println("CreateTables.createTableTelefones Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

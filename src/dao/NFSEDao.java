package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.NFSE;
import util.Conexao;

/**
 *
 * @author Miquéias
 */
public class NFSEDao {

    public static String checkNFSe(int numeroInterno, int codigoEmpresa) {

        Connection connection = Conexao.getConnection();
        String sql = "Select NF_NumeroNFEletronica from NotasFiscais where NF_Numero = '" + numeroInterno + "' and Emp_Codigo = " + codigoEmpresa;
        String retorno = "";
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            pstm = connection.prepareStatement(sql);
            rset = pstm.executeQuery();
            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                if (retorno.isEmpty()) {
                    retorno = String.valueOf(rset.getString("NF_NumeroNFEletronica"));
                } else {
                    retorno += " - " + String.valueOf(rset.getString("NF_NumeroNFEletronica"));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (connection != null) {
                    connection.close();
                    Conexao.closeConnection();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retorno;
    }

    public static NFSE findNFSe(int numeroInterno, int codigoEmpresa) {
        Connection connection = Conexao.getConnection();
        String sql = "Select * from NotasFiscais where NF_Numero = '" + numeroInterno + "' and Emp_Codigo = " + codigoEmpresa;
        PreparedStatement pstm = null;
        NFSE nfse = new NFSE();

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            pstm = connection.prepareStatement(sql);
            rset = pstm.executeQuery();
            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                nfse.setClienteCodigo(rset.getInt("Pessoa_CodigoCliente"));
                nfse.setCodigoEmpresa(rset.getInt("Emp_Codigo"));
                nfse.setNumeroExterno(rset.getInt("NF_NumeroNFEletronica"));
            }
            nfse.setNumeroInterno(numeroInterno);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    connection.close();
                    Conexao.closeConnection();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return nfse;
    }

    public static void executeCorrection(NFSE nfse, int notaExterna) {
        Connection connection = Conexao.getConnection();
        try {
            connection.setAutoCommit(false);
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate("Update NotasFiscais Set NF_NumeroNFEletronica = '" + notaExterna + "' Where Emp_Codigo = " + nfse.getCodigoEmpresa() + " And NF_Numero  = '" + nfse.getNumeroInterno() + "' ");
                connection.commit(); //This commits the transaction and starts a new one.
            }
            System.out.println("Sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                System.out.println("Falha");
                connection.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                        Conexao.closeConnection();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

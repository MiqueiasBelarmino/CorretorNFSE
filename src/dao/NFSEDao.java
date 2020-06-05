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

    /**
     * <p>
     * Esse método verifica se a NFSE consta completa na base de dados e se está
     * correta</p>
     *
     * @param numeroInterno o numero da nota fiscal no sistema interno
     * @param codigoEmpresa o codigo de qual empresa pertence a ser localizada
     * @return o numero externo da nota
     */
    public static String verificar(int numeroInterno, int codigoEmpresa) {
        //estabelece conexão com o banco de dados
        Connection connection = Conexao.getConnection();
        String sql = "Select NF_NumeroNFEletronica from NotasFiscais where NF_Numero = '" + numeroInterno + "' and Emp_Codigo = " + codigoEmpresa;
        String retorno = "";
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            //prepara e executa a query
            pstm = connection.prepareStatement(sql);
            rset = pstm.executeQuery();
            //enquanto existir dados no banco de dados, faça
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

    /**
     * <p>
     * Esse método localiza uma NFSe com base nas informações passadas por
     * parâmetros</p>
     *
     * @param numeroInterno o numero da nota fiscal no sistema interno
     * @param codigoEmpresa o codigo de qual empresa pertence a ser localizada
     * @return as informações da NFSE em uma/um instância/objeto NFSE
     */
    public static NFSE localizar(int numeroInterno, int codigoEmpresa) {
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
                nfse.setCaixaPapelaoQtd(rset.getInt("NF_QtdCaixasPapelao"));
                nfse.setClienteNome(rset.getString("NF_NomeCliente"));
                nfse.setContainers(rset.getString("NF_Containers"));
                nfse.setNumeroInterno(numeroInterno);
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
        return nfse;
    }

    /**
     * <p>
     * Esse método efetua correção da nfse na base de dados da empresa</p>
     *
     * @param nfse instância de NFSE contendo todas as informações da nota
     * @param notaExterna o numero da nota fiscal
     */
    public static void corrigir(NFSE nfse, int notaExterna) {
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

    /**
     * <p>
     * Esse método efetua correção dos containers do pedido na nota</p>
     *
     * @param nfse instância de NFSE contendo todas as informações da nota
     */
    public static void corrigirContainer(NFSE nfse) {
        Connection connection = Conexao.getConnection();
        try {
            connection.setAutoCommit(false);
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate("Update NotasFiscais Set NF_Containers = '" + nfse.getContainers() + "' Where Emp_Codigo = " + nfse.getCodigoEmpresa() + " And NF_Numero  = '" + nfse.getNumeroInterno() + "' ");
                connection.commit(); //This commits the transaction and starts a new one.
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
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

    /**
     * <p>
     * Esse método efetua correção da quantidade de caixas de papelao do pedido
     * na nota</p>
     *
     * @param nfse instância de NFSE contendo todas as informações da nota
     */
    public static void corrigirCaixaPapelao(NFSE nfse) {
        Connection connection = Conexao.getConnection();
        try {
            connection.setAutoCommit(false);
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate("Update NotasFiscais Set NF_QtdCaixasPapelao = " + nfse.getCaixaPapelaoQtd() + " Where Emp_Codigo = " + nfse.getCodigoEmpresa() + " And NF_Numero  = '" + nfse.getNumeroInterno() + "' ");
                connection.commit(); //This commits the transaction and starts a new one.
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
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

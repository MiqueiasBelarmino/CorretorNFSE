package modelo;

import java.lang.reflect.Field;

/**
 *
 * @author Miquéias
 */
public class NFSE {

    private int numeroInterno = 0;
    private int numeroExterno = 0;
    private int clienteCodigo = 0;
    private int codigoEmpresa = 0;
    private String containers = null;
    private String clienteNome = null;
    private int caixaPapelaoQtd = 0;

    public int getNumeroInterno() {
        return numeroInterno;
    }

    public void setNumeroInterno(int numeroInterno) {
        if (numeroInterno > 0) {
            this.numeroInterno = numeroInterno;
        } else {
            this.numeroInterno = 0;
        }
    }

    public int getNumeroExterno() {
        return numeroExterno;
    }

    public void setNumeroExterno(int numeroExterno) {
        if (numeroExterno > 0) {
            this.numeroExterno = numeroExterno;
        } else {
            this.numeroExterno = 0;
        }
    }

    public int getClienteCodigo() {
        return clienteCodigo;
    }

    public void setClienteCodigo(int clienteCodigo) {
        if (clienteCodigo > 1) {
            this.clienteCodigo = clienteCodigo;
        } else {
            this.clienteCodigo = 0;
        }
    }

    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(int codigoEmpresa) {
        if (codigoEmpresa > 0) {
            this.codigoEmpresa = codigoEmpresa;
        } else {
            this.codigoEmpresa = 1;
        }
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        if (clienteNome.isEmpty()) {
            this.clienteNome = "Desconhecido";
        } else {
            this.clienteNome = clienteNome;
        }
    }

    public String getContainers() {
        return containers;
    }

    public void setContainers(String containers) {
        this.containers = containers;
    }

    public int getCaixaPapelaoQtd() {
        return caixaPapelaoQtd;
    }

    public void setCaixaPapelaoQtd(int caixaPapelaoQtd) {
        if (caixaPapelaoQtd > 0) {
            this.caixaPapelaoQtd = caixaPapelaoQtd;
        } else {
            this.caixaPapelaoQtd = 0;
        }
    }

    @Override
    public String toString() {
        return "NFSE{" + "numeroInterno=" + numeroInterno + ", numeroExterno=" + numeroExterno + ", clienteCodigo=" + clienteCodigo + ", codigoEmpresa=" + codigoEmpresa + ", containers=" + containers + ", clienteNome=" + clienteNome + ", caixaPapelaoQtd=" + caixaPapelaoQtd + '}';
    }

    public boolean isEmpty() {

        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(this) == null) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Exception occured in processing");
            }
        }
        return false;
    }

}

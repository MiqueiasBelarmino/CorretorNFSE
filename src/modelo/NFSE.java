package modelo;

/**
 *
 * @author Miquéias
 */
public class NFSE {

    private int numeroInterno;
    private int numeroExterno;
    private int clienteCodigo;
    private int codigoEmpresa;

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
    
}

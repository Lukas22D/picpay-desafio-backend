package tech.buildrun.picpay.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "tb_WalletType")
public class WalletType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    
    private String description;

    public WalletType() {
    }

    public WalletType(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
     *  O trecho de código fornecido define um enum dentro da classe WalletType. 
     * Este enum é utilizado para facilitar a criação de registros no banco de dados com valores pré-definidos da tabela tb_WalletType. 
     * A enumeração é chamada Enum e possui duas constantes: USER e MERCHANT, cada uma com um id e uma description associados. 
     * Cada constante do enum é inicializada com um construtor que aceita dois parâmetros: um Long id e uma String description.
     * 
     */

    public enum Enum {
        USER(1L, "User"),
        MERCHANT(2L, "Merchant");

        Enum(Long id, String description) {
            this.id = id;
            this.description = description;
        }
        private Long id;
        private String description;

        public WalletType get(){
            return new WalletType(id, description);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WalletType other = (WalletType) obj;
        if (id != other.id)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }

    
}

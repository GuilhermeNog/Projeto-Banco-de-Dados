package Model;

import java.math.BigInteger;

public class HabilitacaoExame extends GenericModel {
    private String observacao;
    private BigInteger custo;
    private Integer laboratorio_id;
    private Integer tipo_exame_id;

    public HabilitacaoExame(Integer id, String observacao, BigInteger custo, Integer laboratorio_id, Integer tipo_exame_id) {
        this.observacao = observacao;
        this.custo = custo;
        this.laboratorio_id = laboratorio_id;
        this.tipo_exame_id = tipo_exame_id;
        super.setId(id);
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigInteger getCusto() {
        return custo;
    }

    public void setCusto(BigInteger custo) {
        this.custo = custo;
    }

    public Integer getLaboratorio_id() {
        return laboratorio_id;
    }

    public void setLaboratorio_id(Integer laboratorio_id) {
        this.laboratorio_id = laboratorio_id;
    }

    public Integer getTipo_exame_id() {
        return tipo_exame_id;
    }

    public void setTipo_exame_id(Integer tipo_exame_id) {
        this.tipo_exame_id = tipo_exame_id;
    }

    @Override
    public String toString() {
        return "HabilitacaoExame{" +
                "id'" + this.getId() + "\'" +
                "observacao='" + observacao + '\'' +
                ", custo=" + custo +
                ", laboratorio_id=" + laboratorio_id +
                ", tipo_exame_id=" + tipo_exame_id +
                '}';
    }
}

package Model;

public class TipoExame extends GenericModel {
    private String descricao;
    private String observacao;

    public TipoExame(Integer id, String descricao, String observacao) {
        this.descricao = descricao;
        this.observacao = observacao;
        super.setId(id);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "TipoExame{" +
                "id'" + this.getId() + "\'" +
                "descricao='" + descricao + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
